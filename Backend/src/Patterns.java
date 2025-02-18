
public interface Patterns {//these patterns that we use to validate the requirements and optimize the code appearance
    String egyptianSSNPattern = "\\d{14}";
    String egyptianPhoneNumberPattern = "01\\d{9}";
    String datePattern ="^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";
    String sexPattern = "(male|female)";
    String courseNamePattern = "^\\S+\\s+\\S+$";
}
