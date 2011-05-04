package com.askcs.ADK.lib;

import cs.ask.com.Ask;
import cs.ask.com.AskPortType;
import cs.ask.com.StringResponse;

public class SessionHandler {

	protected String wsdl="";
	protected String authKey="";
	
	protected static String sessionId="";
	
	protected AskPortType askport;
	
	public SessionHandler() {
		// TODO Auto-generated constructor stub
		this.authKey="b8c4c76e-75fd-102e-bf75-005056bc3799";
		
		Ask ask = new Ask();
		askport = ask.getAskPort();
		
		if(sessionId=="")
			this.startSession();
	}
	
	protected void startSession(){
		StringResponse res = askport.startSession("b8c4c76e-75fd-102e-bf75-005056bc3799");
		if(res.getError()==0){
			this.sessionId=res.getResult();
		}		
	}

	public String getSessionId(){
		return this.sessionId;		
	}
}
