package org.irri.iric.portal.genotype.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.domain.Variety;
import org.irri.iric.portal.genotype.dao.Variety3kDAO;
import org.irri.iric.portal.genotype.domain.Variety3k;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for Variety3k entities
 * 
 */

@Service("Variety3kService")
@Transactional
public class Variety3kServiceImpl implements Variety3kService {

	/**
	 * DAO injected by Spring that manages Variety3k entities
	 * 
	 */
	@Autowired
	private Variety3kDAO variety3kDAO;

	/**
	 * Instantiates a new Variety3kServiceImpl.
	 *
	 */
	public Variety3kServiceImpl() {
	}

	/**
	 * Return a count of all Variety3k entity
	 * 
	 */
	@Transactional
	public Integer countVariety3ks() {
		return ((Long) variety3kDAO.createQuerySingleResult("select count(o) from Variety3k o").getSingleResult()).intValue();
	}

	/**
	 * Load an existing Variety3k entity
	 * 
	 */
	@Transactional
	public Set<Variety3k> loadVariety3ks() {
		return variety3kDAO.findAllVariety3ks();
	}

	/**
	 * Save an existing Variety3k entity
	 * 
	 */
	@Transactional
	public void saveVariety3k(Variety3k variety3k) {
		Variety3k existingVariety3k = variety3kDAO.findVariety3kByPrimaryKey(variety3k.getVarname());

		if (existingVariety3k != null) {
			if (existingVariety3k != variety3k) {
				existingVariety3k.setVarname(variety3k.getVarname());
			}
			variety3k = variety3kDAO.store(existingVariety3k);
		} else {
			variety3k = variety3kDAO.store(variety3k);
		}
		variety3kDAO.flush();
	}

	/**
	 * Delete an existing Variety3k entity
	 * 
	 */
	@Transactional
	public void deleteVariety3k(Variety3k variety3k) {
		variety3kDAO.remove(variety3k);
		variety3kDAO.flush();
	}

	/**
	 * Return all Variety3k entity
	 * 
	 */
	@Transactional
	public List<Variety3k> findAllVariety3ks(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Variety3k>(variety3kDAO.findAllVariety3ks(startResult, maxRows));
	}

	/**
	 */
	@Transactional
	public Variety3k findVariety3kByPrimaryKey(String varname) {
		return variety3kDAO.findVariety3kByPrimaryKey(varname);
	}

	@Override
	public Set findAllVariety() {
		// TODO Auto-generated method stub
		return 	variety3kDAO.findAllVariety3ks() ;
	}

	@Override
	public Set findAllVarietyByCountry(String country) {
		// TODO Auto-generated method stub
		return null ;
	}

	@Override
	public Set findAllVarietyBySubpopulation(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set findAllVarietyByExample(Variety germplasm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Variety findVarietyByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Variety findVarietykByIrisId(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Variety findVarietykById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
