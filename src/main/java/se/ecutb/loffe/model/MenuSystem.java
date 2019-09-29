package se.ecutb.loffe.model;

import se.ecutb.loffe.data.CourseDao;
import se.ecutb.loffe.data.CourseDaoList;
import se.ecutb.loffe.data.StudentDao;
import se.ecutb.loffe.data.StudentDaoList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuSystem {

    private StudentDao studentDao = new StudentDaoList();
    private CourseDao courseDao = new CourseDaoList();
    private SchoolManager schoolManager = new SchoolManager();
    private Course course;


    private void setupBefore() {

        Student s1 = new Student("Loffe", "loffe@gmail.com", "Oak road 15");
        Student s2 = new Student("John Doe", "jd@123.com", "Birch road 15");
        Student s3 = new Student("Loffe", "loffe@viverk.se", "Park Avenue");
        Student s4 = new Student("Lucifer", "sixsixsix@666.com", "Route 666");

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

        Course c1 = new Course("Java", LocalDate.parse("2019-08-19"), 8, studentList);
        Course c2 = new Course("Java", LocalDate.parse("2019-11-01"), 8, studentList2);
        Course c3 = new Course("Tea", LocalDate.parse("2019-10-01"), 4, studentList2);
        Course c4 = new Course("Tea", LocalDate.parse("2019-11-01"), 4, studentList);
        Course c5 = new Course("Coffee", LocalDate.parse("2019-12-23"), 1, studentList2);

        courseDao.saveCourse(c1);
        courseDao.saveCourse(c2);
        courseDao.saveCourse(c3);
        courseDao.saveCourse(c4);
        courseDao.saveCourse(c5);
    }

    public void run() {
        setupBefore();
        boolean isRunning = true;
        while (isRunning) {
            switch (mainMenu()) {
                case 1:
                    System.out.printf("%n%-4s%-22s%-35s%-10s%-2s", "ID", "NAME", "START DATE / EMAIL", "DURATION / ADDRESS", "");
                    System.out.println(courseDao.findAll());
                    System.out.println();
                    break;
                case 2:
                    System.out.printf("%n%-4s%-22s%-35s%-20s", "ID", "NAME", "EMAIL", "ADDRESS");
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
                case 6:
                    register();
                    break;
                case 9:
                    System.out.println("\tThank you for using EC Utbildning School Management.");
                    isRunning = false;
                    break;
                default:
                    menuErrorMsg();
                    break;
            }
        }
    }


    private void search() {
        boolean isSearchMenuRunning = true;
        while (isSearchMenuRunning) {
            switch (searchMenu()) {
                case 1:
                    System.out.print("Enter name to search for: ");
                    String input = Tools.getString();
                    if (!studentDao.findByName(input).isEmpty()) {
                        System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", "" +
                                studentDao.findByName(input) + "\n");
                    } else {
                        System.out.println("\nNo one with that name exists in the db.");
                    }
                    break;
                case 2:
                    System.out.print("Enter email to search for: ");
                    System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", "" +
                            studentDao.findByEmail(Tools.getString()) + "\n");
                    break;
                case 3:
                    System.out.print("Enter a course name to search for: ");
                    System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                            courseDao.findByName(Tools.getString()) + "\n");
                    break;
                case 4:
                    System.out.print("Enter a course date (yyyy-mm-dd) to search for: ");
                    System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                            courseDao.findByDate(Tools.getValidDate()) + "\n");
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
                    System.out.print("\tEnter name: ");
                    String name = Tools.getString();
                    System.out.print("\tEnter email: ");
                    String email = Tools.getString();
                    System.out.print("\tEnter address: ");
                    String address = Tools.getString();
                    if (!schoolManager.createNewStudent(name, email, address)) {
                        break;
                    }
                    System.out.printf("%nStudent %s", studentDao.findByEmail(email).getName() + " added.\n");
                    System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", ""
                            + studentDao.findByEmail(email) + "\n");
                    break;
                case 2:
                    System.out.print("\tEnter student ID: ");
                    int idToEdit = Tools.getValidInt();
                    if (idToEdit < 1) {
                        menuErrorMsg();
                        break;
                    }
                    System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", ""
                            + studentDao.findById(idToEdit) + "\n");
                    switch (editStudentMenu()) {
                        case 1:
                            System.out.print("Enter new data: ");
                            String newName = Tools.getString();
                            studentDao.findById(idToEdit).setName(newName);

                            break;
                        case 2:
                            System.out.print("Enter new data: ");
                            String newEmail = Tools.getString();
                            studentDao.findById(idToEdit).setEmail(newEmail);
                            break;
                        case 3:
                            System.out.print("Enter new address: ");
                            String newAddress = Tools.getString();
                            studentDao.findById(idToEdit).setAddress(newAddress);
                            break;
                        default:
                            menuErrorMsg();
                            break;
                    }
                    break;

                case 3:
                    System.out.print("\tEnter student ID: ");
                    int idToDelete = Tools.getValidInt();
                    if (idToDelete < 1) {
                        menuErrorMsg();
                        break;
                    }
                    System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", ""
                            + studentDao.findById(idToDelete) + "\n");
                    System.out.print("\tDelete this student? (Y) : ");
                    if (Tools.getString().equalsIgnoreCase("Y")) {
                        studentDao.deleteStudent(studentDao.findById(idToDelete));
                        System.out.println("\n\tStudent deleted from database.");
                    } else {
                        System.out.println("\n\tNothing deleted.");
                    }
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
                    System.out.print("\tEnter course name: ");
                    String newCourseName = Tools.getString();
                    System.out.print("\tEnter start date: ");
                    LocalDate newStartDate = Tools.getValidDate();
                    System.out.print("\tEnter course duration: ");
                    int newDuration = Tools.getValidInt();
                    course = new Course(newCourseName, newStartDate, newDuration);
                    courseDao.saveCourse(course);
                    System.out.printf("%nCourse %s", courseDao.findById(course.getId()).getCourseName() + " added.\n");
                    System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "COURSE NAME", "START DATE", "DURATION",
                            "STUDENTS" + course.toString() + "\n");
                    break;
                case 2:
                    int courseId = 0;
                    while (courseId == 0) {
                        System.out.print("\n\tEnter course id: ");
                        courseId = Tools.getValidInt();
                    }
                    System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                            courseDao.findById(courseId) + "\n");
                    System.out.print("\tDelete this course? (Y) : ");
                    if (Tools.getString().equalsIgnoreCase("Y")) {
                        courseDao.removeCourse(courseDao.findById(courseId));
                        System.out.println("\n\tCourse deleted from database.");
                    } else {
                        System.out.println("\n\tNothing deleted.");
                    }
                    break;

                case 3:
                    courseId = 0;
                    while (courseId == 0) {
                        System.out.print("\n\tEnter course id: ");
                        courseId = Tools.getValidInt();
                    }
                    System.out.printf("%n%-4s%-22s%-35s%-20s%-13s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                            courseDao.findById(courseId) + "\n");
                    System.out.print("\tEnter new start date: ");
                    newStartDate = Tools.getValidDate();
                    courseDao.findById(courseId).setStartDate(newStartDate);
                    System.out.println("\nStart date changed to " + newStartDate);
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

    private void register() {
        boolean isRegisterMenuRunning = true;
        while (isRegisterMenuRunning) {
            switch (registerMenu()) {
                case 1:
                    System.out.print("\n\tEnter course id: ");
                    int courseId = Tools.getValidInt();
                    System.out.printf("%n%-4s%-22s%-15s%-10s%-4s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                            courseDao.findById(courseId) + "\n");
                    System.out.print("\tEnter student id: ");
                    int studentId = Tools.getValidInt();
                    System.out.printf("%n%-4s%-20s%-30s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", ""
                            + studentDao.findById(studentId) + "\n");
                    System.out.print("\tRegister this student? (Y) : ");
                    if (Tools.getString().equalsIgnoreCase("Y")) {
                        Course newCourse = courseDao.findById(courseId);
                        newCourse.register(studentDao.findById(studentId));
                        System.out.println("\n\t" + studentDao.findById(studentId).getName() + " registered.");
                    }
                    break;
                case 2:
                    System.out.print("\n\tEnter course id: ");
                    courseId = Tools.getValidInt();
                    System.out.printf("%n%-4s%-22s%-15s%-10s%-4s", "ID", "COURSE NAME", "START DATE", "DURATION", "" +
                            courseDao.findById(courseId) + "\n");
                    System.out.print("\tEnter student id: ");
                    studentId = Tools.getValidInt();
                    System.out.printf("%n%-4s%-20s%-30s%-20s%-13s", "ID", "NAME", "EMAIL", "ADDRESS", ""
                            + studentDao.findById(studentId) + "\n");
                    System.out.print("\tUnregister this student? (Y) : ");
                    if (Tools.getString().equalsIgnoreCase("Y")) {
                        courseDao.findById(courseId).unregister(studentDao.findById(studentId));
                        System.out.println("\n\t" + studentDao.findById(studentId).getName() + " unregistered.");
                    }
                    break;

                case 9:
                    isRegisterMenuRunning = false;
                    break;
                default:
                    menuErrorMsg();
                    break;
            }
        }
    }

    private void menuErrorMsg() {
        System.out.println("\n\tPlease enter a valid number. Try again.");
    }

    private int mainMenu() {
        System.out.println("\n\tWelcome to EC Utbildning School Management\n" +
                "\n\tToday we have " + studentDao.findAll().size() +
                " students and " + courseDao.findAll().size() + " courses.\n" +
                "\n\tMAIN MENU" +
                "\n\t1) View all courses with registered students" +
                "\n\t2) View all students" +
                "\n\t3) Search course / student" +
                "\n\t4) Edit / add / delete student" +
                "\n\t5) Edit / add / delete course" +
                "\n\t6) Register / unregister student from course" +
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

    private int editStudentMenu() {
        System.out.println("\n\tEDIT STUDENT MENU" +
                "\n\t1) Name" +
                "\n\t2) Email" +
                "\n\t3) Address" +
                "\n\t9) Exit to student menu");
        System.out.print("\tPlease make a choice: ");
        return Tools.getValidInt();
    }

    private int courseMenu() {
        System.out.println("\n\tCOURSE MENU" +
                "\n\t1) Add course" +
                "\n\t2) Delete course" +
                "\n\t3) Change start date" +
                "\n\t9) Exit to main menu\n");
        System.out.print("\tPlease make a choice: ");
        return Tools.getValidInt();
    }

    private int registerMenu() {
        System.out.println("\n\tREGISTER MENU" +
                "\n\t1) Register student" +
                "\n\t2) Unregister student" +
                "\n\t9) Exit to course menu\n");
        System.out.print("\tPlease make a choice: ");
        return Tools.getValidInt();
    }
}
