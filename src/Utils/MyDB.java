/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed
 */
public class MyDB {
    private static MyDB instance ;
    final String URL ="jdbc:mysql://127.0.0.1:3306/pidev";
    final String USERNAME ="root";
    final String PWD ="";
   private  Connection cnx;
   private static int idCoachingToPick;
    private static Stage stageAdresse;
   

    public static Stage getStageAdresse() {
        return stageAdresse;
    }

    public static void setStageAdresse(Stage stageAdresse) {
        MyDB.stageAdresse = stageAdresse;
    }


    public static int getIdCoachingToPick() {
        return idCoachingToPick;
    }

    public static void setIdCoachingToPick(int idCoachingToPick) {
        MyDB.idCoachingToPick = idCoachingToPick;
    }
    
    private MyDB(){
        try {
             cnx =DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("connected ......");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public  Connection getCnx (){
        return this.cnx;
    }
    public static MyDB getInstance (){
        if (instance == null )
            instance = new MyDB();
        
        return instance;
    }
}
