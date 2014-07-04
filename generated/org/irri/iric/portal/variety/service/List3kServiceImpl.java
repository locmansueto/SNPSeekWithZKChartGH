package org.irri.iric.portal.variety.service;

import java.util.List;
import java.util.Set;

import org.irri.iric.portal.variety.dao.List3kDAO;

import org.irri.iric.portal.variety.domain.List3k;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring service that handles CRUD requests for List3k entities
 * 
 */

@Service("List3kService")
@Transactional
public class List3kServiceImpl implements List3kService {

	/**
	 * DAO injected by Spring that manages List3k entities
	 * 
	 */
	@Autowired
	private List3kDAO list3kDAO;

	/**
	 * Instantiates a new List3kServiceImpl.
	 *
	 */
	public List3kServiceImpl() {
	}

	/**
	 * Return all List3k entity
	 * 
	 */
	@Transactional
	public List<List3k> findAllList3ks(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<List3k>(list3kDAO.findAllList3ks(startResult, maxRows));
	}

	/**
	 * Delete an existing List3k entity
	 * 
	 */
	@Transactional
	public void deleteList3k(List3k list3k) {
		list3kDAO.remove(list3k);
		list3kDAO.flush();
	}

	/**
	 */
	@Transactional
	public List3k findList3kByPrimaryKey(String irisUniqueId) {
		return list3kDAO.findList3kByPrimaryKey(irisUniqueId);
	}

	/**
	 * Save an existing List3k entity
	 * 
	 */
	@Transactional
	public void saveList3k(List3k list3k) {
		List3k existingList3k = list3kDAO.findList3kByPrimaryKey(list3k.getIrisUniqueId());

		if (existingList3k != null) {
			if (existingList3k != list3k) {
				existingList3k.setSortCode(list3k.getSortCode());
				existingList3k.setBoxPositionCode(list3k.getBoxPositionCode());
				existingList3k.setGidForDna(list3k.getGidForDna());
				existingList3k.setIrisUniqueId(list3k.getIrisUniqueId());
				existingList3k.setDesignationDnaSample(list3k.getDesignationDnaSample());
				existingList3k.setGeneticStockForDna(list3k.getGeneticStockForDna());
				existingList3k.setVarnameOfGenStockSrc(list3k.getVarnameOfGenStockSrc());
				existingList3k.setAccessionOfGenStockSource(list3k.getAccessionOfGenStockSource());
				existingList3k.setAltAccessOfGenStockSrc(list3k.getAltAccessOfGenStockSrc());
				existingList3k.setCountryOfOriginOfSource(list3k.getCountryOfOriginOfSource());
				existingList3k.setVarietygroupOfSource(list3k.getVarietygroupOfSource());
				existingList3k.setInternalRpocessingSet(list3k.getInternalRpocessingSet());
				existingList3k.setBgiShipment1(list3k.getBgiShipment1());
				existingList3k.setQa(list3k.getQa());
				existingList3k.setLev(list3k.getLev());
				existingList3k.setBgiShipment2(list3k.getBgiShipment2());
				existingList3k.setOverlapAffyBgi(list3k.getOverlapAffyBgi());
				existingList3k.setDuplication(list3k.getDuplication());
				existingList3k.setOrigId(list3k.getOrigId());
				existingList3k.setFundingSrc(list3k.getFundingSrc());
				existingList3k.setSequencing(list3k.getSequencing());
				existingList3k.setListName(list3k.getListName());
				existingList3k.setEntryCode(list3k.getEntryCode());
				existingList3k.setTenkSelect200(list3k.getTenkSelect200());
				existingList3k.setIsSequenced(list3k.getIsSequenced());
				existingList3k.setIsDataAvailable(list3k.getIsDataAvailable());
				existingList3k.setIsClustered(list3k.getIsClustered());
			}
			list3k = list3kDAO.store(existingList3k);
		} else {
			list3k = list3kDAO.store(list3k);
		}
		list3kDAO.flush();
	}

	/**
	 * Return a count of all List3k entity
	 * 
	 */
	@Transactional
	public Integer countList3ks() {
		return ((Long) list3kDAO.createQuerySingleResult("select count(o) from List3k o").getSingleResult()).intValue();
	}

	/**
	 * Load an existing List3k entity
	 * 
	 */
	@Transactional
	public Set<List3k> loadList3ks() {
		return list3kDAO.findAllList3ks();
	}
}
