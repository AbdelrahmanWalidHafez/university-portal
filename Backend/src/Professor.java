public class Professor extends User {
    public Professor() {
        super();
    }

    public Professor(String ssn, String fName, String mName, String lName, String DOB, String address, String phoneNumber, String sex) {
        super(ssn, fName, mName, lName, DOB, address, phoneNumber, sex);
    }

    public Professor(String email) {
        super(email);
    }

    String UpdateStudentGrades(String courseCode, String regNumber, String grade, String columnName, boolean updateTotal) {
        String MYSQL = "UPDATE student_course_registration SET " + columnName + "='" + grade + "' WHERE Reg_num= '" + regNumber + "' AND Course_Code='" + courseCode + "'";
        if (DataAccess.update(MYSQL)) {// this if the rows updated or not
            if (updateTotal) {//see if the update total is tru or not to see if we will display the final grade or not
                String MYSQL2 = "update student_course_registration set grade= cast(midTerm_grade as unsigned)+cast(course_Work as unsigned)+cast(final as unsigned) WHERE Reg_num= '" + regNumber + "' AND Course_Code='" + courseCode + "'";
                /*this is a special query because we cast the strings into unsigned numbers then we can make the processing in the database and calculate the grade
                and store it in the database and this will make the processing faster because the database has a special data structure called B-Tree (Balanced Tree) Index
                 */
                DataAccess.update(MYSQL2);
            }
            return "grades updated successfully";
        }
        return "check course code or student registration number";//if there is an error
    }
}

