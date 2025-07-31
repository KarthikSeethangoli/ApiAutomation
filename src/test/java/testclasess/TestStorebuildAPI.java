package testclasess;

import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import helperclass.HelperClass;
import dataproviders.DataProviderClass;

@Slf4j
public class TestStorebuildAPI extends HelperClass {


    @Test(dataProvider = "Storebuild", dataProviderClass = DataProviderClass.class)
    public void runTest(String username, String schoolCode) {
        log.info("Starting test: Storebuild API");

        String payload = getJson("Storebuild", "Json")
                .replace("USER_NAME", username)
                .replace("SCHOOL_CODE", schoolCode);

        String response = POSTCALL("/K12_Clients/externalAPI/utility/syncUsers", payload);
        Assert.assertNotNull(response, "API response is null, test cannot continue.");

        JsonPath jsonPath = new JsonPath(response);
        Assert.assertEquals(jsonPath.getInt("statusCode"), 1, "Expected statusCode to be 1");
        Assert.assertEquals(jsonPath.getString("statusMesssge"), "User sync successful.", "Expected statusMessage to be 'User sync successful.'");
        Assert.assertEquals(jsonPath.getInt("timestamp"), 0, "Expected timestamp to be 0");

        log.info("Test completed successfully.");


    }
}
