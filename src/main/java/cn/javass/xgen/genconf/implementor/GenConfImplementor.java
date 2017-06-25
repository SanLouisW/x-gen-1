package cn.javass.xgen.genconf.implementor;

import java.util.List;
import java.util.Map;

import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;

/**
 * @author Administrator
 * ��ȡ�������ݵĽӿ�
 */
public interface GenConfImplementor {
	public List<NeedGenModel> getNeedGens();
	
	public List<ThemeModel> getTheme();
	
	public Map<String,String> getMapContants();
}
