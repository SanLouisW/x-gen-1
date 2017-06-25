 package cn.javass.xgen.genconf.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * ���Ŀ�����ö��ڵ�����model
 */
public class GenConfModel {
	/**
	 * ����ע��Ķ���û���Ҫ���ɵ�ģ���model
	 */
	private List<NeedGenModel> needGens = new ArrayList<NeedGenModel>();
	
	/**
	 * ����ע��Ķ���ⲿ�����model
	 */
	private List<ThemeModel> themes = new ArrayList<ThemeModel>();
	
	/**
	 * ����ͨ�õĳ�������ļ���
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
