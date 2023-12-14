package com.sistecredito.api.tasks.upload;

import com.sistecredito.api.utils.Endpoints;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.model.util.EnvironmentVariables;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import java.io.File;

public class CargarImagen implements Task {

    private final String ruta;

    private EnvironmentVariables environmentVariables;

    public CargarImagen(String ruta){
        this.ruta = ruta;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        File fileToUpload = new File(ruta);
        actor.attemptsTo(
                Post.to(Endpoints.URL_UPLOAD.getEndpoint())
                        .with(
                                resource -> resource
                                        .header("Authorization", environmentVariables.optionalProperty("KEY").get())
                                        .contentType("multipart/form-data")
                                        .multiPart("file", fileToUpload, "application/octet-stream")
                                        .relaxedHTTPSValidation()
                        )
        );

        if (SerenityRest.lastResponse().statusCode() != HttpStatus.SC_CREATED){
            throw new AssertionError("El codigo de respuesta es diferente al apropiado");
        }else {
            JSONObject response = new JSONObject(SerenityRest.lastResponse().body().asString());
            if (response.getBoolean("success")){
                actor.remember("responseUpload", response.getString("message"));
            }
        }
    }

    public static CargarImagen on(String ruta){
        return Tasks.instrumented(CargarImagen.class, ruta);
    }
}
