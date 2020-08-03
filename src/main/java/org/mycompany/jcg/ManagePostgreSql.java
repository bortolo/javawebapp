package org.mycompany.jcg;

import java.sql.*;
import java.util.Properties;
import java.io.IOException;

import org.mycompany.models.Actor;
import org.mycompany.util.ManageConfigProperties;


// Useful links
// https://www.codementor.io/@engineerapart/getting-started-with-postgresql-on-mac-osx-are8jcopb


public class ManagePostgreSql{

    private String url = "";
    private String user = "";
    private String password = "";
    // private final String url = "jdbc:postgresql://localhost:5432/mypgsqldb";
    // private final String user = "andreabortolossi";
    // private final String password = "Password1234!";
    // private final String url = "jdbc:postgresql://mybortolodbprova.postgres.database.azure.com:5432/mypgsqldb";
    // private final String user = "psqladminun@mybortolodbprova";
    // private final String password = "H@Sh1CoR3!";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() throws ClassNotFoundException {

        // check that the driver is installed
    		try{
    			   Class.forName("org.postgresql.Driver");
    		} catch (ClassNotFoundException e) {
    			throw new ClassNotFoundException("PostgreSQL JDBC driver NOT detected in library path.", e);
    		}

        // upload DB config
        ManageConfigProperties config = new ManageConfigProperties();
        try{
            this.url =  config.getConfigValues("/Users/andreabortolossi/.my-real-app-war/config.properties","url");
            this.user =  config.getConfigValues("/Users/andreabortolossi/.my-real-app-war/config.properties","user");
            this.password =  config.getConfigValues("/Users/andreabortolossi/.my-real-app-war/config.properties","password");
        } catch (Exception e) {
    			System.out.println("Exception: " + e);
    		}

        // create the connection with the db
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

public void populatedb() throws ClassNotFoundException{

  try (Connection conn = this.connect()) {

            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS inventory;");
            System.out.println("Finished dropping table (if existed).");

            // Create table.
            statement.execute("CREATE TABLE actor (id serial PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50));");
            System.out.println("Created table.");

            // Insert some data into table.
            int nRowsInserted = 0;
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO actor (first_name,last_name) VALUES (?, ?);");
            preparedStatement.setString(1, "Napoleone");
            preparedStatement.setString(2, "Bonaparte");
            nRowsInserted += preparedStatement.executeUpdate();

            System.out.println(String.format("Inserted %d row(s) of data.", nRowsInserted));

          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          }
        }

  public long insertActor(Actor actor) throws ClassNotFoundException {
        String SQL = "INSERT INTO actor(first_name,last_name) "
                + "VALUES(?,?)";

        long id = 0;

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, actor.getFirstName());
            pstmt.setString(2, actor.getLastName());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

  }
