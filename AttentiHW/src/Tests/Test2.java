package Tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Test2 extends Test1 {
    //convert from Celsius to Celsius
    @Test
    public void apiTest() {
        HttpURLConnection con = null;
        StringBuilder result = new StringBuilder();
        try {

            URL myurl = new URL("https://jsonplaceholder.typicode.com/posts/1");
            HttpURLConnection conection = (HttpURLConnection) myurl.openConnection();
            conection.setConnectTimeout(99999999);
            conection.setRequestMethod("GET");
            conection.getResponseCode();
            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } catch (Exception e) {

        } finally {

            con.disconnect();
        }

    }

    @Test
    public void wetherTestGui() {
        Assert.assertTrue(openSiteByChromDriver("https://www.weather.com"));
        String weather = getWeatherForecast("20852");


    }

    public String getWeatherForecast(String zipCode) {
        String temp = null, pharse = null;
        try {
            TimeUnit.SECONDS.sleep(15);
            driver.findElement(By.xpath("//input[@aria-label=\"Location Search\"]")).sendKeys(zipCode);
            TimeUnit.SECONDS.sleep(4);
            driver.findElement(By.cssSelector("a[class=\"styles__item__3sdr8 styles__selected__SEH0e\"]")).click();
            TimeUnit.SECONDS.sleep(15);
            temp = driver.findElement(By.cssSelector("div[class=today_nowcard-temp]")).getText();
            pharse = driver.findElement(By.cssSelector("div[class=today_nowcard-phrase]")).getText();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return temp + " " + pharse;

    }
}
