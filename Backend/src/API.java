class API implements Patterns {
    static synchronized String login(User user) {//login
        String MYSQL = "select email from login_credentials where password='" + user.getPassword().hashCode() + "'";
        //see if the user exists in the database and the login credentials are correct but we myst to hash the input password first to compare it with the hash value in the database table
        if (DataAccess.isObject(MYSQL)) {
            String MYSQL2 = "SELECT Role From User_Personal_Information WHERE Email='" + user.getEmail() + "'";
            //returning back the role after getting it from the database to know which screen will be displayed
            return DataAccess.select(MYSQL2);
        } else {
            return "user not found";
            //if the user not found
        }
    }

    static synchronized String studentRegistration(Student student) {
        Admin admin = new Admin();//creating an admin object to use the function
        if (!student.getSsn().matches(egyptianSSNPattern)) {//validate inputs in the server side
            return "check ssn pattern";
        } else if (!student.getPhoneNumber().matches(egyptianPhoneNumberPattern)) {
            return "check phone number pattern";
        } else if (!student.getDOB().matches(datePattern)) {
            return "check date pattern";
        } else if (!student.getSex().matches(sexPattern)) {
            return "check sex pattern";
        } else {
            return admin.registerStudent(student);//if inputs are valid we will call the function of register student in the logic layer
        }
    }

    static synchronized String adminOrProfessorRegistration(User user) {
        Admin admin = new Admin();
        if (!user.getSsn().matches(egyptianSSNPattern)) {
            return "check ssn pattern";
        } else if (!user.getPhoneNumber().matches(egyptianPhoneNumberPattern)) {
            return "check phone number pattern";
        } else if (!user.getDOB().matches(datePattern)) {
            return "check date pattern";
        } else if (!user.getSex().matches(sexPattern)) {
            return "check sex pattern";
        } else {
            return admin.registerAdminOrProfessor(user);//if inputs are valid we will call the function of register admin or professor in the logic layer
        }
    }

    static synchronized String courseRegistration(Course course) {
        Admin admin = new Admin();
        if (!course.getCourseName().matches(courseNamePattern)) {// the course name consists of two words
            return "check course name";
        }
        return admin.registerCourse(course);
    }

    static synchronized String registerCourseForStudent(Course course, Student student) {
        Admin admin = new Admin();
        if (admin.isCourse(course)) {//check if the course is valid first
            String MYSQL = "SELECT Reg_num FROM Student WHERE Reg_num ='" + student.getRegNumber() + "'";
            if (admin.isUser(student) && !DataAccess.select(MYSQL).equalsIgnoreCase("user data not found")) {//see if the student is in the database and is a student not any user type
                return admin.registerCourseForStudent(course, student);
            }
            return "student not found check email or registration number";//if the student not found
        } else {
            return "invalid course code";// if the course code is not found
        }
    }

    static synchronized String[] viewStudentPersonalInformation(Student student) {
        return student.studentViewPersonalInformation();//calling function in the logic layer only because there will be no validation
    }

    static synchronized String[][] viewStudentGrades(Student student) {
        return student.studentViewGrades();//calling function in the logic layer only because there will be no validation
    }

    static synchronized String deleteUser(User user) {
        Admin admin = new Admin();
        if (admin.isUser(user) && !DataAccess.select("SELECT Role From User_Personal_Information WHERE Email='" + user.getEmail() + "'").equalsIgnoreCase("admin")) {//check if the user is available
            if (user instanceof Professor) {//see the type of the user
                return admin.deleteProfessor((Professor) user);
            }
            return admin.deleteStudent((Student) user);
        } else {
            return "User not found";
        }
    }

    static synchronized String deleteCourse(Course course) {
        Admin admin = new Admin();
        if (admin.isCourse(course))// see if the course is available
            return admin.deleteCourse(course);
        return "Invalid Course code";
    }

    static synchronized String updateStudentGrades(String courseCode, String regNumber, String grade, String gradeType) {
        Professor professor = new Professor();
        boolean updateTotal = false;//this flag is used to calculate the final grade if the professor will update the final grade
        String columnName;//to select the column name in the query
        if (gradeType.equalsIgnoreCase("midterm") && Integer.parseInt(grade) <= 30) {//validation of grades
            columnName = "Midterm_Grade";
        } else if (gradeType.equalsIgnoreCase("courseWork") && Integer.parseInt(grade) <= 20) {
            columnName = "Course_Work";
        } else if (gradeType.equalsIgnoreCase("final") && Integer.parseInt(grade) <= 50) {
            columnName = "Final";
            updateTotal = true;
        } else {
            return "invalid input parameter";//if there is a problem in validation
        }
        return professor.UpdateStudentGrades(courseCode, regNumber, grade, columnName, updateTotal);
    }
}