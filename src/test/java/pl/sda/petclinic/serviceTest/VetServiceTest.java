package pl.sda.petclinic.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.petclinic.model.Vet;
import pl.sda.petclinic.repositories.VetRepository;
import pl.sda.petclinic.services.VetService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class VetServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetService vetService;

    private Vet vet = new Vet();

    @BeforeEach
    public void setup(){
        vet.setFirstName("Test");
        vet.setLastName("LastName");
        vet.setId(1L);
    }

    @Test
    public void createsNewVet() {
        Vet vet = new Vet("Fake", "Vet");
        vetService.createVet(vet);
        verify(vetRepository, times(1)).save(vet);
    }

    @Test
    public void getsAllVets() {
        vetService.getAllVets();
        verify(vetRepository, times(1)).findAll();
    }

    @Test void returnsOneVet() {
        given(vetRepository.findById(1L)).willReturn(Optional.of(vet));

        Vet savedVet = vetService.getVet(vet.getId());

        verify(vetRepository, times(1)).findById(1L);
        assertThat(savedVet).isNotNull();
        assertThat(savedVet.getFirstName()).isUpperCase();
    }

}
