package se.ecutb.loffe.model;

import se.ecutb.loffe.data.CourseDao;
import se.ecutb.loffe.data.CourseDaoList;
import se.ecutb.loffe.data.StudentDao;
import se.ecutb.loffe.data.StudentDaoList;

public class SchoolManager {

    private StudentDao studentDao;
    private CourseDao courseDao;

    public SchoolManager() {
        studentDao = new StudentDaoList();
        courseDao = new CourseDaoList();
    }

    public boolean createNewStudent(String name, String email, String address){
        if(studentDao.findByEmail(email) != null){
            System.out.println("\nEmail '" + email + "' already exists in the database. '" + name + "' was not added.");
            return false;
        }
        Student newStudent = new Student(name, email, address);
        studentDao.saveStudent(newStudent);
        return true;
    }

}
