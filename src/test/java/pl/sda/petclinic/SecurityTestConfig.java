package pl.sda.petclinic;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@TestConfiguration
public class SecurityTestConfig {

    @MockBean
    public SecurityFilterChain filterChain;
    @MockBean
    public UserDetailsManager userDetailsManager;
}
