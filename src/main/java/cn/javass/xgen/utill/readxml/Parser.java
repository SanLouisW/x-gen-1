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
	 * 按照分解的先后顺讯记录的路径
	 */
	private static List<String> listElePath = new ArrayList<String>();
	
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
		if(needPaseExpr.trim().length() > 0){
			ReadXmlExpression re = parse2(needPaseExpr);
		}
		
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
	private static ReadXmlExpression parse2(String expr){
		// 清空缓存
		listElePath = new ArrayList<String>();
		
		// root/a/b/c
		// 1  分解表达式，得到需要解析的元素名称，和该元素对应的解析模型
		Map<String, ParseModel> mapPath = parseMapPath(expr);
		
		// 2  根据元素对应的解析模型，转化成相应的解释器对象
		Map<String, ReadXmlExpression> mapExpression = mapPath2Expression(mapPath);
		
		// 3  按照先后顺序组合成为抽象的语法树
		ReadXmlExpression buildTree = buildTree(mapExpression);
		
		return buildTree;
	}
	
	/**
	 * 按照从左到右的顺序来解析表达式得到相应该元素的路径和对应的解析模型
	 * @param expr
	 * @return
	 */
	private static Map<String, ParseModel> parseMapPath(String expr){
		Map<String, ParseModel> mapPath = new HashMap<String, ParseModel>();
		
		// 从根开始的前缀路径
		StringBuffer pathBuffer = new StringBuffer();
		
		StringTokenizer tokenizer = new StringTokenizer(expr, BACKLASH);
		
		while (tokenizer.hasMoreTokens()) {
			String onePath = tokenizer.nextToken();
			
			if(tokenizer.hasMoreTokens()){
				// 还有下一个，说明不是结尾
				pathBuffer.append(onePath+BACKLASH);//设置路径
				
				setParsePath(pathBuffer, onePath, false, false, mapPath);
			}else {
				int dotIndex = onePath.indexOf(DOT);
				
				if(dotIndex>0){
					String eleName = onePath.substring(0, dotIndex);
					String propName = onePath.substring(dotIndex+1);
					
					pathBuffer.append(eleName + DOT);//设置路径
					
					//设置属性前面的元素
					setParsePath(pathBuffer, eleName, false, false, mapPath);
					
					pathBuffer.append(propName);
					
					//设置属性
					setParsePath(pathBuffer, propName, true, true, mapPath);
				} else {
					// 说明元素结尾
					pathBuffer.append(onePath);
					
					setParsePath(pathBuffer, onePath, true, false, mapPath);
				}
				
				break ;
			}
		}
		
		return mapPath;
	}
	
	private static void setParsePath(StringBuffer pathBuffer, String eleName, boolean end, boolean propertyVale, Map<String, ParseModel> mapPath){
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
		
		mapPath.put(pathBuffer.toString(), pm);
		
		System.out.println("===>pm="+pm.toString());
		
		listElePath.add(pathBuffer.toString());
	}
	
	/**
	 * 根据元素对应的解析模型，转化成相应的解释器对象
	 * @param mapPath
	 * @return
	 */
	private static Map<String, ReadXmlExpression> mapPath2Expression(Map<String, ParseModel> mapPath){
		Map<String, ReadXmlExpression> map = new HashMap<String, ReadXmlExpression>();
		for (String key : listElePath) {
			ParseModel pm = mapPath.get(key);
			
			ReadXmlExpression obj = parseModel2ReadXmlExpression(pm);
			
			map.put(key, obj);
		}
		
		return map;
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
	
	private static ReadXmlExpression buildTree(String prefixStr, ReadXmlExpression prefixRe, 
			Map<String, ReadXmlExpression> mapPathAndRe, Map<String, ReadXmlExpression> mapRe){
		// 第一个对象，跟对象，也是要返回去对象
		ReadXmlExpression retRe = prefixRe;
		// 临时记录父元素
		ReadXmlExpression preRe = getLastRe(prefixRe);
		
		for (String path : listElePath) {
			ReadXmlExpression re = mapPathAndRe.get(path);
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
			// 每次生成一个抽象树对象，添加到缓存
			if(prefixStr != null && prefixStr.length() > 0){
				
				mapRe.put(prefixStr + BACKLASH + path, value);
			}
		}
		
		return retRe;
	}
	
	/**
	 * 获取解析过的对象树的最后一个元素对象
	 * @return
	 */
	private static ReadXmlExpression getLastRe(ReadXmlExpression prefixRe){
		ReadXmlExpression lastRe = prefixRe;
		boolean flag = true;
		
		while (flag) {
			if(lastRe instanceof ElementExpression){
				if(((ElementExpression)lastRe).getEles().size() > 0){
					lastRe = ((ElementExpression)lastRe).getEles().get(0);//取子元素，循环实现递归到树的结尾
					
					if(lastRe instanceof ElementExpression){
						flag =  ((ElementExpression)lastRe).getEles().size() > 0;
					} else if(lastRe instanceof ElementsExpression){
						flag =  ((ElementsExpression)lastRe).getEles().size() > 0;
					} else {
						flag = false;
					}
				}else {
					flag = false;
				}
			}else if(lastRe instanceof ElementsExpression){
				lastRe = ((ElementExpression)lastRe).getEles().get(0);//取子元素，循环实现递归到树的结尾
				
				if(lastRe instanceof ElementExpression){
					flag =  ((ElementExpression)lastRe).getEles().size() > 0;
				} else if(lastRe instanceof ElementsExpression){
					flag =  ((ElementsExpression)lastRe).getEles().size() > 0;
				} else {
					flag = false;
				}
			} else {
				flag = false;
			}
		}
		
		return lastRe;
	}
}
