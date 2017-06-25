package cn.javass.xgen.genconf;

import java.util.Map;

import cn.javass.xgen.genconf.confManager.ConfManager;
import cn.javass.xgen.genconf.implementor.GenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

/**
 * @author Administrator
 * ����ʽ����
 * ����������ù���ģ���ҵ����
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
	 * ���л�ȡ���Ŀ���������ݵľ���ʵ�ֶ���ӿ�
	 */
	private GenConfImplementor provider;
	
	public GenConfModel getGenConf() {
		// ��ȡ��Ӧ����
		return ConfManager.getInstance(provider).getGenConf();
	}

	public Map<String, ModuleConfModel> getMapModelConf() {
		// ��ȡ��Ӧ����
		return ConfManager.getInstance(provider).getMapModuleConf();
	}
}
