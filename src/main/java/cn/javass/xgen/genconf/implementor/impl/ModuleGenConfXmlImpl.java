package cn.javass.xgen.genconf.implementor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.constants.ModuleGenConfEnum;
import cn.javass.xgen.genconf.implementor.ModuleGenConfImplementor;
import cn.javass.xgen.genconf.vo.ExtendConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;
import cn.javass.xgen.utill.readxml.Context;
import cn.javass.xgen.utill.readxml.Parser;
import cn.javass.xgen.utill.readxml.ReadXmlExpression;

public class ModuleGenConfXmlImpl implements ModuleGenConfImplementor {

	public ModuleConfModel getBaseModuleConfModel(Map<String, String> params) {
		ModuleConfModel mcm = new ModuleConfModel();
		// 设置模块标志
		parseModuleId(mcm, this.getContext(params));
		return mcm;
	}

	public Map<String, List<String>> getMapNeedGenTypes(Map<String, String> params) {
		return parseNeedGenTypes(this.getContext(params));
	}

	public Map<String, ExtendConfModel> getMapExtends(Map<String, String> params) {
		Map<String, ExtendConfModel> map = new HashMap<String, ExtendConfModel>();
		
		String[] extendIds = parseExtendIds(this.getContext(params));
		String[] isSingles = parseIsSingles(this.getContext(params));
		String[] values = parseValues(this.getContext(params));
		
		for (int i = 0; i < extendIds.length; i++) {
			ExtendConfModel ecm = new ExtendConfModel();
			
			ecm.setId(extendIds[i]);
			ecm.setSingle(Boolean.parseBoolean(isSingles[i]));
			ecm.setValue(values[i]);
			
			if(ecm.isSingle()){
				ecm.setValues(ecm.getValue().split(ExpressionEnum.comma.getExpr()));
			}
			
			map.put(ecm.getId(), ecm);
		}
		
		return map;
	}
	
	private String[] parseExtendIds(Context c){
		c.init();
		
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		
		ReadXmlExpression re = Parser.parse(
				new ModuleGenConfBuilder().addModuleGenConf()
				.addSeparator().addExtendConfs().addSeparator()
				.addExtendConf().addDollar()
				.addDot().addId().addDollar()
				.build()
				);
		
		return re.interpret(c);
	}
	
	private String[] parseIsSingles(Context c){
		c.init();
		
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		
		ReadXmlExpression re = Parser.parse(
				new ModuleGenConfBuilder().addModuleGenConf()
				.addSeparator().addExtendConfs().addSeparator()
				.addExtendConf().addDollar()
				.addDot().addIsSingle().addDollar()
				.build()
				);
		
		return re.interpret(c);
	}
	
	private String[] parseValues(Context c){
		c.init();
		
		ReadXmlExpression re = Parser.parse(
				new ModuleGenConfBuilder().addModuleGenConf()
				.addSeparator().addExtendConfs().addSeparator()
				.addExtendConf().addDollar()
				.build()
				);
		
		return re.interpret(c);
	}

	/////////////////////////////////////////////////////////////////////////////////
	private Context getContext(Map<String,String> param){
		Context c = null;
		try {
			c = Context.getInstance(
						new GenConfBuilder().addXmlFilePre().addOtherValue(param.get(ModuleGenConfEnum.fileName)).build()
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	private void parseModuleId(ModuleConfModel mcm, Context c){
		c.init();
		
		ReadXmlExpression re = Parser.parse(
				new ModuleGenConfBuilder().addModuleGenConf().addDot().addId().build()
				);
		
		String[] ss = re.interpret(c);
		
		mcm.setModuleId(ss[0]);
	}
	///////////////////////////////////////////////////////////////////////////////
	private Map<String,List<String>> parseNeedGenTypes(Context c){
		c.init();
		
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		
		ReadXmlExpression re = Parser.parse(
				new ModuleGenConfBuilder().addModuleGenConf()
				.addSeparator().addNeedGenTypes().addSeparator()
				.addNeedGenType().addDollar()
				.addDot().addId().addDollar()
				.build()
				);
		
		String[] needGenTypes = re.interpret(c);
		
		for (String s : needGenTypes) {
			map.put(s, parseNeedGenOutTypes(c, s));
		}
		
		return map;
	}
	
	private List<String> parseNeedGenOutTypes(Context ctx, String needGenId){
		ctx.init();
		
		List<String> list = new ArrayList<String>();
		
		ReadXmlExpression re = Parser.parse(
				new ModuleGenConfBuilder().addModuleGenConf()
				.addSeparator().addNeedGenTypes().addSeparator()
				.addNeedGenType().addDollar()
				.addOpenBracket().addId().addEqual().addOtherValue(needGenId).addCloseBracket().addSeparator()
				.addNeedGenOutType().addDollar()
				.addDot().addId().addDollar()
				.build()
				);
		
		String[] ss = re.interpret(ctx);
		
		for (String s : ss) {
			list.add(s);
		}
		
		return list;
	}
	/////////////////////////////////////////////////////////////////////
}
