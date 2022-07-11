package com.chaseUK.backend_Demo.Utils;

import com.chaseUK.backend_Demo.endpoints.RetrieveEndpoint;
import com.chaseUK.backend_Demo.parseData.backenddata;
import io.restassured.response.ValidatableResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CommonUtils {
    private RetrieveEndpoint base = new RetrieveEndpoint();
    private backenddata data = new backenddata();

    public static int covertResponseToJSONArray(ValidatableResponse response) {
        int json_response_array_size = 0;
        try {
            String response_str = response.extract().asPrettyString();
            JSONArray jsonarray = new JSONArray(response_str);
            json_response_array_size = jsonarray.length();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json_response_array_size;
    }

    public JSONObject getDataFromJSONarray(ValidatableResponse response) {
        String response_str = response.extract().asPrettyString();
        base.isValid(response_str);
        JSONArray jsonarray = new JSONArray(response_str);
        JSONObject jsonobject = jsonarray.getJSONObject(0);
        return jsonobject;
    }

    public void loadtestdata(String key) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/test/resources/testdata/backenddata.json");
            JSONObject testdata = (JSONObject) jsonParser.parse(reader);
            data.setBasURL(String.valueOf(testdata.get(key)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
