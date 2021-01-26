package helper;

import java.sql.*;
import java.util.Properties;


public class MySQLConnection {
	
    	public static void   MySQLDBConnection()  {
				
    		
    		//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"	
    		String Environment;
    		String username;
			String password ;
			String dbUrl ;

			Properties prop = Configutil.configdatafile("Config.Properties");
			Environment = prop.getProperty("DBEnvironment");
    		  /*
    		
    		  username = "catobsco";
    		  password = "5Vh0o*d6";
    		  dbUrl = "jdbc:mysql://66.45.225.10:3306/catobsco_po";
    		  */
    		    		 
    		  if (Environment.equalsIgnoreCase("it786"))
    		  { System. out.println("Connecting With "+Environment+" Environment");
    			 username = prop.getProperty("it786_dbUser");
  				 password = prop.getProperty("it786_dbPassword");
  				 dbUrl = "jdbc:mysql://"+prop.getProperty("it786_dbHostAddress")+":"+prop.getProperty("it786_dbPort")+"/"+prop.getProperty("it786_dbName");	
  				
    		  }
    		  
    		  else if (Environment.equalsIgnoreCase("Local"))
    		  {
    			  System. out.println("Connect With "+Environment+" Environment");
    			 username = prop.getProperty("Local_dbUser");
  				 password = prop.getProperty("Local_dbPassword");
  				 dbUrl = "jdbc:mysql://"+prop.getProperty("Local_dbHostAddress")+":"+prop.getProperty("Local_dbPort")+"/"+prop.getProperty("Local_dbName");	
    		  }
    		  
    		  else if (Environment.equalsIgnoreCase("TEST"))
    		  {
    			 System. out.println("Connect With "+Environment+" Environment");
    			 username = prop.getProperty("TEST_dbUser");
  				 password = prop.getProperty("TEST_dbPassword");
  				 dbUrl = "jdbc:mysql://"+prop.getProperty("TEST_dbHostAddress")+":"+prop.getProperty("TEST_dbPort")+"/"+prop.getProperty("TEST_dbName");	
    		  }
    		  else if (Environment.equalsIgnoreCase("QA")) 
    		  {
    			  System. out.println("Connect With "+Environment+" Environment");
    			 username = prop.getProperty("QA_dbUser");
  				 password = prop.getProperty("QA_dbPassword");
  				 dbUrl = "jdbc:mysql://"+prop.getProperty("QA_dbHostAddress")+":"+prop.getProperty("QA_dbPort")+"/"+prop.getProperty("QA_dbName");	
    		  }
    		  else
    		  {
    			 System. out.println("Connect With Unknown Environment"+Environment);
    			 username = prop.getProperty("it786_dbUser");
  				 password = prop.getProperty("it786_dbPassword");
  				 dbUrl = "jdbc:mysql://"+prop.getProperty("it786_dbHostAddress")+":"+prop.getProperty("it786_dbPort")+"/"+prop.getProperty("it786_dbName");	
    		  }
    		  
    		  				
                
         	    /*Load mysql jdbc drive*/	
           	  //  Class.forName("com.mysql.cj.jdbc.Driver");			
           		//Query to Execute		
				String query ="SELECT*FROM`user` WHERE CWS_id LIKE '%pressz1%';";	
           	    
           	    
           		//Create Connection to DB		
			Connection con = null;
			try {
				con = DriverManager.getConnection(dbUrl,username,password);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

			//Create Statement Object
			Statement stmt = null;
			try {
				assert con != null;
				stmt = con.createStatement();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs= null;
			try {
				assert stmt != null;
				rs = stmt.executeQuery(query);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

			// While Loop to iterate through all data and print results
				while (true){
					try {
						assert rs != null;
						if (!rs.next()) break;
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
					String Data = null;
					try {
						Data = rs.getString(4)+rs.getString("first_name");
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}

					System. out.println(Data);
                    }		
      			 // closing DB Connection		
			try {
				con.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
}