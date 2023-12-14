package com.sistecredito.api.stepDefinitions;

import com.sistecredito.api.models.Member;
import com.sistecredito.api.questions.ValidarListas;
import com.sistecredito.api.tasks.members.RegistarMember;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class PostMemberStepdefinitions {

    @When("ejecuto el endpoint post members")
    public void ejecutarEndpointPost(DataTable dataTable) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistarMember.on(Member.setData(dataTable))
        );
    }
    @Then("válido la respuesta del enpoint {string} {string} {string}")
    public void validarRespuesta(String name, String gender, String response) {
        JSONObject json = OnStage.theActorInTheSpotlight().recall(response);
        List<String> lista1 = new ArrayList<>();
        List<String> lista2 = new ArrayList<>();
        lista1.add(name);
        lista1.add(gender);
        lista2.add(json.getString("name"));
        lista2.add(json.getString("gender"));
        OnStage.theActorInTheSpotlight().should(
                seeThat(ValidarListas.items(lista1, lista2))
        );
    }
}
