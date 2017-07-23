package cn.javass.xgen.utill.readxml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Parser {
	private static final String BACKLASH = "/";
	private static final String DOT = ".";
	private static final String DOLLAR = "$";
	private static final String OPEN_BRACKET = "$";
	private static final String CLOSE_BRACKET = "$";
	
	/**
	 * 工具类，构造函数私有化，防止外部创建实例
	 */
	private Parser(){
		
	}
	
	/**
	 * 根据传入的字符串表达式，解析成为一个抽象的语法树
	 * @param cxpr字符串表达式
	 * @return 抽象的语法树
	 */
	public static ReadXmlExpression parse(String expr){
		// root/a/b/c
		// 1  分解表达式，得到需要解析的元素名称，和该元素对应的解析模型
		Map<String, ParseModel> mapPath = parseMapPath(expr);
		
		// 2  根据元素对应的解析模型，转化成相应的解释器对象
		List<ReadXmlExpression> listExpression = mapPath2Expression(mapPath);
		
		// 3  按照先后顺序组合成为抽象的语法树
		buildTree(listExpression);
		
		return null;
	}
	
	/**
	 * 按照从左到右的顺序来解析表达式得到相应该元素对应的解析模型
	 * @param expr
	 * @return
	 */
	private static Map<String, ParseModel> parseMapPath(String expr){
		Map<String, ParseModel> mapPath = new HashMap<String, ParseModel>();
		StringTokenizer tokenizer = new StringTokenizer(expr, BACKLASH);
		
		while (tokenizer.hasMoreTokens()) {
			String onePath = tokenizer.nextToken();
			
			if(tokenizer.hasMoreTokens()){
				// 还有下一个，说明不是结尾
				setParsePath(onePath, false, false, mapPath);
			}else {
				int dotIndex = onePath.indexOf(DOT);
				
				if(dotIndex>0){
					String eleName = onePath.substring(0, dotIndex);
					String propName = onePath.substring(dotIndex);
					
					//设置属性前面的元素
					setParsePath(eleName, false, false, mapPath);
					
					//设置属性
					setParsePath(propName, true, true, mapPath);
				} else {
					// 说明元素结尾
					setParsePath(onePath, true, false, mapPath);
				}
				
				break ;
			}
		}
		
		return mapPath;
	}
	
	private static void setParsePath(String eleName, boolean end, boolean propertyVale, Map<String, ParseModel> mapPath){
		// 还有下一个，说明不是结尾
		ParseModel pm = new ParseModel();
		
		pm.setEnd(end);
		pm.setPropertyValue(propertyVale);
		pm.setSingleValue(!(eleName.indexOf(DOLLAR) > 0));
		// 去掉$
		eleName.replace(DOLLAR, "");
		
		int tempBegin = 0;
		int tempEnd = 0;
		
		if((tempBegin = eleName.indexOf(OPEN_BRACKET)) > 0){
			tempEnd = eleName.indexOf(CLOSE_BRACKET);
			
			pm.setCondition(eleName.substring(tempBegin+1, tempEnd));
			
			eleName = eleName.substring(0, tempBegin);
		} else {
			pm.setEleName(eleName);
		}
		
		mapPath.put(eleName, pm);
	}
	
	/**
	 * 根据元素对应的解析模型，转化成相应的解释器对象
	 * @param mapPath
	 * @return
	 */
	private static List<ReadXmlExpression> mapPath2Expression(Map<String, ParseModel> mapPath){
		
		return null;
	}
	
	private static ReadXmlExpression buildTree(List<ReadXmlExpression> listExpression){
		return null;
	}
}
