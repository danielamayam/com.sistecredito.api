package com.sistecredito.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ValidarDatos implements Question<Boolean> {

    private String items;

    private ValidarDatos(String datos){
        this.items = datos;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        JSONArray responseLocal = new JSONArray(items);
        JSONArray response = actor.recall("response");

        boolean respuesta = false;

        for (int i = 0; i < responseLocal.length(); i++) {
            JSONObject local = responseLocal.getJSONObject(i);
            JSONObject endpoint = response.getJSONObject(i);
            respuesta = local.getInt("id") == endpoint.getInt("id") && local.getString("name").equals(endpoint.getString("name")) && local.getString("gender").equals(endpoint.getString("gender"));
            if (!respuesta) {
                throw new AssertionError("Los datos recibidos no coinciden con los esperados.\n" +
                        "Mensaje esperado: " + local.getInt("id") + " " + local.getString("name") + " " + local.getString("gender") + "\n" +
                        "Mensaje recibido: " + endpoint.getInt("id") + " " + endpoint.getString("name") + " " + endpoint.getString("gender"));
            }

        }

        return respuesta;
    }

    public static ValidarDatos items(String texto){
        return new ValidarDatos(texto);
    }
}
