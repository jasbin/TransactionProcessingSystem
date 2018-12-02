package com.progressoft.induction.tp;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import com.progressoft.induction.tp.daos.TransactionProcessor;
import com.progressoft.induction.tp.daos.TransactionProcessorImpl;
import com.progressoft.induction.tp.models.Transaction;
import com.progressoft.induction.tp.models.Violation;
/*
 Assistment done by Jasbin Karki
*/

public class Controller {

	public static void main(String[] args) {
		System.out.println("transaction processor started");
		InputStream is = null;
		Scanner sc = new Scanner(System.in);
		TransactionProcessor tp = new TransactionProcessorImpl();
		List<Transaction> tlist;
		List<Violation> vlist;

		System.out.println("===================================================================");
		for (;;) {

			System.out.println("");
			System.out.println("");
			System.out.println("===================================================================");
			System.out.println("Make a choice");
			System.out.println("press 1. to import csv file");
			System.out.println("press 2. to read csv file");
			System.out.println("press 3. to get validation results of transaction");
			System.out.println("press 4. to check if DR and CR is balanced");
			System.out.println("===================================================================");
			char key = sc.next().charAt(0);

			switch (key) {
			case '1':
				tp.importTransactions(is);
				break;

			case '2':
				try {
					tlist = tp.getImportedTransactions();
					// printing ArrayList of type Transation
					for (Transaction transaction : tlist) {
						System.out.println("Type : " + transaction.getType());
						System.out.println("Amount : " + transaction.getAmount());
						System.out.println("Narration : " + transaction.getNarration());
						System.out.println("---------------------------");
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

				break;

			case '3':
				try {
					// Checking Violation of Transaction
					vlist = tp.validate();

					for (Violation v : vlist) {
						System.out.println("order:" + v.getOrder());
						System.out.println(v.getProperty());
						System.out.println(v.getDescription());
						System.out.println("=============");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				break;

			case '4':
				try {
					// checking if DR and CR amount is balanced
					if (tp.isBalanced() == true) {
						System.out.println("The DR & CR is balanced");
					} else {
						System.out.println("The DR & CR is not balanced");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;

			default:
				System.out.println("invalid input");
				break;
			}
		}

	}
}
