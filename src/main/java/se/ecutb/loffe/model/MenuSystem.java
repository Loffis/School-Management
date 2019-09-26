package se.ecutb.loffe.model;

import se.ecutb.loffe.data.CourseDao;
import se.ecutb.loffe.data.CourseDaoList;
import se.ecutb.loffe.data.StudentDao;
import se.ecutb.loffe.data.StudentDaoList;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuSystem {



    private StudentDao studentDao = new StudentDaoList();
    private CourseDao courseDao = new CourseDaoList();
    private Student student;
    private Course course;

    private Student s1 = new Student("Loffe", "loffe@gmail.com", "Oak road 15");
    private Student s2 = new Student("Alvar", "abc@123.com", "Birch road 15");
    private Student s3 = new Student("Loffe", "loffe@viverk.se", "Park Avenue");
    private Student s4 = new Student("Lucifer", "sixsixsix@666.com", "Hell");


    public void run(){

        setupBefore();



        System.out.println("\tWelcome to EC Utbildning School Management");
        boolean isRunning = true;
        while (isRunning) {
            switch (mainMenu()) {
                case 1:
                    System.out.printf("%n%-4s%-22s%-15s%-10s", "ID", "COURSE NAME", "START DATE", "DURATION");
                    System.out.println(courseDao.findAll());
                    break;
                case 2:
                    System.out.printf("%n%-4s%-20s%-30s%-20s", "ID", "NAME", "EMAIL", "ADDRESS");
                    System.out.println(studentDao.findAll());
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    student();
                    break;
                case 5:
                    course();
                    break;
                case 9:
                    System.out.println("\tThank you for using EC Utbildning School Management. GLHF!");
                    isRunning = false;
                    break;
                default:
                    menuErrorMsg();
                    break;
            }
        }
    }

    private void setupBefore() {

        studentDao.saveStudent(s1);
        studentDao.saveStudent(s2);
        studentDao.saveStudent(s3);
        studentDao.saveStudent(s4);

        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);

        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(s3);
        studentList2.add(s4);

        List<Student> studentList3 = new ArrayList<>();
        studentList3.add(s1);
        studentList3.add(s2);
        studentList3.add(s3);
        studentList3.add(s4);

        Course c1 = new Course("Java", LocalDate.parse("2019-08-19"), 8);
        Course c2 = new Course("Coffee", LocalDate.parse("2019-10-01"), 8);
        Course c3 = new Course("Tea", LocalDate.parse("2019-10-01"), 4);
        Course c4 = new Course("Tea", LocalDate.parse("2019-11-01"), 4);

        courseDao.saveCourse(c1);
        courseDao.saveCourse(c2);
        courseDao.saveCourse(c3);
        courseDao.saveCourse(c4);
    }


    private void search() {
        boolean isSearchMenuRunning = true;
        while (isSearchMenuRunning) {
            switch (searchMenu()) {
                case 1:
                    System.out.print("Enter name to search for: ");
                    String input = Tools.getString();
                    if (!studentDao.findByName(input).isEmpty()) {
                        System.out.printf("%n%-4s%-20s%-30s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", "" +
                                studentDao.findByName(input) + "\n");
                    } else {
                        System.out.println("\nNo one with that name exists in the db.");
                    }
                    break;
                case 2:
                    System.out.print("Enter email to search for: ");
                    System.out.printf("%n%-4s%-20s%-30s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", "" +
                            studentDao.findByEmail(Tools.getString()) + "\n");
                    break;
                case 3:
                    System.out.print("Enter a course name to search for: ");
                    System.out.printf("%n%-4s%-22s%-15s%-10s%-4s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                            courseDao.findByName(Tools.getString()) + "\n");
                    break;
                case 4:
                    System.out.print("Enter a course date (yyyy-mm-dd) to search for: ");
                    System.out.printf("%n%-4s%-22s%-15s%-10s%-4s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                            courseDao.findByDate(getDate()) + "\n");
                    break;
                case 9:
                    isSearchMenuRunning = false;
                    break;
                default:
                    menuErrorMsg();
                    break;
            }
        }
    }

    private void student() {
        boolean isStudentMenuRunning = true;
        while (isStudentMenuRunning) {
            switch (studentMenu()) {
                case 1:
                    // TODO - när ska det kollas om student redan finns?
                    System.out.print("Enter name: ");
                    String name = Tools.getString();
                    System.out.print("Enter email: ");
                    String email = Tools.getString();
                    System.out.print("Enter address: ");
                    String address = Tools.getString();
                    student = new Student(name, email, address);
                    studentDao.saveStudent(student);
                    System.out.printf("%nStudent %s", student.getName() + " added.\n");
                    System.out.printf("%n%-4s%-20s%-30s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", ""
                            + student.toString() + "\n");



                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    // TODO - lägg till funktion för att editera student

                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    Tools.getValidInt();

                    break;
                case 9:
                    isStudentMenuRunning = false;
                    break;
                default:
                    menuErrorMsg();
                    break;

            }
        }
    }

    private void course() {
        boolean isCourseMenuRunning = true;
        while (isCourseMenuRunning) {
            switch (courseMenu()) {
                case 1:
                    // TODO - ska det kollas om kurs redan finns?
                    System.out.print("Enter course name: ");
                    String name = Tools.getString();
                    System.out.print("Enter start date: ");
                    LocalDate date = getDate();
                    System.out.print("Enter course duration: ");
                    int duration = Tools.getValidInt();
                    course = new Course(name, date, duration);
                    courseDao.saveCourse(course);
                    System.out.printf("%nCourse %s", course.getCourseName() + " added.\n");
                    System.out.printf("%n%-4s%-22s%-15s%-10s%-4s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                            course.toString() + "\n");

                    break;
                case 2:
                    boolean isEditCourseMenuRunning = true;
                    while(isEditCourseMenuRunning) {
                        switch (editCourseMenu()) {
                            case 1:
                                System.out.print("Enter course id: ");
                                int id = Tools.getValidInt();
                                System.out.printf("%n%-4s%-22s%-15s%-10s%-4s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                                        courseDao.findById(id) + "\n");
                                System.out.print("Enter what to edit. 1) Course name, 2) Start date, 3) Duration: ");
                                int menuChoice = Tools.getValidInt();

                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 9:
                                isEditCourseMenuRunning = false;
                                break;
                            default:
                                menuErrorMsg();
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter course ID: ");
                    Tools.getValidInt();

                    break;
                case 9:
                    isCourseMenuRunning = false;
                    break;
                default:
                    menuErrorMsg();
                    break;
            }
        }
    }

    private LocalDate getDate() {
        boolean valid = false;
        LocalDate date = LocalDate.parse("1900-01-01");
        while (!valid) {
            try {
                date = LocalDate.parse(Tools.getString());
                valid = true;
            } catch (DateTimeException e) {
                System.out.println("\nPlease enter a valid date.");
                return date;
            }
        }
        return date;
    }

    private void menuErrorMsg() {
        System.out.println("\n\tPlease enter a valid number. Try again.");
    }

    private int mainMenu(){
        System.out.println("\n\tMAIN MENU" +
                           "\n\t1) View all courses" +
                           "\n\t2) View all students" +
                           "\n\t3) Search courses / students" +
                           "\n\t4) Edit/add/delete students" +
                           "\n\t5) Edit/add/delete courses" +
                           "\n\t9) Quit\n");
        System.out.print("\tPlease make a choice: ");
        return Tools.getValidInt();
    }

    private int searchMenu() {
        System.out.println("\n\tSEARCH MENU" +
                "\n\t1) Student, by name" +
                "\n\t2) Student, by email" +
                "\n\t3) Course, by name" +
                "\n\t4) Course, by date" +
                "\n\t9) Exit to main menu\n");
        System.out.print("\tPlease make a choice: ");
        return Tools.getValidInt();
    }

    private int studentMenu() {
        System.out.println("\n\tSTUDENT MENU" +
                "\n\t1) Add student" +
                "\n\t2) Edit student" +
                "\n\t3) Delete student" +
                "\n\t9) Exit to main menu\n");
        System.out.print("\tPlease make a choice: ");
        return Tools.getValidInt();
    }

    private int courseMenu() {
        System.out.println("\n\tCOURSE MENU" +
                "\n\t1) Add course" +
                "\n\t2) Edit course" +
                "\n\t3) Delete course" +
                "\n\t9) Exit to main menu\n");
        System.out.print("\tPlease make a choice: ");
        return Tools.getValidInt();
    }

    private int editCourseMenu() {
        System.out.println("\n\tEDIT COURSE MENU" +
                "\n\t1) Register student" +
                "\n\t2) Unregister student" +
                "\n\t3) Change start date" +
                "\n\t9) Exit to course menu\n");
        System.out.print("\tPlease make a choice: ");
        return Tools.getValidInt();
    }
}
