package cn.javass.xgen.utill.readxml;

import java.util.List;

import org.w3c.dom.Element;

public class ProportysTerminalExpression extends ReadXmlExpression{
	private String propName;
	
	public ProportysTerminalExpression(String propName){
		this.propName = propName;
	}
	@Override
	public String[] interpret(Context context) {
		List<Element> preEles = context.getPreEles();
		
		String[] values = new String[preEles.size()];
		
		for (int i = 0; i < preEles.size(); i++) {
			values[i] = preEles.get(i).getAttribute(propName);
		}
		
		return values;
	}

}
