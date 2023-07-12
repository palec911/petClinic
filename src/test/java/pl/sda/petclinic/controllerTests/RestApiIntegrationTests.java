package pl.sda.petclinic.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import pl.sda.petclinic.SecurityTestConfig;
import pl.sda.petclinic.model.Owner;
import pl.sda.petclinic.model.Pet;
import pl.sda.petclinic.model.PetType;
import pl.sda.petclinic.repositories.OwnerRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = ("spring.h2.console.enabled=true"))
@ContextConfiguration(classes = SecurityTestConfig.class)
public class RestApiIntegrationTests {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @Autowired
    OwnerRepository ownerRepository;

    @Test
    public void addOwner() {

        //Given
        Owner owner = new Owner( "Piotr", "Palcz", "Andersa","Gdansk");
        HttpEntity<Owner> entity = new HttpEntity<Owner>(owner, headers);
        //When
        ResponseEntity<Owner> response = restTemplate //.withBasicAuth("ppalczew", "fun123")
                .exchange(
                        createURLWithPort("/api/owner"),
                        HttpMethod.POST, entity, Owner.class);
        //Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotEquals(ownerRepository.findAll().size(), 0);
        assert(ownerRepository.findById(1L).get().getId().equals(response.getBody().getId()));
        assert(ownerRepository.findById(1L).get().getFirstName().equals(owner.getFirstName()));
        assert(ownerRepository.findById(1L).get().getLastName().equals(owner.getLastName()));
        assert(ownerRepository.findById(1L).get().getCity().equals(owner.getCity()));
        assert(ownerRepository.findById(1L).get().getAddress().equals(owner.getAddress()));
    }

    @Test
    public void addOwner_failsWithWrongBody() {
        Pet pet = new Pet("Sonia", LocalDate.now(), PetType.DOG);
        HttpEntity<Pet> entity = new HttpEntity<Pet>(pet, headers);
        //When
        ResponseEntity<Pet> response = restTemplate //.withBasicAuth("ppalczew", "fun123")
                .exchange(
                        createURLWithPort("/api/owner"),
                        HttpMethod.POST, entity, Pet.class);
        System.out.println(response);
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    private String createURLWithPort(String uri) {
        int port = 8080;
        return "http://localhost:" + port + uri;
    }
}
