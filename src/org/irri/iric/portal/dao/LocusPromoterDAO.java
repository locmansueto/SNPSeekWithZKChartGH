package org.irri.iric.portal.dao;

import java.util.Collection;
import java.util.Set;

import org.irri.iric.portal.domain.Locus;
import org.irri.iric.portal.domain.Position;

public interface LocusPromoterDAO extends LocusDAO {
	
	public static int PROMOTER_PLANTPROMDB_PRED=1;
	public static int PROMOTER_FGENESH=2;
	public static int PROMOTER_PLANTPROMDB_VER=3;
	
	public Set<Locus> getLociWithPromoters(String contig, Collection posset, int db, String organism, Integer plusminus, String genemodel);

}
