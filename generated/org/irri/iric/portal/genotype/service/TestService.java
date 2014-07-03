package org.irri.iric.portal.genotype.service;

import org.springframework.stereotype.Service;

@Service("ITestService")
public class TestService implements ITestService {

	private String msg="Hello";

	/* (non-Javadoc)
	 * @see org.irri.iric.portal.genotype.service.ITestService#getMsg()
	 */
	@Override
	public String getMsg() {
		return msg;
	}

	/* (non-Javadoc)
	 * @see org.irri.iric.portal.genotype.service.ITestService#setMsg(java.lang.String)
	 */
	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
