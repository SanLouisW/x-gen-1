package cn.javass.xgen.utill.readxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Parser {
	private static final String BACKLASH = "/";
	private static final String DOT = ".";
	private static final String DOLLAR = "$";
	private static final String OPEN_BRACKET = "[";
	private static final String CLOSE_BRACKET = "]";
	
	/**
	 * ���շֽ���Ⱥ�˳Ѷ��¼����
	 */
	private static List<String> listEle = new ArrayList<String>();
	
	/**
	 * �����࣬���캯��˽�л�����ֹ�ⲿ����ʵ��
	 */
	private Parser(){
		
	}
	
	/**
	 * ���ݴ�����ַ������ʽ��������Ϊһ��������﷨��
	 * @param cxpr�ַ������ʽ
	 * @return ������﷨��
	 */
	public static ReadXmlExpression parse(String expr){
		// root/a/b/c
		// 1  �ֽ���ʽ���õ���Ҫ������Ԫ�����ƣ��͸�Ԫ�ض�Ӧ�Ľ���ģ��
		Map<String, ParseModel> mapPath = parseMapPath(expr);
		
		// 2  ����Ԫ�ض�Ӧ�Ľ���ģ�ͣ�ת������Ӧ�Ľ���������
		List<ReadXmlExpression> listExpression = mapPath2Expression(mapPath);
		
		// 3  �����Ⱥ�˳����ϳ�Ϊ������﷨��
		ReadXmlExpression buildTree = buildTree(listExpression);
		
		return buildTree;
	}
	
	/**
	 * ���մ����ҵ�˳�����������ʽ�õ���Ӧ��Ԫ�ض�Ӧ�Ľ���ģ��
	 * @param expr
	 * @return
	 */
	private static Map<String, ParseModel> parseMapPath(String expr){
		Map<String, ParseModel> mapPath = new HashMap<String, ParseModel>();
		StringTokenizer tokenizer = new StringTokenizer(expr, BACKLASH);
		
		while (tokenizer.hasMoreTokens()) {
			String onePath = tokenizer.nextToken();
			
			if(tokenizer.hasMoreTokens()){
				// ������һ����˵�����ǽ�β
				setParsePath(onePath, false, false, mapPath);
			}else {
				int dotIndex = onePath.indexOf(DOT);
				
				if(dotIndex>0){
					String eleName = onePath.substring(0, dotIndex);
					String propName = onePath.substring(dotIndex+1);
					
					//��������ǰ���Ԫ��
					setParsePath(eleName, false, false, mapPath);
					
					//��������
					setParsePath(propName, true, true, mapPath);
				} else {
					// ˵��Ԫ�ؽ�β
					setParsePath(onePath, true, false, mapPath);
				}
				
				break ;
			}
		}
		
		return mapPath;
	}
	
	private static void setParsePath(String eleName, boolean end, boolean propertyVale, Map<String, ParseModel> mapPath){
		// ������һ����˵�����ǽ�β
		ParseModel pm = new ParseModel();
		
		pm.setEnd(end);
		pm.setPropertyValue(propertyVale);
		pm.setSingleValue(!(eleName.indexOf(DOLLAR) > 0));
		// ȥ��$
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
	 * ����Ԫ�ض�Ӧ�Ľ���ģ�ͣ�ת������Ӧ�Ľ���������
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
		// ��һ�����󣬸�����Ҳ��Ҫ����ȥ����
		ReadXmlExpression retRe = null;
		// ��ʱ��¼��Ԫ��
		ReadXmlExpression preRe = null;
		
		for (ReadXmlExpression re : listExpression) {
			if(preRe == null){
				retRe = re;
                preRe = re;
			} else {
				// �ѵ�ǰԪ����ӵ���Ԫ�����棬ͬʱ���Լ�����Ϊ��Ԫ��
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
