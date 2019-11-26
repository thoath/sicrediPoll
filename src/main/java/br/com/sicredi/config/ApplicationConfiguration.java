package br.com.sicredi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracoes do componente modelMapper, de conversao de beans
 * @author lucaskoch
 * @see http://modelmapper.org/user-manual/
 */
@Configuration
public class ApplicationConfiguration {

	   @Bean
	   public ModelMapper modelMapper() {
	     
	      return new ModelMapper();
	   }
	
}
