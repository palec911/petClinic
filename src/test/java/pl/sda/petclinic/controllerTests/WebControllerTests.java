package pl.sda.petclinic.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.sda.petclinic.SecurityTestConfig;
import pl.sda.petclinic.controller.WebController;
import pl.sda.petclinic.services.PetService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
@ContextConfiguration(classes = SecurityTestConfig.class)
public class WebControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PetService petService;

    @Test
    public void homepageReturnsHelloWorld() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/hello"))
                .andExpect(status().isOk()).andReturn();
        assertEquals(result.getModelAndView().getViewName(), "helloworld");
    }
}
