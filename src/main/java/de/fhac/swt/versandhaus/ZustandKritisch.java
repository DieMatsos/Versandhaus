package de.fhac.swt.versandhaus;

public class ZustandKritisch implements Zustand {

    public String auskunft() {
        return "zeigt Verhaltensdefizite";
    }

    public int kredit() {
        return 100;
    }

    public Zustand puenktlich() {
        return new ZustandBeobachtung();
    }

    public Zustand unpuenktlich() {
        return new ZustandVorkasse();
    }

    public Zustand nichtBezahlt() {
        return new ZustandVorkasse();
    }

}
