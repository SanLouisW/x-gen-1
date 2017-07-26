package cn.javass.xgen.utill.readxml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;

public class ElementsExpression extends ReadXmlExpression{

	/**
	 * 用于记录组合的子ReadXmlExpression元素
	 */
	private List<ReadXmlExpression> eles = new ArrayList<ReadXmlExpression>(); 
	
	/**
	 * 元素名称
	 */
	private String eleName = "";
	
	/**
	 * 判断条件
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
		// 1.维护父级节点记录,使用的场景是多个肯定不是根元素，根元素只有一个
		List<Element> preEles = context.getPreEles();
		
		// 获取当前元素，多个
		List<Element> nowEles = new ArrayList<Element>();
		
		for (Element preEle : preEles) {
			nowEles.addAll(context.getNowEles(preEle, eleName));
		}
		
		//判断条件
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
		String[] ss = null;
		for (ReadXmlExpression tempEle : eles) {
			ss = tempEle.interpret(context);
		}
		
		return ss;
	}

}
