package seleniumBasics;

public class DynamicXpath {
	
	// input format: //input[@id='{0}' and @class='{1}' and @type='{3}']
	public static String createXpath(String xpathExp, Object ...args) {
		
		for(int i=0; i<args.length; i++) {			
			xpathExp = xpathExp.replace("{" + i + "}", (CharSequence)args[i]);			
		}
		
		return xpathExp;
		
	}
	

	public static void main(String[] args) {
		
		// this is the xpath format that will be passed to the createXpath method
		String xpathExp = "//input[@id='{0}' and @type='{1}']";
		
		// we are getting val1 & val2 dynamically at the runtime from some API etc. 
		String val1 = "username";
		String val2 = "email";
		
		// dynamically created xpath will be stored into xpathExp variable
		xpathExp = createXpath(xpathExp, val1, val2);
		
		// print the dynamically created xpath
		System.out.println(xpathExp);
		
	}

}
