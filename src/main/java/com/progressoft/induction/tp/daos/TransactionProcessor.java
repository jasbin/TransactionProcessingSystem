package com.progressoft.induction.tp.daos;

import java.io.InputStream;
import java.util.List;

import com.progressoft.induction.tp.models.Transaction;
import com.progressoft.induction.tp.models.Violation;

/**
 * Created by Ahmad Y. Saleh on 7/24/17.
 */
public interface TransactionProcessor {

	void importTransactions(InputStream is);

	List<Transaction> getImportedTransactions();

	List<Violation> validate();

	boolean isBalanced();
}