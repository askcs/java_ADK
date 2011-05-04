package com.askcs.ADK.lib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	protected DateFormat dateFormat;
	
	public Logger() {
		// TODO Auto-generated constructor stub
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	}
	
	public void log(String message){
		
		System.out.println(dateFormat.format(new Date())+" "+message);
	}

}
