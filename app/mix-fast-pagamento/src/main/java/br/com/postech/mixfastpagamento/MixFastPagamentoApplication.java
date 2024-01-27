package br.com.postech.mixfastpagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MixFastPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MixFastPagamentoApplication.class, args);
	}

}
