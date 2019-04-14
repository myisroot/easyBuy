package com.easybuynet.entity;

import java.io.Serializable;
public class Easy_product implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer p_id;
	private String p_name;
	private String p_description;
	private String p_price;
	private String p_stock;
	private Integer p_categorylevel1;
	private Integer p_categorylevel2;
	private String p_categorylevel3;
	private String p_filename;
	private String p_isdelete;

	public void setP_id(Integer p_id){
		this.p_id=p_id;
	}
	public Integer getP_id(){
		return p_id;
	}
	public void setP_name(String p_name){
		this.p_name=p_name;
	}
	public String getP_name(){
		return p_name;
	}
	public void setP_description(String p_description){
		this.p_description=p_description;
	}
	public String getP_description(){
		return p_description;
	}
	public void setP_price(String p_price){
		this.p_price=p_price;
	}
	public String getP_price(){
		return p_price;
	}
	public void setP_stock(String p_stock){
		this.p_stock=p_stock;
	}
	public String getP_stock(){
		return p_stock;
	}
	public void setP_categorylevel1(Integer p_categorylevel1){
		this.p_categorylevel1=p_categorylevel1;
	}
	public Integer getP_categorylevel1(){
		return p_categorylevel1;
	}
	public void setP_categorylevel2(Integer p_categorylevel2){
		this.p_categorylevel2=p_categorylevel2;
	}
	public Integer getP_categorylevel2(){
		return p_categorylevel2;
	}
	public void setP_categorylevel3(String p_categorylevel3){
		this.p_categorylevel3=p_categorylevel3;
	}
	public String getP_categorylevel3(){
		return p_categorylevel3;
	}
	public void setP_filename(String p_filename){
		this.p_filename=p_filename;
	}
	public String getP_filename(){
		return p_filename;
	}
	public void setP_isdelete(String p_isdelete){
		this.p_isdelete=p_isdelete;
	}
	public String getP_isdelete(){
		return p_isdelete;
	}

	@Override
	public String toString() {
		return "Easy_product{" +
				"p_id=" + p_id +
				", p_name='" + p_name + '\'' +
				", p_description='" + p_description + '\'' +
				", p_price='" + p_price + '\'' +
				", p_stock='" + p_stock + '\'' +
				", p_categorylevel1=" + p_categorylevel1 +
				", p_categorylevel2=" + p_categorylevel2 +
				", p_categorylevel3='" + p_categorylevel3 + '\'' +
				", p_filename='" + p_filename + '\'' +
				", p_isdelete='" + p_isdelete + '\'' +
				'}';
	}
}

