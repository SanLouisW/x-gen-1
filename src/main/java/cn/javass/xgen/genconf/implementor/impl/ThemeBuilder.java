package cn.javass.xgen.genconf.implementor.impl;

import cn.javass.xgen.genconf.constants.ThemeEnum;

public class ThemeBuilder extends CommonBuilder<ThemeBuilder>{
	private StringBuffer buffer = new StringBuffer();

	
	public ThemeBuilder addTheme(){
		buffer.append(ThemeEnum.Theme);
		return this;
	}
	public ThemeBuilder addGenTypes(){
		buffer.append(ThemeEnum.GenTypes);
		return this;
	}
	public ThemeBuilder addGenType(){
		buffer.append(ThemeEnum.GenType);
		return this;
	}
	public ThemeBuilder addParams(){
		buffer.append(ThemeEnum.Params);
		return this;
	}
	public ThemeBuilder addParam(){
		buffer.append(ThemeEnum.Param);
		return this;
	}
	public ThemeBuilder addGenOutTypes(){
		buffer.append(ThemeEnum.GenOutTypes);
		return this;
	}
	public ThemeBuilder addGenOutType(){
		buffer.append(ThemeEnum.GenOutType);
		return this;
	}
	public ThemeBuilder addProviders(){
		buffer.append(ThemeEnum.Providers);
		return this;
	}
	public ThemeBuilder addProvider(){
		buffer.append(ThemeEnum.Provider);
		return this;
	}
	public ThemeBuilder addType(){
		buffer.append(ThemeEnum.type);
		return this;
	}
	
	
	protected StringBuffer getBuilderBuffer() {
		return this.buffer;
	}

	protected ThemeBuilder getBuilderClassInstance() {
		return this;
	}
}
