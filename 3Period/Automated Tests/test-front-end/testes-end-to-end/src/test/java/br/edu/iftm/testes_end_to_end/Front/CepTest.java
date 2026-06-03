package br.edu.iftm.testes_end_to_end.Front;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CepTest {

    private WebDriver driver; //webdriver que vai acessar o site para testar

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    // Cenário de teste
    @Test
    @DisplayName("Verifica se o CEP correto retorna o endereço correto.")
    public void testarCorreiosCepCorreto() {
        //Arrange
        String urlTestada = "https://www.achecep.com.br/";
        String tituloPaginaTestada = "Busca CEP Correios - Consulta e Pesquisa CEP das Ruas";
        String tituloPesquisado = "Busca CEP 38401-036 em Minas Gerais - MG"; //Definir a página a ser testada
        String cepBuscado = "38401-036";

        driver.get(urlTestada);
        WebElement inputCep = driver.findElement(By.name("q"));
        WebElement botaoConsultar = driver.findElement(By.cssSelector("#btnConsultar"));
        assertEquals(tituloPaginaTestada, driver.getTitle());
        assertEquals(urlTestada, driver.getCurrentUrl());

        inputCep.sendKeys("38401-036");
        botaoConsultar.click();

        WebElement linkCep = driver.findElement(By.linkText("38401-036"));

        //assert
        assertEquals(tituloPesquisado, driver.getTitle());
        assertEquals(cepBuscado, linkCep.getText());
    }

    // @AfterEach
    // public void exit() {
    //     driver.close();
    // }
}
