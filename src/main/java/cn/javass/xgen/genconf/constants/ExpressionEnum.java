package cn.javass.xgen.genconf.constants;

public enum ExpressionEnum {
	dot("."), 
	separator("/"),
	dollar("$"),
	openBracket("["),
	closeBracket("]"),
	equal("="),
	comma(","),
	xml("xml"),
	xmlFilePre("/"), 
	themeXmlName("ThemeConf.xml"),
	location("Location");
	
	private String expr;
	private ExpressionEnum(String expr){
		this.expr = expr;
	}
	
	public String getExpr(){
		return expr;
	}
}
