package org.irri.iric.portal.genotype.zkui;

public interface SNPRowRendererStyle {

	public static String STYLE_HETERO = "font-weight:bold;color:brown;align:center";
	public static String STYLE_A = "color:green;align:center";
	public static String STYLE_T = "color:red;align:center";
	public static String STYLE_G = "color:orange;align:center";
	public static String STYLE_C = "color:blue;align:center";
	public static String STYLE_SYNONYMOUS = "font-weight:bold;color:lightgray;align:center";
	
	public static short COLOR_MISMATCH = 0;
	public static short COLOR_NUCLEOTIDE = 1;
	


	public static String STYLE_INTERESTING = "font-weight:bold;color:red;font-size:13;align:center";
	public static String STYLE_BORING =  "font-size:13;align:center";
	public static String STYLE_DIFFFROMREF = "font-weight:bold;color:red;align:center";
	public static String STYLE_HEADERNARROW = "font-weight:normal;color:black;background-color:lightgray;font-family:Arial Narrow;";
	public static String STYLE_HEADER = "font-weight:bold;color:black;background-color:lightgray;font-size:13;align:center";
	public static String STYLE_SAMEASREF = "";
}