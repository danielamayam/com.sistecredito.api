package com.sistecredito.api.tasks.members;


import com.sistecredito.api.utils.Endpoints;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.model.util.EnvironmentVariables;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.http.ContentType.JSON;

public class RegistarMember implements Task {

    private final String json;

    private EnvironmentVariables environmentVariables;

    public RegistarMember(String json){
        this.json = json;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Post.to(Endpoints.URL_MEMBERS.getEndpoint())
                        .with(
                                resource -> resource
                                        .header("Authorization", environmentVariables.optionalProperty("KEY").get())
                                        .contentType(JSON)
                                        .body(json)
                                        .relaxedHTTPSValidation()
                        )
        );
        if (SerenityRest.lastResponse().statusCode() != HttpStatus.SC_CREATED){
            throw new AssertionError("El codigo de respuesta es diferente al apropiado");
        }else {
            JSONObject response = new JSONObject(SerenityRest.lastResponse().body().asString());
            actor.remember("responsePost", response);
        }
    }

    public static RegistarMember on(String json) {
        return Tasks.instrumented(RegistarMember.class, json);
    }
}
