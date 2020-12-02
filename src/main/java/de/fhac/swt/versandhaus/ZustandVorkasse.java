package de.fhac.swt.versandhaus;

public class ZustandVorkasse implements Zustand {

	public String auskunft() {
		return "ist kreditunwuerdig";
	}

	public int kredit() {
		return 0;
	}

	public Zustand puenktlich() {
		return this;
	}

	public Zustand unpuenktlich() {
		return this;
	}

	public Zustand nichtBezahlt() {
		return this;
	}

}
