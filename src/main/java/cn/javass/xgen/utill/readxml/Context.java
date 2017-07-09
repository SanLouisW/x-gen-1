package cn.javass.xgen.utill.readxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 解释执行需要的全局信息
 * @author Administrator
 *
 */
public class Context {
	/**
	 * 上一次被处理的多个父节点元素
	 */
	private List<Element> preEles = new ArrayList<Element>();
	
	private Document document = null;
	
	private Context(Document document){
		this.document = document;
	}
	
	private static Map<String, Context> mapCtx = new HashMap<String, Context>();
	
	public static Context getInstance(String filePathName) throws Exception{
		Context c = mapCtx.get(filePathName);
		if(c == null ){
			 Document document = XmlUtill.getDocument(filePathName);
			 
			 c = new Context(document);
			 
			 mapCtx.put(filePathName, c);
		}
		
		// 先初始化
		c.init();
		
		return c;
	}

	public List<Element> getPreEles() {
		return preEles;
	}
	
	public void setPreEles(List<Element> preEles) {
		this.preEles = preEles;
	}
	
	private void init(){
		mapCtx = new HashMap<String, Context>();
	} 
	
	public Document getDocument(){
		return this.document;
	}
	
	public List<Element> getNowEles(Element preEle, String eleNmae){
		List<Element> nowEles = new ArrayList<Element>();
		NodeList tempNodes = preEle.getChildNodes();
		for (int i = 0; i < tempNodes.getLength(); i++) {
			if(tempNodes.item(i) instanceof Element){
				Element ele = (Element)tempNodes.item(i);
				
				if(ele.getTagName().equals(eleNmae)){
					nowEles.add(ele);
				}
			}
		}
		return nowEles;
	}
	
	public boolean judgeCondition(Element ele, String condition){
		if(condition == null || condition.trim().length() == 0){
			return true;
		}
		
		String[] ss = condition.split("=");
		
		if(ss[1] != null && ss[1].equals(ele.getAttribute(ss[0]))){
			return true;
		}
		return false;
	}
	
}
