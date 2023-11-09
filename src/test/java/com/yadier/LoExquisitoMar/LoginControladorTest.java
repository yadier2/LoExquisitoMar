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
public class LoginControladorTest {
	Model model = null;
	String baseUrl = "http://localhost:8080/login";
	@Autowired
	WebDriver driver;
	LoginControlador login = Mockito.mock(LoginControlador.class);
	
	@Test
	@DisplayName("Ver login")
	 void navigateToLogin() {
        driver.get(baseUrl);
        assertEquals("Inicio de sesión", driver.getTitle());
	}
	
	@Test
	@DisplayName("Valiar page login")
	 void pageLogin() {
		Mockito.when( login.iniciarSesion()).thenReturn("login");
	}
	
	@Test
	@DisplayName("Validar usuario y password")
	 void validateUserPassword() {
        driver.get(baseUrl);
        WebElement user = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("password"));
        user.sendKeys("admin1");
        password.sendKeys("1234");
       
        WebElement btlLogin = driver.findElement(By.name("blogin"));
        btlLogin.submit();
        WebElement errorData = driver.findElement(By.name("error"));
       
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        assertEquals("Usuario o contraseña inválidos", errorData.getText());
	}
	
	@Test
	@DisplayName("Usuario y contraseña correctos")
	void userAndPasswordsuccessful(){
		   driver.get(baseUrl);
		   WebElement user = driver.findElement(By.name("email"));
	        WebElement password = driver.findElement(By.name("password"));
	        user.sendKeys("admin");
	        password.sendKeys("1234");
	        WebElement btlLogin = driver.findElement(By.name("blogin"));
	        btlLogin.submit();
	        try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        assertEquals("Home",  driver.getTitle());
	 }
}
