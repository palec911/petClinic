package pl.sda.petclinic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails ppalczew = User.builder()
                .username("ppalczew")
                .password("{noop}test123")
                .roles("USER")
                .build();

        UserDetails ahyz = User.builder()
                .username("ahyz")
                .password("{noop}test123")
                .roles("USER", "VET")
                .build();

        UserDetails sda = User.builder()
                .username("sda")
                .password("{noop}test123")
                .roles("USER", "VET", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(sda,ppalczew,ahyz);
    }
}