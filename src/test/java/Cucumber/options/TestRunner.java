package Cucumber.options;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
//we are writing test runner file for all features and stepdefinition thats why we gave full package path or package name 
@CucumberOptions(
		features = "src/test/java/features",
		glue= {"stepdefinition"},
		strict = true,
		plugin = "json:target/jsonReports/cucumber-report.json"
		)
public class TestRunner {
	//tags = "@DeletePlaceAPI"
}
