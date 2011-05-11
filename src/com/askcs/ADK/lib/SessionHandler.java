package com.askcs.ADK.lib;

import java.net.MalformedURLException;
import java.net.URL;

import com.askcs.webservices.Ask;
import com.askcs.webservices.AskPortType;
import com.askcs.webservices.StringResponse;
import javax.xml.namespace.QName;

public class SessionHandler {
	private static AskPortType askport = null;
	private static String authKey="";
	private static String sessionId="";
		

	public SessionHandler() {
		if (SessionHandler.askport == null) return;
		if(sessionId=="")
			startSession();
	}
	
	public SessionHandler(String endpoint, String authKey){
		URL url = null;
		try {
			url = new URL(com.askcs.webservices.Ask.class.getResource("."),"http://ask-dev.customers.luna.net/~ludo/ludoDev/webservices/index.php?wsdl");
		} catch(MalformedURLException e){
		}
		Ask ask=new Ask(url,new QName("urn:webservices.askcs.com", "Ask"));
		SessionHandler.askport = ask.getAskPort();
		if (SessionHandler.authKey != authKey || SessionHandler.sessionId != ""){
			SessionHandler.authKey = authKey;
			startSession();
		}
	}
	
	protected static boolean startSession(){
		StringResponse res = askport.startSession(authKey);
		if(res.getError()==0){
			sessionId=res.getResult();
			return true;
		} else {
			return false;
		}
	}
	
	public static AskPortType getAskPort(){
		return askport;
	}
	public static String getSessionId() {
		return sessionId;
	}
}


