package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.SendFileService;
import com.poka.app.anno.enity.SendFile;
import com.poka.app.util.PokaDateUtil;

@Component
public class SendFileBusiness {

	Logger logger = Logger.getLogger(SendFileBusiness.class);

	private SendFileService sendFileService;
	
	@Autowired
	@Qualifier("sendFileService")
	public void setSendFileService(SendFileService sendFileService) {
		this.sendFileService = sendFileService;
	}
	
	public boolean getSendFile(List<SendFile> sendFileListData){
		if(null!=sendFileListData&&sendFileListData.size()>0){
			for(SendFile sendFile:sendFileListData){
				sendFileService.save(sendFile);
			}
		}
		logger.info("下发到的文件数据同步成功...**"+PokaDateUtil.getNow()+"**");
		logger.info("共计"+sendFileListData.size()+"条.");
		return true;
	}
}
