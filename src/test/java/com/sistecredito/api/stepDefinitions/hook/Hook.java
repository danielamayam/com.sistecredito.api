package com.sistecredito.api.stepDefinitions.hook;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Hook {

    private EnvironmentVariables environmentVariables;

    @Before
    public void configuracionBaseUrl(){
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("Daniel");
        String theRestApiBaseUrl = environmentVariables.optionalProperty("URL_QA").get();
        theActorInTheSpotlight().whoCan(CallAnApi.at(theRestApiBaseUrl));

    }
}
