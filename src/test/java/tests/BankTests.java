package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

public class BankTests extends BaseTest {

    public BankTests() throws MalformedURLException {
    }

    @Test
    @DisplayName("Проверка авторизации, пополнения, списания и отображения транзакций")
    public void simbTest() throws InterruptedException {
        init(urls.XYZ_BANK_URL);
        loginPage.loginAsHarryPotter();
        transactionsPage.checkTransactionsOpportunity();
        transactionsPage.makeCSV();
    }
}
