package com.yadier.LoExquisitoMar;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yadier.LoExquisitoMar.configuration.Paginas;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@SpringBootTest
public class PaginasTest {
	@Autowired
	WebDriver driver;
	
	
	@Test
	@DisplayName("Ver Reseva")
	 void navigateToReserva() {
		
		String baseUrl = "http://localhost:8080";
        driver.get(baseUrl);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(By.xpath("//a[@href='/reserva']")).click();
       
		assertEquals("Reserva", driver.getTitle());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	@Test
	@DisplayName("Ver Pedidos")
	 void navigateToRP() {
		
		String baseUrl = "http://localhost:8080";
	        driver.get(baseUrl);
	       
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        driver.findElement(By.xpath("//a[@href='/pedidos']")).click();
	       
			assertEquals("Mis pedidos", driver.getTitle());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	
	@Test
	@DisplayName("Validar nombre pagina")
	void validateNamePage() {
		 assertAll(
				 () -> assertEquals("product", Paginas.PRODUCT," Pagina correcta"),
				 () -> assertEquals("reserva", Paginas.RESERVA,"Pagina correcta"),
				 () -> assertEquals("mispedidos", Paginas.PEDIDOS, "Pagina correcta")
				 );
	}
}
