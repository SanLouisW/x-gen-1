package cn.javass.xgen.genconf.confManager;

import java.util.HashMap;
import java.util.Map;

import cn.javass.xgen.genconf.implementor.GenConfImplementor;
import cn.javass.xgen.genconf.implementor.ModuleGenConfImplementor;
import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.genconf.vo.NeedGenModel;

/**
 * @author Administrator
 * 获取配置数据，并缓存
 */
public class ConfManager {
	private static ConfManager confManager= null;
	
	private ConfManager(GenConfImplementor provider){
		readConf(provider);
	}
	
	public static ConfManager getInstance(GenConfImplementor provider){
		if(confManager == null){
			confManager = new ConfManager(provider);
		}
		
		return confManager;
	}
	
	private GenConfModel genConf = new GenConfModel();
	private Map<String,ModuleConfModel> mapModuleConf = new HashMap<String,ModuleConfModel>();
	
	private void readConf(GenConfImplementor provider){
		// 真正获取配置数据
		readGenConf(provider);
		
		for (NeedGenModel ngm : genConf.getNeedGens()) {
			readOneModuleGenConf(ngm);
		}
	}
	private void readOneModuleGenConf(NeedGenModel ngm){
		ModuleConfModel mcm = new ModuleConfModel();
		
		mcm.setUseTheme(ngm.getTheme());
		String providerClassName = this.genConf.getThemeById(ngm.getTheme()).getMapproviders().get(ngm.getProvider());
		ModuleGenConfImplementor userGenConfImpl = null;
		try {
			userGenConfImpl = (ModuleGenConfImplementor) Class.forName(providerClassName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mcm = userGenConfImpl.getBaseModuleConfModel(ngm.getMapParams());
		//mcm.setMapExtends(mapExtends);
		mcm.setUseTheme(ngm.getTheme());
		mcm.setMapExtends(userGenConfImpl.getMapExtends(ngm.getMapParams()));
		mcm.setMapNeedGendTypes(userGenConfImpl.getMapNeedGenTypes(ngm.getMapParams()));
		
		// 缓存起来
		this.mapModuleConf.put(mcm.getModuleId(), mcm);
	}

	private void readGenConf(GenConfImplementor provider){
		genConf.setNeedGens(provider.getNeedGens());
		genConf.setThemes(provider.getTheme());
		genConf.setMapContants(provider.getMapContants());
	}
	
	/**
	 * @param genConf the genConf to set
	 * @return 
	 */
	public GenConfModel getGenConf() {
		return this.genConf;
	}

	/**
	 * @param mapModuleConf the mapModuleConf to set
	 * @return 
	 */
	public Map<String, ModuleConfModel> getMapModuleConf() {
		return this.mapModuleConf;
	}
	
}
