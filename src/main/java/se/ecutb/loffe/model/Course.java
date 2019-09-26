package se.ecutb.loffe.model;

import se.ecutb.loffe.model.Student;

import java.time.LocalDate;
import java.util.List;

public class Course {
    private static int idCourseCounter;
    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private List<Student> students;

    public Course(int id, String courseName, LocalDate startDate, int weekDuration) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
    }

    public Course(String courseName, LocalDate startDate, int weekDuration) {
        this(++idCourseCounter, courseName, startDate, weekDuration);
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void register(Student student){

    }


    public void unregister(Student student){

    }

    @Override
    public String toString() {
        return String.format("%n%-4s%-22s%-15tF%-10d", id, courseName, startDate, weekDuration);
    }

}


