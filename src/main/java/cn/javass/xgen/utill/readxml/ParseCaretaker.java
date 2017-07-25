package cn.javass.xgen.utill.readxml;

public class ParseCaretaker {
	private static ParseCaretaker taker = new ParseCaretaker();
	private ParseMedento medento = null;
	
	private ParseCaretaker(){}
	
	public static ParseCaretaker getInstance(){
		return taker;
	}
	
	public void saveMedento (ParseMedento medento){
		this.medento = medento;
	}
	
	public ParseMedento retriveMedento (){
		return this.medento;
	}
}
