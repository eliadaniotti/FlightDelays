package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportIdMap {
	Map<Integer, Airport> map = new HashMap<Integer,Airport>();
	
	public AirportIdMap() {
	}
	
	public Airport get(int id) {
		return map.get(id);
	}
	
	public void add(Airport a) {
		map.put(a.getId(), a);
	}
	
	public void addAll(List<Airport> aa) {
		for(Airport aaa : aa)
			map.put(aaa.getId(), aaa);
	}

}
