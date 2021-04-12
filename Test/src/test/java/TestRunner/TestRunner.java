package TestRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/Feature",
	    glue= {"StepDefinition"},
	    tags= {"@ConsoleErrorValidation,@PageLinksValidations,@ResponseValidation"},
	    plugin = { "pretty", "html:target/cucumber-reports" },
	    monochrome = true
		//dryRun = true
		)

public class TestRunner {

}
