/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nooby
 */
class DatabaseConnection {
    
    private static DatabaseConnection jdbc;
    
    private DatabaseConnection(){}
    
    public static DatabaseConnection getInstance(){
        if (jdbc==null)
        {
          jdbc = new DatabaseConnection();  
        }
    return jdbc;
    }
    
    private static Connection getConnection() throws ClassNotFoundException,SQLException{
        String url = "jdbc:mysql://localhost:3306/java_registration";
        String user = "root";
        String password = "";
        Connection conn = null;
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,password);
        }
        catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    return conn;
    }
    
    public int CourseInsert(String Name,String Description,String Price,String Period) throws SQLException{
        Connection c=null;
        PreparedStatement ps=null;
        String insert=null;
        int recordCounterCourse=0;
        try{
            c=this.getConnection();
            insert ="Insert into Course_details(Course_Name , Course_Description, Course_Price, Course_Period) values(?,?,?,?)";
            ps=c.prepareStatement(insert);
            ps.setString(1, Name);
            ps.setString(2, Description);
            ps.setString(3, Price);
            ps.setString(4, Period);
            recordCounterCourse=ps.executeUpdate();
        }
        catch(Exception e){e.printStackTrace();}
        finally{
            if(ps!=null){
                ps.close();
            }
            if(c!=null){
                c.close();
            }
        }
    return recordCounterCourse;
    }
    
    public int StudentInsert(String Name,String Address,String Age,String Number) throws SQLException{
        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String insert=null;
        int recordCounterStudent=0;
        try{
            c=this.getConnection();
            insert = "Insert into Student_details(Student_Name , Student_Address, Student_Age, Student_ContactNumber) values(?,?,?,?)";
            ps=c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Name);
            ps.setString(2, Address);
            ps.setString(3, Age);
            ps.setString(4, Number);
            recordCounterStudent=ps.executeUpdate();
            rs = ps.getGeneratedKeys();
                if(rs.next()){
                    studentId = rs.getInt(1);
                }
        }
        catch(Exception e){e.printStackTrace();}
        finally{
            if(ps!=null){
                ps.close();
            }
            if(c!=null){
                c.close();
            }
        }
    return recordCounterStudent;
    }
    
    public int StudentCourseInsert(Integer StudentId, Integer CourseId) throws SQLException{
        Connection c=null;
        PreparedStatement ps=null;
        String insert=null;
        int recordCounterStudentCourse=0;
        try{
            c=this.getConnection();
            insert ="Insert into Student_course (StudentID, CourseID) values(?,?)";
            ps=c.prepareStatement(insert);
            ps.setInt(1, StudentId);
            ps.setInt(2, CourseId);
            recordCounterStudentCourse=ps.executeUpdate();
        }
        catch(Exception e){e.printStackTrace();}
        finally{
            if(ps!=null){
                ps.close();
            }
            if(c!=null){
                c.close();
            }
        }
    return recordCounterStudentCourse;
    }
    
    public List getCourseName() {
        Connection c=null;
        Statement stmt=null;
        ResultSet rs=null;
        String q=null;
        try {
            c=this.getConnection();
            stmt = c.createStatement();
            q = "Select * from Course_details";
            rs = stmt.executeQuery(q);
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("Course_Name"));
                course.setCoursePrice(rs.getInt("Course_Price"));
                course.setCoursePeriod(rs.getInt("Course_Period"));
                course.setDescription(rs.getString("Course_Description"));
                courseList.add(course);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return courseList;
    }
     
//varible Declaration
List<Course> courseList = new ArrayList<>();
int studentId = 0;
//end of variable declaration
}
