package helperclass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

@Slf4j
public class HelperClass {

    @BeforeClass
    @Parameters("baseURI")
    public void setup(String baseURI) {
        RestAssured.baseURI = baseURI;
        log.info("Base URI set from XML: {}", baseURI);
    }

    public String POSTCALL(String apiPath, String apiBody) {
        try {
            Response response = given()
                    .header("Authorization", "Basic emVuZGFfcWE6RkFAMzM0MV83JnM1Nkw=")
                    .contentType(ContentType.JSON)
                    .body(apiBody)
                    .when()
                    .post(apiPath)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            assertEquals(response.getStatusCode(), 200, "Expected status 200");
            return response.asString();
        } catch (Exception e) {
            log.error("API call failed due to exception: ", e);
            fail("API call failed due to exception: " + e.getMessage());
            return null;
        }
    }

    public String getJson(String fileName, String folder) {
        try {
            String path = folder + "/" + fileName + ".json";
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            if (is == null) throw new RuntimeException("File not found: " + path);

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }
}
