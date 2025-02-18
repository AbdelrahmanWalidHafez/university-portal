package userlogin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class network {
    private static Socket socket;
    private static  DataOutputStream out;
    private static DataInputStream in;
  static{
        try {
            socket = new Socket("172.20.10.4", 8000);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } 
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
      static String login (String email , String password){
     try{
          out.writeUTF("login");
          out.writeUTF(email);
          out.writeUTF(password);
          return in.readUTF();
      }
  
     catch(IOException e){
           return "";    
      } 
  }

 static String studentRegistration (String ssn , String fn , String mn, String ln, String address, String dob, String phoneNo , String gender){
     try{
          out.writeUTF("studentRegistration");
          out.writeUTF(ssn);
          out.writeUTF(fn);
          out.writeUTF(mn);
          out.writeUTF(ln);
          out.writeUTF(dob);
          out.writeUTF(address);
          out.writeUTF(phoneNo);
          out.writeUTF(gender);
          
          return in.readUTF();
      }
  
     catch(IOException e){
     return "";    
      }
  }
  static String adminOrProfessorRegistration (String ssn , String fn , String mn, String ln, String address, String dob, String phoneNo , String gender, String userType){
     try{
          out.writeUTF("adminOrProfessorRegistration");
          out.writeUTF(ssn);
          out.writeUTF(fn);
          out.writeUTF(mn);
          out.writeUTF(ln);
          out.writeUTF(dob);
          out.writeUTF(address);
          out.writeUTF(phoneNo);
          out.writeUTF(gender);
          out.writeUTF(userType); 
          
          return in.readUTF();
      }
       catch(IOException e){
         return "";    
     } 
  }
      static String courseRegistration (String firstWord , String secondWord){
     try{
          out.writeUTF("courseRegistration");
          String courseName = firstWord+" "+secondWord;
          out.writeUTF(courseName);
          return in.readUTF();
      }
      catch(IOException e){
       return "";    
    }
      }
    static String deleteUser (String email, String userType){
     try{
          out.writeUTF("deleteUser");
          out.writeUTF(email);
          out.writeUTF(userType);
          
          return in.readUTF();
      }
     catch(IOException e){
      return "";    
     } 
  }
     static String deleteCourse (String courseCode){
     try{
          out.writeUTF("deleteCourse");
          out.writeUTF(courseCode); 
          return in.readUTF();
      }
     catch(IOException e){
     return "";    
    }  
  }
     static String updateStudentGrades (String courseCode, String regNo, String grade, String gradeType){
     try{
          out.writeUTF("updateStudentGrades");
          out.writeUTF(courseCode); 
          out.writeUTF(regNo); 
          out.writeUTF(grade); 
          out.writeUTF(gradeType); 
          return in.readUTF();
      }
    catch(IOException e){
       return "";    
      }
     }
      static String registerCourseForStudent(String courseCode, String email, String regNo ){
         try{
          out.writeUTF("registerCourseForStudent");
          out.writeUTF(courseCode); 
          out.writeUTF(email); 
          out.writeUTF(regNo); 
          return in.readUTF();
        }
        catch(IOException e){
         return "";    
        }   
      }
      static String[] viewStudentPersonalInformation(String email ){
         try{
          out.writeUTF("viewStudentPersonalInformation");
          out.writeUTF(email); 
          String personalInfo [] = new String [10];
         for(int i=0 ; i<personalInfo.length; i++){
              personalInfo[i] = in.readUTF();
          }
          return personalInfo ;   
      }
     catch(IOException e){
     return new String []{};    
     }   
    }
       static String viewStudentGrades(String email ){
         try{
          out.writeUTF("viewStudentGrades");
          out.writeUTF(email); 
          return in.readUTF();
         } 
         catch(IOException e){
          return "";    
        }   
      }
    }
     
      
  

