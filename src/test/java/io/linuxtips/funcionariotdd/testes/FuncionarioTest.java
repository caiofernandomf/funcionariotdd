package io.linuxtips.funcionariotdd.testes;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@SpringBootTest
@Import(LocalStackConfig.class)
@ActiveProfiles("test")
public class FuncionarioTest {

    static {
        RestAssured.baseURI="HTTP://localhost:8080/";
    }

    public Response criaFuncionario(Funcionario funcionario)throws Exception{
        RequestSpecification requestSpecification =
                given()
                        .contentType("application/json")
                        .body(funcionario);
        return requestSpecification.post("/funcionario");
    }

    private ResponseSpecification responseSpecification(int responseStatus){
        return new ResponseSpecBuilder()
                .expectStatusCode(responseStatus)
                .build();
    }
}
