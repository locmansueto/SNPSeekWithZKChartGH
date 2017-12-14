package org.irri.iric.portal.domain;

import org.irri.iric.portal.AppContext;

public class TextSearchOptions {

	// text string is a regular expression
	private boolean isRegex=false;
	
	// text string should not be prefixed/suffixed with an alphanumeric character
	private boolean isWholeWord=true;
	
	// word is exact, case insensitive
	private boolean isExact=false;

	private String text="";
	
	
	public TextSearchOptions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TextSearchOptions(String text) {
		super();
		// TODO Auto-generated constructor stub
		this.text=text.trim();
		
	}

	public TextSearchOptions(String text, boolean isRegex, boolean isWholeWord, boolean isExact) {
		super();
		this.isRegex = isRegex;
		this.isWholeWord = isWholeWord;
		this.text=text.trim();
		this.isExact=isExact;
	}

	public boolean isRegex() {
		return isRegex;
	}

	public boolean isWholeWord() {
		return isWholeWord;
	}

	public String getText() {
		if(isRegex())
			return text; //.replace("\\", "\\\\"); //.replace(".", "\\.").replace("\\-", "\\-");
		else if(isWholeWord()) {
			if(AppContext.isOracle())
				return  "(^|\\W)" + text +"($|\\W)";
			else 
				return "\\y" + text  + "\\y"; 
		}
		return text;
		
	}

	public boolean isExact() {
		return isExact;
	}
	
	

	
}
