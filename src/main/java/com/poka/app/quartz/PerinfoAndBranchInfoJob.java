package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.PerInfoAndBranchInfoBussiness;

public class PerinfoAndBranchInfoJob {
	
	private PerInfoAndBranchInfoBussiness perInfoAndBranchInfoBussiness;
	
	@Autowired
	public void setPerInfoAndBranchInfoBussiness(PerInfoAndBranchInfoBussiness perInfoAndBranchInfoBussiness) {
		this.perInfoAndBranchInfoBussiness = perInfoAndBranchInfoBussiness;
	}

	public void work() {
		perInfoAndBranchInfoBussiness.SendPerinfoAndBranchInfo();
	}
	
}
