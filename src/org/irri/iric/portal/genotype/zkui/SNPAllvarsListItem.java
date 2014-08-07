package org.irri.iric.portal.genotype.zkui;

public class SNPAllvarsListItem {
	private String varname;
	private Long mismatches;
	private java.util.Map<Long,Character> pos2nuc;
	
	
	public String getVarname() {
		return varname;
	}
	public void setVarname(String varname) {
		this.varname = varname;
	}
	public Long getMismatches() {
		return mismatches;
	}
	public void setMismatches(Long mismatches) {
		this.mismatches = mismatches;
	}
	public java.util.Map<Long, Character> getPos2nuc() {
		return pos2nuc;
	}
	public void setPos2nuc(java.util.Map<Long, Character> pos2nuc) {
		this.pos2nuc = pos2nuc;
	}
	
	
	
	
}
