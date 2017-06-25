package cn.javass.xgen.genconf;

import java.util.Map;

import cn.javass.xgen.genconf.confManager.ConfManager;
import cn.javass.xgen.genconf.implementor.GenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

/**
 * @author Administrator
 * 懒汉式单例
 * 负责完成配置管理模块的业务功能
 */
public class GenConfEbo implements GenConfEbi{
	private static GenConfEbo ebo = null;
	
	private GenConfEbo(GenConfImplementor provider){
		this.provider = provider;
	}
	
	public static GenConfEbo getInstance(GenConfImplementor provider){
		if(ebo == null){
			ebo = new GenConfEbo(provider);
		}
		return ebo;
	}
	
	/**
	 * 持有获取核心框架配置数据的具体实现对象接口
	 */
	private GenConfImplementor provider;
	
	public GenConfModel getGenConf() {
		// 获取相应配置
		return ConfManager.getInstance(provider).getGenConf();
	}

	public Map<String, ModuleConfModel> getMapModelConf() {
		// 获取相应配置
		return ConfManager.getInstance(provider).getMapModuleConf();
	}
}
