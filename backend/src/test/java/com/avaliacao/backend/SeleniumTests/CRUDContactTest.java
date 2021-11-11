package com.avaliacao.backend.SeleniumTests;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CRUDContactTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void AddContact() {
    driver.navigate().to("http://localhost:3000/");
    driver.findElement(By.name("add-button")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).sendKeys("Thainá");
    driver.findElement(By.name("email")).sendKeys("thainawchagas@gmail.com");
    driver.findElement(By.name("phone")).sendKeys("84994592656");
    driver.findElement(By.name("save-button")).click();
  }

  @Test
  public void editUser() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(1) path")).click();
    driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(3)")).click();
    driver.findElement(By.name("phone")).sendKeys(Keys.CONTROL + "a");
    driver.findElement(By.name("phone")).sendKeys(Keys.DELETE);
    driver.findElement(By.name("phone")).sendKeys("41994592656");
    driver.findElement(By.name("save-button")).click();
  }

  @Test
  public void phoneConflict() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.name("add-button")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).sendKeys("Kael");
    driver.findElement(By.name("email")).sendKeys("kael@gmail.com");
    driver.findElement(By.name("phone")).sendKeys("45992228878");
    driver.findElement(By.name("save-button")).click();

    driver.findElement(By.name("add-button")).click();
    driver.findElement(By.name("name")).sendKeys("Luci");
    driver.findElement(By.name("email")).sendKeys("luci@gmail.com");
    driver.findElement(By.name("phone")).sendKeys("45992228878");
    driver.findElement(By.name("save-button")).click();

    WebDriverWait wait = new WebDriverWait(driver, 2);
    assertNotNull(wait.until(ExpectedConditions.alertIsPresent()));
    assertEquals("This phone is already registered", driver.switchTo().alert().getText());
    driver.switchTo().alert().accept();

    driver.findElement(By.cssSelector(".MuiDialog-container")).click();
    driver.findElement(By.name("phone")).click();
    driver.findElement(By.name("phone")).sendKeys("41992228878");
    driver.findElement(By.cssSelector(".MuiButton-contained")).click();
  }
}
