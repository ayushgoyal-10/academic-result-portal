package com.result_portal.repository;

import com.result_portal.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByRollNumber(String rollNumber);
    @Query("SELECT s FROM Student s WHERE s.rollNumber =:rollNumber AND s.dateOfBirth=:dob")
    Optional<Student> findByRollNumberAndDob(@Param("rollNumber") String rollNumber, @Param("dob") LocalDate dob);
}
