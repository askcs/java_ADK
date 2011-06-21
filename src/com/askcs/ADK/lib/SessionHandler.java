package com.askcs.ADK.lib;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.askcs.webservices.Ask;
import com.askcs.webservices.AskPortType;
import com.askcs.webservices.StringResponse;
import javax.xml.namespace.QName;

public class SessionHandler
implements Runnable {
	
	static private SessionHandler gHandler;
	static public SessionHandler getHandler() {
		return gHandler;
	}
	static public SessionHandler createHandler( String endpoint, String authKey ) {
		synchronized ( SessionHandler.class ) {
			if ( gHandler == null ) {
				gHandler = new SessionHandler( endpoint, authKey );
			} else {
				if ( ! gHandler.fEndPoint.equals( endpoint ) || ! gHandler.fAuthKey.equals( authKey ) ) {
					throw new RuntimeException( "Already had a session with different params!?" );
				}
			}
			return gHandler;
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
	
	
	private AskPortType fAskPort = null;
	private String fEndPoint="";
	private String fAuthKey="";
	private String fSessionId="";
	
	private MessageDigest fDigest;
	private SecureRandom fRandom;
	
		

	private SessionHandler(String endpoint, String authKey){
		URL url = null;
		try {
			url = new URL(com.askcs.webservices.Ask.class.getResource("."),endpoint);
		} catch(MalformedURLException e){
		}
		Ask ask=new Ask(url,new QName("urn:webservices.askcs.com", "Ask"));
		fAskPort = ask.getAskPort();
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
		
		(new Thread( this )).start();
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
	
	// implement Runnable -- this keeps the session alive
	public void run() {
		try {
			Thread.sleep( 50*60*60 ); // 50 minutes (server ttl default 60 mins)
		} catch ( InterruptedException x ) {
			x.printStackTrace( System.err );
			return;
		}
		startSession();
	}
	
}


