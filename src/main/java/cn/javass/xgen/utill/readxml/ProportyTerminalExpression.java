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
		// 1 ��ȡ��Ԫ�� �����Ǹ�Ԫ�ص�����
		Element preEle = context.getPreEles().get(0);
		
		values[0] = preEle.getAttribute(propName);
		
		return values;
	}

}
