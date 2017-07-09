package cn.javass.xgen.utill.readxml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;

public class ElementsTerminalExpression extends ReadXmlExpression{
	/**
	 * 元素名称
	 */
	private String eleName = "";
	
	/**
	 * 判断条件
	 */
	private String condition = "";
	
	public ElementsTerminalExpression(String eleName, String condition){
		this.eleName = eleName;
		this.condition = condition;
	}
	
	@Override
	public String[] interpret(Context context) {
		// 1.维护父级节点记录
		List<Element> preEles = context.getPreEles();
		
		// 获取当前元素，多个
		List<Element> nowEles = new ArrayList<Element>();
		
		for (Element preEle : preEles) {
			nowEles.addAll(context.getNowEles(preEle, eleName));
		}
		
		//判断是否满足条件
		Iterator<Element> it = nowEles.iterator();
		while(it.hasNext()){
			Element ele = it.next();
			
			if(!context.judgeCondition(ele, condition)){
				it.remove();
			}
		}
		
		//设置父节点
		context.setPreEles(nowEles);
		
		// 2.循环解释子元素
		String[] ss = new String[nowEles.size()];
		
		for (int i = 0; i < ss.length; i++) {
			Element ele = nowEles.get(i);
			if(ele.getFirstChild() != null){
				ss[i] = ele.getFirstChild().getNodeValue();
			} else {
				ss[i] = "";
			}
		}
		return ss;
	}
}
