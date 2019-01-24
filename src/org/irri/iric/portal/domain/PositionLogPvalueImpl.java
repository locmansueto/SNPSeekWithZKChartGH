package org.irri.iric.portal.domain;

import java.math.BigDecimal;

import org.irri.iric.portal.AppContext;

public class PositionLogPvalueImpl extends PositionImpl implements PositionLogPvalue {

	private Double logpvalue;

	public PositionLogPvalueImpl(String contig, BigDecimal position, Integer chr, Double minuslogp) {
		super(contig, position, null, chr);
		logpvalue = minuslogp;
	}

	@Override
	public Double getMinusLogPvalue() {

		return logpvalue;
	}

	@Override
	public void setMinusLogPvalue(Double pval) {

		logpvalue = pval;
	}

	@Override
	public String toString() {

		if (super.getRefnuc() == null)
			if (logpvalue == null)
				return "(" + contig + "," + position + ")";
			else
				return "(" + contig + "," + position + "," + AppContext.decf.format(logpvalue) + ")";
		else {
			if (logpvalue == null)
				return "(" + contig + "," + position + "," + super.getRefnuc() + ")";
			else
				return "(" + contig + "," + position + "," + super.getRefnuc() + "," + AppContext.decf.format(logpvalue)
						+ ")";
		}

	}

}
