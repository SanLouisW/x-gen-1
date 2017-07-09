package cn.javass.xgen.utill.readxml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

public class ElementExpression extends ReadXmlExpression{

	/**
	 * ���ڼ�¼��ϵ���ReadXmlExpressionԪ��
	 */
	private List<ReadXmlExpression> eles = new ArrayList<ReadXmlExpression>(); 
	
	/**
	 * Ԫ������
	 */
	private String eleName = "";
	
	/**
	 * �ж�����
	 */
	private String condition = "";
	
	public ElementExpression(String eleName, String condition){
		this.eleName = eleName;
		this.condition = condition;
	}
	
	public void addEle(ReadXmlExpression readXmlExpression){
		this.eles.add(readXmlExpression);
	}
	
	public boolean removeEle(ReadXmlExpression readXmlExpression){
		this.eles.remove(readXmlExpression);
		return true;
	}
	
	public boolean removeAllEle(){
		this.eles.clear();
		return true;
	}
	
	@Override
	public String[] interpret(Context context) {
		// 1.ά�������ڵ��¼
		// 1.1��ȡ����Ԫ��
		List<Element> preEles = context.getPreEles();
		Element ele = null;
		List<Element> nowEles = new ArrayList<Element>();
		// 1.2�жϸ�Ԫ���Ƿ���ڣ������ڸ��ڵ�
		// 1.3������ڣ��ҵ���ǰ���ʽ��Ӧ��Ԫ�أ������Ԫ������Ϊ�����ڵ�
		if(preEles == null || preEles.size() == 0){
			ele = context.getDocument().getDocumentElement();
			
			preEles.add(ele);
			
			context.setPreEles(preEles);
		} else {
			for (Element preEle : preEles) {
				nowEles.addAll(context.getNowEles(preEle, eleName));
				if(nowEles.size() > 0){
					break;
				}
			}
			
			if(nowEles.size() > 0 && context.judgeCondition(nowEles.get(0), condition)){
				List<Element> tempList = new ArrayList<Element>();
				tempList.add(nowEles.get(0));
				
				context.setPreEles(tempList);
			}
		}
		// 2.ѭ��������Ԫ��
		String[] ss = null;
		for (ReadXmlExpression tempEle : eles) {
			ss = tempEle.interpret(context);
		}
		
		return ss;
	}

}
