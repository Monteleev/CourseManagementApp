package _4352_4421_4480.springbootproject.controller;
import _4352_4421_4480.springbootproject.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc

public class StudentControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    StudentController studentController;

    @BeforeEach
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testStudentControllerIsNotNull() {
        Assertions.assertNotNull(studentController);
    }

    @Test
    void testMockMvcIsNotNull() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void testListStudentsReturnsPage() throws Exception {
        mockMvc.perform(get("/students")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("students")).
                andExpect(view().name("students"));

    }

    @Test
    void testSaveStudentReturnsPage() throws Exception {

        Student student = new Student(5L, "Andrew", 2019);

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("id", Long.toString(student.getId()));
        multiValueMap.add("name", student.getName());

        multiValueMap.add("yearOfRegistration", Long.toString(student.getYearOfRegistration()));

        mockMvc.perform(
                        post("/students").
                                params(multiValueMap)).
                andExpect(status().isFound()).
                andExpect(view().name("redirect:/students"));
    }

    @Test
    void testDeleteStudentReturnsPage() throws Exception {

        mockMvc.perform(
                        get("/students/1")).
                andExpect(status().isFound()).
                andExpect(view().name("redirect:/students"));
    }
}
