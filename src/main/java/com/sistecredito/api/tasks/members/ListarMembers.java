package com.sistecredito.api.tasks.members;

import com.sistecredito.api.utils.Endpoints;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.model.util.EnvironmentVariables;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import static io.restassured.http.ContentType.JSON;

public class ListarMembers implements Task {

    private EnvironmentVariables environmentVariables;

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.attemptsTo(
                Get.resource(Endpoints.URL_MEMBERS.getEndpoint())
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
            JSONArray response = new JSONArray(SerenityRest.lastResponse().body().asString());
            actor.remember("response", response);
        }
    }

    public static ListarMembers on() {
        return Tasks.instrumented(ListarMembers.class);
    }
}
