package com.sistecredito.api.stepDefinitions;

import com.sistecredito.api.models.Member;
import com.sistecredito.api.tasks.members.ActualizarMember;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;

public class PutMemberStepdefinitions {

    @When("ejecuto el endpoint put members {string}")
    public void ejecutarEndpointPut(String id, DataTable dataTable) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ActualizarMember.on(Member.setData(dataTable), id)
        );
    }

}
