package cn.javass.xgen.genconf;

import java.util.Map;

import cn.javass.xgen.genconf.vo.GenConfModel;
import cn.javass.xgen.genconf.vo.ModuleConfModel;

public interface GenConfEbi {
	/**
	 * ��ȡx-gen���Ŀ��������Ҫ����������model
	 * @return
	 */
	public GenConfModel getGenConf();

	/**
	 * ��ȡ��Ҫ���ɵ�����ģ������
	 * @return
	 */
	public Map<String,ModuleConfModel> getMapModelConf(); 
}
