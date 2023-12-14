package com.sistecredito.api.stepDefinitions;

import com.sistecredito.api.questions.ValidarTexto;
import com.sistecredito.api.tasks.upload.CargarImagen;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class PostUploadStepdefinitions {

    @When("ejecuto el endpoint post upload {string}")
    public void ejecutarCargarImagen(String ruta) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CargarImagen.on(ruta)
        );
    }
    @Then("válido la respuesta del enpoint {string}")
    public void validoLaRespuesta(String msg) {
        String msgRest = OnStage.theActorInTheSpotlight().recall("responseUpload");
        OnStage.theActorInTheSpotlight().should(
                seeThat(ValidarTexto.esperado(msg, msgRest))
        );
    }


}
