package com.askcs.ADK;

import java.util.ArrayList;

import com.askcs.ADK.lib.SessionHandler;

import cs.ask.com.Ask;
import cs.ask.com.AskPortType;
import cs.ask.com.StringArray;
import cs.ask.com.StringArrayResponse;

public class PersonalAgent {
	
	protected String uuid="";
	protected String name="";
	
	protected SessionHandler sh;
	protected AskPortType askport;

	public PersonalAgent(String uuid, String name, SessionHandler sh) {
		this.uuid=uuid;
		this.name=name;
		
		this.sh=sh;
		askport = sh.getAskport();
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<AskatarMessage> getMessages(){
		StringArrayResponse res = askport.getNodeMembersByTag(sh.getSessionId(), uuid, "askatarMessages");
		if(res.getError()==0){
			String uuid = res.getResult().getString().get(0);
			StringArrayResponse resMes = askport.getNodeMembers(sh.getSessionId(), uuid);
			StringArray messages = resMes.getResult();
			
			ArrayList<AskatarMessage> messageList = new ArrayList<AskatarMessage>();
			for(String messageUUID : messages.getString()){
				messageList.add(new AskatarMessage(messageUUID, sh));
			}
			
			return messageList;
		}
		
		return null;
	}

}
