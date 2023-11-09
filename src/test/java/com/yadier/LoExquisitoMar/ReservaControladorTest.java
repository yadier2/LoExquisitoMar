package com.yadier.LoExquisitoMar;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.yadier.LoExquisitoMar.configuration.Paginas;
import com.yadier.LoExquisitoMar.controllers.LoginControlador;
import com.yadier.LoExquisitoMar.controllers.RegistroUsuarioControlador;
import com.yadier.LoExquisitoMar.models.Usuario;

@SpringBootTest
public class ReservaControladorTest {
	Model model = null;
	String baseUrl = "http://localhost:8080/reserva";

	@Autowired
	WebDriver driver;

	@Test
	@DisplayName("Validar reserva")
	void validateBooking() {
		driver.get(baseUrl);
		WebElement fecha = driver.findElement(By.id("fecha"));
		WebElement hora = driver.findElement(By.id("hora"));
		WebElement numPersonas = driver.findElement(By.id("numPersonas"));
		WebElement mesa = driver.findElement(By.id("mesa"));
		WebElement errorData = null;
		fecha.sendKeys("07/10/2023");
		hora.sendKeys("07:30");
		numPersonas.sendKeys("3");
		mesa.sendKeys("2");

		WebElement btnReserva = driver.findElement(By.id("btnSaveR"));
		btnReserva.submit();

		errorData = driver.findElement(By.name("error"));
		assertEquals("La fecha debe ser mayor o igual al afecha actual.", errorData.getText());

		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	@DisplayName("Guardar reserva")
	void guardarReserva() {
		driver.get(baseUrl);
		WebElement fecha = driver.findElement(By.id("fecha"));
		WebElement hora = driver.findElement(By.id("hora"));
		WebElement numPersonas = driver.findElement(By.id("numPersonas"));
		WebElement mesa = driver.findElement(By.id("mesa"));
		fecha.sendKeys("08/12/2023");
		hora.sendKeys("07:30");
		numPersonas.sendKeys("3");
		mesa.sendKeys("2");

		WebElement btnReserva = driver.findElement(By.id("btnSaveR"));
		btnReserva.submit();

		assertEquals("Home", driver.getTitle());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
