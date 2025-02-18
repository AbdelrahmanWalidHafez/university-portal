import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Arrays;

public class ClientHandler extends Thread {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream()))//The try-with-resources statement ensures that both the input and output streams are automatically closed when the try block exits
        {
            while (true) {
                // Read the API command sent by the client
                String command = in.readUTF();
                // Process the received command
                if (clientSocket.isClosed()) {
                    DataAccess.connection.close();//closing the database connection
                    // If the client requests to exit, close the connection
                    break;
                } else if (command.equalsIgnoreCase("login")) {
                    // Handle login functionality
                    // Read email and password from the client
                    String email = in.readUTF();
                    String password = in.readUTF();
                    User user = new User(email, password);
                    // Perform login operation and send back the result to the client
                    out.writeUTF(API.login(user));
                } else if (command.equalsIgnoreCase("studentRegistration")) {
                    // Handle student registration functionality
                    // Read student registration details from the client
                    String ssn = in.readUTF();
                    String firstName = in.readUTF();
                    String middleName = in.readUTF();
                    String lastName = in.readUTF();
                    String dob = in.readUTF();
                    String address = in.readUTF();
                    String phoneNumber = in.readUTF();
                    String sex = in.readUTF();
                    // Construct a Student object and perform registration
                    Student student = new Student(ssn, firstName, middleName, lastName, dob, address, phoneNumber, sex);
                    String registrationResult = API.studentRegistration(student);
                    // Send back the registration result to the client
                    out.writeUTF(registrationResult);
                } else if (command.equalsIgnoreCase("adminOrProfessorRegistration")) {
                    // Handle admin or professor registration functionality
                    // Read admin/professor registration details from the client
                    String ssn = in.readUTF();
                    String firstName = in.readUTF();
                    String middleName = in.readUTF();
                    String lastName = in.readUTF();
                    String dob = in.readUTF();
                    String address = in.readUTF();
                    String phoneNumber = in.readUTF();
                    String sex = in.readUTF();
                    String userType = in.readUTF();
                    // Determine the type of user (admin or professor)
                    if (userType.equalsIgnoreCase("Professor")) {
                        // Construct a Professor object and perform registration
                        Professor professor = new Professor(ssn, firstName, middleName, lastName, dob, address, phoneNumber, sex);
                        String registrationResult = API.adminOrProfessorRegistration(professor);
                        // Send back the registration result to the client
                        out.writeUTF(registrationResult);
                    } else if (userType.equalsIgnoreCase("Admin")) {
                        // Construct an Admin object and perform registration
                        Admin admin = new Admin(ssn, firstName, middleName, lastName, dob, address, phoneNumber, sex);
                        String registrationResult = API.adminOrProfessorRegistration(admin);
                        // Send back the registration result to the client
                        out.writeUTF(registrationResult);
                    } else {
                        out.writeUTF("check user type");
                    }
                } else if (command.equalsIgnoreCase("courseRegistration")) {
                    // Handle course registration functionality
                    // Read course details from the client
                    String courseName = in.readUTF();
                    // Construct a Course object and perform registration
                    Course course = new Course(courseName);
                    String registrationResult = API.courseRegistration(course);
                    // Send back the registration result to the client
                    out.writeUTF(registrationResult);
                } else if (command.equalsIgnoreCase("deleteUser")) {
                    // Handle delete user functionality
                    // Read user details (user type and email) from the client
                    String email = in.readUTF();
                    String userType = in.readUTF();
                    // Determine the type of user (student or professor)
                    if (userType.equalsIgnoreCase("Student")) {
                        // Construct a Student object and perform deletion
                        Student student = new Student(email);
                        String deletionResult = API.deleteUser(student);
                        // Send back the deletion result to the client
                        out.writeUTF(deletionResult);
                    } else if (userType.equalsIgnoreCase("Professor")) {
                        // Construct a Professor object and perform deletion
                        Professor professor = new Professor(email);
                        String deletionResult = API.deleteUser(professor);
                        // Send back the deletion result to the client
                        out.writeUTF(deletionResult);
                    } else {
                        out.writeUTF("check user type");
                    }
                } else if (command.equalsIgnoreCase("deleteCourse")) {
                    // Handle delete course functionality
                    // Read course details (course code) from the client
                    String courseCode = in.readUTF();
                    Course course = new Course(courseCode, "");
                    // Perform deletion of the course
                    String deletionResult = API.deleteCourse(course);
                    // Send back the deletion result to the client
                    out.writeUTF(deletionResult);
                } else if (command.equalsIgnoreCase("updateStudentGrades")) {
                    String courseCode = in.readUTF();
                    String regNumber = in.readUTF();
                    String grade = in.readUTF();
                    String gradeType = in.readUTF();
                    String updateStudentGradesResult = API.updateStudentGrades(courseCode, regNumber, grade, gradeType);
                    out.writeUTF(updateStudentGradesResult);
                } else if (command.equalsIgnoreCase("registerCourseForStudent")) {
                    String courseCode = in.readUTF();
                    String email = in.readUTF();
                    String regNumber = in.readUTF();
                    Course course = new Course(courseCode, "");
                    Student student = new Student(email, regNumber);
                    String registerCourseForStudent = API.registerCourseForStudent(course, student);
                    out.writeUTF(registerCourseForStudent);
                } else if (command.equalsIgnoreCase("viewStudentPersonalInformation")) {
                    String email = in.readUTF();
                    Student student = new Student(email);
                    String[] viewStudentPersonalInformation = API.viewStudentPersonalInformation(student);
                    for (String s : viewStudentPersonalInformation) {
                        out.writeUTF(s);
                    }
                } else if (command.equalsIgnoreCase("viewStudentGrades")) {
                    String email = in.readUTF();
                    Student student = new Student(email);
                    String viewStudentGradesResponse = Arrays.deepToString(API.viewStudentGrades(student));
                    out.writeUTF(viewStudentGradesResponse);
                }
            }
        } catch (NullPointerException | IOException | SQLException e) {

            System.err.println("Error handling client connection: " + e.getMessage());
        }
    }
}
