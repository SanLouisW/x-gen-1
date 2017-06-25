package cn.javass.xgen.genconf.implementor;

import java.util.List;
import java.util.Map;

import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;

/**
 * @author Administrator
 * ��ȡ�������ݵĽӿ�
 */
public interface ModuleGenConfImplementor {
	
	/**
	 * ���ݺ��Ŀ������ע�����Ҫ����ģ������ò���������ȡ��Ӧ����Ҫ����ģ�����������model,����ֻ�л����Ĳ���
	 * @param params
	 * @return
	 */
	public ModuleConfModel getBaseModuleConfModel(Map<String, String> params);
	
	/**
	 * ���ݺ��Ŀ������ע�����Ҫ����ģ������ò���������ȡ��Ӧ����Ҫ����ģ��Ĺ�������
	 * @return
	 */
	public Map<String,List<String>> getMapNeedGenTypes(Map<String,String> params);
	
	public Map<String,ExtendConfModel> getMapExtends(Map<String,String> params);
}
