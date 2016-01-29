package main.java.com.jhayes.sql;

import java.sql.*;
import java.util.Properties;

/**
 * Created by hayesj3 on 1/28/2016.
 */
public class DataBase {

	private String dbName = null;
	private boolean isOpen = false;
	private boolean autoCommitEnabled;

	private Connection connection;
	private Statement statement;
	private PreparedStatement psInsert;
	private PreparedStatement psUpdate;
	private ResultSet resultSet;

	public DataBase(String dbName) { this(dbName, "jdbc:derby:");}
	public DataBase(String dbName, String protocol) { this(dbName, protocol, ";create=true"); }
	public DataBase(String dbName, String protocol, String urlAddons) { this(dbName, protocol, urlAddons, true); }
	public DataBase(String dbName, String protocol, String urlAddons, boolean autoCommit) {
		this(dbName, protocol, urlAddons, "user1", "user1", autoCommit);
	}
	public DataBase(String dbName, String protocol, String urlAddons,
	                String user, String password, boolean autoCommit) {
		this.dbName = dbName;
		this.autoCommitEnabled = autoCommit;

		Properties props = new Properties(); // connection properties
		props.put("user", user);
		props.put("password", password);
		try {
			connection = DriverManager.getConnection(protocol + dbName
					+ urlAddons , props);

			statement = connection.createStatement();
		} catch (SQLException e) {
			SQLSystem.printSQLException(e);
		}
	}

	/**
	 * Stores the result of query <code>String query</code> in the
	 * resultSet field, accessible with code simialr to                 <p><p>
	 * <code>
	 *     Database db = new Database("MyDataBase);                     <p>
	 *     db.executeQuery(String sql)                                  <p>
	 *     ResultSet rs = db.getResultSet;                              <p><p>
	 * </code>
	 * @see ResultSet
	 * @param sql the query to execute on the database
	 */
	public void executeQuery(String sql) {
		try {
			setResultSet(getStatement().executeQuery(sql));
		} catch (SQLException sqle) {
			SQLSystem.printSQLException(sqle);
		}
	}


	public boolean isOpen() { return isOpen; }
	public boolean isClosed() { return !isOpen; }

	public String getDbName() { return dbName; }
	public void setDbName(String dbName) { if(!isOpen) { this.dbName = dbName; } }

	public Connection getConnection() { return connection; }
	public void setConnection(Connection connection) { this.connection = connection; }

	public Statement getStatement() { return statement; }
	public void setStatement(Statement statement) { this.statement = statement; }

	public PreparedStatement getPsInsert() { return psInsert; }
	public void setPsInsert(PreparedStatement psInsert) { this.psInsert = psInsert; }

	public PreparedStatement getPsUpdate() { return psUpdate; }
	public void setPsUpdate(PreparedStatement psUpdate) { this.psUpdate = psUpdate; }

	public ResultSet getResultSet() { return resultSet; }
	public void setResultSet(ResultSet resultSet) { this.resultSet = resultSet; }

	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
		}
	}

	@Override
	public String toString() {
		return ("Name: " + this.getDbName() + " Open: "+ this.isOpen);
	}
}
