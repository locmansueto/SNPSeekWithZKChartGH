package org.irri.iric.portal.domain;

import java.math.BigDecimal;

public class Snps2VarsImpl implements Snps2Vars {

	private BigDecimal snpFeatureId;
	private BigDecimal var1;
	private BigDecimal var2;
	private Integer chr;
	private BigDecimal pos;
	private String refnuc;
	private String var1nuc;
	private String var2nuc;
	private String var1nuc2;
	private String var2nuc2;
	
	private boolean var1Nonsyn;
	private boolean var2Nonsyn;
	
	
	public Snps2VarsImpl(BigDecimal snpFeatureId, BigDecimal var1,
			BigDecimal var2, Integer chr, BigDecimal pos, String refnuc,
			String var1nuc, String var2nuc) {
		super();
		this.snpFeatureId = snpFeatureId;
		this.var1 = var1;
		this.var2 = var2;
		this.chr = chr;
		this.pos = pos;
		this.refnuc = refnuc;
		this.var1nuc = var1nuc;
		this.var2nuc = var2nuc;
	}

	public Snps2VarsImpl(BigDecimal snpFeatureId, BigDecimal var1,
			BigDecimal var2, Integer chr, BigDecimal pos, String refnuc,
			String var1nuc, String var2nuc, String var1nuc2, String var2nuc2) {
		super();
		this.snpFeatureId = snpFeatureId;
		this.var1 = var1;
		this.var2 = var2;
		this.chr = chr;
		this.pos = pos;
		this.refnuc = refnuc;
		this.var1nuc = var1nuc;
		this.var2nuc = var2nuc;
		this.var1nuc2 = var1nuc2;
		this.var2nuc2 = var2nuc2;
	}
	
	
	

	public Snps2VarsImpl(BigDecimal snpFeatureId, BigDecimal var1,
			BigDecimal var2, Integer chr, BigDecimal pos, String refnuc,
			String var1nuc, String var2nuc, String var1nuc2, String var2nuc2,
			Boolean var1Nonsyn, Boolean var2Nonsyn) {
		super();
		this.snpFeatureId = snpFeatureId;
		this.var1 = var1;
		this.var2 = var2;
		this.chr = chr;
		this.pos = pos;
		this.refnuc = refnuc;
		this.var1nuc = var1nuc;
		this.var2nuc = var2nuc;
		this.var1nuc2 = var1nuc2;
		this.var2nuc2 = var2nuc2;
		this.var1Nonsyn = var1Nonsyn;
		this.var2Nonsyn = var2Nonsyn;
	}

	public Snps2VarsImpl(BigDecimal snpFeatureId, BigDecimal var1,
			BigDecimal var2, Integer chr, BigDecimal pos, String refnuc,
			Character var1nuc, Character var2nuc, Character var1nuc2, Character var2nuc2) {
		super();
		this.snpFeatureId = snpFeatureId;
		this.var1 = var1;
		this.var2 = var2;
		this.chr = chr;
		this.pos = pos;
		this.refnuc = refnuc;
		
		if(var1nuc==null || var1nuc=='0' || var1nuc=='.')
		this.var1nuc = "";
		else this.var1nuc = var1nuc.toString();
		
		if(var2nuc==null || var2nuc=='0' || var2nuc=='.')
		this.var2nuc = "";
		else this.var2nuc = var2nuc.toString();

		if(var1nuc2==null)
		this.var1nuc2 = "";
		else this.var1nuc2 = var1nuc2.toString();

		if(var2nuc2==null)
		this.var2nuc2 = "";
		else this.var2nuc2 = var2nuc2.toString();
		
	}
	

	public Snps2VarsImpl(BigDecimal snpFeatureId, BigDecimal var1,
			BigDecimal var2, Integer chr, BigDecimal pos, String refnuc,
			Character var1nuc, Character var2nuc, Character var1nuc2, Character var2nuc2, Boolean var1Nonsyn, Boolean var2Nonsyn) {
		super();
		this.snpFeatureId = snpFeatureId;
		this.var1 = var1;
		this.var2 = var2;
		this.chr = chr;
		this.pos = pos;
		this.refnuc = refnuc;
		
		this.var1Nonsyn = var1Nonsyn;
		this.var2Nonsyn = var2Nonsyn;
		
		
		if(var1nuc==null || var1nuc=='0' || var1nuc=='.')
		this.var1nuc = "";
		else this.var1nuc = var1nuc.toString();
		
		if(var2nuc==null || var2nuc=='0' || var2nuc=='.')
		this.var2nuc = "";
		else this.var2nuc = var2nuc.toString();

		if(var1nuc2==null)
		this.var1nuc2 = "";
		else this.var1nuc2 = var1nuc2.toString();

		if(var2nuc2==null)
		this.var2nuc2 = "";
		else this.var2nuc2 = var2nuc2.toString();
		
	}

	
	@Override
	public BigDecimal getVar1() {
		// TODO Auto-generated method stub
		return var1;
	}

	@Override
	public BigDecimal getVar2() {
		// TODO Auto-generated method stub
		return var2;
	}

	@Override
	public BigDecimal getSnpFeatureId() {
		// TODO Auto-generated method stub
		return snpFeatureId;
	}

	@Override
	public Integer getChr() {
		// TODO Auto-generated method stub
		return chr;
	}

	@Override
	public BigDecimal getPos() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public String getRefnuc() {
		// TODO Auto-generated method stub
		return refnuc;
	}

	@Override
	public String getVar1nuc() {
		// TODO Auto-generated method stub
		return var1nuc;
	}

	@Override
	public String getVar2nuc() {
		// TODO Auto-generated method stub
		return var2nuc;
	}

	@Override
	public String getVar1nuc2() {
		// TODO Auto-generated method stub
		return var1nuc2;
	}

	@Override
	public String getVar2nuc2() {
		// TODO Auto-generated method stub
		return var2nuc2;
	}

	public boolean isVar1Nonsyn() {
		return var1Nonsyn;
	}

	public boolean isVar2Nonsyn() {
		return var2Nonsyn;
	}

	
	
	
	
}
