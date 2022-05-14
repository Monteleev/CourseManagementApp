package _4352_4421_4480.springbootproject.controller;

import _4352_4421_4480.springbootproject.entity.Course;
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
public class CourseControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CourseController courseController;

    @BeforeEach
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testCourseControllerIsNotNull() {
        Assertions.assertNotNull(courseController);
    }

    @Test
    void testMockMvcIsNotNull() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void testListCoursesReturnsPage() throws Exception {
        mockMvc.perform(get("/courses")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("courses")).
                andExpect(view().name("courses"));

    }

    @Test
    void testSaveCourseReturnsPage() throws Exception {

        Course course = new Course(3L, "Algebra","Syllabus","course description",1,1);

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("id", Long.toString(course.getId()));
        multiValueMap.add("name", course.getName());
        multiValueMap.add("syllabus", course.getSyllabus());
        multiValueMap.add("year", Integer.toString(course.getYear()));
        multiValueMap.add("semester", Integer.toString(course.getSemester()));

        mockMvc.perform(
                        post("/courses").
                                params(multiValueMap)).
                andExpect(status().isFound()).
                andExpect(view().name("redirect:/courses"));
    }

    @Test
    void testDeleteCourseReturnsPage() throws Exception {

        mockMvc.perform(
                        get("/courses/1")).
                andExpect(status().isFound()).
                andExpect(view().name("redirect:/courses"));
    }

}
