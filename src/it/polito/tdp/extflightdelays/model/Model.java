package it.polito.tdp.extflightdelays.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
	List<Airport> aeroporti = new LinkedList<Airport>();
	List<Rotta> rotte = new LinkedList<Rotta>();
	AirportIdMap aeroportiId = new AirportIdMap();
	SimpleWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	
	public Model() {
		aeroporti.addAll(dao.loadAllAirports());
		aeroportiId.addAll(aeroporti);
		rotte.addAll(dao.getRotte(aeroportiId));
	}
	
	public void creaGrafo(int distanza) {
		grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(grafo, aeroporti);
		for(Rotta r : rotte)
			if(!grafo.containsEdge(r.getA1(), r.getA2()) && r.getDistanza() >= distanza)
				grafo.addEdge(r.getA1(), r.getA2());
	}
	
	public boolean areCollegati(Airport a1, Airport a2) {
		GraphIterator<Airport,DefaultWeightedEdge> dfi = new DepthFirstIterator<Airport,DefaultWeightedEdge>(grafo, a1);
		List<Airport> collegati = new LinkedList<Airport>();
		
		while(dfi.hasNext())
			collegati.add(dfi.next());	
		
		if(collegati.contains(a2))
			return true;
		return false;		
	}
	
	public List<Rotta> getRotteDistanti(int distanza){
		List<Rotta> rotteDistanti = new LinkedList<Rotta>();
		for(Rotta r : rotte)
			if(r.getDistanza() >= distanza)
				rotteDistanti.add(r);
		return rotteDistanti;
	}
	
	public List<Airport> getAllAeroporti(){
		return aeroporti;
	}


}
