package se.ecutb.loffe.data;

import se.ecutb.loffe.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoList implements CourseDao {

    private static List<Course> courses;

    public CourseDaoList(){
        courses = new ArrayList<>();
    }

    @Override
    public Course saveCourse(Course course) {
        if(!courses.contains(course)){
            courses.add(course);
        }
        return null;
    }

    @Override
    public Course findById(int id) {
        for(Course course : courses){
            if(course.getId() == id){
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> findByName(String name) {
        List<Course> courseList = new ArrayList<>();
        for(Course course : courses){
            if(course.getCourseName().equalsIgnoreCase(name)){
                courseList.add(course);
            }
        }
        return courseList;
    }

    @Override
    public List<Course> findByDate(LocalDate date) {
        List<Course> courseList = new ArrayList<>();
        for (Course course : courses) {
            if(course.getStartDate().equals(date)){
                courseList.add(course);
            }
        }
        return courseList;
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }
}
