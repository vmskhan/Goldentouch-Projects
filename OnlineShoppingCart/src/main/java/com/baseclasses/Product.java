package com.baseclasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name="Products")
@Entity
public class Product {
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Id
	private int pid;
	private String pname;
	private String pdes;
	private int pqty;
	private int price;
	@Lob
	@Column(columnDefinition="LONGBLOB")
	private byte[] pimg;
	
	

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPdes() {
		return pdes;
	}
	public void setPdes(String pdes) {
		this.pdes = pdes;
	}
	public int getPqty() {
		return pqty;
	}
	public void setPqty(int pqty) {
		this.pqty = pqty;
	}
	public byte[] getPimg() {
		return pimg;
	}
	public void setPimg(byte[] b) {
		this.pimg = b;
	}
	
	
}
