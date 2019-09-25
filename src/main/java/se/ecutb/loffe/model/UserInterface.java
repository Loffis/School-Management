package se.ecutb.loffe.model;

import se.ecutb.loffe.data.CourseDao;
import se.ecutb.loffe.data.CourseDaoList;
import se.ecutb.loffe.data.StudentDao;
import se.ecutb.loffe.data.StudentDaoList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {


    private static Scanner SCANNER = new Scanner(System.in);
    private StudentDao studentDao = new StudentDaoList();
    private CourseDao courseDao = new CourseDaoList();

    private Student s1 = new Student("Loffe", "loffe@home.se", "Hem");
    private Student s2 = new Student("Alvar", "abc@123.com", "Långtbort");
    private Student s3 = new Student("Loffe", "loffe@viverk.se", "Nånannanstans");
    private Student s4 = new Student("Lucifer", "sixsixsix@666.com", "Hell");


    public void run(){

        studentDao.saveStudent(s1);
        studentDao.saveStudent(s2);
        studentDao.saveStudent(s3);
        studentDao.saveStudent(s4);
        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);

        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(s1);
        studentList2.add(s2);
        studentList2.add(s3);

        List<Student> studentList3 = new ArrayList<>();
        studentList3.add(s1);
        studentList3.add(s2);
        studentList3.add(s3);
        studentList3.add(s4);

        Course c1 = new Course("Java", LocalDate.parse("2019-08-19"), 6, studentList);
        Course c2 = new Course("Coffee", LocalDate.parse("2018-10-01"), 8, studentList2);
        Course c3 = new Course("Tea", LocalDate.parse("2017-10-01"), 10, studentList3);
        Course c4 = new Course("Tea", LocalDate.parse("2019-10-01"), 10, studentList2);

        courseDao.saveCourse(c1);
        courseDao.saveCourse(c2);
        courseDao.saveCourse(c3);
        courseDao.saveCourse(c4);

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
                    boolean isSearchMenuRunning = true;
                    while (isSearchMenuRunning) {
                        switch (searchMenu()) {
                            case 1:
                                System.out.print("Enter name to search for: ");
                                String input = getString();
                                if (!studentDao.findByName(input).isEmpty()) {
                                    System.out.println(studentDao.findByName(input));
                                } else {
                                    System.out.println("No one with that name exists in the db.");
                                }
                                break;
                            case 2:
                                System.out.print("Enter email to search for: ");
                                System.out.println(studentDao.findByEmail(getString()));
                                break;
                            case 3:
                                System.out.print("Enter a course name to search for: ");
                                System.out.println(courseDao.findByName(getString()));
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 9:
                                isSearchMenuRunning = false;
                                break;
                            default:
                                menuErrorMsg();
                                break;
                        }
                    }
                    break;
                case 4:
                    break;
                case 5:
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

    private void menuErrorMsg() {
        System.out.println("\n\tPlease enter a valid number. Try again.");
    }

    private int searchMenu() {
        System.out.println("\n\tSEARCH MENU" +
                           "\n\t1) Student name" +
                           "\n\t2) Student email" +
                           "\n\t3) Course name" +
                           "\n\t4) Course" +
                           "\n\t5) Edit/add/remove students" +
                           "\n\t9) Exit to main menu\n");
        System.out.print("\tPlease make a choice: ");
        return getValidInt();
    }

    private int mainMenu(){
        System.out.println("\n\tMAIN MENU" +
                           "\n\t1) View all courses" +
                           "\n\t2) View all students" +
                           "\n\t3) Search courses / students" +
                           "\n\t4) Edit/add/remove courses" +
                           "\n\t5) Edit/add/remove students" +
                           "\n\t9) Quit\n");
        System.out.print("\tPlease make a choice: ");
        return getValidInt();
    }

    private static String getString(){
        return SCANNER.nextLine();
    }

    private int getValidInt() {
        boolean valid = false;
        int number = 0;
        while (!valid) {
            try {
                number = Integer.parseInt(getString());
                valid = true;
            } catch (NumberFormatException exception) {
                //System.out.println("Please enter a number.");
                return number;
            }
        }
        return number;
    }
}
