package br.com.projectschedule.model;

import br.com.projectschedule.controll.Contact;
import br.com.projectschedule.controll.Phone;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas David
 */
public class DAO  {

     //------------Variáveis nescessarias
    final String url = "jdbc:mysql://localhost:3306/schedule";
    final String user = "root";
    final String pass = "12345";
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    //---------------------------------------
    
    
    private void ConnectBank(){
        try {
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection(url,user,pass);
             System.out.println("Connected");
             statement = connection.createStatement();
             
             
        } catch (ClassNotFoundException ex) {//tratamento de erro de drive
		  System.out.println("Classe nao encontrada, adicione o driver nas bibliotecas.");
		Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
		} 
		
		catch(SQLException e) {//tratamento de erro de SQL
		  System.out.println(e);
		  throw new RuntimeException(e);
	  } 
        
    }
    
    private void CloseBank(){
        try {
            if (resultSet != null){
                resultSet.close();
            }        
            statement.close();
            connection.close();              
        }             
        catch (SQLException exception){
        }         
    }
      
    public void CreateContact(Contact contact,List<Phone> phones, int counter) {
        int id = 0;
        try {
            ConnectBank();
            
            //Insert in table contact
            ps = connection.prepareStatement("INSERT INTO contact(name,email)VALUES(?,?)"
                    ,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,contact.getNome());
            ps.setString(2,contact.getEmail());
            ps.execute();
            //_________________________
            
            //get id of last contact insert 
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                id =rs.getInt(1);
                System.out.println(id);
            }
            //__________________
            
            
            //Insert in table phone
            for(int i = 0; i < counter; i++){
            ps = connection.prepareStatement("INSERT INTO phone(ddd,number,idContato)VALUES(?,?,?)");
            ps.setString(1,phones.get(i).getDdd());
            ps.setString(2,phones.get(i).getTelefone());
            ps.setInt(3,id);
            ps.execute();
            }
            //__________________
                     
            CloseBank();

          
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
       
    }
    
    public void UpdateContact(String i, String name, String email) {
        int id = Integer.parseInt(i);
        try {
            ConnectBank();

            //Update in table Contact
            ps = connection.prepareStatement("UPDATE contact SET name = ? , email = ?  where id = ?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);
            ps.execute();
            //_________________________

            CloseBank();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
    public void UpdatePhone(String i, String ddd, String number) {
        int id = Integer.parseInt(i);
        try {
            ConnectBank();

            //Update in table phone
            ps = connection.prepareStatement("UPDATE phone SET ddd = ? , number = ?  where id = ?");
            ps.setString(1, ddd);
            ps.setString(2, number);
            ps.setInt(3, id);
            ps.execute();
            //_________________________

            CloseBank();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
    public List ReadContact(){
        List<Contact> contacts = new ArrayList<>();
        List<Phone> phones = new ArrayList<>();
        ConnectBank();
        try {     
              resultSet = statement.executeQuery("SELECT * FROM contact");
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int numberOfColumns = metaData.getColumnCount();
              System.out.println("_____________________Contatos___________________\n");
            while(resultSet.next()){
                contacts.add(new Contact(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email")));
            }
            System.out.println(contacts);
            
//            for(int i =1; i<= numberOfColumns; i++){
//                System.out.printf("%-8s",metaData.getColumnName(i));
//            }
//            System.out.println("");
//            
//            while (resultSet.next()) {
//                for(int i =1; i <= numberOfColumns; i++){
//                    
//                    System.out.printf("%-8s",resultSet.getObject(i));
//                }
//                System.out.println("");
//                
//            }

        } catch (SQLException e) {
        }
        
        CloseBank();
        return contacts;
    }
    public List ReadPhones(int id){
        List<Phone> phones = new ArrayList<>();
        ConnectBank();
        try {  

              ps = connection.prepareStatement("SELECT * FROM phone WHERE idContato = ?");            
              ps.setInt(1, id);
              resultSet = ps.executeQuery();
              
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int numberOfColumns = metaData.getColumnCount();
              System.out.println("_____________________Contatos___________________\n");
                
            while(resultSet.next()){
                phones.add(new Phone(resultSet.getInt("id"),resultSet.getInt("idContato"),  resultSet.getString("ddd"), resultSet.getString("number")));
            }
            System.out.println(phones);
            

        } catch (SQLException e) {
        }
        
        CloseBank();
        return phones;
    }
    public void DelPhone(String i) {
        int id = Integer.parseInt(i);
        try {
            ConnectBank();
            
            //Insert in table phone
            ps = connection.prepareStatement("DELETE FROM phone where id = ?");
            ps.setInt(1,id);
            ps.execute();
            //_________________________
 
            CloseBank();

          
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
       
    }
    public void DelContact(String i) {
        int id = Integer.parseInt(i);
        try {
            ConnectBank();
            
            //Delelte in table phone
            ps = connection.prepareStatement("DELETE FROM phone where idContato = ?");
            ps.setInt(1,id);
            ps.execute();
            //_________________________
 
            //Delete in table contact
            ps = connection.prepareStatement("DELETE FROM contact where id = ?");
            ps.setInt(1,id);
            ps.execute();
            //_________________________
            
            
            CloseBank();

          
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
       
    }
}
