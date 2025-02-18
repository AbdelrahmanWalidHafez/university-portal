import java.util.Random;

class HelperMethods {
    static String generateEmail(String fName, String idNumber) { //generate email
        return fName.toLowerCase() + "_" + idNumber + "@gmail.com";
    }

    static String generateRegistrationOrIdNumberOrPasswords() {
        Random rand = new Random();
        StringBuilder regNumber = new StringBuilder();// stringBuilder is a class to use to append on regNumber
        for (int i = 0; i < 9; i++) {
            regNumber.append(rand.nextInt(10));//making random ids/pass/reg.numbers to any type of user
        }
        return regNumber.toString();//returning the toString of the object stringBuilder
    }

    static String generateCourseCode(String courseName) {
        courseName = courseName.trim().toUpperCase(); // Convert to uppercase and trim whitespace
        String[] words = courseName.split("\\s+"); // Split into words
        StringBuilder courseCode = new StringBuilder();
        for (String word : words) {//looping on the words only which their count is 2
            courseCode.append(word.charAt(0)); // Append first character of each word
        }
        Random random = new Random();
        courseCode.append(random.nextInt(1000));// Append three random digits
        return courseCode.toString();
    }
}
