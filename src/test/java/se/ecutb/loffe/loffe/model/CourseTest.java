package se.ecutb.loffe.loffe.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.ecutb.loffe.model.Course;
import se.ecutb.loffe.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseTest {

    Student s1;
    Student s2;
    List<Student> studentList = new ArrayList<>();
    Course c1;

    @Before
    public void setup(){
        s1 = new Student("Loffe", "a@b.com", "Home");
        s2 = new Student("Arne", "a@c.com", "Away");

        studentList.add(s1);
        studentList.add(s2);
        c1 = new Course("Java", LocalDate.parse("2019-10-01"), 12);
    }

    @Test
    public void add_course_correctly(){
        String expected = String.format("%n1   Java                  2019-10-01     12        ");

        Assert.assertEquals(expected, c1.toString());
    }

    @Test
    public void test_setters_and_getters(){
        c1.setCourseName("Coffee");
        c1.setStartDate(LocalDate.parse("2010-01-01"));
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(s2);
        c1.setStudents(studentList2);
        c1.setWeekDuration(1);

        int expectedId = 3;
        String expectedCourseName = "Coffee";
        LocalDate expectedStartDate = LocalDate.parse("2010-01-01");

        int expectedWeekDuration = 1;

        Assert.assertEquals(expectedCourseName, c1.getCourseName());
        Assert.assertEquals(expectedStartDate, c1.getStartDate());
        Assert.assertEquals(expectedWeekDuration, c1.getWeekDuration());
        Assert.assertEquals(expectedId, c1.getId());

    }

    @Test
    public void get_students(){
        List<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(s1);
        expectedStudents.add(s2);
        

        Assert.assertEquals(expectedStudents, c1.getStudents());

    }
}
