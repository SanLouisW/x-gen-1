package cn.javass.xgen.utill.readxml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

public class ElementExpression extends ReadXmlExpression{

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
	
	public ElementExpression(String eleName, String condition){
		this.eleName = eleName;
		this.condition = condition;
	}
	
	public void addEle(ReadXmlExpression readXmlExpression){
		this.eles.add(readXmlExpression);
	}
	
	/**
	 * @param eles the eles to set
	 */
	public void setEles(List<ReadXmlExpression> eles) {
		this.eles = eles;
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
		// 1.维护父级节点记录
		// 1.1先取出父元素
		List<Element> preEles = context.getPreEles();
		Element ele = null;
		List<Element> nowEles = new ArrayList<Element>();
		// 1.2判断父元素是否存在，不存在根节点
		// 1.3如果存在，找到当前表达式对应的元素，把这个元素设置为父级节点
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
		// 2.循环解释子元素
		String[] ss = null;
		for (ReadXmlExpression tempEle : eles) {
			ss = tempEle.interpret(context);
		}
		
		return ss;
	}

	/* (non-Javadoc)
	 * @see cn.javass.xgen.utill.readxml.ReadXmlExpression#clone()
	 */
	@Override
	protected Object clone(){
		ElementExpression obj = null;
		try {
			obj = (ElementExpression) super.clone();
			
			ArrayList<ReadXmlExpression> objEles = new ArrayList<ReadXmlExpression>();
			
			for (ReadXmlExpression re : objEles) {
				objEles.add((ReadXmlExpression)re.clone());
			}
			
			obj.setEles(objEles);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return obj;
	}
}
