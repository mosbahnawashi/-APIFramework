package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\features\\placeValidations.feature", plugin = "json:target/jsonRepoerts/cucumber-report.json", glue = { "stepDefinations" })
public class TestRunner {
	
	//	Get Git updated file from here.
	
	
	//	, tags = {"@DeletePlace"}
	
	//	To run from maven command prompt:
	//	mvn test or:
	//	mvn test -Dcucumber.options="--tags @DeletePlace"
	
	//	To generate report: run from maven command:
	//	mvn test verify
}
