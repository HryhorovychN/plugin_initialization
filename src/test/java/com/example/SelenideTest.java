package com.example;

import com.codeborne.selenide.AuthenticationType;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SelenideTest {

    @Test
    public void runTest() {
        // Настройка Selenide
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "firefox";
        Configuration.headless = false;  // для отладки лучше отключить headless
        Configuration.baseUrl = "https://stage-plugins-third-party.stripo.email/";

        boolean result = false;
        try {
            // Переход на сайт с базовой аутентификацией
            open("/", AuthenticationType.BASIC, "monitoring", "lksh8UHHKns");

            // Выполнение сценария
            $(By.cssSelector(".backToConfigButton")).shouldBe(visible).click();
            sleep(1000);

            $(By.id("uiEditorToUseChoice4")).click();
            $(By.id("useHtmlAndCssChoice")).click();
            $(By.id("labelValue")).setValue("Live");
            $(By.id("pluginIdValue")).setValue("b1b0c46440b244f785e86e1362a78c4e");
            $(By.id("secretKeyValue")).setValue("ef4427a9c22d4161a1529f3a5349be1f");
            $(By.id("roleValue")).setValue("admin");
            $(By.id("localeValue")).setValue("en");
            $(By.id("apiBaseUrlValue")).setValue("https://plugins.stripo.email/api/v1");
            $(By.id("clientSourceUrlValue")).setValue("https://plugins.stripo.email/static/latest/stripo.js");
            $(By.id("uiEditorUrlValue")).setValue("https://stripo-dev.devel.ardas.dp.ua/uieditor/UIEditor.js");
            $(By.id("templateUrlValue")).setValue("v1/plugin/demo/template");
            $(By.id("apiRequestDataValue")).setValue("{\"emailId\": \"stripoEmail\", \"userId\":\"stripoTestUser\"}");
            $(By.id("additionalEditorConfigValue")).setValue("{}");
            $(By.id("htmlUrlValue")).setValue("/v1/proxy?url=https://stripoeditor.stripocdnplugin.email/content/stripoeditor/template/template4.html");
            $(By.id("cssUrlValue")).setValue("/v1/proxy?url=https://stripoeditor.stripocdnplugin.email/content/stripoeditor/template/template5.css");
            $(By.id("submit")).click();
            sleep(1000);

            // Проверка существования элементов
            $("#stripoSettingsContainer").should(exist);
            $("#previewPanel").should(exist);
            System.out.println("Test Passed");
            result = true;
        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        }

        // Отправка результата в Better Stack
        sendResultToBetterStack(result, System.getenv("BETTER_STACK_API_TOKEN"));
    }

    public static void sendResultToBetterStack(boolean result, String apiToken) {
        String url = "https://uptime.betterstack.com/api/v1/monitoring/checks/2455345/results";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        try {
            String json = "{\"status\": \"" + (result ? "up" : "down") + "\"}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Authorization", "Bearer " + apiToken);
            httpPost.setHeader("Content-Type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            String responseString = EntityUtils.toString(response.getEntity());
            System.out.println("Response: " + responseString);
            client.close();
        } catch (Exception e) {
            System.out.println("Failed to send result: " + e.getMessage());
        }
    }
}
