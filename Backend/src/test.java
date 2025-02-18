public class test {
    public static void main(String[] args) {
        Admin admin = new Admin("12345678912345", "abdelrahman", "walid", "hafez", "11/11/2003", "6 roushdy basha st", "01000906333", "male");
        System.out.println(API.adminOrProfessorRegistration(admin));
    }
}
