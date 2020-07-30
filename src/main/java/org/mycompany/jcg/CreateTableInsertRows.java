package org.mycompany.jcg;

import java.sql.*;
import java.util.Properties;

public class CreateTableInsertRows{

//    private final String url = "jdbc:postgresql://mybortolodbprova.postgres.database.azure.com/mypgsqldb";
    private final String url = "jdbc:postgresql://mybortolodbprova.postgres.database.azure.com:5432/mypgsqldb";
    private final String user = "psqladminun@mybortolodbprova";
    private final String password = "H@Sh1CoR3!";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() throws ClassNotFoundException {

        // check that the driver is installed
    		try
    		{
    			Class.forName("org.postgresql.Driver");
    		}
    		catch (ClassNotFoundException e)
    		{
    			throw new ClassNotFoundException("PostgreSQL JDBC driver NOT detected in library path.", e);
    		}

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

  }
  /*
public class CreateTableInsertRows {

	public void populatedb ()	{

		// Initialize connection variables.
		String host = "mybortolodbprova.postgres.database.azure.com";
		String database = "mypgsqldb";
		String user = "psqladminun@mybortolodbprova";
		String password = "H@Sh1CoR3!";

		// check that the driver is installed
		//	Class.forName("org.postgresql.Driver");

		System.out.println("PostgreSQL JDBC driver detected in library path.");

		Connection connection = null;

		// Initialize connection object
			String url = String.format("jdbc:postgresql://%s/%s", host, database);

			// set up the connection properties
			Properties properties = new Properties();
			properties.setProperty("user", user);
			properties.setProperty("password", password);
			properties.setProperty("ssl", "true");

			// get connection
			connection = DriverManager.getConnection(url, properties);

		if (connection != null)
		{
			System.out.println("Successfully created connection to database.");
			// Perform some SQL queries over the connection.
				// Drop previous table of same name if one exists.
				Statement statement = connection.createStatement();
				statement.execute("DROP TABLE IF EXISTS inventory;");
				System.out.println("Finished dropping table (if existed).");

				// Create table.
				statement.execute("CREATE TABLE inventory (id serial PRIMARY KEY, name VARCHAR(50), quantity INTEGER);");
				System.out.println("Created table.");

				// Insert some data into table.
				int nRowsInserted = 0;
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO inventory (name, quantity) VALUES (?, ?);");
				preparedStatement.setString(1, "banana");
				preparedStatement.setInt(2, 150);
				nRowsInserted += preparedStatement.executeUpdate();

				preparedStatement.setString(1, "orange");
				preparedStatement.setInt(2, 154);
				nRowsInserted += preparedStatement.executeUpdate();

				preparedStatement.setString(1, "apple");
				preparedStatement.setInt(2, 100);
				nRowsInserted += preparedStatement.executeUpdate();
				System.out.println(String.format("Inserted %d row(s) of data.", nRowsInserted));

				// NOTE No need to commit all changes to database, as auto-commit is enabled by default.
		}
		else {
			System.out.println("Failed to create connection to database.");
		}
		System.out.println("Execution finished.");
	}
}*/
