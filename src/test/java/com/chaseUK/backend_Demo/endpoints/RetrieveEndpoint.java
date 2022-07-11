package com.chaseUK.backend_Demo.endpoints;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.*;

import com.chaseUK.backend_Demo.parseData.backenddata;
import io.restassured.response.ValidatableResponse;

import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Reporter;

public class RetrieveEndpoint {
    backenddata data = new backenddata();

    protected final String base_url = data.getBaseURL();

    public static final String GET_REQUEST_BASE_PATH = "/posts";
    public static final String InVALId_GET_REQUEST_BASE_PATH = "/post";

    private static ValidatableResponse response;

    public void verifyResponsehasKey(JSONObject jsonobject,String key, String key_value_datatype) {
        assertThat(jsonobject.has(key),is(true));
        assertThat(jsonobject.get(key).getClass().getSimpleName(),is(key_value_datatype));
        Reporter.log("The key "+key+" with "+jsonobject.get(key)+" is displayed as expected");
    }

    public void verifyTrue(boolean val) {
        assertThat(val,is(true));
    }

    public void verifyResponseBodyContent(String bodyContent) {
        response.assertThat().body(Matchers.is(bodyContent));
    }

    public void verifyResponseStatusValue(ValidatableResponse response, int expectedCode) {
        response.assertThat().statusCode(expectedCode);
    }

    public void verifyResponseStatusDescription(ValidatableResponse response, String status_content) {
        response.assertThat().statusLine(containsString(status_content));
    }

    public String getBaseUrl() {
        return this.base_url;
    }

    public boolean isValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public static ValidatableResponse sendGetRequest() {
        response = given().
                    when().
                    get(GET_REQUEST_BASE_PATH).
                    then();
        return response;
    }

    public static ValidatableResponse sendGetRequestwronguri() {
        response = given().
                when().
                get(InVALId_GET_REQUEST_BASE_PATH).
                then();
        return response;
    }

    public static ValidatableResponse sendGetRequest(int user_id) {
        response = given().
                    param("userId", user_id).
                    when().
                    get(GET_REQUEST_BASE_PATH).
                    then();
        return response;
    }
}
