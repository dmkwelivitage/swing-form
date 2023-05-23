/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

/**
 *
 * @author nooby
 * 
 */
import java.sql.*;
public class Registration {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Registration initialize = new Registration();
        initialize.connect();
        initialize.init();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuFrame().setVisible(true);
            }
        });
        
    }  
    
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            Statement sdb;
            sdb = conn.createStatement();
            sdb.execute("Create Database if not exists Java_Registration");
            System.out.println(conn);
            sdb.close();
            conn.close();
            
        }
        catch (Exception e){
            System.out.println("inter.DBConnect.connect()");
        }
    }
    
    public void init() {
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Java_Registration", "root", "");
            Statement sDB;
            sDB = conn.createStatement();
            sDB.execute("Create Table if not exists Course_details(CourseID int primary key auto_increment, Course_Name varchar(300),Course_Description varchar(300),Course_Price int,Course_Period int)");
            sDB.execute("Create Table if not exists Student_details(StudentID int primary key auto_increment, Student_Name varchar(300),Student_Address varchar(300),Student_Age int,Student_ContactNumber int)");
            sDB.execute("Create Table if not exists Student_Course(StudentID int, CourseID int , constraint FK_Student_Course foreign key(StudentID,CourseID) references Student_details(StudentID,CourseID))");
            System.out.println(conn);
            sDB.close();
            conn.close();
            
        }
        catch (Exception e){
            System.out.println("inter.DBConnect.connect()");
        }
    }
    
}
