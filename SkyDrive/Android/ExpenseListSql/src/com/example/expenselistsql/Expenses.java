package com.example.expenselistsql;

public class Expenses {
	private int id;
	private String label;
	private double price;

	public Expenses() {
		this.setLabel(null);
		this.setPrice(0);
	}

	public Expenses(String label, double price) {
		super();
		this.setLabel(label);
		this.setPrice(price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

}
