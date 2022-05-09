package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;


class ServiceTest {
    private static Service service;

    private static Validator<Student> studentValidator = new StudentValidator();
    private static Validator<Homework> homeworkValidator = new HomeworkValidator();
    private static Validator<Grade> gradeValidator = new GradeValidator();

    @BeforeEach
    public void setUp() {
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void saveStudent() {
        assertEquals(1, service.saveStudent("1", "Kiki", 533), "ok");
    }

    @Test
    public void saveHomework() {
        assertEquals(1, service.saveHomework("1", "lab1", 22, 12), "ok");
    }

    @Test
    public void deleteStudent() {
        assertNotEquals(0, service.deleteStudent("1"));
    }

    @Test
    public void deleteHomework() {
        assertNotEquals(1, service.deleteHomework("100"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "100", "-5"})
    public void deleteHomework(String id) {
        assertTrue(service.deleteHomework(id) == 0);
    }

    @Test
    public void updateHomework() {
        assertTrue(service.updateHomework("1", "lab1", 23, 12) == 1);
    }

}