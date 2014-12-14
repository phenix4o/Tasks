package com.example.exenselsit;

public class Expense {
	private final String name;

	private final String number;

	private final long id;

	public Expense(String name, String number, long id) {
		this.name = name;
		this.number = number;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public long getId() {
		return id;
	}
}