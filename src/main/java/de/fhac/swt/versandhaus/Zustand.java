package de.fhac.swt.versandhaus;

public interface Zustand {
    String auskunft();

    int kredit();

    Zustand puenktlich();

    Zustand unpuenktlich();

    Zustand nichtBezahlt();
}