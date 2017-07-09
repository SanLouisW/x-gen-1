package cn.javass.xgen.utill.readxml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;

public class ElementsTerminalExpression extends ReadXmlExpression{
	/**
	 * Ԫ������
	 */
	private String eleName = "";
	
	/**
	 * �ж�����
	 */
	private String condition = "";
	
	public ElementsTerminalExpression(String eleName, String condition){
		this.eleName = eleName;
		this.condition = condition;
	}
	
	@Override
	public String[] interpret(Context context) {
		// 1.ά�������ڵ��¼
		List<Element> preEles = context.getPreEles();
		
		// ��ȡ��ǰԪ�أ����
		List<Element> nowEles = new ArrayList<Element>();
		
		for (Element preEle : preEles) {
			nowEles.addAll(context.getNowEles(preEle, eleName));
		}
		
		//�ж��Ƿ���������
		Iterator<Element> it = nowEles.iterator();
		while(it.hasNext()){
			Element ele = it.next();
			
			if(!context.judgeCondition(ele, condition)){
				it.remove();
			}
		}
		
		//���ø��ڵ�
		context.setPreEles(nowEles);
		
		// 2.ѭ��������Ԫ��
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
