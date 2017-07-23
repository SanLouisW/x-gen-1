package cn.javass.xgen.utill.readxml;

public class ParseModel {
	/**
	 * 元素名称
	 */
	private String eleName;
	/**
	 * 是否属性值
	 */
	private boolean propertyValue;
	/**
	 * 是否终结符
	 */
	private boolean end;
	/**
	 * 是否单个值
	 */
	private boolean singleValue;
	/**
	 * 条件
	 */
	private String condition;
	/**
	 * @return the eleName
	 */
	public String getEleName() {
		return eleName;
	}
	/**
	 * @param eleName the eleName to set
	 */
	public void setEleName(String eleName) {
		this.eleName = eleName;
	}
	/**
	 * @return the propertyValue
	 */
	public boolean isPropertyValue() {
		return propertyValue;
	}
	/**
	 * @param propertyValue the propertyValue to set
	 */
	public void setPropertyValue(boolean propertyValue) {
		this.propertyValue = propertyValue;
	}
	/**
	 * @return the end
	 */
	public boolean isEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(boolean end) {
		this.end = end;
	}
	/**
	 * @return the singleValue
	 */
	public boolean isSingleValue() {
		return singleValue;
	}
	/**
	 * @param singleValue the singleValue to set
	 */
	public void setSingleValue(boolean singleValue) {
		this.singleValue = singleValue;
	}
	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
