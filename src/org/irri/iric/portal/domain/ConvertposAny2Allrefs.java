package org.irri.iric.portal.domain;

import java.io.Serializable;
import java.math.BigDecimal;

//import org.irri.iric.portal.genotype.zkui.VConvertposAny2allrefs;

public interface ConvertposAny2Allrefs extends Serializable, SnpsAllvarsMultirefsPos {

	public BigDecimal getNbPosition();

	public BigDecimal getNbContigId();

	public String getNBContigName();

	public String getNbRefcall();

}
