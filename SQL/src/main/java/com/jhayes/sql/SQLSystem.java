package main.java.com.jhayes.sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by hayesj3 on 1/27/2016.
 */
public class SQLSystem {
	private static SQLSystem instance = new SQLSystem();

	private String framework;
	private String protocol;
	private String urlAddon = ";create=true";
	private FrameworkStates state;

	public SQLSystem() {}

	/**
	 * Returns a new database. If a db with the name <code>dbname</code> exists
	 * it is returned, otherwise a new db is created
	 * @param dbname the name of the database
	 * @return a <code>Database</code> object used for interfacing with the actual database
	 */
	public static DataBase getDataBase(String dbname) {
		return new DataBase(dbname);
	}
	/**
	 * Returns a new database. If a db with the name <code>dbname</code> exists
	 * it is returned, otherwise a new db is created
	 * @param dbname the name of the database
	 * @param protocol <code>jdbc:derby:</code> for an embedded or
	 *                    <code>jdbc:derby:<bold>//localhost:1527/</bold></code> for a client;
	 *                    bold text can be replaced by a remote url is needed(NYI)
	 * @return a <code>Database</code> object used for interfacing with the actual database
	 */
	public static DataBase getDataBase(String dbname, String protocol) {
		return new DataBase(dbname, protocol);
	}
	/**
	 * Returns a new database. If a db with the name <code>dbname</code> exists
	 * it is returned, otherwise a new db is created
	 * @param dbname the name of the database
	 * @param protocol <code>jdbc:derby:</code> for an embedded or
	 *                    <code>jdbc:derby:<bold>//localhost:1527/</bold></code> for a client;
	 *                    bold text can be replaced by a remote url is needed(NYI)
	 * @param urlAddons Strings like <code>;create=true</code> see Derby documentation for others
	 * @return a <code>Database</code> object used for interfacing with the actual database
	 */
	public static DataBase getDataBase(String dbname, String protocol, String urlAddons) {
		return new DataBase(dbname, protocol, urlAddons);
	}
	/**
	 * Returns a new database. If a db with the name <code>dbname</code> exists
	 * it is returned, otherwise a new db is created
	 * @param dbname the name of the database
	 * @param protocol <code>jdbc:derby:</code> for an embedded or
	 *                    <code>jdbc:derby:<bold>//localhost:1527/</bold></code> for a client;
	 *                    bold text can be replaced by a remote url is needed(NYI)
	 * @param urlAddons Strings like <code>;create=true</code> see Derby documentation for others
	 * @param autoCommit true for autocommit feature, false otherwise. see dery documentation for more details
	 * @return a <code>Database</code> object used for interfacing with the actual database
	 */
	public static DataBase getDataBase(String dbname, String protocol, String urlAddons, boolean autoCommit) {
		return new DataBase(dbname, protocol, urlAddons, autoCommit);
	}

	/**
	 * Returns a new database. If a db with the name <code>dbname</code> exists
	 * it is returned, otherwise a new db is created
	 * @param dbname the name of the database
	 * @param protocol <code>jdbc:derby:</code> for an embedded or
	 *                    <code>jdbc:derby:<bold>//localhost:1527/</bold></code> for a client;
	 *                    bold text can be replaced by a remote url is needed(NYI)
	 * @param urlAddons Strings like <code>;create=true</code> see Derby documentation for others
	 * @param user the username for the db
	 * @param password the password for the db(optional on embedded db)
	 * @param autoCommit true for autocommit feature, false otherwise. see dery documentation for more details
	 * @return a <code>Database</code> object used for interfacing with the actual database
	 */
	public static DataBase getDataBase(String dbname, String protocol, String urlAddons,
	                                   String user, String password, boolean autoCommit) {
		return new DataBase(dbname, protocol, urlAddons, user, password, autoCommit);
	}

	/**
	 * Executes a the lines of a SQL File given by the <code>String path</code>
	 * parameter. Will skip both line and block commented lines
	 * NOTE: Skips lines of SQL code containing <code>SELECT</code> clauses.
	 * @param path The path to the SQL file containing the sql to execute
	 * @throws SQLException
	 */
	public void executeSQLFile(String path, DataBase db) throws SQLException {
		Scanner input = null;
		try {
			input = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			System.err.println("SQL file not found!");
		}
		input.useDelimiter(";");
		boolean commented = false;
		while(input.hasNext()) {
			String str = input.next();
			str = str.trim();
			if(str.contains("*/")) { commented = false; continue; }
			else if(commented) { continue; }
			else if(str.contains("/*") && !str.contains("*/")) { commented = true; continue; }
			else if(str.contains("/*") || str.contains("--") || str.contains("SELECT")) { continue; }
			else { db.getStatement().execute(str); System.out.println("Executed: " + str); }
		}
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println();
		db.commit();
	}

	/**
	 * Shuts down the given database
	 * @param db the database who'se connection should be closed
	 * @return true if the database was shutodwn, false other wise
	 */
	public boolean shutDownDB(DataBase db) {
		//if SQLSystem is in the shutdownDB state, and the protocol is embedded
		if (state.equals(FrameworkStates.SHUTDOWNDB) && protocol.equals(FrameworkStates.EMBEDDED.getProtocol()))
		{
			try
			{
				db.commit();

				// the shutdown=true attribute shuts down Derby
				DriverManager.getConnection(protocol + db.getDbName() + urlAddon);

				// To shut down a specific database only, but keep the
				// engine running (for example for connecting to other
				// databases), specify a database in the connection URL:
				//DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
			}
			catch (SQLException se)
			{
				if ((se.getErrorCode() == 45000)
						&& ("08006".equals(se.getSQLState()))) {
					// we got the expected exception
					System.out.println("Database " + db.getDbName() + " shut down normally");
					return true;

				} else if (( (se.getErrorCode() == 50000)
						&& ("XJ015".equals(se.getSQLState()) ))) {
					// SQL state is "08006", and the error code is 45000
					// this means derby, and all active databases, were shutdown
					System.err.println("Derby shutdown; this wasn't supposed to happen!");
					return true;
				} else {
					// if the error code or SQLState is different, we have
					// an unexpected exception (shutdown failed)
					System.err.println("Derby did not shut down normally");
					printSQLException(se);
				}
			}
		}
		return false;
	}
	/**
	 * Prints an SQL exception in an easy to read manner to System.err
	 * @param e the <code>SQLException</code> to print
	 */
	static void printSQLException(SQLException e) {
		// Unwraps the entire exception chain to unveil the real cause of the
		// Exception.
		while (e != null) {
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State:  " + e.getSQLState());
			System.err.println("  Error Code: " + e.getErrorCode());
			System.err.println("  Message:    " + e.getMessage());
			// for stack traces, Inter to derby.log or uncomment this:
			//e.printStackTrace(System.err);
			e = e.getNextException();
		}
	}

	/**
	 * Sets the current framework for new databases
	 * @param state framework state to use
	 */
	public void setFramework(FrameworkStates state) {
		this.state = state;
		switch (state) {
			case CLIENT:
				framework = state.getValue();
				protocol = state.getProtocol();
				break;
			case EMBEDDED:
				framework = state.getValue();
				protocol = state.getProtocol();
				break;
			case SHUTDOWNDB:
				framework = state.getValue();
				urlAddon = state.getUrlAddons();
				break;
			case SHUTDOWNDERBY:
				framework = state.getValue();
				urlAddon = state.getUrlAddons();
				break;
			default:
				this.state = null;
				break;
		}
	}

	public static SQLSystem getInstance() { return instance; }
}
