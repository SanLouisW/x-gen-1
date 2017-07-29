package cn.javass.xgen.genconf.implementor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.constants.GenConfEnum;
import cn.javass.xgen.genconf.implementor.GenConfImplementor;
import cn.javass.xgen.genconf.vo.NeedGenModel;
import cn.javass.xgen.genconf.vo.ThemeModel;
import cn.javass.xgen.utill.readxml.Context;

/** 
 * 1.用builder获取用于取值的字符串
 * 2.
 * 
 * @author Administrator
 *
 */
public class GenConfXmlImpl implements GenConfImplementor {

	public List<NeedGenModel> getNeedGens() {
		
		
		return null;
	}

	public List<ThemeModel> getTheme() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getMapContants() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//////////////////////////////////////////////////////////////////////
    private Context getContext(){
    	Context c = null;
		try {
			c = Context.getInstance(new GenConfBuilder().addXmlFilePre().addGenConf().addDot().build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
    }
	private List<NeedGenModel> readNeedGends(){
		List<NeedGenModel> retList = new ArrayList<NeedGenModel>();
		
		return retList;
	}
	//////////////////////////////////////////////////////////////////////
}
