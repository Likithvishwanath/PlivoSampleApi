package Utils;

import org.junit.runner.RunWith;



import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = { "src/test/Resources/Features/" }, glue = {
		"Steps" }, format = { "pretty", "json:target/cucumber.json" })
public class Runner{

	
	
}