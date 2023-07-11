package pl.sda.petclinic.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sda.petclinic.PetClinicApplication;
import pl.sda.petclinic.controller.RestApiController;
import pl.sda.petclinic.model.Note;
import pl.sda.petclinic.model.Owner;
import pl.sda.petclinic.model.Pet;
import pl.sda.petclinic.model.PetType;
import pl.sda.petclinic.repositories.OwnerRepository;
import pl.sda.petclinic.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RestApiControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PetClinicApplication petClinicApplication;

    @MockBean
    private OwnerRepository ownerRepository;

    @MockBean
    private OwnerService ownerService;

    @MockBean
    private VisitService visitService;

    @MockBean
    private VetService vetService;

    @MockBean
    private VetSpecialityService vetSpecialityService;

    @MockBean
    private PetService petService;

    @Test
    public void getAllOwnersAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/owners"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", empty()));
    }

    private Pet sonia = new Pet("Sonia", LocalDate.now(), PetType.DOG, new Note("Test Description"));
    private HashSet<Pet> pets = new HashSet<>(Arrays.asList(sonia));

    private Owner ppalczew = new Owner("Piotr", "Palczewski", "Andersa", "Gdansk", pets);

    @Test
    public void createNewOwnerPositive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/owner")
                        .content(asJsonString(ppalczew)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pets").exists());
    }

    // Using Mockito.when to make ownerService Mock to return a stubbed List of Owners to test if
    // passes further a proper value to the controller
    @Test
    public void getAllOwnersWithMock() throws Exception {
        Mockito.when(ownerService.getAll()).thenReturn(new ArrayList<Owner>(Arrays.asList(new Owner(), new Owner())));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/owners")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)));
        verify(ownerService, times(1)).getAll();
    }

    @Test
    public void createNewOwner_FailsWithBadBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/owner")
                .content(asJsonString(sonia)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}