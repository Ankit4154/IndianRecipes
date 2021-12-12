package com.recipe.indianrecipe.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bill {
	@Id
	private long billNumber;
	private String billedTo;
	private Date billedDate;
	private double billPrice;
	private double tax;
	private double totalAmount;
	
	public long getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(long billNumber) {
		this.billNumber = billNumber;
	}
	public String getBilledTo() {
		return billedTo;
	}
	public void setBilledTo(String billedTo) {
		this.billedTo = billedTo;
	}
	public Date getBilledDate() {
		return billedDate;
	}
	public void setBilledDate(Date billedDate) {
		this.billedDate = billedDate;
	}
	public double getBillPrice() {
		return billPrice;
	}
	public void setBillPrice(double billPrice) {
		this.billPrice = billPrice;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
