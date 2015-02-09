package org.irri.iric.portal.domain;

import java.math.BigDecimal;

/**
 * Container entity for SNP position
 * @author lmansueto
 *
 */
public interface SnpsAllvarsPos {

	
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
}
