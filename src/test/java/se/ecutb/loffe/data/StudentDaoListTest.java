package se.ecutb.loffe.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import se.ecutb.loffe.data.CourseDaoList;
import se.ecutb.loffe.data.StudentDaoList;
import se.ecutb.loffe.model.Course;
import se.ecutb.loffe.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoListTest {

    public Course course;
    public Student student1;
    public Student student2;
    public StudentDaoList studentDao;
    public CourseDaoList courseDao;

    @Before
    public void setup() {
        course = new Course(1, "Test", LocalDate.parse("2019-01-01"), 1);
        student1 = new Student(1, "Test", "test@test.se", "Testroad");
        student2 = new Student(2, "Test2", "test2@test.se", "Testroad2");
        studentDao = new StudentDaoList();
        courseDao = new CourseDaoList();
    }

    @Test
    public void save_student_correctly(){
        Student expected = student1;
        studentDao.saveStudent(student1);

        Assert.assertEquals(expected, studentDao.findById(1));
    }

    @Test
    public void test_find_by_id(){
        Student expected = student1;
        studentDao.saveStudent(student1);
        studentDao.saveStudent(student2);

        Assert.assertEquals(expected, studentDao.findById(1));
        Assert.assertNotEquals(expected, studentDao.findById(2));
    }

    @Test
    public void test_find_by_email(){
        String expected = "test@test.se";
        studentDao.saveStudent(student1);

        Assert.assertEquals(expected, studentDao.findByEmail("test@test.se").getEmail());
    }

    @Test
    public void test_find_by_name(){
        studentDao.saveStudent(student1);
        List<Student> expected = new ArrayList<>();
        expected.add(student1);

        Assert.assertEquals(expected, studentDao.findByName("Test"));
    }

    @Test
    public void test_find_all_should_return_2(){
        int expectedStudents = 2;
        studentDao.saveStudent(student1);
        studentDao.saveStudent(student2);

        Assert.assertEquals(expectedStudents, studentDao.findAll().size());
    }

    @Test
    public void test_delete_1_student_and_find_1(){
        studentDao.saveStudent(student1);
        studentDao.saveStudent(student2);
        List<Student> expected = new ArrayList<>();
        expected.add(student1);

        studentDao.deleteStudent(student2);

        Assert.assertEquals(expected, studentDao.findAll());
    }
}
