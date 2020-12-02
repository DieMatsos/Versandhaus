package de.fhac.swt.versandhaus;

public class ZustandBeobachtung implements Zustand {

    private int puenktlicheZahlungen = 0;

    public int getPuenktlicheZahlungen() {
        return puenktlicheZahlungen;
    }

    public String auskunft() {
        return "wird noch bewertet";
    }

    public int kredit() {
        return 200;
    }

    public Zustand puenktlich() {
        puenktlicheZahlungen++;
        if (puenktlicheZahlungen > 2) {
            return new ZustandZuverlaessig();
        } else {
            return this;
        }
    }

    public Zustand unpuenktlich() {
        return new ZustandKritisch();
    }

    public Zustand nichtBezahlt() {
        return new ZustandVorkasse();
    }

}
