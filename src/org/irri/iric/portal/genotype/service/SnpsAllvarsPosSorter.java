package org.irri.iric.portal.genotype.service;

import java.util.Comparator;

import org.irri.iric.portal.domain.SnpsAllvarsPos;

public class SnpsAllvarsPosSorter implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		SnpsAllvarsPos pos1 = (SnpsAllvarsPos)o1;
		SnpsAllvarsPos pos2 = (SnpsAllvarsPos)o2;
		return pos1.getPos().compareTo(pos2.getPos());
	}
}