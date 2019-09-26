package se.ecutb.loffe.loffe.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.ecutb.loffe.model.Student;


public class StudentTest {

    Student s1;

    @Before
    public void setup(){
        s1 = new Student("Loffe", "mail@mail.com", "Hemma");

    }


    @Test
    public void add_student_correctly(){
        String expected = "Student{id=1, name='Loffe', email='mail@mail.com', address='Hemma'}";

        Assert.assertEquals(expected, s1.toString());
    }

    @Test
    public void test_student_setters_and_getters(){
        s1.setName("Loffis");
        s1.setEmail("newmail@mail.com");
        s1.setAddress("Home");
        String expectedName = "Loffis";
        String expectedEmail = "newmail@mail.com";
        String expectedAdress = "Home";
        int expectedId = 2;

        Assert.assertEquals(expectedName, s1.getName());
        Assert.assertEquals(expectedEmail, s1.getEmail());
        Assert.assertEquals(expectedAdress, s1.getAddress());
        Assert.assertEquals(expectedId, s1.getId());
    }



}
