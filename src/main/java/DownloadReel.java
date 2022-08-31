import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class DownloadReel {
    public static void main(String[] args) throws InterruptedException {

        // Configuring the system properties of chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver_win32\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver(options);


        driver.get("https://instafinsta.com/reels");

        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);


        Duration duration = Duration.ofSeconds(10,0);

        WebDriverWait wait = new WebDriverWait(driver, duration);

        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(20,0));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("link")));

        WebElement link = driver.findElement(By.id("link"));

        link.clear();

        link.sendKeys("https://www.instagram.com/p/Cg8nYPklgeO/");

        wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("get")));

        WebElement search =  driver.findElement(By.id("get"));


        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .elementToBeClickable(By.id("get")));

        search.click();



        By byLocator = By.tagName("video");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .presenceOfElementLocated(byLocator));
        WebElement findElement = driver.findElement(byLocator);
        System.out.println(findElement.getText());
        System.out.println("Src file : " + findElement.getAttribute("src"));

        wait1.until(ExpectedConditions.presenceOfElementLocated(By.tagName("video")));

        WebElement video = driver.findElement(By.tagName("video"));


        WebElement dowloadServer = driver.findElement(By.xpath("//*[@id=\"result\"]/div/div/div[2]/div/a[2]"));

        System.out.println(dowloadServer.getAttribute("href"));

        String videoLink = dowloadServer.getAttribute("href");


        try (BufferedInputStream in = new BufferedInputStream(new URL(videoLink).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("reel.mp4")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }


        //driver.quit();
    }
}
