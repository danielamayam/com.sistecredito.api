package com.sistecredito.api.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ValidarSchema implements Question<Boolean> {

    private final String estructura;

    public ValidarSchema(String estructura){
        this.estructura = estructura;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                seeThatResponse("Respuesta del servicio de esquema de validación",
                        response -> response.assertThat()
                                .body(matchesJsonSchemaInClasspath("schemas/"+estructura+".json")))
        );
        return true;
    }

    public static ValidarSchema esperado(String esquema){
        return new ValidarSchema(esquema);
    }
}

