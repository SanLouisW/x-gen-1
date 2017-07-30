package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.implementor.GenConfImplementor;

/**
 * @author Administrator
 * 简单工厂模式
 */
public class GenConfFactory {
	
	private GenConfFactory(){
		
	}
	
	public static GenConfEbi createGenConfEbi(GenConfImplementor provider){
		return GenConfEbo.getInstance(provider);
	}
}
