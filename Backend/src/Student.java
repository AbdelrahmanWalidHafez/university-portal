

public class Student extends User {//constructors
    private String regNumber;
    public Student(String ssn, String fName, String mName, String lName, String DOB, String address, String phoneNumber, String sex) {
        super(ssn, fName, mName, lName, DOB, address, phoneNumber, sex);
    }
    public Student(String email) {
        super(email);
    }
    public Student(String email, String regNumber) {
        super(email);
        this.regNumber=regNumber;
    }
    String[] studentViewPersonalInformation(){
        String MYSQL=" select * from user_personal_information where email ='"+getEmail()+"'";
        return DataAccess.viewPersonalStudentInfo(MYSQL);
    }
    String[][] studentViewGrades(){
        String MYSQL = "SELECT Course_Code,Midterm_Grade,Course_Work,Final,grade FROM student_course_registration WHERE Email='"+getEmail()+"'";
        return DataAccess.viewStudentGrades(MYSQL);
    }
    public String getRegNumber() {
        return regNumber;
    }
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
}
