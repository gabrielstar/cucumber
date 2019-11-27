package com.mycompany.app.extra;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty","json:target/site/results.json"},
		tags = {"@smoke"},
		features = { "src/test/resources/com/mycompany/app/" },
		glue = { "com.mycompany.app"}
		)
public class SmokeRunner {

}
