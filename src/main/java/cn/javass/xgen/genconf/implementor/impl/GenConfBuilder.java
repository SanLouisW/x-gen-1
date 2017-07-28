package cn.javass.xgen.genconf.implementor.impl;

public class GenConfBuilder {
	/**
	 * 用于记录分部记录的字符串
	 */
	private StringBuffer buffer = new StringBuffer();
	
	 public StringBuffer addGenConf(StringBuffer buffer){
		 buffer.append("GenConf");
		 return buffer;
	 }
}
