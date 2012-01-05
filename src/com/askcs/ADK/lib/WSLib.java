package com.askcs.ADK.lib;

import java.util.HashMap;
import java.util.Map;

import com.askcs.webservices.Tuple;
import com.askcs.webservices.TupleArray;

public class WSLib {

	public static TupleArray convertMapToTupleArray(Map<String, String> map){
		TupleArray array = new TupleArray();
		for(Map.Entry<String, String> e : map.entrySet()){
			Tuple tuple = new Tuple();
			tuple.setName(e.getKey());
			tuple.setValue(e.getValue());
			array.getTuple().add(tuple);
		}
		return array;
	}
	
	public static Map<String, String> convertTupleArrayToMap(TupleArray tArray) {
		HashMap<String, String> data = new HashMap<String, String>();
		for (Tuple t : tArray.getTuple()) {
			String name	=	t.getName();
			String value = t.getValue();
			data.put(name, value);
		}
		return data;
	}
}
