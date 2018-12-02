package com.progressoft.induction.tp.daos;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.progressoft.induction.tp.models.Transaction;
import com.progressoft.induction.tp.models.Violation;

public class TransactionProcessorImpl implements TransactionProcessor {

	boolean flag = false;
	static final String CSV_FILE_PATH = "C:\\Users\\anon\\Desktop\\Transaction.csv";
	CsvToBean<Transaction> csvToBean = null;
	List<Transaction> tlist = null;
	Iterator<Transaction> csvtransaction = null;
	Reader reader = null;
	BigDecimal debit_sum = new BigDecimal(0);
	BigDecimal credit_sum = new BigDecimal(0);

	@Override
	public void importTransactions(InputStream is) {
		try {

			is = new FileInputStream(CSV_FILE_PATH);
			reader = new InputStreamReader(is);
			System.out.println("Transaction Imported");
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Transaction> getImportedTransactions() {
		if (flag == true) {

			try {
				csvToBean = new CsvToBeanBuilder(reader).withType(Transaction.class).withSkipLines(1).build();

				// creating iterator
				csvtransaction = csvToBean.iterator();

				// converting iterator to list
				tlist = IteratorUtils.toList(csvtransaction);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("please import the transaction first");
		}
		return tlist;
	}

	@Override
	public List<Violation> validate() {
		List<Violation> vlist = new ArrayList<>();

		if (flag == true) {
			int index_order = 0;

			try {

				for (Transaction t : tlist) {

					Violation v = new Violation();

					System.out.println("transaction:" + index_order++);

					if (t.getType().isEmpty()) {

						v.setOrder(index_order);
						v.setProperty("error on: Type");
						v.setDescription("Transaction Type Mission or Cannot be empty");

					} else if (t.getAmount().compareTo(BigDecimal.ZERO) == 0) {

						v.setOrder(index_order);
						v.setProperty("error on: Amount");
						v.setDescription("Transaction Amount Mission or Cannot be Zero");

					} else if (t.getNarration().isEmpty()) {

						v.setOrder(index_order);
						v.setProperty("error on: Narration");
						v.setDescription("Transaction Narration Mission or Cannot be empty");

					} else {

						v.setOrder(index_order);
						v.setProperty("no error on property");
						v.setDescription("no error on description");
						System.out.println("=============");
					}

					vlist.add(v);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("please import the transaction first");
		}
		return vlist;

	}

	@Override
	public boolean isBalanced() {
		Boolean result = false;
		if (flag == true) {

			try {
				// Checking if DR and CR is balanced
				for (Transaction t : tlist) {

					if (t.getType().equals("D")) {
						debit_sum = debit_sum.add(t.getAmount());
					}
					if (t.getType().equals("C")) {
						credit_sum = credit_sum.add(t.getAmount());
					}
				}

				// checking for equality of DR & CR
				if (debit_sum.equals(credit_sum)) {
					result = true;
				} else {
					result = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("please import the transaction first");
		}
		return result;
	}

}
