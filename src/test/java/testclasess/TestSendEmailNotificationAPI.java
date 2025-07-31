package testclasess;

import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import helperclass.HelperClass;
import dataproviders.DataProviderClass;

@Slf4j
public class TestSendEmailNotificationAPI extends HelperClass {

    @Test(dataProvider = "EmailNotification", dataProviderClass = DataProviderClass.class)
    public void runTest(String schoolId, String moneyhashCredId, String tenantId, String supplierCode, String gatewayId) {
        log.info("Starting test: Send Email Notification API");

        // Prepare the payload
        String payload = getJson("EmailNotification", "Json")
                .replace("SCHOOL_ID", schoolId)
                .replace("MONEYHASH_CRED_ID", moneyhashCredId)
                .replace("TENANT_ID", tenantId)
                .replace("SUPPLIER_CODE", supplierCode)
                .replace("GATEWAY_ID", gatewayId)
                .replace("FROM_DATE", "2024-07-01")
                .replace("TO_DATE", "2024-07-31")
                .replace("PAY_TYPE", "Autopay")
                .replace("JOB_TYPE", "tax-invoice-report");

        // API Call
        String response = POSTCALL("/externalAPI/test/sendEmailNotification", payload);
        Assert.assertNotNull(response, "API response is null, test cannot continue.");

        // Parse the response
        JsonPath jsonPath = new JsonPath(response);

        // Validate expected status code and message
        Assert.assertEquals(jsonPath.getInt("statusCode"), 1, "Expected statusCode to be 1");
        Assert.assertEquals(jsonPath.getString("statusMessage"), "Email notification sent successfully.", "Expected statusMessage to be 'Email notification sent successfully.'");

        // Optional: Add more validations based on response data

        log.info("Test completed successfully.");
    }
}
