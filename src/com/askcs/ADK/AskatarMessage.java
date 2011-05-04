package com.askcs.ADK;

import com.askcs.ADK.lib.SessionHandler;
import com.askcs.webservices.AskPortType;
import com.askcs.webservices.ResourceDataResponse;

public class AskatarMessage {

	protected String message;
	protected String type;
	
	public AskatarMessage(String uuid, SessionHandler sh){
		
		AskPortType askport = sh.getAskPort();
		ResourceDataResponse res = askport.getResourceDataByTag(sh.getSessionId(), uuid, "message", "TXT");
		if(res.getError()==0){
			message = res.getResult().getValue();
		}
	}
	
	public AskatarMessage(String message, String type){
		
		this.message=message;
		this.type=type;
	}

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}
}
