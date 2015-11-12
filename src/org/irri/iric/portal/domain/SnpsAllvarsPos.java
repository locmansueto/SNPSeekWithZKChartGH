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
		
		public String getContig();
		
		public BigDecimal getAlleleIndex();
		
		public BigDecimal getSnpFeatureId();
}
