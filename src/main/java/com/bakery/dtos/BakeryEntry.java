package com.bakery.dtos;

import java.math.BigDecimal;

public class BakeryEntry {

	private String name;
	private BigDecimal price;
	private Integer quantity;

	public BakeryEntry(String name, BigDecimal price, Integer quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "BakeryEntry [name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
}
