package cn.javass.xgen.genconf.implementor.impl;

import cn.javass.xgen.genconf.constants.ModuleGenConfEnum;

public class ModuleGenConfBuilder extends CommonBuilder<ModuleGenConfBuilder>{
	private StringBuffer buffer = new StringBuffer();
	
	public ModuleGenConfBuilder addModuleGenConf(){
		buffer.append(ModuleGenConfEnum.ModuleGenConf);
		return this;
	}
	public ModuleGenConfBuilder addNeedGenTypes(){
		buffer.append(ModuleGenConfEnum.NeedGenTypes);
		return this;
	}
	public ModuleGenConfBuilder addNeedGenType(){
		buffer.append(ModuleGenConfEnum.NeedGenType);
		return this;
	}
	public ModuleGenConfBuilder addNeedGenOutType(){
		buffer.append(ModuleGenConfEnum.NeedGenOutType);
		return this;
	}
	public ModuleGenConfBuilder addExtendConfs(){
		buffer.append(ModuleGenConfEnum.ExtendConfs);
		return this;
	}
	public ModuleGenConfBuilder addExtendConf(){
		buffer.append(ModuleGenConfEnum.ExtendConf);
		return this;
	}
	public ModuleGenConfBuilder addIsSingle(){
		buffer.append(ModuleGenConfEnum.isSingle);
		return this;
	}
	
	protected StringBuffer getBuilderBuffer() {
		return this.buffer;
	}
	protected ModuleGenConfBuilder getBuilderClassInstance() {
		return this;
	}
}
