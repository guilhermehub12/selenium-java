package login;

import org.junit.jupiter.api.*;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach() {
        paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        paginaDeLogin.fechar();
    }

    @Test
    public void deveEfetuarLoginComDadosValidos() {
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuaLogin();
        Assertions.assertFalse(paginaDeLogin.isLoginPage());
        Assertions.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveEfetuarLoginComDadosInvalidos() {
        paginaDeLogin.preencheFormularioDeLogin("invalid", "pass123");
        paginaDeLogin.efetuaLogin();

        Assertions.assertTrue(paginaDeLogin.isLoginPageComDadosInvalidos());
        Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());
        Assertions.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        paginaDeLogin.navegaParaPaginaDeLances();
        Assertions.assertTrue(paginaDeLogin.isLoginPage());
        Assertions.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));

    }
}
