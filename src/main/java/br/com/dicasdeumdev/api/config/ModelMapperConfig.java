package br.com.dicasdeumdev.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Em vez de instanciar um ModelMapper em cada classe de serviço (adicionando um new ModelMapper em cada classe), podemos criar uma classe de configuração para o ModelMapper.
 * Desse modo configuramos para que o SPRING fique responsável por instanciar o ModelMapper e injetar onde for necessário.
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
