package cn.javass.xgen.utill.readxml;

public class ParseModel {
	/**
	 * Ԫ������
	 */
	private String eleName;
	/**
	 * �Ƿ�����ֵ
	 */
	private boolean propertyValue;
	/**
	 * �Ƿ��ս��
	 */
	private boolean end;
	/**
	 * �Ƿ񵥸�ֵ
	 */
	private boolean singleValue;
	/**
	 * ����
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
