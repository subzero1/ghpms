package com.netsky.base.baseObject;

import java.util.Map;
import java.util.HashMap;

public class SharedCfgData {

	private static Map<String,String> cfgUrlmap = new HashMap<String,String>();
	
	public static void setMap(Map<String,String> map){
		cfgUrlmap = map;
	}
	
	public static Map<String,String> getMap(){
		return cfgUrlmap;
	}

}
