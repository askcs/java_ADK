package com.askcs.ADK.lib;

import java.util.Map;

import com.askcs.webservices.Tuple;
import com.askcs.webservices.TupleArray;

public class WSLib {

	public static TupleArray convertMapToTupleArray(Map<String, String> map){
		TupleArray array = new TupleArray();
		for(Map.Entry<String, String> e : map.entrySet()){
			Tuple tuple = new Tuple();
			tuple.setName(e.getKey());
			tuple.setName(e.getValue());
			array.getTuple().add(tuple);
		}
		return array;
	}
}
