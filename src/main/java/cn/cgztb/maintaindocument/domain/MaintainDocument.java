package cn.cgztb.maintaindocument.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MaintainDocument implements Serializable{


	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 7603545179524577454L;

	private Map<String, Object> costBeans = new HashMap<String, Object>();

	private Map<String, Object> costOtherBeans = new HashMap<String, Object>();

	private Map<String, Object> investmentBeans = new HashMap<String, Object>();

	private Map<String, Object> investmentOtherBeans = new HashMap<String, Object>();

	public Map<String, Object> getCostBeans() {
		return costBeans;
	}

	public void setCostBeans(Map<String, Object> costBeans) {
		this.costBeans = costBeans;
	}

	public Map<String, Object> getCostOtherBeans() {
		return costOtherBeans;
	}

	public void setCostOtherBeans(Map<String, Object> costOtherBeans) {
		this.costOtherBeans = costOtherBeans;
	}

	public Map<String, Object> getInvestmentBeans() {
		return investmentBeans;
	}

	public void setInvestmentBeans(Map<String, Object> investmentBeans) {
		this.investmentBeans = investmentBeans;
	}

	public Map<String, Object> getInvestmentOtherBeans() {
		return investmentOtherBeans;
	}

	public void setInvestmentOtherBeans(Map<String, Object> investmentOtherBeans) {
		this.investmentOtherBeans = investmentOtherBeans;
	}

	

}
