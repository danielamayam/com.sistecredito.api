package com.sistecredito.api.stepDefinitions;

import com.sistecredito.api.questions.ValidarDatos;
import com.sistecredito.api.questions.ValidarSchema;
import com.sistecredito.api.tasks.members.ListarMembers;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class GetMembersStepdefinitions {
    @When("ejecuto el endpont get members")
    public void endpontGetMembers() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ListarMembers.on()
        );
    }
    @Then("válido la estructura de los datos {string}")
    public void validacionEstructura(String schema) {
        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarSchema.esperado(schema))
        );
    }
    @Then("válido los datos recibidos {string}")
    public void validacionDatos(String datos) {
        OnStage.theActorInTheSpotlight().should(seeThat(
                ValidarDatos.items(datos))
        );
    }
}
