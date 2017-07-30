package cn.javass.xgen.genconf.vo;

import java.util.HashMap;
import java.util.Map;

public class GenTypeModel {
	private String id;
	private String genTypeClass;
	private Map<String, String> mapParams = new HashMap<String, String>();
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the genTypeClass
	 */
	public String getGenTypeClass() {
		return genTypeClass;
	}
	/**
	 * @param genTypeClass the genTypeClass to set
	 */
	public void setGenTypeClass(String genTypeClass) {
		this.genTypeClass = genTypeClass;
	}
	/**
	 * @return the mapParams
	 */
	public Map<String, String> getMapParams() {
		return mapParams;
	}
	/**
	 * @param mapParams the mapParams to set
	 */
	public void setMapParams(Map<String, String> mapParams) {
		this.mapParams = mapParams;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenTypeModel [id=" + id + ", genTypeClass=" + genTypeClass + ", mapParams=" + mapParams + "]";
	}
}
