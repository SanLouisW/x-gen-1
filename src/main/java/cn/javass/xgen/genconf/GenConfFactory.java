package cn.javass.xgen.genconf;

import cn.javass.xgen.genconf.implementor.GenConfImplementor;

/**
 * @author Administrator
 * �򵥹���ģʽ
 */
public class GenConfFactory {
	
	private GenConfFactory(){
		
	}
	
	private static GenConfEbi createGenConfEbi(GenConfImplementor provider){
		return GenConfEbo.getInstance(provider);
	}
}