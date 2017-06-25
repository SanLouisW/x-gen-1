package cn.javass.xgen.genconf.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleConfModel {
	
	/**
	 * 用户需要生成模块的标识
	 */
	private String moduleId = "";
	
	/**
	 * 用户需要生成的这个模块所使用的外部主题标识
	 */
	private String useTheme = "";
	
	/**
	 * 用户需要生成的具体功能，key需要生成的功能标识，value该功能生成后的多种输出类型的标识的集合
	 */
	private Map<String,List<String>> mapNeedGendTypes = new HashMap<String,List<String>>();
	
	/**
	 * 模块生成需要的扩展数据，key数据id，value对应的扩展数据model
	 */
	private Map<String,ExtendConfModel> mapExtends = new HashMap<String,ExtendConfModel>();

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getUseTheme() {
		return useTheme;
	}

	public void setUseTheme(String useTheme) {
		this.useTheme = useTheme;
	}

	public Map<String, List<String>> getMapNeedGendTypes() {
		return mapNeedGendTypes;
	}

	public void setMapNeedGendTypes(Map<String, List<String>> mapNeedGendTypes) {
		this.mapNeedGendTypes = mapNeedGendTypes;
	}

	public Map<String, ExtendConfModel> getMapExtends() {
		return mapExtends;
	}

	public void setMapExtends(Map<String, ExtendConfModel> mapExtends) {
		this.mapExtends = mapExtends;
	}
}
