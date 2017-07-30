package cn.javass.xgen.genconf.implementor;

import java.util.Map;

import cn.javass.xgen.genconf.vo.GenTypeModel;

public interface ThemeImplmeter {
	/**
	 * 根据theme ID 和在核心框架里面注册theme 时配置的相应参数，来获取theme中定义的能生成的功能类型
	 * @param themeId
	 * @param params
	 * @return
	 */
	public Map<String,GenTypeModel> getMapGenTypes(String themeId,Map<String,String> params);
	
	public Map<String,String>  getMapGenOutTypes(String themeId, Map<String,String> params);
	
	public Map<String,String>  getMapProviders(String themeId, Map<String,String> params);
}
