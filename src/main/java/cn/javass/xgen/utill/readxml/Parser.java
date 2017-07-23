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
		buildTree(listExpression);
		
		return null;
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
					String propName = onePath.substring(dotIndex);
					
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
	 * ����Ԫ�ض�Ӧ�Ľ���ģ�ͣ�ת������Ӧ�Ľ���������
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
