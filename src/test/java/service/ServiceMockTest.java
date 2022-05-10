package service;

import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

public class ServiceMockTest {
    private static Service service;

    @Mock
    private StudentXMLRepository studentXMLRepository;
    @Mock
    private HomeworkXMLRepository homeworkXMLRepository;
    @Mock
    private GradeXMLRepository gradeXMLRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new Service(studentXMLRepository, homeworkXMLRepository, gradeXMLRepository);
    }

    @Test
    public void saveHomework() {
        Homework homework = new Homework("2", "lab2", 22, 12);
        when(homeworkXMLRepository.save(homework)).thenReturn(null);
        assertEquals(1, service.saveHomework("2", "lab2", 22, 12), "ok");
    }

    @Test
    public void deleteHomework() {
        when(homeworkXMLRepository.delete("100")).thenReturn(null);
        assertNotEquals(1, service.deleteHomework("100"));
    }

    @Test
    public void saveStudent() {
        Student student = new Student("2", "Kiki", 533);
        when(studentXMLRepository.save(student)).thenReturn(null);
        assertEquals(1, service.saveStudent("2", "Kiki", 533), "ok");
    }

    @Test
    public void deleteStudent() {
        when(studentXMLRepository.delete("100")).thenReturn(null);
        assertNotEquals(1, service.deleteStudent("100"));
    }

    @Test
    public void updateHomeworkResultCode() {
        Homework homework = new Homework("2", "lab2", 23, 12);
        when(homeworkXMLRepository.update(homework)).thenReturn(null);
        int result = service.updateHomework("2", "lab2", 23, 12);
        Mockito.verify(homeworkXMLRepository).update(homework);
        assertEquals(0, result);
    }

}
