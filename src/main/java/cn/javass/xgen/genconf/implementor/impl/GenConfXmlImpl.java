package cn.javass.xgen.genconf.implementor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javass.xgen.genconf.constants.ThemeEnum;
import cn.javass.xgen.genconf.implementor.GenConfImplementor;
import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;
import cn.javass.xgen.utill.readxml.Context;
import cn.javass.xgen.utill.readxml.Parser;
import cn.javass.xgen.utill.readxml.ReadXmlExpression;

/** 
 * 1.用builder获取用于取值的字符串
 * 2.
 * 
 * @author Administrator
 *
 */
public class GenConfXmlImpl implements GenConfImplementor {

	public List<NeedGenModel> getNeedGens() {
		return readNeedGends();
	}

	public List<ThemeModel> getTheme() {
		// TODO Auto-generated method stub
		return readThemes();
	}

	public Map<String, String> getMapContants() {
		// TODO Auto-generated method stub
		return readMapContants();
	}
	
	//////////////////////////////////////////////////////////////////////
    private Context getContext(){
    	Context c = null;
		try {
			c = Context.getInstance(new GenConfBuilder().addXmlFilePre().addGenConf().addDot().addXml().build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
    }
	private List<NeedGenModel> readNeedGends(){
		List<NeedGenModel> retList = new ArrayList<NeedGenModel>();
		
		Context c = getContext();
		
		String[] needGenIds = parseNeedGenIds(c);
		String [] needGenProviders = parseNeedGenProviders(c);
		String [] needGenThemes = parseNeedGenThemes(c);
		
		for (int i = 0; i < needGenIds.length; i++) {
			NeedGenModel ngm = new NeedGenModel();
			
			ngm.setId(needGenIds[i]);
			ngm.setProvider(needGenProviders[i]);
			ngm.setTheme(needGenThemes[i]);
			
			// 获取参数的值
			String[] paramIds = parseParamIds(c, ngm.getId());
			String[] paramValues = parseParamValues(c, ngm.getId());
			
			Map<String, String> mapParams = new HashMap<String, String>();
			
			for (int j = 0; j < paramIds.length; j++) {
				mapParams.put(paramIds[j], paramValues[j]);
			}
			
			ngm.setMapParams(mapParams);
			
			retList.add(ngm);
		}
		
		return retList;
	}
	
	private String[] parseNeedGenIds(Context c){
		c.init();
		ReadXmlExpression re = Parser.parse(
			new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
			.addNeedGen().addDollar().addDot().addId().addDollar().build()
		);
		return re.interpret(c);
	}
	private String[] parseParamIds(Context c, String NeedGenId){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
				.addNeedGen().addDollar()
				.addOpenBracket().addId().addEqual().addOtherValue(NeedGenId).addCloseBracket()
				.addSeparator().addParams().addDollar().addSeparator().addParam().addDollar()
				.addDot().addId().addDollar().build()
				);
		return re.interpret(c);
	}
	private String[] parseParamValues(Context c, String NeedGenId){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
				.addNeedGen().addDollar()
				.addOpenBracket().addId().addEqual().addOtherValue(NeedGenId).addCloseBracket()
				.addSeparator().addParams().addDollar().addSeparator().addParam().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	private String[] parseNeedGenThemes(Context c){
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
				.addNeedGen().addDollar().addDot().addThemeId().addDollar()
				.build()
				);
		return re.interpret(c);
	}
	private String[] parseNeedGenProviders(Context c){
		c.init();
		ReadXmlExpression re = Parser.parse(
			new GenConfBuilder().addGenConf().addSeparator().addNeedGens().addSeparator()
			.addNeedGen().addDollar().addDot().addProvider().addDollar().build()
		);
		return re.interpret(c);
	}
	//////////////////////////////////////////////////////////////////////
	private List<ThemeModel> readThemes(){
		List<ThemeModel> retList = new ArrayList<ThemeModel>();
		
		Context c = getContext();
		
		String[] ids = parseThemeIds(c);
		String[] locations = parseThemeLocations(c);
		
		for (int i = 0; i < ids.length; i++) {
			ThemeModel tm = new ThemeModel();
			ThemeXmlImpl themeImpl = new ThemeXmlImpl();
			
			Map<String, String> params = new HashMap<String, String>();
			params.put(""+ThemeEnum.Location, locations[i]);
			
			tm.setId(ids[i]);
			tm.setLocation(locations[i]);
			tm.setMapGenOutTypes(themeImpl.getMapGenOutTypes(ids[i], params));
			tm.setMapGenTypes(themeImpl.getMapGenTypes(ids[i], params));
			tm.setMapproviders(themeImpl.getMapProviders(ids[i], params));
		}
		
		return retList;
	}
	private String[] parseThemeLocations(Context c) {
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator()
				.addTheme().addDollar()
				.build()
				);
		return re.interpret(c); 
	}

	private String[] parseThemeIds(Context c) {
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addThemes().addSeparator()
				.addTheme().addDollar()
				.addDot().addId().addDollar()
				.build()
				);
		return re.interpret(c);
	}

	//////////////////////////////////////////////////////////////////////
	private Map<String,String> readMapContants(){
		Map<String,String> map = new HashMap<String, String>();
		
		Context c = getContext();
		
		String[] ids = parseConstantIds(c);
		String[] values = parseConstantValus(c);
		
		for (int i = 0; i < ids.length; i++) {
			map.put(ids[i], values[i]);
		}
		
		return map;
	}
	private String[] parseConstantIds(Context c) {
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addConstants().addSeparator()
				.addConstant().addDollar()
				.addDot().addId().addDollar()
				.build()
				);
		return re.interpret(c); 
	}
	private String[] parseConstantValus(Context c) {
		c.init();
		ReadXmlExpression re = Parser.parse(
				new GenConfBuilder().addGenConf().addSeparator().addConstants().addSeparator()
				.addConstant().addDollar()
				.build()
				);
		return re.interpret(c); 
	}
	/////////////////////////////////////////////////////////////////////
}
