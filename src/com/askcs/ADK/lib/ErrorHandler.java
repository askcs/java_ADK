package com.askcs.ADK.lib;

import cs.ask.com.Ask;
import cs.ask.com.AskPortType;
import cs.ask.com.StringResponse;

public class ErrorHandler {

	protected SessionHandler sh;
	protected AskPortType askport;
	
	public ErrorHandler() {
		// TODO Auto-generated constructor stub
		sh = new SessionHandler();
		askport = sh.getAskport();
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
