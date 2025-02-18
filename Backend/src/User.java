public class User {
    private String ssn;
    private String fName;
    private String mName;
    private String lName;
    private String DOB;
    private String address;
    private String phoneNumber;
    private String sex;
    private String role;
    private String password;
    private String email;

    public User(String ssn, String fName, String mName, String lName, String DOB, String address, String phoneNumber, String sex) {
        this.ssn = ssn;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.DOB = DOB;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public User(String email) {
        this.email = email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getSsn() {
        return ssn;
    }

    public String getfName() {
        return fName;
    }

    public String getmName() {
        return mName;
    }

    public String getlName() {
        return lName;
    }

    public String getDOB() {
        return DOB;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
