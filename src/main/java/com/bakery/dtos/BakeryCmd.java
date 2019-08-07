package com.bakery.dtos;

public class BakeryCmd {
	
	private String code;
	private Integer orderQuantity;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Integer getOrderQuantity() {
		return orderQuantity;
	}
	
	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
}
