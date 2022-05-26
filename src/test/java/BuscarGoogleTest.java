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
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.*;

/****************************************/
// Historia de Usuario: Como usuario quiero buscar terminos en Google
//
// Prueba de Aceptacion: Verificar que el criterio de busqueda sea mostrado en los resultados
/****************************************/

public class BuscarGoogleTest {
    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception{
        //geckodriver
        String path = "/Users/gustavo/Downloads/chromedriver102";
        
        System.setProperty("webdriver.chrome.driver", path);
        //System.setProperty("webdriver.gecko.driver", path);
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
        
        /*driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        */
    }
    
    @Test
    public void paginaPrincipalGoogle(){
        
        //1. Preparacion de la prueba
        String googleUrl = "https://www.google.com";
        driver.get(googleUrl);
        
        //2. Logica de la prueba
        
        //1. Capturar el campo de busqueda
        WebElement campoBusqueda = driver.findElement(By.name("q"));
        
        //2. Escribir el criterio de busqueda
        campoBusqueda.sendKeys("Universidad Catolica Boliviana");
        
        
        //3. Mandar peticion
        campoBusqueda.submit();
        
        
        try{
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        
        //3. Verificacion de la situacion esperada - Assert
        WebElement resultado = driver.findElement(By.xpath("/html/body/div[7]/div/div[10]/div[2]/div/div/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/h2/span"));
        
        
        String label = resultado.getText();
        System.out.println("Texto del resultado:: "+label);
        
        Assert.assertEquals("Universidade Católica Boliviana San Pablo (Universidad Católica Boliviana \"San Pablo\" Regional La Paz)", label);
        
    }
    
    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
    
}
