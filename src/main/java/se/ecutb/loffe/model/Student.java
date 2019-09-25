package se.ecutb.loffe.model;


public class Student {
    private static int idCounter;
    private int id;
    private String name;
    private String email;
    private String address;

    public Student(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Student(String name, String email, String address) {
        this(++idCounter, name, email, address);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("\n%-4d%-20s%-30s%-20s", id, name, email, address);
    }

//    @Override
//    public boolean equals(Object o /* tar in ett object*/) {
//        // Om this jag(this) är på samma minnesadress som o
//        if (this == o)
//            return true;
//
//        // om object o är null ELLER är vi samma typ? false
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        //
//        Student student = (Student) o;
//        return id == student.id &&
//                Objects.equals(name, student.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }

}
