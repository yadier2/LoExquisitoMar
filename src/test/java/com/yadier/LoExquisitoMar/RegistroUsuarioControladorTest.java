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
import com.yadier.LoExquisitoMar.controllers.ProductController;
import com.yadier.LoExquisitoMar.controllers.RegistroUsuarioControlador;
import com.yadier.LoExquisitoMar.models.Producto;
import com.yadier.LoExquisitoMar.models.Usuario;

@SpringBootTest
public class RegistroUsuarioControladorTest {
	String baseUrl = "http://localhost:8080/registro";
	
	@Autowired
	WebDriver driver;
	@Autowired 
	RegistroUsuarioControlador registroUsuarioC = new RegistroUsuarioControlador();
	
	@Autowired 
	ProductController productController = new ProductController();
	
	
	@Autowired 
	RegistroUsuarioControlador registroUsuario = Mockito.mock(RegistroUsuarioControlador.class);
	Model model = null;
	ModelAndView modelView = null;
	
	@Test
	@DisplayName("Validar datos registro usuario")
	public  void validarDatosUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNombre("");
		usuario.setApellido("caicedo");
		usuario.setEmail("ya@gmail.com");
		usuario.setPassword("");
		modelView = new ModelAndView();
		modelView= registroUsuarioC.registrarCuentaDeUsuario(usuario);

		assertEquals("registro", modelView.getViewName());
	}
	
	@Test
	@DisplayName("Registro usuario exitoso")
	public  void registroUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNombre("yadier");
		usuario.setApellido("caicedo");
		usuario.setEmail("ya@gmail.com");
		usuario.setDireccion("robledo calle 26");
		usuario.setPassword("awrygfdd34");
		modelView = new ModelAndView();
		modelView =  registroUsuarioC.registrarCuentaDeUsuario(usuario);
		System.out.println(modelView.getViewName());
		assertEquals("redirect:/login", modelView.getViewName());
	}
	
	@Test
	@DisplayName("Formulario registro usuario")
	 void validateDataUserForm() {
        driver.get(baseUrl);
        WebElement nombre = driver.findElement(By.id("nombre"));
        WebElement apellido = driver.findElement(By.id("apellido"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        
        
        //nombre.sendKeys("yadier");
        apellido.sendKeys("caicedo");
        email.sendKeys("admin");
        password.sendKeys("1234");
       
        WebElement btnSave = driver.findElement(By.id("btsave"));
        btnSave.submit();
        WebElement errorData = driver.findElement(By.id("err"));
       
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        assertEquals("El nombre debe tener mas de 3 letras y menos de 20.", errorData.getText());
	}
	
	@Test
	@DisplayName("Consulta producto exitoso")
	public  void consultaProducto() {
	    int id = 1;
		modelView = new ModelAndView();
		modelView =  productController.productoById(id);
		
	    Producto producto = (Producto) modelView.getModel().get("product");
		
		assertEquals(1, producto.getId(), "Producto 1 encontrado exitosamente" );
	}
	
}
