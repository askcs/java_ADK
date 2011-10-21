package com.askcs.ADK.lib;

import java.net.MalformedURLException;
import java.net.URL;

import com.askcs.soaptoid.Soaptoid;
import com.askcs.soaptoid.SoaptoidPortType;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;

public class SoaptoidHandler {
	private SoaptoidPortType fSoaptoidPort = null;
	private int fSessionID = 0;
	
	private boolean __startSession(String initString, String okString, Holder<String> answer) {
		Holder<Integer> oSessionID = new Holder<Integer>();
		fSoaptoidPort.doCall(0, initString, oSessionID, answer);
		if (oSessionID.value > 0 && answer.value.equals(okString)) {
			fSessionID = oSessionID.value;
			return true;
		}
		return false;
	}
	
	public SoaptoidHandler(String endPoint, String initString, String okString, Holder<String> answer) {
		URL url = null;
		try {
			url = new URL(Soaptoid.class.getResource("."), endPoint);
		} catch(MalformedURLException e){
		}
		Soaptoid soaptoid = new Soaptoid(url, new QName("urn:soaptoid", "soaptoid"));
		fSoaptoidPort = soaptoid.getSoaptoid();
		fSessionID = 0;
		__startSession(initString, okString, answer);
	}
	
	public boolean doSoaptoidCall(String question, Holder<String> answer) {
		Holder<Integer> oSessionID = new Holder<Integer>();
		Holder<String> oAnswer = new Holder<String>();
		oSessionID.value = 0;
		answer.value = "FAILURE";
		
		if (fSessionID > 0) {
			fSoaptoidPort.doCall(fSessionID, question, oSessionID, oAnswer);
			answer.value = oAnswer.value;
			if (oSessionID.value > 0 && oSessionID.value == fSessionID) {
				return true;
			}
		}
		return false;
	}
	
	public int getSessionID() {
		return fSessionID;
	}
}