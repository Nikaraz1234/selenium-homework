package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommandsTest {

    @Test
    public void commandsTest() {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();

            driver.get("http://the-internet.herokuapp.com/dynamic_controls");

            WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
            enableButton.click();

            WebElement inputField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//input"))
            );

            WebElement message = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("message"))
            );

            if (inputField.isEnabled() && message.getText().equals("It's enabled!")) {
                System.out.println("Input field enabled and text visible");
            }

            WebElement disableButton = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Disable']"))
            );

            if (disableButton.getText().equals("Disable")) {
                System.out.println("Button text changed successfully");
            }

            inputField.sendKeys("Bootcamp");
            inputField.clear();

            driver.get("http://the-internet.herokuapp.com/drag_and_drop");

            WebElement columnA = driver.findElement(By.id("column-a"));
            WebElement columnB = driver.findElement(By.id("column-b"));

            int yA = columnA.getLocation().getY();
            int yB = columnB.getLocation().getY();

            if (yA == yB) {
                System.out.println("Columns A and B aligned successfully");
            }

        } finally {
            driver.quit();
        }
    }
}
