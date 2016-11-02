package com.ttsofts.entity.app;

import com.ttsofts.entity.BaseEntity;

public class AppLogStatistics extends BaseEntity {
	
	/**总数**/
	private String total;
	
	/**日期**/
	private String day;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
}
