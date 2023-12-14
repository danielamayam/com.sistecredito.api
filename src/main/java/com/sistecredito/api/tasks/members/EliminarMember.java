package com.sistecredito.api.tasks.members;

import com.sistecredito.api.utils.Endpoints;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.thucydides.model.util.EnvironmentVariables;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import static io.restassured.http.ContentType.JSON;

public class EliminarMember implements Task {

    private final String id;

    private EnvironmentVariables environmentVariables;
    public EliminarMember(String id){
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.remember("id", id);
        actor.attemptsTo(
                Delete.from(Endpoints.URL_MEMBERS.getEndpoint() + "/" + id)
                        .with(
                                resource -> resource
                                        .header("Authorization", environmentVariables.optionalProperty("KEY").get())
                                        .contentType(JSON)
                                        .relaxedHTTPSValidation()
                        )
        );
        if (SerenityRest.lastResponse().statusCode() != HttpStatus.SC_OK){
            throw new AssertionError("El codigo de respuesta es diferente al apropiado");
        }else {
            JSONObject response = new JSONObject(SerenityRest.lastResponse().body().asString());
            actor.remember("responseDelete", response.getString("msg"));
        }
    }

    public static EliminarMember on(String id){
        return Tasks.instrumented(EliminarMember.class, id);
    }
}
