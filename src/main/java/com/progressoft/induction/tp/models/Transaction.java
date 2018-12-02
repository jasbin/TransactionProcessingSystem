package com.progressoft.induction.tp.models;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByPosition;

public class Transaction {

	@CsvBindByPosition(position = 0)
	private String type;

	@CsvBindByPosition(position = 1)
	private BigDecimal amount;

	@CsvBindByPosition(position = 2)
	private String narration;

	public Transaction() {
		super();
		this.type = "";
		this.amount = new BigDecimal(0);
		this.narration = "";
	}

	public Transaction(String type, BigDecimal amount, String narration) {
		super();
		this.type = type;
		this.amount = amount;
		this.narration = narration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

}