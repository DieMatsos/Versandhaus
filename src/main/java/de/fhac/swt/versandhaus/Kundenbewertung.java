package de.fhac.swt.versandhaus;

public class Kundenbewertung {
	private Zustand zustand;
	
	public Kundenbewertung(){
		zustand= new ZustandBeobachtung();
	}

	public Kundenbewertung(Zustand startZustand) {
		this.zustand = startZustand;
	}
	
	public Zustand getZustand() {
		return zustand;
	}
	
	public void korrektGezahlt(){
		zustand = zustand.puenktlich();
	}
	
	public void verspaetetGezahlt(){
		zustand = zustand.unpuenktlich();
	}
	
	public void nichtGezahlt(){
		zustand = zustand.nichtBezahlt();
	}
	
	public int kredit(){
		return zustand.kredit();
	}
	
	public String auskunft(){
		return zustand.auskunft();
	}
	
}
