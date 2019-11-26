package com.mycompany.app.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {

	@Given("^I have first task$")
	public void i_have_first_task() {
		;
	}

	@When("^I attempt to solve it$")
	public void i_attempt_to_solve_it() {
		;
	}

	@Then("^I surely succeed$")
	public void i_surely_succeed() {
		;
	}

	@And("^Step from \"([^\"]*)\" in \"([^\"]*)\" feature file$")
	public void stepFromInFeatureFile(String scenario, String file) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.format("Thread ID - %2d - %s from %s feature file.\n",
				Thread.currentThread().getId(), scenario, file);
	}
}
