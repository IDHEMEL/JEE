package com.example.etudientapp;

import com.example.etudientapp.entities.Etudiant;
import com.example.etudientapp.entities.Genre;
import com.example.etudientapp.repositories.EtudiantRepository;
import com.example.etudientapp.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class EtudientAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtudientAppApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(EtudiantRepository etudiantRepository) {
        return args -> {
            etudiantRepository.save(
                    new Etudiant(null, "elmehdi", "ouajjani", "elmehdiouajjani@gmail.com", new Date(), Genre.MASCULIN, false));
            etudiantRepository.save(
                    new Etudiant(null, "ahmed", "khalid", "ahmedkhalid@gmail.com", new Date(), Genre.MASCULIN, false));
            etudiantRepository.save(
                    new Etudiant(null, "ali", "haytham", "alihaytham@gmail.com", new Date(), Genre.FEMININ, true));
            etudiantRepository.save(
                    new Etudiant(null, "mohamed", "alaoui", "mohamedalaoui@gmail.com", new Date(), Genre.FEMININ, true));

            etudiantRepository.findAll().forEach(etudiant -> {
                System.out.println(etudiant);
            });
        };
    }

    // @Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
            securityService.saveNewUser("elmehdi", "1234", "1234");
            securityService.saveNewUser("ouajjani", "1234", "1234");
            securityService.saveNewUser("mehdi", "1234", "1234");

            securityService.saveNewRole("USER", "");
            securityService.saveNewRole("ADMIN", "");

            securityService.addRoleToUser("ouajjani", "ADMIN");
            securityService.addRoleToUser("ouajjani", "USER");
            securityService.addRoleToUser("elmehdi", "USER");
            securityService.addRoleToUser("mehdi", "USER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
