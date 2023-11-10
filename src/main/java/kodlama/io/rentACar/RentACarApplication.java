package kodlama.io.rentACar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args); 
	}

	//Bean gerekli çünkü ModelMapper birisi ihtiyaç duyduğunda ModelMapper'ın anotasyonu olmadığı için oluşturur
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
