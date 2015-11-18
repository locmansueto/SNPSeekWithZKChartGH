package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Container entity for SNP position
 * @author lmansueto
 *
 */
public interface SnpsAllvarsPos extends Position {

	
		/**
		 * Position
		 * @return
		 */
		public BigDecimal getPos();
		
		/**
		 * Reference nucleotide
		 * @return
		 */
		public String getRefnuc();
		
		public void setRefnuc(String refnuc);
		
		/**
		 * Contig name
		 */
		public String getContig();
		
		/**
		 * column index of position in HDF5 matrix 
		 * @return
		 */
		public BigDecimal getAlleleIndex();
		
		/**
		 * SNP feature ID
		 * @return
		 */
		public BigDecimal getSnpFeatureId();
}
