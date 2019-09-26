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

    public boolean addStudent(Student student){
        
        return true;
    }
}
