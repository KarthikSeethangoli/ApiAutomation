package dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    // Data provider for Storebuild test
    @DataProvider(name = "Storebuild")
    public Object[][] getStorebuildData() {
        return new Object[][] {
                {"Arun@mailinator.com", "etwo-m8"},{"haripriya.gopi@zenda.com" , "Sps1-m8"}
        };
    }

    // Data provider for EmailNotification test
    @DataProvider(name = "EmailNotification")
    public Object[][] getEmailNotificationData() {
        return new Object[][] {
                {"1", "7", "dubaischolars1.zenda.com", "23", "10"},
                // Add more test cases as required
        };
    }

    // Data provider for Missing Fields test
    @DataProvider(name = "EmailNotificationMissingFields")
    public Object[][] getEmailNotificationMissingFieldsData() {
        return new Object[][] {
                {null, "7", "dubaischolars1.zenda.com", "23", "10"},  // Missing schoolId
                {"1", null, "dubaischolars1.zenda.com", "23", "10"},  // Missing moneyhashCredId
                {"1", "7", null, "23", "10"},  // Missing tenantId
                {"1", "7", "dubaischolars1.zenda.com", null, "10"},  // Missing supplierCode
                {"1", "7", "dubaischolars1.zenda.com", "23", null},  // Missing gatewayId
        };
    }
}
