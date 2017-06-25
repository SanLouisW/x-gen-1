 package cn.javass.xgen.genconf.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 核心框架配置对于的数据model
 */
public class GenConfModel {
	/**
	 * 描叙注册的多个用户需要生成的模块的model
	 */
	private List<NeedGenModel> needGens = new ArrayList<NeedGenModel>();
	
	/**
	 * 描叙注册的多个外部主题的model
	 */
	private List<ThemeModel> themes = new ArrayList<ThemeModel>();
	
	/**
	 * 描叙通用的常量定义的集合
	 */
	private Map<String,String> mapContants = new HashMap<String,String>();
	
	public ThemeModel getThemeById(String themeId){
		for(ThemeModel tm : this.themes){
			if(tm.getId().equals(themeId)){
				return tm;
			}
		}
		
		return new ThemeModel();
	}

	public List<NeedGenModel> getNeedGens() {
		return needGens;
	}

	public void setNeedGens(List<NeedGenModel> needGens) {
		this.needGens = needGens;
	}

	public List<ThemeModel> getThemes() {
		return themes;
	}

	public void setThemes(List<ThemeModel> themes) {
		this.themes = themes;
	}

	public Map<String, String> getMapContants() {
		return mapContants;
	}

	public void setMapContants(Map<String, String> mapContants) {
		this.mapContants = mapContants;
	}
}
