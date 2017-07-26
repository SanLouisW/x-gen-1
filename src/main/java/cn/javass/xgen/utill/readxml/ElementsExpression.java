package cn.javass.xgen.utill.readxml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;

public class ElementsExpression extends ReadXmlExpression{

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
	
	public ElementsExpression(String eleName, String condition){
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
	
	/**
	 * @return the eles
	 */
	public List<ReadXmlExpression> getEles() {
		return eles;
	}

	@Override
	public String[] interpret(Context context) {
		// 1.ά�������ڵ��¼,ʹ�õĳ����Ƕ���϶����Ǹ�Ԫ�أ���Ԫ��ֻ��һ��
		List<Element> preEles = context.getPreEles();
		
		// ��ȡ��ǰԪ�أ����
		List<Element> nowEles = new ArrayList<Element>();
		
		for (Element preEle : preEles) {
			nowEles.addAll(context.getNowEles(preEle, eleName));
		}
		
		//�ж�����
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
		String[] ss = null;
		for (ReadXmlExpression tempEle : eles) {
			ss = tempEle.interpret(context);
		}
		
		return ss;
	}

}
