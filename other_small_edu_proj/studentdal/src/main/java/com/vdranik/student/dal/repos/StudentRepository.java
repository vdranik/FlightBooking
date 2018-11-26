package com.vdranik.student.dal.repos;

import com.vdranik.student.dal.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
