package org.irri.iric.portal.genotype.dao;

import org.springframework.stereotype.Repository;

@Repository("ITestDaoDAO")
public class TestDao implements ITestDao {

	private String msg;

	/* (non-Javadoc)
	 * @see org.irri.iric.portal.genotype.dao.ITestDao#getMsg()
	 */
	@Override
	public String getMsg() {
		return "Hello DAO";
	}

	/* (non-Javadoc)
	 * @see org.irri.iric.portal.genotype.dao.ITestDao#setMsg(java.lang.String)
	 */
	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
