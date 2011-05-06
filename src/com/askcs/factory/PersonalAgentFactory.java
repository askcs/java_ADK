package com.askcs.factory;

import com.askcs.ADK.PersonalAgent;
import com.askcs.ADK.lib.ErrorHandler;
import com.askcs.ADK.lib.Logger;
import com.askcs.ADK.lib.SessionHandler;

import com.askcs.webservices.AskPortType;
import com.askcs.webservices.StringResponse;
import com.askcs.webservices.Tuple;
import com.askcs.webservices.TupleArray;
import com.askcs.webservices.TupleArrayResponse;

public class PersonalAgentFactory {

	protected Logger logger;
	protected SessionHandler sh;
	protected ErrorHandler err;
	
	protected AskPortType askport;
	
	public PersonalAgentFactory() {
		// TODO Auto-generated constructor stub
		sh = new SessionHandler();
		err = new ErrorHandler(sh.getAskPort());
		askport = sh.getAskPort();
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

			PersonalAgent agent=new PersonalAgent(res.getResult(), name, sh);
			agent.setTextResource("askatar", "");
			return agent;
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
	
	public SessionHandler getSh(){
		return this.sh;
	}

}
