package cn.javass.xgen;

import cn.javass.xgen.utill.readxml.Context;
import cn.javass.xgen.utill.readxml.Parser2;

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
		params.addEle(param);
		
		ElementExpression genConf = new ElementExpression("GenConf", "");
		ElementExpression needGens = new ElementExpression("NeedGens", "");
		ElementExpression needGen = new ElementExpression("NeedGen", "");
		ElementExpression params = new ElementExpression("Params", "");
		ElementsExpression param = new ElementsExpression("Param", "id=fileName");
		
		ProportysTerminalExpression props = new ProportysTerminalExpression("id");
		
		genConf.addEle(needGens);
		needGens.addEle(needGen);
		needGen.addEle(params);
		params.addEle(param);
		param.addEle(props);
		
		long a1 = System.currentTimeMillis();
		
		Context ctx = Context.getInstance("/GenConf.xml");
		
		String[] ss = genConf.interpret(ctx);
		for (int i = 0; i < ss.length; i++) {
			System.out.println("SS["+i+"]="+ss[i]);
		}*/
		
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
		long a1 = System.currentTimeMillis();
		
		Context ctx = Context.getInstance("/GenConf.xml");
		String [] ss = Parser2.parse("GenConf/NeedGens/NeedGen$/Params$/Param$").interpret(ctx);
		
		long a2 = System.currentTimeMillis();
		System.out.println("now use time==="+(a2-a1));
		for(String s : ss){
			System.out.println("ss=="+s);
		}
	}

	
}
