public class Admin extends User {
    public Admin(String ssn, String fName, String mName, String lName, String DOB, String address, String phoneNumber, String sex) {//constructors
        super(ssn, fName, mName, lName, DOB, address, phoneNumber, sex);
    }

    public Admin() {
    }
    String registerStudent(Student student) {
        student.setRegNumber(HelperMethods.generateRegistrationOrIdNumberOrPasswords());//using the helper methods to generate a password
        student.setPassword(HelperMethods.generateRegistrationOrIdNumberOrPasswords());//setting the password to the student object
        String hashedPass=student.getPassword().hashCode()+"";//hashing the password to put it in the database
        student.setRole("student");//setting the role as a student
        student.setEmail(HelperMethods.generateEmail(student.getfName(), student.getRegNumber()));//using the helper methods to generate an email using the first name and the regNumber
        String MYSQL1 = "INSERT INTO Login_Credentials (Email, Password) VALUES ('"+student.getEmail()+"', '"+hashedPass+"')";//insert queries
        String MYSQL2 = "INSERT INTO User_Personal_Information (ssn, FirstName, MiddleName, LastName, DOB, Address, PhoneNumber, Sex, Role, Email) VALUES ('"+student.getSsn()+"', '"+student.getfName()+"', '"+student.getmName()+"', '"+student.getlName()+"', '"+student.getDOB()+"', '"+student.getAddress()+"', '"+student.getPhoneNumber()+"', '"+student.getSex()+"', '"+student.getRole()+"', '" +student.getEmail()+"')";
        String MYSQL3 = "INSERT INTO Student (Reg_num, Email) VALUES ('"+student.getRegNumber()+ "','"+student.getEmail()+"' )";
        boolean flag1 = DataAccess.insert(MYSQL1);
        boolean flag2 = DataAccess.insert(MYSQL2);
        boolean flag3 = DataAccess.insert(MYSQL3);//checking if all the queries executed successfully without any problem
        if (flag1 && flag2 && flag3) {
            return "student registered ! Email:"+student.getEmail()+" Password:"+student.getPassword()+" registration number:"+student.getRegNumber();//returning the result pf the queries
        } else {
            return "error occurred during registering the student check the snn it might be duplicated!";//in case of any error
        }
    }
    String registerAdminOrProfessor(User user) {//the parameter is a type of user because the server cant know what is the type of the user that will be registered
        user.setPassword(HelperMethods.generateRegistrationOrIdNumberOrPasswords());//same as student
        String hashedPass=user.getPassword().hashCode()+"";
        if (user instanceof Professor) {
            user.setRole("Professor");
        } else {
            user.setRole("Admin");
        }
        user.setEmail(HelperMethods.generateEmail(user.getfName(), HelperMethods.generateRegistrationOrIdNumberOrPasswords()));
        String MYSQL1 = "INSERT INTO Login_Credentials (Email, Password) VALUES ('"+user.getEmail()+"', '"+hashedPass+"')";
        String MYSQL2 = "INSERT INTO User_Personal_Information (ssn, FirstName, MiddleName, LastName, DOB, Address, PhoneNumber, Sex, Role, Email) VALUES ('"+user.getSsn()+"', '"+user.getfName()+"', '"+user.getmName()+"', '"+user.getlName()+"', '"+user.getDOB()+"', '"+user.getAddress()+"', '"+user.getPhoneNumber()+"', '"+user.getSex()+"', '"+user.getRole()+"', '"+user.getEmail()+"')";
        boolean flag1 = DataAccess.insert(MYSQL1);
        boolean flag2 = DataAccess.insert(MYSQL2);
        if (flag1 && flag2) {
            return "user registered ! Email:"+user.getEmail()+" Password:"+user.getPassword();
        } else {
            return "\"error occurred during registering the user check the snn it might be duplicated!";
        }
    }
    String registerCourse(Course course) {
        course.setCourseCode(HelperMethods.generateCourseCode(course.getCourseName()));//using helper methods to generate a course code
        String courseCode = course.getCourseCode();
        String MYSQL = "INSERT INTO Course_Master (Course_Code, Course_Name) VALUES ('"+courseCode+"', '"+course.getCourseName()+"')";//insert query
        return DataAccess.insert(MYSQL) ? "course registered ! course code:" + course.getCourseCode() : "error occurred during registering the course !";//returning the first statement if  ataAccess.insert(MYSQL returning true or the second statement if false its like a yes or no question
    }
    String registerCourseForStudent(Course course, Student student) {//calling the function in the data access layer directly because there will be no processing
        String MYSQL = "INSERT INTO student_course_registration (Email, Course_Code, Midterm_Grade, Course_Work, Grade, Final, Reg_num) VALUES ('"+student.getEmail()+"','"+course.getCourseCode()+"','U','U','U','U','" + student.getRegNumber() + "')";
        return DataAccess.insert(MYSQL) ? "student is registered to course !" : "error occurred, the student might be already registered to this course";
    }
    String deleteProfessor(Professor professor) {
        String MYSQL1 = "DELETE FROM User_Personal_Information WHERE Email = '"+professor.getEmail()+"'";//we must delete first from login credentials table because of the foreign key constraint
        String MYSQL2 = "DELETE FROM Login_Credentials WHERE Email = '"+professor.getEmail()+"'";
        return DataAccess.delete(MYSQL1) && DataAccess.delete(MYSQL2) ? "Professor is deleted" : "error occurred in deletion";
    }
    String deleteStudent(Student student) {
        String MYSQL1 = "DELETE FROM Student_Course_Registration WHERE Email = '"+student.getEmail()+"'";//we must delete by this order because of the foreign key constraint
        String MYSQL2 = "DELETE FROM Student WHERE Email= '"+student.getEmail()+"'";
        String MYSQL3 = "DELETE FROM User_Personal_Information WHERE Email = '"+student.getEmail()+"'";
        String MYSQL4 = "DELETE FROM Login_Credentials WHERE Email = '"+student.getEmail()+"'";
        return DataAccess.delete(MYSQL1) && DataAccess.delete(MYSQL2) && DataAccess.delete(MYSQL3) && DataAccess.delete(MYSQL4) ? "Student is deleted" : "error occurred in deletion";
    }
    String deleteCourse(Course course) {
        String MYSQL = "DELETE FROM Course_Master WHERE Course_Code= '"+course.getCourseCode()+"'";//we must delete by this order because of the foreign key constraint
        return DataAccess.delete(MYSQL) ? "Course is deleted" : "error occurred in deletion";
    }
    boolean isUser(User user) {
        String MYSQL = "SELECT Email FROM Login_Credentials WHERE  Email = '"+user.getEmail()+"'";// to see if the user available
        return DataAccess.isObject(MYSQL);
    }
    boolean isCourse(Course course) {
        String MYSQL = "SELECT Course_Code FROM Course_Master WHERE Course_Code = '"+course.getCourseCode()+"'";//to see if the course available
        return DataAccess.isObject(MYSQL);
    }
}