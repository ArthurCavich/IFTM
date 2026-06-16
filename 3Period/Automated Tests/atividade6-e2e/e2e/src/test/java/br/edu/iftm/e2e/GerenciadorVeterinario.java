package br.edu.iftm.e2e;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GerenciadorVeterinario {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String TITULO_PAGINA = "Gerenciador de Veterinários";

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    /**
     * Cenário: ao preencher o formulário de cadastro e confirmar, o novo
     * veterinário
     * deve aparecer na listagem.
     */
    @Test
    @Order(1)
    public void testarCadastrarVeterinarioAdicionaRegistroNaListagem() {
        // arrange
        String nome = "Marcos Silva";
        String email = "marcos@gmail.com";
        String especialidade = "dermatologia";
        String salario = "4200.0";

        driver.get(BASE_URL + "/form");

        assertEquals("Cadastrar novo veterinário", driver.findElement(By.tagName("h1")).getText());

        WebElement campoNome = driver.findElement(By.id("nome"));
        WebElement campoEmail = driver.findElement(By.id("inputEmail"));
        WebElement campoEspecialidade = driver.findElement(By.id("inputEspecialidade"));
        WebElement campoSalario = driver.findElement(By.id("inputSalario"));

        // act
        campoNome.sendKeys(nome);
        campoEmail.sendKeys(email);
        campoEspecialidade.sendKeys(especialidade);
        campoSalario.sendKeys(salario);

        assertEquals(nome, campoNome.getAttribute("value"));
        assertEquals(email, campoEmail.getAttribute("value"));
        assertEquals(especialidade, campoEspecialidade.getAttribute("value"));
        assertEquals(salario, campoSalario.getAttribute("value"));

        driver.findElement(By.xpath("//button[contains(.,'Cadastrar')]")).click();

        // assert
        assertEquals(TITULO_PAGINA, driver.getTitle());
        assertTrue(driver.getPageSource().contains(nome));
        assertEquals(especialidade, obterTextoCelulaTabelaPorNome(nome, 2));
        assertEquals(email, obterTextoCelulaTabelaPorNome(nome, 3));
    }

    /**
     * Cenário: ao informar parte do nome na tela de consulta, o sistema deve
     * retornar
     * apenas os veterinários correspondentes.
     */
    @Test
    @Order(2)
    public void testarPesquisarVeterinarioFiltraResultadoPorNome() {
        // arrange
        driver.get(BASE_URL + "/find");

        assertEquals("Pesquisar veterinários", driver.findElement(By.tagName("h1")).getText());

        WebElement campoNome = driver.findElement(By.id("nome"));

        // act
        campoNome.sendKeys("ROB");
        assertEquals("ROB", campoNome.getAttribute("value"));
        driver.findElement(By.xpath("//button[contains(.,'Consultar')]")).click();

        // assert
        assertEquals(TITULO_PAGINA, driver.getTitle());
        assertEquals("ROBERTO", obterTextoCelulaTabela(1, 2));
        assertEquals("cirurgiao", obterTextoCelulaTabela(1, 3));
        assertEquals("roberto@gmail.com", obterTextoCelulaTabela(1, 4));
    }

    /**
     * Cenário: ao excluir um veterinário, ele não deve mais aparecer na listagem.
     */
    @Test
    @Order(3)
    public void testarExcluirVeterinarioRemoveRegistroDaListagem() {
        // arrange
        driver.get(BASE_URL + "/home");

        assertTrue(driver.getPageSource().contains("CARLA"));

        // act
        driver.findElement(By.xpath("//tr[td/span[text()='CARLA']]/td/a[contains(@class,'btn-danger')]")).click();

        // assert
        assertEquals(TITULO_PAGINA, driver.getTitle());
        assertTrue(!driver.getPageSource().contains("CARLA"));
    }

    /**
     * Cenário: ao editar um veterinário existente, o formulário deve carregar os
     * dados
     * atuais e persistir as alterações na listagem.
     */
    @Test
    @Order(4)
    public void testarAlterarVeterinarioAtualizaDadosNaListagem() {
        // arrange
        String nomeAlterado = "JOAO ATUALIZADO";
        String emailAlterado = "joao.atualizado@gmail.com";
        String especialidadeAlterada = "cardiologia";
        String salarioAlterado = "3100.0";

        driver.get(BASE_URL + "/home");
        driver.findElement(By.xpath("//tr[td/span[text()='JOAO']]/td/a[contains(@class,'btn-warning')]")).click();

        assertEquals("Atualizar informacoes", driver.findElement(By.tagName("h1")).getText());

        WebElement campoNome = driver.findElement(By.id("nome"));
        WebElement campoEmail = driver.findElement(By.id("inputEmail"));
        WebElement campoEspecialidade = driver.findElement(By.id("inputEspecialidade"));
        WebElement campoSalario = driver.findElement(By.id("inputSalario"));

        assertEquals("JOAO", campoNome.getAttribute("value"));
        assertEquals("joao@gmail.com", campoEmail.getAttribute("value"));
        assertEquals("felinos", campoEspecialidade.getAttribute("value"));
        assertEquals("2500.00", campoSalario.getAttribute("value"));

        // act
        campoNome.clear();
        campoEmail.clear();
        campoEspecialidade.clear();
        campoSalario.clear();
        campoNome.sendKeys(nomeAlterado);
        campoEmail.sendKeys(emailAlterado);
        campoEspecialidade.sendKeys(especialidadeAlterada);
        campoSalario.sendKeys(salarioAlterado);

        assertEquals(nomeAlterado, campoNome.getAttribute("value"));
        assertEquals(emailAlterado, campoEmail.getAttribute("value"));
        assertEquals(especialidadeAlterada, campoEspecialidade.getAttribute("value"));
        assertEquals(salarioAlterado, campoSalario.getAttribute("value"));

        driver.findElement(By.xpath("//button[contains(.,'Atualizar')]")).click();

        // assert
        assertEquals(TITULO_PAGINA, driver.getTitle());
        assertEquals(nomeAlterado, obterTextoCelulaTabelaPorNome(nomeAlterado, 1));
        assertEquals(especialidadeAlterada, obterTextoCelulaTabelaPorNome(nomeAlterado, 2));
        assertEquals(emailAlterado, obterTextoCelulaTabelaPorNome(nomeAlterado, 3));
    }

    /**
     * Cenário: ao acessar a tela inicial, o sistema deve listar todos os
     * veterinários
     * cadastrados no banco de dados.
     */
    @Test
    @Order(5)
    public void testarListarVeterinariosExibeDadosDoBanco() {
        // arrange
        driver.get(BASE_URL + "/home");

        // act

        // assert
        assertEquals(TITULO_PAGINA, driver.getTitle());
        assertEquals("Veterinarios", driver.findElement(By.tagName("h1")).getText());
        assertEquals("Nome", driver.findElement(By.xpath("//th[contains(.,'Nome')]")).getText());
        assertEquals("PEDRO", obterTextoCelulaTabela(1, 2));
        assertEquals("ROBERTO", obterTextoCelulaTabela(2, 2));
        assertEquals("JOAO ATUALIZADO", obterTextoCelulaTabela(3, 2));
        assertEquals("ANA", obterTextoCelulaTabela(4, 2));
        assertEquals("Marcos Silva", obterTextoCelulaTabela(5, 2));
    }

    private String obterTextoCelulaTabela(int linha, int coluna) {
        WebElement celula = driver
                .findElement(By.cssSelector("tr:nth-child(" + (linha + 1) + ") > td:nth-child(" + coluna + ")"));
        return celula.getText();
    }

    private String obterTextoCelulaTabelaPorNome(String nome, int coluna) {
        WebElement celula = driver.findElement(By.xpath("//tr[td/span[text()='" + nome + "']]/td[" + coluna + "]"));
        return celula.getText();
    }

    @AfterEach
    public void exit() {
        driver.close();
    }
}
