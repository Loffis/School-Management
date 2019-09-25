package se.ecutb.loffe.data;

import se.ecutb.loffe.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoList implements StudentDao {

    private static List<Student> students;


    public StudentDaoList(){
        students = new ArrayList<>();
    }


    @Override
    public Student findById(int id) {
        for(Student student : students){
            if(student.getId() == id){
                return student;
            }
        }
        return null;
    }

    @Override
    public Student saveStudent(Student student) {
        if(!students.contains(student)){
            students.add(student);
            return student;
        }
        return null;
    }

    @Override
    public Student findByEmail(String email) {
        for(Student student : students){
            if(student.getEmail().equalsIgnoreCase(email)){
                return student;
            }
        }
        return null;
    }


    @Override
    public List<Student> findByName(String name) {
        List<Student> studentList = new ArrayList<>();
        for(Student student : students){
            if(student.getName().equalsIgnoreCase(name)){
                studentList.add(student);
            }
        }
        return studentList;
    }



    @Override
    public List<Student> findAll() {
        return students;
    }



    @Override
    public boolean deleteStudent(Student student) {
        return students.remove(student);
    }


//    Får inte denna att funka?
//    @Override
//    public boolean deleteStudent(int id) {
//        return deleteStudent(findById(id));
//    }


}
