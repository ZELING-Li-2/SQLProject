import java.sql.*;
import java.io.*;
import java.util.Scanner;

/*
CSCE 315
9-27-2021 Lab
 */
public class jdbcpostgreSQL {

  //Commands to run this script
  //This will compile all java files in this directory
  //javac *.java 
  //This command tells the file where to find the postgres jar which it needs to execute postgres commands, then executes the code
  //java -cp ".;postgresql-42.2.8.jar" jdbcpostgreSQL

  //MAKE SURE YOU ARE ON VPN or TAMU WIFI TO ACCESS DATABASE
  public static void main(String args[]) {
 
    //Building the connection with your credentials
    //TODO: update dbName, userName, and userPassword here
     Connection conn = null;
     String teamNumber = "2";
     String sectionNumber = "901";
     String dbName = "csce315" + sectionNumber + "_" + teamNumber + "db";
     String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
     String userName = "csce315" + sectionNumber + "_" + teamNumber + "user";
     String userPassword = "password1";

    //Connecting to the database 
    try {
        conn = DriverManager.getConnection(dbConnectionString,userName, userPassword);
        } 
        catch (Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
        }

    System.out.println("Opened database successfully");
     
    try{
       //create a statement object
       

       //Running a query
       //TODO: update the sql command here
       
        String command_type = "";
        BufferedReader reader = new BufferedReader(new FileReader("data/test.csv"));
        //much faster than scanner, but will loop around file for some reason
        String line = "";
        String value = "";
        String sqlStatement = "";
        int num = 0;
        line = reader.readLine();
        while (line != null){

            sqlStatement = line;
            //System.out.println(sqlStatement);
            num = num+1;
            if (num % 100 == 0){
                System.out.println(num);
                //System.out.println(line);
            }
            if (num > 300000){//buffered reader loops around for some reason
                break;
            }
            Statement stmt = conn.createStatement();
           //System.out.println(command_type);
            
            if ((sqlStatement.equals("Query")) || (sqlStatement.equals("Update"))){//. equals. wtf. this language is literally garbage
               command_type = sqlStatement;
                }
            else if (command_type.equals("Query")){
               //send statement to DBMS
               //This executeQuery command is useful for data retrieval
               //ResultSet result = stmt.executeQuery(sqlStatement);
               //OR
               //This executeUpdate command is useful for updating data
                try{
                    ResultSet resultSet = stmt.executeQuery(sqlStatement);
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    
                    System.out.println("--------------------Query Results--------------------");
                    while (resultSet.next()) {
                        for (int i = 0; i <= columnsNumber; i++) {
                            
                            if (i > 0){ 
                            
                            String columnValue = resultSet.getString(i);
                            System.out.print(columnValue + " " + rsmd.getColumnName(i));
                            System.out.print(",  ");
                            }
                        }
                        System.out.println("");
                    }
                }                        
                catch(Exception e){//java randomly reads files wrong..
                    System.out.println(sqlStatement);
                    System.out.println(num);
                    e.printStackTrace();
                    System.err.println(e.getClass().getName()+": "+e.getMessage());
                    System.exit(0);
                }
               
               /*
               ResultSet result = stmt.executeQuery(sqlStatement);

               //OUTPUT
               //You will need to output the results differently depeninding on which function you use
               System.out.println("--------------------Query Results--------------------");
                while (result.next()){
                    value = result.getString("section");
                    }
                  
                  System.out.println(value);
                  
                 */
            }                                
            else if (command_type.equals("Update")){
               try{
                   int result = stmt.executeUpdate(sqlStatement);
                
                   //OUTPUT
                   //You will need to output the results differently depeninding on which function you use
                   //System.out.println("--------------------Query Results--------------------");
                   /*
                   while (result.next()) {
                        System.out.println(result.getString("column_name"));
                   }*/
                   //OR
                   
                   //System.out.println(result);
                }
                catch(Exception e){
                    System.out.println(sqlStatement);
                    System.out.println(num);
                    e.printStackTrace();
                    System.err.println(e.getClass().getName()+": "+e.getMessage());
                    System.exit(0);
                }
            }
            line = reader.readLine();
        }
        reader.close();
    }
    catch (Exception e){
        e.printStackTrace();
        System.err.println(e.getClass().getName()+": "+e.getMessage());
        System.exit(0);
        }
        

    
    //closing the connection
    try {
      conn.close();
      System.out.println("Connection Closed.");
    } catch(Exception e) {
      System.out.println("Connection NOT Closed.");
    }//end try catch
  }//end main
}//end Class