package com.chenmaunion.app.cmdriver.bean.order;
// default package


import java.util.Date;

/**
 *客户地址信息
 */
public class Location implements java.io.Serializable {
	public String id;
	public String userIdx;
	public Double CORDINATEX;
	public Double CORDINATEY;
	public String ADDRESS;
	public Date CREATETIME;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(String userIdx) {
		this.userIdx = userIdx;
	}

	public Double getCORDINATEX() {
		return CORDINATEX;
	}

	public void setCORDINATEX(Double CORDINATEX) {
		this.CORDINATEX = CORDINATEX;
	}

	public Double getCORDINATEY() {
		return CORDINATEY;
	}

	public void setCORDINATEY(Double CORDINATEY) {
		this.CORDINATEY = CORDINATEY;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String ADDRESS) {
		this.ADDRESS = ADDRESS;
	}

	public Date getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(Date CREATETIME) {
		this.CREATETIME = CREATETIME;
	}

	@Override
	public String toString() {
		return "CORDINATEX:"+CORDINATEX+"\t,CORDINATEY"+CORDINATEY;
	}
}