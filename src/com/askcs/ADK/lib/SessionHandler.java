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
	
	private ReconnectingAskPort fAskPort = null;
	private String fEndPoint="";
	private String fAuthKey="";
	
	private static MessageDigest fDigest;
	private final static SecureRandom fRandom = new SecureRandom();

	/*
	static private SessionHandler gHandler;
	static public SessionHandler getHandler() {
		return gHandler;
	}
	*/

	//run at first class instantiation
	static
	{
		try {
			fDigest = MessageDigest.getInstance( "SHA-256" );
			System.out.println("using SHA-256");
		} catch ( NoSuchAlgorithmException x ) {
			try {
				fDigest = MessageDigest.getInstance( "MD5" );
				System.out.println( "No SHA256 algo available, falling back on MD5(!)" );
			} catch ( NoSuchAlgorithmException xx ) {
				throw new RuntimeException( "Neither SHA256 nor MD5 algo available" );
			}
		}
	}
	
	
	synchronized boolean connect( String endpoint, String authKey )
	{
		if( !fAuthKey.equals(authKey) )
		{
			//create webservice interface
			URL url = null;
			try {
				url = new URL(com.askcs.webservices.Ask.class.getResource("."),endpoint);
			} catch(MalformedURLException e){}
			Ask ask=new Ask(url,new QName("urn:webservices.askcs.com", "Ask"));	//fetches wsdl
			fAskPort = new ReconnectingAskPort(ask.getAskPort());
			
			//init webservice session
			StringResponse res = null;
			try
			{
				res = fAskPort.startSession(authKey);	//also fetches wsdl
			}
			catch(Exception e)
			{
				System.err.println( e.toString() );
				System.out.println("startSession(..) failure (wsdl changed? "+endpoint+")" );
			}
			
			if( res.getError()!=0)
			{
				System.out.println("startSession() DISALLOWED");
			// 	return false;
			}
			
			fAuthKey = authKey;
			fEndPoint = endpoint;
		}
		
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
	
	/*
	protected boolean startSession(String authKey){
		
	}
	*/
	
	public AskPortType getAskPort(){
		return fAskPort;
	}
	
	public String getSessionId() {
		return fAskPort.getSessionKey();
	}
	
	public static byte[] getDigest( byte[] src ) {
		fDigest.reset();
		return fDigest.digest( src );
	}
	
	public static byte[] getRandom( int len ) {
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
