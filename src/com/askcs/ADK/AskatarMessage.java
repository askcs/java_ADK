package com.askcs.ADK;

import com.askcs.ADK.lib.ErrorHandler;
import com.askcs.ADK.lib.SessionHandler;
import com.askcs.webservices.AskPortType;
import com.askcs.webservices.BoolResponse;
import com.askcs.webservices.ResourceDataResponse;
import com.askcs.webservices.StringResponse;

public class AskatarMessage {
	
	protected String messageUUID=null;
	protected String message="";
	protected String type="askMessage";
	protected String sender="";
	protected Boolean beenRead=false;
	protected Integer prio=0;
	
	protected SessionHandler sh;
	protected ErrorHandler err;
	protected AskPortType askport;
	
	public AskatarMessage(){ //Needed for flexJson
		sh=new SessionHandler();
		askport=sh.getAskPort();
		err=new ErrorHandler(askport);
	}
	public AskatarMessage(String messageUUID,String message, String type, String sender, Integer prio, SessionHandler sh){
		this.messageUUID = messageUUID;
		this.message = message;
		this.type = type;
		this.beenRead = false;
		this.sender = sender;
		this.prio = prio;
		
		this.sh=sh;
		this.askport=sh.getAskPort();
	}	
	public AskatarMessage(String uuid, SessionHandler sh){
		ResourceDataResponse res;
		Boolean error=false;
		this.sh=sh;
		askport = sh.getAskPort();
		
		res = askport.getResourceDataByTag(sh.getSessionId(), uuid, "message", "TXT");
		if(res.getError()==0) this.message = res.getResult().getValue(); else error=true;
		res = askport.getResourceDataByTag(sh.getSessionId(), uuid, "type", "TXT");
		if(res.getError()==0) this.type = res.getResult().getValue(); else error=true;
		res = askport.getResourceDataByTag(sh.getSessionId(), uuid, "beenRead", "TXT");
		if(res.getError()==0 && res.getResult().getValue() != null) this.beenRead=true;
		res = askport.getResourceDataByTag(sh.getSessionId(), uuid, "sender", "TXT");
		if(res.getError()==0) this.sender = res.getResult().getValue(); else error=true;
		res = askport.getResourceDataByTag(sh.getSessionId(), uuid, "prio", "TXT");
		try {
			if(res.getError()==0) this.prio = new Integer(res.getResult().getValue()); else error=true;
		} catch (NumberFormatException e) { error=true; }

		if (!error) this.messageUUID = uuid;
	}
	
	private boolean setField(String tag,String value){
		ResourceDataResponse res;
		
		if(value.isEmpty())
			value="NULL";
		System.out.println("Session: "+sh.getSessionId()+", UUID: "+this.messageUUID+", Tag: "+tag+", Value: "+value);
		res = askport.getResourceDataByTag(sh.getSessionId(), this.messageUUID, tag, "TXT");
		if(res.getError()==0){
			BoolResponse boolRes = askport.setResourceData(sh.getSessionId(), res.getResult().getResUUID(), this.messageUUID, res.getResult().getName(), res.getResult().getDescription(), res.getResult().getCategory(), res.getResult().getTag(),value);
			if(boolRes.getError()!=0) {
				err.printErrorMessage(boolRes.getError(), "setResourceData");
				return false;
			}
			return boolRes.isResult();
		} else return false;
	}
	private boolean createField(String tag,String value){
		StringResponse res = askport.createResource(sh.getSessionId(), "", "TXT", this.messageUUID, "field "+tag, "From appservices", "Node "+this.messageUUID, tag, value);
		if(res.getError()!=0){
			err.printErrorMessage(res.getError(), "createResource");
			return false;
		}
		if(res.getResult()!=null)
			return true;
		return false;
	}
	private boolean deleteField(String tag){
		ResourceDataResponse res;
		res = askport.getResourceDataByTag(sh.getSessionId(), this.messageUUID, tag, "TXT");
		if(res.getError()==0 && res.getResult().getResUUID()!=null){
			return askport.removeResource(sh.getSessionId(), res.getResult().getResUUID()).isResult();	
		} else {
			return true; //already gone!
		}
		
	}
	private boolean setField(String tag,Boolean value){
		if (value.booleanValue()) return createField(tag,value.toString());
		return deleteField(tag);
	}
	private boolean setField(String tag,Integer value){
		return setField(tag,value.toString());
	}
		
	public boolean update(){
		Boolean noerror=true;
		if (noerror) noerror=setField("message",this.message);
		if (noerror) noerror=setField("type",this.type);
		if (noerror) noerror=setField("sender",this.sender);
		if (noerror) noerror=setField("prio",this.prio);
		if (noerror) noerror=setField("beenRead",this.beenRead);
		
		return noerror;
	}
	
	public boolean insert(){
		boolean noerror=true;
		if (noerror) noerror=createField("message",this.message);
		if (noerror) noerror=createField("type",this.type);
		if (noerror) noerror=createField("sender",this.sender);
		if (noerror) noerror=createField("prio",this.prio+"");
		if (noerror) noerror=createField("beenRead",this.beenRead+"");
		
		return noerror;
	}

	public String getMessageUUID() {
		return messageUUID;
	}

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}

	public String getSender() {
		return sender;
	}

	public Boolean getBeenRead() {
		return beenRead;
	}

	public Integer getPrio() {
		return prio.intValue();
	}

	public void setMessageUUID(String messageUUID) {
		this.messageUUID = messageUUID;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setBeenRead(Boolean beenRead) {
		this.beenRead = beenRead;
	}

	public void setPrio(Integer prio) {
		this.prio = prio;
	}
}
