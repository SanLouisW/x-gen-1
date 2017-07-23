package cn.javass.xgen.utill.readxml;

import java.util.List;

import org.w3c.dom.Element;

public class ProportyTerminalExpression extends ReadXmlExpression{
	
	private String propName;
	
	public ProportyTerminalExpression(String propName){
		this.propName = propName;
	}

	@Override
	public String[] interpret(Context context) {
		String[] values = new String[1];
		// 1 获取父元素 属性是父元素的属性
		Element preEle = context.getPreEles().get(0);
		
		values[0] = preEle.getAttribute(propName);
		
		return values;
	}

}
