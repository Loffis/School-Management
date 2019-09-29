package se.ecutb.loffe.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.ecutb.loffe.model.Course;
import se.ecutb.loffe.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoListTest {
    public Course course1;
    public Course course2;
    public Student student1;
    public Student student2;
    public StudentDaoList studentDao;
    public CourseDaoList courseDao;


    @Before
    public void setup() {
        course1 = new Course(1, "Test", LocalDate.parse("2019-01-01"), 1);
        course2 = new Course(2, "Test2", LocalDate.parse("2019-01-01"), 1);
        student1 = new Student(1, "Test", "test@test.se", "Testroad");
        student2 = new Student(2, "Test2", "test2@test.se", "Testroad2");
        studentDao = new StudentDaoList();
        courseDao = new CourseDaoList();
    }

    @Test
    public void test_save_course(){
        Course expected = course1;
        courseDao.saveCourse(course1);

        Assert.assertEquals(expected, courseDao.findById(1));
    }

    @Test
    public void test_find_by_id(){
        Course expected = course2;
        courseDao.saveCourse(course2);

        Assert.assertEquals(expected, courseDao.findById(2));
        Assert.assertNotEquals(expected, courseDao.findById(1));
    }

    @Test
    public void test_find_by_name(){
        String name = "Test";
        courseDao.saveCourse(course1);
        List<Course> expected = new ArrayList<>();
        expected.add(course1);

        Assert.assertEquals(expected, courseDao.findByName(name));
    }

    @Test
    public void test_find_by_date_and_find_2(){
        LocalDate date = LocalDate.parse("2019-01-01");
        courseDao.saveCourse(course1);
        courseDao.saveCourse(course2);
        List<Course> expected = new ArrayList<>();
        expected.add(course1);
        expected.add(course2);

        Assert.assertEquals(expected, courseDao.findByDate(LocalDate.parse("2019-01-01")));
    }

    @Test
    public void test_find_all_and_find_2(){
        courseDao.saveCourse(course1);
        courseDao.saveCourse(course2);
        List<Course> expected = new ArrayList<>();
        expected.add(course1);
        expected.add(course2);

        Assert.assertEquals(expected, courseDao.findAll());
    }

    @Test
    public void test_remove_1_and_find_1(){
        courseDao.saveCourse(course1);
        courseDao.saveCourse(course2);
        List<Course> expected = new ArrayList<>();
        expected.add(course2);
        courseDao.removeCourse(course1);

        Assert.assertEquals(expected, courseDao.findAll());
    }









}
