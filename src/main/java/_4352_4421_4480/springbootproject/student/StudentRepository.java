package _4352_4421_4480.springbootproject.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>
{
    Optional<Student> findById(int id);
}
