package com.askcs.ADK.lib;

import com.askcs.webservices.Ask;
import com.askcs.webservices.AskPortType;

public class Settings {
	public static final Ask ask = new Ask();
	public static final AskPortType askport = ask.getAskPort();
}
