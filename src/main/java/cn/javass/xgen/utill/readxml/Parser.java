package cn.javass.xgen.utill.readxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.MenuElement;

public class Parser {
	private static final String BACKLASH = "/";
	private static final String DOT = ".";
	private static final String DOLLAR = "$";
	private static final String OPEN_BRACKET = "[";
	private static final String CLOSE_BRACKET = "]";
	
	/**
	 * 按照分解的先后顺讯记录名称
	 */
	private static List<String> listEle = new ArrayList<String>();
	
	/**
	 * 工具类，构造函数私有化，防止外部创建实例
	 */
	private Parser(){
		
	}
	
	///////////////////备忘录开始////////////////////////
	private static class MementoImpl implements ParseMedento{
		private Map<String, ReadXmlExpression> mapRe = new HashMap<String, ReadXmlExpression>();
		
		public MementoImpl(Map<String, ReadXmlExpression> mapRe){
			this.mapRe = mapRe;
		}
		
		public Map<String, ReadXmlExpression> getMapRe(){
			return mapRe;
		}
	}
	
	/**
	 * @param expr
	 * @return
	 */
	public static ReadXmlExpression parse(String expr){
		// 1获取备忘录
		ParseCaretaker memento = ParseCaretaker.getInstance();
		
		// 2备忘录中取出数据
		Map<String, ReadXmlExpression> mapRe = null;
		if(mapRe == null){
			mapRe = new HashMap<String, ReadXmlExpression>();
		} else {
			mapRe = ((MementoImpl)mapRe).getMapRe();
		}
		
		// 3从缓存取出最长的相同string,这部分不要解析
		String notPaseExpr = searchMaxLongEquals(expr, mapRe);
		
		// 4获取剩下部分需要解析的
		String needPaseExpr = "";
		if(notPaseExpr.trim().length() == 0){
			needPaseExpr = expr;
		} else {
			if(notPaseExpr.length() < expr.length()){
				expr.substring(notPaseExpr.length()+1);
			}else{
				needPaseExpr = "";
			}
		}

		
		// 5解析剩下部分
		
		// 6把剩下部分抽象语法树合并起来
		
		return null;
	}
	
	private static String searchMaxLongEquals(String expr, Map<String, ReadXmlExpression> mapRe){
		// a/b/c
		boolean flag = mapRe.containsKey(expr);
		
		while (!flag) {
			int lastIndex = expr.lastIndexOf(BACKLASH);
			
			if(lastIndex > 0){
				expr = expr.substring(0, lastIndex);
				
				flag = mapRe.containsKey(expr+BACKLASH);
			}else{
				expr = "";
				flag = true;
			}
		}
		
		return expr;
	}
	///////////////////备忘录结束////////////////////////
	
	/**
	 * 根据传入的字符串表达式，解析成为一个抽象的语法树
	 * @param cxpr字符串表达式
	 * @return 抽象的语法树
	 */
	public static ReadXmlExpression parse2(String expr){
		// root/a/b/c
		// 1  分解表达式，得到需要解析的元素名称，和该元素对应的解析模型
		Map<String, ParseModel> mapPath = parseMapPath(expr);
		
		// 2  根据元素对应的解析模型，转化成相应的解释器对象
		List<ReadXmlExpression> listExpression = mapPath2Expression(mapPath);
		
		// 3  按照先后顺序组合成为抽象的语法树
		ReadXmlExpression buildTree = buildTree(listExpression);
		
		return buildTree;
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
					String propName = onePath.substring(dotIndex+1);
					
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
		eleName = eleName.replace(DOLLAR, "");
		
		int tempBegin = 0;
		int tempEnd = 0;
		
		if((tempBegin = eleName.indexOf(OPEN_BRACKET)) > 0){
			tempEnd = eleName.indexOf(CLOSE_BRACKET);
			
			pm.setCondition(eleName.substring(tempBegin+1, tempEnd));
			
			eleName = eleName.substring(0, tempBegin);
		}
		
		pm.setEleName(eleName);
		
		mapPath.put(eleName, pm);
		
		System.out.println("===>pm="+pm.toString());
		
		listEle.add(eleName);
	}
	
	/**
	 * 根据元素对应的解析模型，转化成相应的解释器对象
	 * @param mapPath
	 * @return
	 */
	private static List<ReadXmlExpression> mapPath2Expression(Map<String, ParseModel> mapPath){
		List<ReadXmlExpression> list = new ArrayList<ReadXmlExpression>();
		for (String key : listEle) {
			ParseModel pm = mapPath.get(key);
			
			ReadXmlExpression obj = parseModel2ReadXmlExpression(pm);
			
			list.add(obj);
		}
		
		return list;
	}
	
	private static ReadXmlExpression parseModel2ReadXmlExpression(ParseModel pm){
		ReadXmlExpression obj = null;
		
		if(!pm.isEnd()){
			if(pm.isSingleValue()){
				obj = new ElementExpression(pm.getEleName(), pm.getCondition());
			}else{
				obj = new ElementsExpression(pm.getEleName(), pm.getCondition());
			}
		} else {
			if(pm.isPropertyValue()){
				if(pm.isSingleValue()){
					obj = new ProportyTerminalExpression(pm.getEleName());
				} else {
					obj = new ProportysTerminalExpression(pm.getEleName());
				}
			} else {
				if(pm.isSingleValue()){
					obj = new ElementTerminalExpression(pm.getEleName(), pm.getCondition());
				} else {
					obj = new ElementsTerminalExpression(pm.getEleName(), pm.getCondition());
				}
			}
		}
		
		return obj;
	}
	
	private static ReadXmlExpression buildTree(List<ReadXmlExpression> listExpression){
		// 第一个对象，跟对象，也是要返回去对象
		ReadXmlExpression retRe = null;
		// 临时记录父元素
		ReadXmlExpression preRe = null;
		
		for (ReadXmlExpression re : listExpression) {
			if(preRe == null){
				retRe = re;
                preRe = re;
			} else {
				// 把当前元素添加到父元素下面，同时把自己设置为父元素
				if(preRe instanceof ElementExpression){
					ElementExpression ele = (ElementExpression) preRe;
					
					ele.addEle(re);
					
					preRe = re;
				} else if(preRe instanceof ElementsExpression){
					ElementsExpression ele = (ElementsExpression) preRe;
					
					ele.addEle(re);
					
					preRe = re;
				}
			}
		}
		
		return retRe;
	}
}
