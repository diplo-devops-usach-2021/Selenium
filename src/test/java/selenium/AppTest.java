package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    private WebDriver driver;

    @Before
    public void setUp(){
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Iniciando Pruebas...");
    }

    @Test
    public void pruebaGoogle()
    {
        System.out.println("Se inicia el caso de prueba de busqueda en Google...");
        driver.navigate().to("https://www.google.com");
        WebElement searchbox = driver.findElement(By.name("q"));
        searchbox.sendKeys("HandBook Devops Ripley");
        searchbox.submit();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement libro = driver.findElement(By.xpath("//*[contains(text(),'simple.ripley')]//ancestor::a[1]"));
        libro.click();
        WebElement foto = driver.findElement(By.xpath("//img[contains(@src,'//ripleycl.imgix.net/https%3A%2F%2Fimages-na.ssl-images-amazon.com%2Fimages%2FI%2F51Z6uQ57ilL')][1]"));
        assertTrue(foto.isDisplayed());
        System.out.println("Assert - La imagen esta presente.");
        WebElement comprar = driver.findElement(By.xpath("//button[contains(text(),'Agotado')]"));
        assertEquals("Agotado", comprar.getText());
        System.out.println("Assert - No existe Stock de compra.");
    }

    @Test
    public void pruebaAmazon()
    {
        System.out.println("Se inicia el caso de prueba de busqueda en Amazon...");
        driver.get("https://www.amazon.com");
        WebElement buscador = driver.findElement(By.id("twotabsearchtextbox"));
        buscador.sendKeys("The Phoenix Project");
        buscador.submit();
        WebElement libro = driver.findElement(By.xpath("//*[contains(@alt,'The Phoenix Project:')][1]"));
        libro.click();
        assertEquals("Amazon.com: The Phoenix Project: A Novel about IT, DevOps, and Helping Your Business Win 5th Anniversary Edition (Edición audio Audible): Gene Kim, Kevin Behr, George Spafford, Chris Ruen, IT Revolution Press: Libros",driver.getTitle());
        System.out.println("Assert - Titulo correcto");
        
    }

    @After
    public void setOff()
    {
        driver.close();
    }
}
