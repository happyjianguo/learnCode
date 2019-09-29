package cn.com.jansh.model.wxdecode;

public class FuncScope {
	
	private FuncScope_Category funcscope_category;
	
	public FuncScope() {
	}

	public FuncScope_Category getFuncscope_category() {
		return funcscope_category;
	}

	public void setFuncscope_category(FuncScope_Category funcscope_category) {
		this.funcscope_category = funcscope_category;
	}

	@Override
	public String toString() {
		return "[funcscope_category=" + funcscope_category + "]";
	}

	
}
