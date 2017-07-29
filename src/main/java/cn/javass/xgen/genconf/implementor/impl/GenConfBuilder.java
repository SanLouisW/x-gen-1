package cn.javass.xgen.genconf.implementor.impl;

import cn.javass.xgen.genconf.constants.GenConfEnum;

public class GenConfBuilder extends CommonBuilder<GenConfBuilder>{
	/**
	 * 用于记录分部记录的字符串
	 */
	private StringBuffer buffer = new StringBuffer();
	
	public GenConfBuilder addGenConf(){
		buffer.append(GenConfEnum.GenConf);
		return this;
	}	
	public GenConfBuilder addNeedGens(){
		buffer.append(GenConfEnum.NeedGens);
		return this;
	}
	public GenConfBuilder addNeedGen(){
		buffer.append(GenConfEnum.NeedGen);
		return this;
	}
	public GenConfBuilder addThemes(){
		buffer.append(GenConfEnum.Themes);
		return this;
	}
	public GenConfBuilder addTheme(){
		buffer.append(GenConfEnum.Theme);
		return this;
	}
	public GenConfBuilder addConstants(){
		buffer.append(GenConfEnum.Constants);
		return this;
	}
	public GenConfBuilder addConstant(){
		buffer.append(GenConfEnum.Constant);
		return this;
	}
	public GenConfBuilder addProvider(){
		buffer.append(GenConfEnum.provider);
		return this;
	}
	public GenConfBuilder addThemeId(){
		buffer.append(GenConfEnum.themeId);
		return this;
	}
	public GenConfBuilder addParams(){
		buffer.append(GenConfEnum.Params);
		return this;
	}
	public GenConfBuilder addParam(){
		buffer.append(GenConfEnum.Param);
		return this;
	}
	
	protected StringBuffer getBuilderBuffer() {
		return this.buffer;
	}
	protected GenConfBuilder getBuilderClassInstance() {
		return this;
	}
	public GenConfBuilder addXmlFilePre() {
		buffer.append(GenConfEnum.Param);
		return this;
	}
}
