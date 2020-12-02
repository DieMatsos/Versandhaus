package de.fhac.swt.versandhaus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class KundenbewertungTests {

    Kundenbewertung kundenbewertung;

    private static Stream<Arguments> generator() {
        Kundenbewertung beobachtung = new Kundenbewertung();
        Kundenbewertung zuverlaessig = new Kundenbewertung();
        zuverlaessig.korrektGezahlt();
        zuverlaessig.korrektGezahlt();
        zuverlaessig.korrektGezahlt();
        Kundenbewertung kritisch = new Kundenbewertung();
        kritisch.verspaetetGezahlt();
        Kundenbewertung vorkasse = new Kundenbewertung();
        vorkasse.nichtGezahlt();

        return Stream.of(
                Arguments.of(zuverlaessig),
                Arguments.of(kritisch),
                Arguments.of(beobachtung),
                Arguments.of(vorkasse)
        );
    }

    @BeforeEach
    public void setup() {
        kundenbewertung = new Kundenbewertung();
    }

    @Test
    public void testDreiMalKorrektGezahlt() {
        // when
        kundenbewertung.korrektGezahlt();
        kundenbewertung.korrektGezahlt();
        kundenbewertung.korrektGezahlt();
        //then
        assertTrue(kundenbewertung.getZustand() instanceof ZustandZuverlaessig);
        assertEquals(500, kundenbewertung.getZustand().kredit());
    }

    @Test
    public void testVerspaetetGezahlt() {
        //when
        kundenbewertung.verspaetetGezahlt();
        //then
        assertTrue(kundenbewertung.getZustand() instanceof ZustandKritisch);
        assertEquals(100, kundenbewertung.getZustand().kredit());
    }

    @Test
    public void testZweimalVerspaetetGezahlt() {
        //when
        kundenbewertung.verspaetetGezahlt();
        kundenbewertung.verspaetetGezahlt();
        //then
        assertTrue(kundenbewertung.getZustand() instanceof ZustandVorkasse);
        assertEquals(0, kundenbewertung.getZustand().kredit());
    }

    @ParameterizedTest
    @MethodSource("generator")
    public void testNichtGezahlt(Kundenbewertung kundenbewertung) {
        //when
        kundenbewertung.nichtGezahlt();
        //then
        assertTrue(kundenbewertung.getZustand() instanceof ZustandVorkasse);
        assertEquals(0, kundenbewertung.getZustand().kredit());
    }

    @Test
    public void testAuskunftEinmalKorrektGezahlt() {
        //when
        kundenbewertung.korrektGezahlt();
        //then
        assertEquals(new ZustandBeobachtung().auskunft(), kundenbewertung.auskunft());
    }

    @Test
    public void testAuskunftDreimalKorrektGezahlt() {
        //when
        kundenbewertung.korrektGezahlt();
        kundenbewertung.korrektGezahlt();
        kundenbewertung.korrektGezahlt();
        //then
        assertEquals(new ZustandZuverlaessig().auskunft(), kundenbewertung.auskunft());
    }

    @Test
    public void testAuskunftVerspaetetGezahlt() {
        //when
        kundenbewertung.verspaetetGezahlt();
        //then
        assertEquals(new ZustandKritisch().auskunft(), kundenbewertung.auskunft());
    }

    @Test
    public void testAuskunftZweimalVerspaetetGezahlt() {
        //when
        kundenbewertung.verspaetetGezahlt();
        kundenbewertung.verspaetetGezahlt();
        //then
        assertEquals(new ZustandVorkasse().auskunft(), kundenbewertung.auskunft());
    }

    @Test
    public void testPuenktlicheZahlungWennKritisch() {
        //setup
        kundenbewertung.verspaetetGezahlt();
        //when
        kundenbewertung.korrektGezahlt();
        //then
        assertTrue(kundenbewertung.getZustand() instanceof ZustandBeobachtung);
        assertEquals(0, (((ZustandBeobachtung) kundenbewertung.getZustand()).getPuenktlicheZahlungen()));
        assertEquals(200, kundenbewertung.getZustand().kredit());
    }


    @Test
    public void testUnpuenklicheZahlungAusZuverlaessig() {
        //setup
        kundenbewertung.korrektGezahlt();
        kundenbewertung.korrektGezahlt();
        kundenbewertung.korrektGezahlt();
        //when
        kundenbewertung.verspaetetGezahlt();
        //then
        assertTrue(kundenbewertung.getZustand() instanceof ZustandBeobachtung);
        assertEquals(0, (((ZustandBeobachtung) kundenbewertung.getZustand()).getPuenktlicheZahlungen()));
        assertEquals(200, kundenbewertung.getZustand().kredit());
    }

    @Test
    public void testBeobachtungAlsStartzustand() {
        assertTrue(kundenbewertung.getZustand() instanceof ZustandBeobachtung);
        assertEquals(0, (((ZustandBeobachtung) kundenbewertung.getZustand()).getPuenktlicheZahlungen()));
        assertEquals(200, kundenbewertung.getZustand().kredit());
    }
}
