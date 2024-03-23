package br.com.dicasdeumdev.api.config;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration // is a configuration class
@Profile("local") // indicates that this class will only be executed when the local profile is active
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean //indicates that this method will return a bean and will be executed when the application starts
    public List<User> startDB() { //method to insert data into the database when the local profile is active
        // Código para iniciar o banco de dados local
        User user1 = new User(null, "Maria Brown", "maria@email", "123");
        User user2 = new User(null, "Maria Brown2", "maria@email2", "123");

        List<User> users = userRepository.saveAll(List.of(user1, user2));
        return users;
    }

}
