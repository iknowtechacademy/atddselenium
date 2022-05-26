import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegistroDZoneTest {
    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception{
        
    	 String path = "/Users/gustavo/Downloads/chromedriver102";
         
         System.setProperty("webdriver.chrome.driver", path);
         
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
        
    }
    
    @Test
    public void verificarMensajeErrorAlRegistrar(){
        
        //Preparacion de la prueba
        String dzoneUrl = "https://dzone.com";
        driver.get(dzoneUrl);
        
        //Logica de la prueba
        
        WebElement joinLink = driver.findElement(By.xpath("//*[@id=\"ng-app\"]/body/div[2]/div/div/div[1]/div/div[2]/div[2]/a[2]"));
        String linkText = joinLink.getText();
        
        System.out.println("Texto del Link:: "+linkText);
        
        joinLink.click();
        
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        
        //Hacer click en boton JOIN
        
        WebElement joinButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[2]/div[3]/button"));
        
        joinButton.click();
        
        System.out.println("Button Text:: "+joinButton.getText());
        
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        
        //Verificacion - ASSERT
        
        WebElement iconAlert = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[2]/div[1]/div[2]/form/div[1]/span[2]/i"));
        Assert.assertEquals(true, iconAlert.isDisplayed());
        
        System.out.println("Se muestra el icono? "+iconAlert.isDisplayed());
        
        //Validar el mensaje del correo
        WebElement emailErrorMessage = driver.findElement(By.xpath("//div[@data-validate=\"Please enter a valid email address\"]"));
        String attribute = emailErrorMessage.getAttribute("data-validate");
        System.out.println("Valor del attribute::: "+attribute);
        
        Assert.assertEquals("Please enter a valid email address", attribute);
        
        //Validar el mensaje del username
        iconAlert = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[2]/div[1]/div[2]/form/div[2]/span[2]/i"));
        Assert.assertEquals(true, iconAlert.isDisplayed());
        
        WebElement usernameErrorMessage = driver.findElement(By.xpath("//div[@data-validate=\"Please enter a valid username\"]"));
        attribute = usernameErrorMessage.getAttribute("data-validate");
        System.out.println("Valor del attribute::: "+attribute);
        
        Assert.assertEquals("Please enter a valid username", attribute);
    }
    
    
    
    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
    
    
    
}
