package pl.sda.petclinic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails ppalczew = User.builder()
//                .username("ppalczew")
//                .password("{noop}test123")
//                .roles("USER")
//                .build();
//
//        UserDetails ahyz = User.builder()
//                .username("ahyz")
//                .password("{noop}test123")
//                .roles("USER", "VET")
//                .build();
//
//        UserDetails sda = User.builder()
//                .username("sda")
//                .password("{noop}test123")
//                .roles("USER", "VET", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(sda,ppalczew,ahyz);
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( config ->
                config
                        .requestMatchers(HttpMethod.GET, "/api/owners").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/createVet").hasRole("VET")
                        .requestMatchers(HttpMethod.POST, "/api/createSpeciality").hasRole("ADMIN"));

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}