package cn.javass.xgen.utill.readxml;

public abstract class ReadXmlExpression implements Cloneable{
	public abstract String[] interpret(Context context);

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone(){
		Object obj = null;
		try {
			obj = super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
