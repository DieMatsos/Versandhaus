package de.fhac.swt.versandhaus;

public class ZustandZuverlaessig implements Zustand {

	public String auskunft() {
		return "ist zuverlaessig";
	}

	public int kredit() {
		return 500;
	}

	public Zustand puenktlich() {
		return this;
	}

	public Zustand unpuenktlich() {
		return new ZustandBeobachtung();
	}

	public Zustand nichtBezahlt() {
		return new ZustandVorkasse();
	}

}
