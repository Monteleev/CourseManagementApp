package _4352_4421_4480.springbootproject.dao;

import _4352_4421_4480.springbootproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
    Optional<Student> findById(Long id);
}
