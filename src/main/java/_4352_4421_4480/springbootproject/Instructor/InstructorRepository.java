package _4352_4421_4480.springbootproject.Instructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository
        extends JpaRepository<Instructor,Long>
{

}
