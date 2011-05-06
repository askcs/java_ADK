package com.askcs.ADK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.askcs.ADK.lib.ErrorHandler;
import com.askcs.ADK.lib.Logger;
import com.askcs.ADK.lib.SessionHandler;
import com.askcs.ADK.lib.WSLib;

import com.askcs.webservices.*;

public class PersonalAgent {
	
	protected String uuid="";
	protected String name="";
	
	protected SessionHandler sh;
	protected ErrorHandler err;
	protected AskPortType askport;
	protected Logger logger;
	
	protected static String askatarMessagesUUID;

	public PersonalAgent(String uuid, String name, SessionHandler sh) {
		this.uuid=uuid;
		this.name=name;
		
		this.sh=sh;
		
		askport = sh.getAskPort();
		err=new ErrorHandler(askport);
		logger = new Logger();
		
		checkAskatarMessages();
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<AskatarMessage> getMessages(){

		if(askatarMessagesUUID!=null) {
			
			StringArrayResponse resMes = askport.getNodeMembers(sh.getSessionId(), askatarMessagesUUID);
			StringArray messages = resMes.getResult();
			
			ArrayList<AskatarMessage> messageList = new ArrayList<AskatarMessage>();
			for(String messageUUID : messages.getString()){
				messageList.add(new AskatarMessage(messageUUID, this.sh));
			}
			return messageList;
		} else
			System.out.println("askatarMessagesUUID is not set!!");
		
		return null;
	}
	
	public boolean setTextResource(String tag, String value){
		
		ResourceDataResponse res = askport.getResourceDataByTag(sh.getSessionId(), uuid, tag, "TXT");
		if(res.getError()!=0){
			StringResponse resRes = askport.createResource(sh.getSessionId(), new String(), "TXT", uuid, tag, "Automatically added resource tagged "+tag, "", tag, value);
			if(resRes.getError()==0)
				return true;				
		} else {
			BoolResponse boolRes=askport.setResourceData(sh.getSessionId(), res.getResult().getResUUID(), uuid, tag, "Automatically added resource tagged "+tag, "", tag, value);
			return boolRes.isResult();				
		}
		return false;
	}
	
	public String addMessage(AskatarMessage message){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Message");

		StringResponse res=askport.createNode(sh.getSessionId(), message.getMessageUUID(), WSLib.convertMapToTupleArray(map));
		if(res.getError()==0){
			message.setMessageUUID(res.getResult());
			// TODO: add resources to the message
			if(message.insert()) {
				askport.attachNode(sh.getSessionId(), askatarMessagesUUID, message.getMessageUUID());
				return message.getMessageUUID();
			}
		}
		
		return "";
	}
	
	protected void checkAskatarMessages(){
		if(askatarMessagesUUID==null) {
			StringArrayResponse res = askport.getNodeMembersByTag(sh.getSessionId(), uuid, "askatarMessages");
			System.out.println("Number of nodes found: "+res.getResult().getString().size());
			if((res.getError()==0 && res.getResult().getString().size()==0) || res.getError()!=0){
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", uuid+"_askatarMessages");
				StringResponse res1=askport.createNode(sh.getSessionId(), "", WSLib.convertMapToTupleArray(map));
				if(res1.getError()!=0){
					err.printErrorMessage(res1.getError(), "createNode");
				}
				askatarMessagesUUID = res1.getResult();
				askport.attachNode(sh.getSessionId(), this.uuid, askatarMessagesUUID);
			} else {
				askatarMessagesUUID = res.getResult().getString().get(0);
			}
		}
	}

}
