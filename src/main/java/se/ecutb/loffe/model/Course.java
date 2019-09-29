package se.ecutb.loffe.model;

import java.time.LocalDate;
import java.util.ArrayList;
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
        this.id = ++idCourseCounter;
        this.courseName =  courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
    }

    public Course(String courseName, LocalDate startDate, int weekDuration, List<Student> students) {
        this.id = ++idCourseCounter;
        this.courseName =  courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = students;
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

    public String register(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            return "\n\t" + student + " registered.";
        } else {
            return "\n\tStudent + " + student + " not registered.";
        }
    }

    public String unregister(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            return "\n\t" + student + " unregistered.";
        } else {
            return "\n\tStudent " + student + " not unregistered.";
        }
    }

//    @Override
//    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", courseName='" + courseName + '\'' +
//                ", startDate=" + startDate +
//                ", weekDuration=" + weekDuration +
//                ", students=" + students +
//                '}';
//    }


        @Override
    public String toString() {
        return String.format("%n%-4s%-22s%-35tF%-20d%-23s", getId(), getCourseName(),
                getStartDate(), getWeekDuration(), getStudents());
    }

}


