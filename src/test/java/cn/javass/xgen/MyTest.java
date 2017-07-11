package cn.javass.xgen;

import cn.javass.xgen.genconf.GenConfEbi;
import cn.javass.xgen.genconf.GenConfFactory;
import cn.javass.xgen.utill.readxml.Context;
import cn.javass.xgen.utill.readxml.ElementExpression;
import cn.javass.xgen.utill.readxml.ElementsExpression;
import cn.javass.xgen.utill.readxml.ElementsTerminalExpression;

public class MyTest {
	
	public static void main(String[] args) throws Exception{
		//1: 表达式 ----〉抽象语法树====〉解析器
		//2：抽象语法树---〉交给解释器模式去解释执行
		
		
		/*ElementExpression genConf = new ElementExpression("GenConf", "");
		ElementExpression needGens = new ElementExpression("NeedGens", "");
		ElementsExpression needGen = new ElementsExpression("NeedGen", "");
		ElementsExpression params = new ElementsExpression("Params", "");
		
		ElementsTerminalExpression param = new ElementsTerminalExpression("Param", "");
		
		
		//组装抽象语法树
		genConf.addEle(needGens);
		needGens.addEle(needGen);
		needGen.addEle(params);
		params.addEle(param);*/
		
		ElementExpression genConf = new ElementExpression("GenConf", "");
		ElementExpression themes = new ElementExpression("Themes", "");
		
		ElementsTerminalExpression theme = new ElementsTerminalExpression("Theme", "id=simple");
		
		genConf.addEle(themes);
		themes.addEle(theme);
		
		long a1 = System.currentTimeMillis();
		
		Context ctx = Context.getInstance("/GenConf.xml");
		
		String[] ss = genConf.interpret(ctx);
		
		System.out.println("SS[0]="+ss[0]);
		
//		for(int i=0;i<1000;i++){
//			String [] ss = Parser.parse("GenConf/NeedGens/NeedGen/Params/Param$").interpret(ctx);
//			String [] ss2 = Parser.parse("GenConf/NeedGens/NeedGen/Params/Param$.id$").interpret(ctx);
//		}
//		long a2 = System.currentTimeMillis();
//		System.out.println("now use time==="+(a2-a1));
//		
//		for(String s : ss){
//			System.out.println("ss=="+s);
//		}
//		
//		
//		GenConfEbi ebi = GenConfFactory.createGenConfEbi(new GenConfXmlImpl());
//		
//		System.out.println("gm====="+ebi.getMapModuleConf());
	}

	
}
