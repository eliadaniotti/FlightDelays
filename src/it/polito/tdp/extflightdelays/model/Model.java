package it.polito.tdp.extflightdelays.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
	List<Airport> aeroporti = new LinkedList<Airport>();
	SimpleWeightedGraph<Airport, DefaultEdge> grafo = new SimpleWeightedGraph<Airport, DefaultEdge>(DefaultEdge.class);
	
	public Model() {
		aeroporti.addAll(dao.loadAllAirports());
	}
	
	public void creaGrafo() {
		
		for(Airport a : aeroporti) {
			grafo.addVertex(a);
			for(Airport aa : aeroporti)
				if(dao.getDistanzaMedia(a, aa) != 0.0 && !(grafo.containsEdge(a, aa)))
					grafo.addEdge(a, aa);
		}
			
	}
	
	public boolean areCollegati(Airport a1, Airport a2) {
		GraphIterator<Airport,DefaultEdge> dfi = new DepthFirstIterator<Airport,DefaultEdge>(grafo, a1);
		List<Airport> collegati = new LinkedList<Airport>();
		
		while(dfi.hasNext())
			collegati.add(dfi.next());
		
		if(collegati.contains(a2))
			return true;
		return false;		
	}
	
	public List<Airport> getAllAeroporti(){
		return aeroporti;
	}


}
