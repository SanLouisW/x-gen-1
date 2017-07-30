package cn.javass.xgen.genconf.implementor;

import java.util.Map;

import cn.javass.xgen.genconf.vo.GenTypeModel;

public interface ThemeImplmeter {
	/**
	 * ����theme ID ���ں��Ŀ������ע��theme ʱ���õ���Ӧ����������ȡtheme�ж���������ɵĹ�������
	 * @param themeId
	 * @param params
	 * @return
	 */
	public Map<String,GenTypeModel> getMapGenTypes(String themeId,Map<String,String> params);
	
	public Map<String,String>  getMapGenOutTypes(String themeId, Map<String,String> params);
	
	public Map<String,String>  getMapProviders(String themeId, Map<String,String> params);
}
