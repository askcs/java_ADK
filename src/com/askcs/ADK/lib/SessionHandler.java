package com.askcs.ADK.lib;

import java.net.MalformedURLException;
import java.net.URL;

import com.askcs.webservices.Ask;
import com.askcs.webservices.AskPortType;
import com.askcs.webservices.StringResponse;
import javax.xml.namespace.QName;

public class SessionHandler {
	
	static private SessionHandler gHandler;
	static public SessionHandler getHandler() {
		return gHandler;
	}
	static public SessionHandler createHandler( String endpoint, String authKey ) {
		synchronized ( SessionHandler.class ) {
			if ( gHandler == null ) {
				gHandler = new SessionHandler( endpoint, authKey );
			} else {
				if ( gHandler.fEndPoint != endpoint || gHandler.fAuthKey != authKey ) {
					throw new RuntimeException( "Already had a session with different params!?" );
				}
			}
			return gHandler;
		}
	}
	
	
	private AskPortType fAskPort = null;
	private String fEndPoint="";
	private String fAuthKey="";
	private String fSessionId="";
		

	private SessionHandler(String endpoint, String authKey){
		URL url = null;
		try {
			url = new URL(com.askcs.webservices.Ask.class.getResource("."),endpoint);
		} catch(MalformedURLException e){
		}
		Ask ask=new Ask(url,new QName("urn:webservices.askcs.com", "Ask"));
		fAskPort = ask.getAskPort();
		if (fAuthKey != authKey || fSessionId != ""){
			fEndPoint = endpoint;
			fAuthKey = authKey;
			startSession();
		}
	}
	
	protected boolean startSession(){
		StringResponse res = fAskPort.startSession(fAuthKey);
		if(res.getError()==0){
			fSessionId=res.getResult();
			return true;
		} else {
			return false;
		}
	}
	
	public AskPortType getAskPort(){
		return fAskPort;
	}
	public String getSessionId() {
		return fSessionId;
	}
}


