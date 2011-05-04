package com.askcs.factory;

import java.util.List;

import com.askcs.ADK.PersonalAgent;
import com.askcs.ADK.lib.ErrorHandler;
import com.askcs.ADK.lib.Logger;
import com.askcs.ADK.lib.SessionHandler;

import cs.ask.com.Ask;
import cs.ask.com.AskPortType;
import cs.ask.com.StringArrayResponse;
import cs.ask.com.StringResponse;
import cs.ask.com.Tuple;
import cs.ask.com.TupleArray;
import cs.ask.com.TupleArrayResponse;

public class PersonalAgentFactory {

	protected Logger logger;
	protected SessionHandler sh;
	protected ErrorHandler err;
	
	protected AskPortType askport;
	
	public PersonalAgentFactory() {
		// TODO Auto-generated constructor stub
		sh = new SessionHandler();
		err = new ErrorHandler(sh.getAskport());
		askport = sh.getAskport();
	}
	
	public PersonalAgent createPersonalAgent(String name){
		return createPersonalAgent(name, "");		
	}
	
	public PersonalAgent createPersonalAgent(String name, String uuid){
		
		TupleArray data = new TupleArray();
		Tuple tuple = new Tuple();
		tuple.setName("name");
		tuple.setValue(name);
		data.getTuple().add(tuple);
		StringResponse res=askport.createNode(sh.getSessionId(), uuid, data);
		if(res.getError()==0){
			return new PersonalAgent(res.getResult(), name, sh);
		} else
			err.printErrorMessage(res.getError(), "createNode");
		
		return null;
	}
	
	public PersonalAgent getPersonalAgentById(String uuid){
		
		TupleArrayResponse res = askport.getNodeData(sh.getSessionId(), uuid);
		
		if(res.getError()==0){
			return new PersonalAgent(uuid, res.getResult().getTuple().get(0).getValue(),sh);
		} else
			err.printErrorMessage(res.getError(), "getNodeData");
		
		return null;
	}

}
