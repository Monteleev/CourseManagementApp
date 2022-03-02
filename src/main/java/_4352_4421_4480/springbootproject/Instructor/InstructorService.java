package _4352_4421_4480.springbootproject.Instructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    public List<Instructor> getInstructor()
    {
        return List.of(
                new Instructor(
                        1L,"test",40,"test@gmail.com"
                )
        );
    }
}
