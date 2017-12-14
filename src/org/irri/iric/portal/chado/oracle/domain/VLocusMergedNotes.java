package org.irri.iric.portal.chado.oracle.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.irri.iric.portal.domain.MergedLoci;

@Entity(name="VLocusMergedNotes")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "iric_prod_crud/org/irri/iric/portal/chado/domain", name = "VLocusMergedNotes")
public class VLocusMergedNotes extends VLocusNotes implements MergedLoci {


	@Column(name = "IRIC")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String iric;

	@Column(name = "RAP_REP")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String rapRep;

	@Column(name = "MSU7")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String msu7;

	@Column(name = "RAP_PRED")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String rapPred;

	@Column(name = "FGENESH")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fgenesh;

	@Override
	public String getIRICName() {
		// TODO Auto-generated method stub
		return iric;
	}

	@Override
	public String getMSU7Name() {
		// TODO Auto-generated method stub
		return msu7;
	}

	@Override
	public String getRAPPredName() {
		// TODO Auto-generated method stub
		return rapPred;
	}

	@Override
	public String getRAPRepName() {
		// TODO Auto-generated method stub
		return rapRep;
	}

	@Override
	public String getFGeneshName() {
		// TODO Auto-generated method stub
		return fgenesh;
	}

	@Override
	public Set<String> getOverlappingGenes() {
		// TODO Auto-generated method stub
		
		StringBuffer buff=new StringBuffer();
		buff.append(  this.name );
		if(getMSU7Name()!=null && !getMSU7Name().isEmpty()) buff.append(" " + getMSU7Name());
		if(getRAPRepName()!=null && !getRAPRepName().isEmpty()) buff.append( " " + getRAPRepName());
		if(getRAPPredName()!=null && !getRAPPredName().isEmpty()) buff.append(" " + getRAPPredName());
	
		
		Set locnamesets=new HashSet();
		String names[]=buff.toString().trim().split("\\s+|,");
		for(int i=0; i<names.length; i++)
			locnamesets.add( names[i].trim() );
		locnamesets.remove("");
		return locnamesets;
		
	}
/*
	@Override
	public String printFields(String delimiter) {
		// TODO Auto-generated method stub=
		return getUniquename() + delimiter + getContig() + delimiter + getFmin() + delimiter + getFmax() + delimiter
				+ getStrand() + delimiter + getOverlappingGenes() + delimiter + getDescription();
	}
	*/
	
	
}
