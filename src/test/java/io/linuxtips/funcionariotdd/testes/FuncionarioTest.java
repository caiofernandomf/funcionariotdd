package io.linuxtips.funcionariotdd.testes;

import io.linuxtips.funcionariotdd.model.Funcionario;
import io.linuxtips.funcionariotdd.repository.FuncionarioRepository;
import io.linuxtips.funcionariotdd.testes.mock.FuncionarioMock;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(LocalStackConfig.class)
@ActiveProfiles("test")
public class FuncionarioTest {

    static {
        RestAssured.baseURI="HTTP://localhost:8080/funcionario";
    }

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public Response criaFuncionario(Funcionario funcionario)throws Exception{
        RequestSpecification requestSpecification =
                given()
                        .contentType("application/json")
                        .body(funcionario);
        return requestSpecification.post("/salvar");
    }

    private ResponseSpecification responseSpecification(int responseStatus){
        return new ResponseSpecBuilder()
                .expectStatusCode(responseStatus)
                .build();
    }

    @Test
    @DisplayName("Deve cadastrar um funcionario com sucesso")
    public void deveCriarFuncionarioComSucesso(){
        try{
            Funcionario funcSalvo=
                    criaFuncionario(new Funcionario("123","Fernando",4000.0D))
                    .then()
                    .assertThat().spec(responseSpecification(201))
                    .and()
                    .assertThat()
                    .body("nome",equalTo("Fernando")).extract().as(Funcionario.class);
            assertEquals(funcSalvo.getRemuneracao(),4000.0D);


        }catch (Exception e){
            fail("Não foi possível cadastrar um funcionario",e);
        }
    }

    @Test
    @DisplayName("Deve retornar todos os funcionarios cadastrados com sucesso")
    public void deveListarTodosOsFuncionariosComSucesso(){
        funcionarioRepository.saveAll(FuncionarioMock.mockListFuncionarios());

        List<Funcionario> lista =
                given().
                when()
                .basePath("/listar")
                .get("")
                .then()
                .assertThat()
                .spec(responseSpecification(200))
                .and().extract().jsonPath().getList(".", Funcionario.class);

        assertTrue(lista.size()>0);

    }

    @Test
    @DisplayName("Deve retornar um funcionário pelo id com sucesso")
    public void deveRetornarFuncionarioComSucesso(){
        funcionarioRepository.save(FuncionarioMock.mockFuncionario());

        Funcionario funcionario =
        given().
                when()
                .basePath("/")
                .get("/456")
                .then()
                .assertThat()
                .spec(responseSpecification(200))
                .assertThat()
                .extract().as(Funcionario.class);

        assertEquals(funcionario.getNome(),FuncionarioMock.mockFuncionario().getNome());
        assertEquals(funcionario.getId(),FuncionarioMock.mockFuncionario().getId());
        assertEquals(funcionario.getRemuneracao(),FuncionarioMock.mockFuncionario().getRemuneracao());
    }

    @Test
    @DisplayName("Deve retornar um funcionário pelo id com erro")
    public void deveRetornarFuncionarioComErro(){

                given().
                        when()
                        .basePath("/")
                        .get("/456")
                        .then()
                        .assertThat()
                        .spec(responseSpecification(404));

    }
}
