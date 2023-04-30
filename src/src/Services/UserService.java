/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Session;
import Entities.User;
import Utils.MyDB;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rayan
 */
public class UserService implements Iuser<User>{
      private Connection conn;

    public UserService() {
        conn = MyDB.getInstance().getCnx();
       
    }
 public String hashPassword(String password) {
    String salt = BCrypt.gensalt(10);
    String hashedPassword = BCrypt.hashpw(password, salt);
    return hashedPassword;
}
    @Override
    public void insert(User p) {
               String requete="insert into user(nom,prenom,password,email,roles,image) values (?,?,?,?,?,?)";
        try {
             PreparedStatement pst=conn.prepareStatement(requete);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setString(3, p.getPassword());
                         String myPwd = "$2y" + BCrypt.hashpw(p.getPassword(), BCrypt.gensalt(13)).substring(3);
pst.setString(3, myPwd);
            pst.setString(4, p.getEmail());
            pst.setString(5, p.getRoles());
            pst.setString(6, p.getImage());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @Override
    public void delete(User t) {
        String requete="delete from user where id ="+t.getId();
        try {
           
            Statement st=conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

  

    @Override
    public List<User> readAll() {
           String requete="select * from user";
        List<User> list=new ArrayList<>();
        
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                User p=new User(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("roles"),
                        rs.getString("image"),
                rs.getInt("is_active"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
@Override
    public void update(User p) {
String requete="update user set nom=?, prenom=?, password=?, email=?, roles=? ,image=? where id=?";
   try {
            PreparedStatement pst=conn.prepareStatement(requete);
           pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setString(3, p.getPassword());
                         String myPwd = "$2y" + BCrypt.hashpw(p.getPassword(), BCrypt.gensalt(13)).substring(3);
pst.setString(3, myPwd);
            pst.setString(4, p.getEmail());
            pst.setString(5, p.getRoles());
            pst.setString(6, p.getImage());
             pst.setInt(7, p.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

 @Override
    public User readByID(int idUser) {
String requete ="select * from user where idUser="+idUser;
    User p = null;
    try {
        Statement st=conn.createStatement();
        ResultSet rs= st.executeQuery(requete);
        if (rs.next()) {
            
                 p=new User(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("roles"),
                        rs.getString("image"));
   
   
    }  
    }

       catch (SQLException ex) {
              Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
          }
        return p;
    }
    
    public boolean isEmailUsed(String email) {
         String req="SELECT COUNT(*) AS count FROM user WHERE email = ?";
          try {
           
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setString(1, email);
         ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("count");
            return count > 0;
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    }
    
     public Boolean login(String email, String Pwd) {
        try {
            String req = "SELECT * FROM user WHERE email=? ";
            PreparedStatement pre = conn.prepareStatement(req);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                /* String myPwd="$2y"+rs.getString("password").substring(3);
            if (BCrypt.checkpw(Pwd, myPwd)) {
                Session.setId(rs.getInt("id"));
                Session.setUsername(rs.getString("username"));
                Session.setEmail(rs.getString("email"));
                Session.setRoles(rs.getString("roles"));
                System.out.println("login ");
            }*/
                String myPwd = "$2a" + rs.getString("password").substring(3);
                System.out.println(rs.getString("password"));
                    System.out.println(Pwd);
                   System.out.println("AAA"+rs.getInt("is_active"));
                if (BCrypt.checkpw(Pwd, myPwd) ) {
                    Session.setId(rs.getInt("id"));
                    Session.setNom(rs.getString("nom"));
                    Session.setPrenom(rs.getString("prenom"));
                    Session.setEmail(rs.getString("email"));
                    //Session.setPassword(rs.getString("password"));
                    Session.setRoles(rs.getString("roles"));
                      Session.setImage(rs.getString("image"));
                      Session.setIs_active(rs.getInt("is_active"));
                    System.out.println("login ");
                    return true;
                }
                else System.out.println("NOOO ");
                // return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
     
      public void ChangerNom(String nom,int id) {
          String req="update user set nom=? where id=?";
        try {
           
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setString(1, nom);
        pst.setInt(2, id);
        pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void updateprofile(User p) {
String requete="update user set nom=?, prenom=?,email=?, roles=? ,image=? where id=?";
   try {
            PreparedStatement pst=conn.prepareStatement(requete);
           pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setString(3, p.getEmail());
            pst.setString(4, p.getRoles());
            pst.setString(5, p.getImage());
             pst.setInt(6, p.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

  public void updatemdp(User stu) {
        String req = "update user set password=?  WHERE email=?";
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setString(1, stu.getPassword());
                         String myPwd = "$2y" + BCrypt.hashpw(stu.getPassword(), BCrypt.gensalt(13)).substring(3);
pst.setString(1, myPwd);
           pst.setString(2, stu.getEmail());
            pst.executeUpdate();
            System.out.println("Password updated");
            System.out.println(stu.getPassword());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public int existence(String email) {
        int f = 0;
         String query = "select * from user where email =?";
        try {

            
            
           PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
         ResultSet rs = pst.executeQuery();

            if (rs.next() == false) {
                f = 1;
            } else {
                f = 0;
            }
           

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println("f est :"+f);
        return f;

    }
   public User getUser(String email) {
        User stu = new User();
        try {
            String query = "select * from user where email =?";
           PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
         ResultSet rs = pst.executeQuery();

            //tant que rs has next get matiere and add it to the list
            while (rs.next()) {

                stu.setId(rs.getInt("id")); //set id from req result
                stu.setNom(rs.getString("nom"));
                stu.setPrenom(rs.getString("prenom"));
                stu.setEmail(rs.getString("email"));
                stu.setPassword(rs.getString("password"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return stu;
    }
 public ObservableList<User> getAllTriNom() {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
         //   String req = "Select * from espacetalent where roles like '%[]%' order by nom";
                String req = "Select * from user  order by Nom";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            //    EspaceTalent u = new EspaceTalent(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"), rs.getString("file"), rs.getInt("etat"), rs.getDate("created_at"));
               User a = new User( rs.getString("Nom"), rs.getString("Prenom"),rs.getString("Password"),rs.getString("Email"),rs.getString("roles"),rs.getString("Image")); 
        list.add(a) ;
        
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
     public void banUser(int id) {
        try {
             //"UPDATE user SET is_active = CASE WHEN is_active = 1 THEN 0 ELSE 1 END WHERE id = ?";
            String qry ="UPDATE user SET is_active = 1 WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(qry);
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("User with ID " + id + " has been banned/unbanned successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void debloquer(int id) {
        try {
             //"UPDATE user SET is_active = CASE WHEN is_active = 1 THEN 0 ELSE 1 END WHERE id = ?";
            String qry ="UPDATE user SET is_active = 0 WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(qry);
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("User with ID " + id + " has been banned/unbanned successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
      public ObservableList<User> TriNomDESC() {
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
         //   String req = "Select * from espacetalent where roles like '%[]%' order by nom";
                String req = "Select * from user  order by Nom DESC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
            //    EspaceTalent u = new EspaceTalent(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("email"), rs.getString("file"), rs.getInt("etat"), rs.getDate("created_at"));
               User a = new User( rs.getString("Nom"), rs.getString("Prenom"),rs.getString("Password"),rs.getString("Email"),rs.getString("roles"),rs.getString("Image")); 
        list.add(a) ;
        
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
