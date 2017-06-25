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
 * 获取配置数据的接口
 */
public interface ModuleGenConfImplementor {
	
	/**
	 * 根据核心框架里面注册的需要生成模块的配置参数，来获取相应的需要生成模块的配置数据model,数据只有基本的部分
	 * @param params
	 * @return
	 */
	public ModuleConfModel getBaseModuleConfModel(Map<String, String> params);
	
	/**
	 * 根据核心框架里面注册的需要生成模块的配置参数，来获取相应的需要生成模块的功能类型
	 * @return
	 */
	public Map<String,List<String>> getMapNeedGenTypes(Map<String,String> params);
	
	public Map<String,ExtendConfModel> getMapExtends(Map<String,String> params);
}
