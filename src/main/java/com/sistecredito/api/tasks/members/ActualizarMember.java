package com.sistecredito.api.tasks.members;

import com.sistecredito.api.utils.Endpoints;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.thucydides.model.util.EnvironmentVariables;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.Optional;

import static io.restassured.http.ContentType.JSON;

public class ActualizarMember implements Task {

    private final String json;
    private final String id;

    private EnvironmentVariables environmentVariables;

    public ActualizarMember(String json, String id) {
        this.json = json;
        this.id = id;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Put.to(Endpoints.URL_MEMBERS.getEndpoint() + "/" + id)
                        .with(
                                resource -> resource
                                        .header("Authorization", environmentVariables.optionalProperty("KEY").get())
                                        .contentType(JSON)
                                        .body(json)
                                        .relaxedHTTPSValidation()
                        )
        );
        if (SerenityRest.lastResponse().statusCode() != HttpStatus.SC_OK) {
            throw new AssertionError("El codigo de respuesta es diferente al apropiado");
        } else {

            JSONObject response = new JSONObject(SerenityRest.lastResponse().body().asString());
            JSONObject miembro = response.getJSONObject("member");
            actor.remember("responsePut", miembro);
        }

    }

    public static ActualizarMember on(String json, String id) {
        return Tasks.instrumented(ActualizarMember.class, json, id);
    }
}
