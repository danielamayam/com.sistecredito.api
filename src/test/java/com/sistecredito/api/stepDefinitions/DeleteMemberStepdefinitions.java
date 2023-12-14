package com.sistecredito.api.stepDefinitions;

import com.sistecredito.api.tasks.members.EliminarMember;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Ensure;

public class DeleteMemberStepdefinitions {
    @When("ejecuto el endpoint delete members {string}")
    public void ejecutarEndpointDelete(String id) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                EliminarMember.on(id)
        );
    }
    @Then("válido el msg del enpoint {string}")
    public void validarMensaje(String mesaje) {
        String newMesaje = mesaje.replaceAll("\\d+", OnStage.theActorInTheSpotlight().recall("id"));
        Ensure.that("VALIDO EL MENSAJE DE RETORNO", OnStage.theActorInTheSpotlight().recall("msg")).equals(newMesaje);
    }
}
