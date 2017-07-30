package cn.javass.xgen.genconf.vo;

import java.util.HashMap;
import java.util.Map;

public class ThemeModel {
	private String id;
	private String location;
	private Map<String,GenTypeModel> mapGenTypes = new HashMap<String,GenTypeModel>();
	private Map<String,String> mapGenOutTypes = new HashMap<String, String>();
	private Map<String,String> mapproviders = new HashMap<String, String>();
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the mapGenTypes
	 */
	public Map<String, GenTypeModel> getMapGenTypes() {
		return mapGenTypes;
	}
	/**
	 * @param mapGenTypes the mapGenTypes to set
	 */
	public void setMapGenTypes(Map<String, GenTypeModel> mapGenTypes) {
		this.mapGenTypes = mapGenTypes;
	}
	/**
	 * @return the mapGenOutTypes
	 */
	public Map<String, String> getMapGenOutTypes() {
		return mapGenOutTypes;
	}
	/**
	 * @param mapGenOutTypes the mapGenOutTypes to set
	 */
	public void setMapGenOutTypes(Map<String, String> mapGenOutTypes) {
		this.mapGenOutTypes = mapGenOutTypes;
	}
	/**
	 * @return the mapproviders
	 */
	public Map<String, String> getMapproviders() {
		return mapproviders;
	}
	/**
	 * @param mapproviders the mapproviders to set
	 */
	public void setMapproviders(Map<String, String> mapproviders) {
		this.mapproviders = mapproviders;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ThemeModel [id=" + id + ", location=" + location + ", mapGenTypes=" + mapGenTypes + ", mapGenOutTypes="
				+ mapGenOutTypes + ", mapproviders=" + mapproviders + "]";
	}
}
