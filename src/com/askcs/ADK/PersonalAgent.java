package com.askcs.ADK;

import java.util.ArrayList;

import com.askcs.ADK.lib.Logger;
import com.askcs.ADK.lib.SessionHandler;

import com.askcs.webservices.AskPortType;
import com.askcs.webservices.StringArray;
import com.askcs.webservices.StringArrayResponse;

public class PersonalAgent {
	
	protected String uuid="";
	protected String name="";
	
	protected SessionHandler sh;
	protected AskPortType askport;
	protected Logger logger;

	public PersonalAgent(String uuid, String name, SessionHandler sh) {
		this.uuid=uuid;
		this.name=name;
		
		this.sh=sh;
		
		askport = sh.getAskPort();
		logger = new Logger();
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<AskatarMessage> getMessages(){
		logger.log("Start collection messages");
		StringArrayResponse res = askport.getNodeMembersByTag(sh.getSessionId(), uuid, "askatarMessages");
		if(res.getError()==0){
			String uuid = res.getResult().getString().get(0);
			StringArrayResponse resMes = askport.getNodeMembers(sh.getSessionId(), uuid);
			StringArray messages = resMes.getResult();
			
			ArrayList<AskatarMessage> messageList = new ArrayList<AskatarMessage>();
			for(String messageUUID : messages.getString()){
				messageList.add(new AskatarMessage(messageUUID, sh));
			}
			logger.log("Returning "+messageList.size()+" messages");
			return messageList;
		}
		
		return null;
	}

}
