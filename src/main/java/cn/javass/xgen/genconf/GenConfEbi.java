package cn.javass.xgen.genconf;

import java.util.Map;

import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

public interface GenConfEbi {
	/**
	 * 获取x-gen核心框架运行需要的配置数据model
	 * @return
	 */
	public GenConfModel getGenConf();

	/**
	 * 获取需要生成的所有模块配置
	 * @return
	 */
	public Map<String,ModuleConfModel> getMapModelConf(); 
}
