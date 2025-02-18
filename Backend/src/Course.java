
public class Course {
    private String courseCode;
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(String courseCode, String token) {//we added the string token because we cant break the overloading rule in oop
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return this.courseName;
    }
}
