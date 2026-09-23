package com.thiagoferreira.ServiceFlow.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.thiagoferreira.ServiceFlow.entities.User;
import com.thiagoferreira.ServiceFlow.enums.Role;
import com.thiagoferreira.ServiceFlow.repositories.UserRepository;

@Configuration 
@Profile("test")
public class TestConfig implements CommandLineRunner{
    @Autowired  
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Vai rodar oque estiver aqui dentro sempre que a aplicação for iniciada
        User u1 = new User(null, "Maria Silva", "maria@serviceflow.com", "123456", Role.ADMIN);

        User u2 = new User(null, "João Souza", "joao@serviceflow.com", "123456", Role.SUPPORT_AGENT);

        User u3 = new User(null, "Ana Oliveira", "ana@serviceflow.com", "123456", Role.SUPPORT_AGENT);

        User u4 = new User(null, "Carlos Santos", "carlos@serviceflow.com", "123456", Role.SUPPORT_AGENT);

        User u5 = new User(null, "Mariana Costa", "mariana@serviceflow.com", "123456", Role.SUPPORT_AGENT);

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));
    }

    /* */
}
