package com.askcs.ADK.lib;

import com.askcs.webservices.AskPortType;
import com.askcs.webservices.StringResponse;

public class ErrorHandler {

	protected SessionHandler sh;
	protected AskPortType askport;
	
	public ErrorHandler() {
		askport = SessionHandler.getHandler().getAskPort();
	}
	public ErrorHandler(AskPortType askport) {
		this.askport = askport;
	}

	public void printErrorMessage(int errorId, String functionName){
		StringResponse res = askport.getErrorMessage(errorId, functionName);
		String error = res.getResult();
		System.err.println(error + " (" + errorId + ")");
	}
	
	public void printErrorMessage(int errorId, String functionName, String message){
		StringResponse res = askport.getErrorMessage(errorId, functionName);
		String error = res.getResult();
		System.err.println(message + " " + error + " (" + errorId + ")");
	}
}
