package com.chaseUK.backend_Demo.stepDefinition;

import com.chaseUK.backend_Demo.Utils.CommonUtils;
import com.chaseUK.backend_Demo.endpoints.RetrieveEndpoint;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import java.io.IOException;
import java.util.List;

public class RetrievePostsDefinitions {
    private ValidatableResponse response;

    CommonUtils common = new CommonUtils();
    private RetrieveEndpoint base = new RetrieveEndpoint();

    @Before
    public void init() throws IOException, ParseException {
        common.loadtestdata("get_base_url");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Given("A collection of posts uri endpoint")
    public void ACollectionOfPostsUriEndpoint() {
        RestAssured.baseURI = base.getBaseUrl();
    }

    @When("I send the request to retrieve the posts")
    public void iSendTheRequestToRetrieveThePosts() {
        Reporter.log("GET request to retrieve the number of posts");
        response = base.sendGetRequest();
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int status_code) {
        Reporter.log("Check the response returns "+status_code+" status code");
        base.verifyResponseStatusValue(response,status_code);
    }

    @And("the number of posts that should be included in the responses is {int}")
    public void theResponseShouldIncludesTheNumberOfPostsIs(int no_of_posts) {
        Reporter.log("Check the total number of posts in the response is 100");
        int number_of_posts_from_response = common.covertResponseToJSONArray(response);
        boolean expected_output = (number_of_posts_from_response == no_of_posts);
        base.verifyTrue(expected_output);
    }

    @When("I send the request to retrieve the posts with uri that doesn't exist")
    public void iSendTheRequestToRetrieveThePostsWithUriThatDoesnTExist() {
        Reporter.log("Check the total number of posts in the response is 100");
        response = base.sendGetRequestwronguri();
    }

    @And("the description is {string}")
    public void theDescriptionIs(String status_content) {
        Reporter.log("Check the response returns "+status_content+" status content for invalid uri");
        base.verifyResponseStatusDescription(response,status_content);
    }

    @When("I send the request to retrieve the posts for the userID {int}")
    public void iSendTheRequestToRetrieveThePostsForTheUserID(int user_id) {
        response = base.sendGetRequest(user_id);
    }

    @And("the total number of posts in response")
    public void theNumberOfPostsShouldIncludeNumberOfPosts(DataTable numberofposts) {
        int number_of_posts_from_response = common.covertResponseToJSONArray(response);
        boolean expectedOutput = false;
        List<String> conditions = numberofposts.asList();
        for (String condition : conditions) {
            Reporter.log("Check to see the response contains posts "+condition);
            if (condition.equals("greater than zero")) {
                expectedOutput = number_of_posts_from_response > 0;
            } else if (condition.equals("less than 101")) {
                expectedOutput = number_of_posts_from_response < 101;
            }
            base.verifyTrue(expectedOutput);
        }
    }

    @And("the response should include empty {string}")
    public void theResponseShouldIncludeEmpty(String expectedResponseOutput) {
        Reporter.log("The response returns expected output "+expectedResponseOutput);
        base.verifyResponseBodyContent(expectedResponseOutput);
    }

    @Then("the number of posts should include posts greater than zero")
    public void the_number_of_posts_should_include_posts_greater_than_zero() {
        int number_of_posts_from_response = common.covertResponseToJSONArray(response);
        boolean expectedOutput = number_of_posts_from_response > 0;
        base.verifyTrue(expectedOutput);
    }

    @And("validate the post contains below information")
    public void validate_the_attributes_key_information(DataTable expectedKeys) {
        int number_of_posts_from_response = common.covertResponseToJSONArray(response);
        List<String> keys = expectedKeys.asList();
        if (number_of_posts_from_response > 0) {
            JSONObject jsonobject = common.getDataFromJSONarray(response);
            for(String key:keys){
                if(key.contains("id") || key.contains("userId")){
                    base.verifyResponsehasKey(jsonobject, key, "Integer");
                } else {
                    base.verifyResponsehasKey(jsonobject, key, "String");
                }
            }
        }
    }

    @After
    public void cleanup() {
        RestAssured.reset();
    }

}

