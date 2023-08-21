package io.linuxtips.funcionariotdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestFuncionariotddApplication {

	public static void main(String[] args) {
		SpringApplication.from(FuncionariotddApplication::main).with(TestFuncionariotddApplication.class).run(args);
	}

}
