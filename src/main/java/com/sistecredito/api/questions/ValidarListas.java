package com.sistecredito.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class ValidarListas implements Question<Boolean> {

    private List<String> list1;
    private List<String> list2;

    public ValidarListas(List<String> list1, List<String> list2){
        this.list1 = list1;
        this.list2 = list2;
    }


    @Override
    public Boolean answeredBy(Actor actor) {
        boolean respuesta = list1.equals(list2);
        if (!respuesta) {
            throw new AssertionError("Las listas no son iguales");
        }
        return respuesta;
    }

    public static ValidarListas items(List<String> lista1, List<String> lista2){
        return new ValidarListas(lista1, lista2);
    }
}
