package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest
    {
        String id;
        @Test(priority = 1)
        public void post_api() {

            // Base URI of the API
            RestAssured.baseURI = "https://reqres.in/";

            // JSON body for the POST request
            String requestBody = "{\n" +
                    "  \"name\": \"Medhat\",\n" +
                    "  \"job\": \"Engineer\",\n" +
                    "  \"age\": 26\n" +
                    "}";

            // Send POST request
            Response response = given()
                    .contentType(ContentType.JSON) // Set content type to JSON
                    .body(requestBody) // Add the JSON body
                    .when()
                    .post("/api/users") // POST endpoint
                    .then()
                    .statusCode(201) // Validate status code
                    .body("name", equalTo("Medhat")) // Validate response body
                    .body("job", equalTo("Engineer"))
                    .body("age", equalTo(26))
                    .extract()
                    .response();

            // Get the status code
            int statusCode = response.getStatusCode();
            System.out.println("Status Code: " + statusCode);

            JsonPath jsonPath = response.jsonPath();
            id = jsonPath.getString("id"); // Get The ID to use it in the get and put requests

            System.out.println(id); // Print the ID
            System.out.println("Response: " + response.asString());
        }
        @Test(priority = 2)

        public void get_api() {

            // Base URI of the API
            RestAssured.baseURI = "https://reqres.in/";

            // Send a GET request
            Response getResponse = RestAssured
                    .given()
                    .header("Content-Type", "application/json")
                    .when()
                    .get("/api/users/1") // the extracted ID From Post Request not Working because it's not saved in the DB So i Used existed one
                    .then()
                    .statusCode(200) // Assert status code is 200 (OK)
                    .extract()
                    .response();

            // Print the GET response
            String getResponseBody = getResponse.getBody().asString();
            System.out.println("GET Response Body: " + getResponseBody);


        }

        @Test(priority = 3)
        public void put_api(){
            // Base URI (if applicable)
            RestAssured.baseURI = "https://reqres.in/";

            // JSON body for the PUT request
            String requestBody = "{\"name\": \"Medhat After Update\", \"age\": 26}";

            // Send PUT request and validate the response
            Response response = RestAssured
                    .given()
                    .header("Content-Type", "application/json") // Set content type
                    .body(requestBody) // Set request body
                    .when()
                    .put("/api/users/1") // Send PUT request to update user with ID 1
                    .then()
                    .statusCode(200) // Assert status code is 200 (OK)
                    .extract()
                    .response();


            // Print the response body
            String responseBody = response.getBody().asString();
            System.out.println("Response Body: " + responseBody);

            // Extract and validate specific fields from the response
            JsonPath jsonPath = response.jsonPath();
            String name = jsonPath.getString("name");
            int age = jsonPath.getInt("age");

            System.out.println("Updated Name: " + name);
            System.out.println("Updated Age: " + age);
        }


}
