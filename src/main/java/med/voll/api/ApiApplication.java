package med.voll.api;

import med.voll.api.controller.MedicoController;
import med.voll.api.medico.DadosCadastrosMedico;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
