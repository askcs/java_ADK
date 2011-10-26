package com.askcs.ADK.lib;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.askcs.webservices.Ask;
import com.askcs.webservices.AskPortType;
import com.askcs.webservices.ReconnectingAskPort;
import com.askcs.webservices.StringResponse;
import javax.xml.namespace.QName;

public class SessionHandler {
	
	private AskPortType fAskPort = null;
	private String fEndPoint="";
	private String fAuthKey="";
	private String fSessionId="";
	
	private MessageDigest fDigest;
	private SecureRandom fRandom;

	/*
	static private SessionHandler gHandler;
	static public SessionHandler getHandler() {
		return gHandler;
	}
	*/
	
	synchronized boolean connect( String endpoint, String authKey )
	{
		URL url = null;
		try {
			url = new URL(com.askcs.webservices.Ask.class.getResource("."),endpoint);
		} catch(MalformedURLException e){
		}
		Ask ask=new Ask(url,new QName("urn:webservices.askcs.com", "Ask"));
		fAskPort = new ReconnectingAskPort(ask.getAskPort());
		if (! fAuthKey.equals( authKey ) || ! fSessionId.equals( "" ) ){
			fEndPoint = endpoint;
			fAuthKey = authKey;
			startSession();
		}
		
		try {
			fDigest = MessageDigest.getInstance( "SHA-256" );
		} catch ( NoSuchAlgorithmException x ) {
			try {
				fDigest = MessageDigest.getInstance( "MD5" );
				System.out.println( "No SHA256 algo available, falling back on MD5(!)" );
			} catch ( NoSuchAlgorithmException xx ) {
				throw new RuntimeException( "Neither SHA256 nor MD5 algo available" );
			}
		}
		
		fRandom = new SecureRandom();
		
		return true;
	}
	
	public SessionHandler(String endpoint, String authKey)
	{
		 synchronized ( SessionHandler.class ) {
			if ( fAskPort == null ) {
				connect(endpoint,authKey);
			} else {
				if ( ! fEndPoint.equals( endpoint ) || ! fAuthKey.equals( authKey ) ) {
					throw new RuntimeException( "Already had a session with different params!?" );
				}
			}
		}
	}
	
	static public final String bytesToHex( byte[] src ) {
		BigInteger i = new BigInteger( 1, src );
	    String s = i.toString( 16 );
	    if ( (s.length() & 1) != 0 ) {
	        s = "0" + s;
	    }
	    return s;
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
	
	public byte[] getDigest( byte[] src ) {
		fDigest.reset();
		return fDigest.digest( src );
	}
	
	public byte[] getRandom( int len ) {
		byte[] b = new byte[ len ];
		fRandom.nextBytes( b);
		return b;
	}

	
	public void printErrorMessage(int errorId, String functionName){
		StringResponse res = fAskPort.getErrorMessage(errorId, functionName);
		String error = res.getResult();
		System.err.println(error + " (" + errorId + ")");
	}
	
	public void printErrorMessage(int errorId, String functionName, String message){
		StringResponse res = fAskPort.getErrorMessage(errorId, functionName);
		String error = res.getResult();
		System.err.println(message + " " + error + " (" + errorId + ")");
	}
	
}
