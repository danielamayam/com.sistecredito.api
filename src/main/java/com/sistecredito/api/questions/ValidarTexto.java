package com.sistecredito.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidarTexto implements Question<Boolean> {

    private String text1;
    private String text2;

    public ValidarTexto(String text1, String text2){
        this.text1 = text1;
        this.text2 = text2;
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        boolean respuesta = false;
        respuesta = text1.equals(text2);
        if (!respuesta) {
            throw new AssertionError("No se puedo cargar la imagen, Mensaje resibido.\n" +
                    "Mensaje esperado: " + text1 + "\n" +
                    "Mensaje recibido: " + text2 );
        }

        return respuesta;
    }

    public static ValidarTexto esperado(String text1, String text2){
        return new ValidarTexto(text1, text2);
    }
}
