package it.polito.tdp.extflightdelays.model;

public class Rotta {
	Airport a1;
	Airport a2;
	int distanza;
	
	public Rotta(Airport a1, Airport a2, int distanza) {
		this.a1 = a1;
		this.a2 = a2;
		this.distanza = distanza;
	}

	public Airport getA1() {
		return a1;
	}

	public void setA1(Airport a1) {
		this.a1 = a1;
	}

	public Airport getA2() {
		return a2;
	}

	public void setA2(Airport a2) {
		this.a2 = a2;
	}

	public int getDistanza() {
		return distanza;
	}

	public void setDistanza(int distanza) {
		this.distanza = distanza;
	}
	
	
}