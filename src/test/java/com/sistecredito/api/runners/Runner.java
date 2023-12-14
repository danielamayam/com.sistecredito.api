package com.sistecredito.api.runners;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;


@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/",
        tags = "@postUpload",
        glue = {
                "com.sistecredito.api.stepDefinitions.hook",
                "com.sistecredito.api.stepDefinitions"
        },
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"},
        publish = true
)
public class Runner {

}