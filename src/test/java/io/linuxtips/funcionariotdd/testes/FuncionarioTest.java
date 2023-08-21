package io.linuxtips.funcionariotdd.testes;

import io.linuxtips.funcionariotdd.model.Funcionario;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(LocalStackConfig.class)
@ActiveProfiles("test")
public class FuncionarioTest {

    static {
        RestAssured.baseURI="HTTP://localhost:8080/funcionario";
    }

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
}
