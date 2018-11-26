package com.vdranik.student.dal;

import com.vdranik.student.dal.repos.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentdalApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testCreateStudent() {
		Student student = new Student();
		student.setName("John");
		student.setCourse("Web Services");
		student.setFee(30D);

		studentRepository.save(student);
	}

	@Test
	public void testFindStudentById(){
		Optional<Student> student = studentRepository.findById(1L);
		System.out.println(student.get());
	}

	@Test
	public void testUpdateStudentById(){
		Optional<Student> student = studentRepository.findById(1L);
		student.get().setFee(40D);

		studentRepository.save(student.get());
	}

	@Test
	public void testDeleteStudentById(){
		studentRepository.deleteById(1L);

		Optional<Student> student = studentRepository.findById(1L);
		System.out.println(student.isPresent());
	}

}
