package Cucumber.options;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
//we are writing test runner file for all features and stepdefinition thats why we gave full package path or package name 
@CucumberOptions(
		features = "src/test/java/features/GoRest.feature",
		glue= {"stepdefinition"},//for features  we have to give specific file path but for glue we need to just give the folder that contains step definition
		strict = true,
		plugin = "json:target/jsonReports/cucumber-report.json"//report will be given only running through maven
		)
public class TestRunner {
	//tags = "@DeletePlaceAPI"
}
