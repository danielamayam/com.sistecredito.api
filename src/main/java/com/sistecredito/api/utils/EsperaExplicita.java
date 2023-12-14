package com.sistecredito.api.utils;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class EsperaExplicita implements Interaction {
    private final int tiempoEspera;

    public EsperaExplicita(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public static EsperaExplicita empleada(int tiempoEspera) {
        return (EsperaExplicita) Tasks.instrumented(EsperaExplicita.class, new Object[]{tiempoEspera});
    }

    @Step("Ingresa tiempo espera necesario en milisegundos")
    public <T extends Actor> void performAs(T actor) {
        try {
            TimeUnit.MILLISECONDS.sleep(tiempoEspera);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }
        Logger.getAnonymousLogger().info("Espera terminada");
    }
}