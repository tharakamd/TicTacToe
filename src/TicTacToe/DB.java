/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DB {

    private String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");
    private String DB_URL = new String("jdbc:mysql://localhost/tictactoe");
    private Connection conn = null;

    private ResultSet executeStatement(String sql) {
        // creating objects
        Statement statement = null;
        ResultSet resultSet = null;

        // list to store the final result
        ArrayList<String> result = new ArrayList<String>();

        try {
            // creating the statement
            statement = conn.createStatement();

            // extract data from resultSet
            resultSet = statement.executeQuery(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return resultSet;

    }

    private ArrayList<String> exractData(ResultSet resultSet, String column) {

		// list to store the final result
		ArrayList<String> result = new ArrayList<String>();

		try {
			while (resultSet.next()) {

				// adding the data in to the array list
				result.add((String) resultSet.getString(column));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// closing the resultSet
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;

	}

    public String getValue(Recode recode){
		int type = recode.getType();
		switch(type){
		case 0:
			return String.valueOf(recode.getValInt());
		case 1:
			//String.valueOf(recode.getv);break;
		case 2:
			return String.valueOf(recode.getValDouble());
			default:
			return	"'" + String.valueOf(recode.getValString()) + "'";
			
		}
		
	}
    public boolean connectLocal() {
		// variables for user
                
		final String userName = "root";
		final String password = "";
              		// trying to connect to database

		try {
			// register jdbc drive
			Class.forName("com.mysql.jdbc.Driver");
			// connecting to database
			conn = DriverManager.getConnection(DB_URL, userName, password);
               } catch (SQLException e) {
                        // if exception found return false
			return false;
		} catch (ClassNotFoundException e) {
               	// if exception found return false
			return false;
		}

		// if everything went perfect return true
		return true;
	}
  
    public boolean disconnectLocal() {
		try {
			conn.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
    public ArrayList selectLocal(String table, String column, String where) {
		// creating the sql statement
		String sql = "select " + column + " from " + table + " where " + where;

                if(where == null){
                     sql = "select " + column + " from " + table ;

                }
		// creating objects
		ResultSet resultSet = null;

		// list to store the final result
		ArrayList<String> result = new ArrayList<String>();

		// get the resultset
		resultSet = executeStatement(sql);

		// get the data array
		result = exractData(resultSet, column);

		return result;
	}
    public boolean addLocal(String tableName, Table table){
		StringBuilder columns= new StringBuilder();
		StringBuilder value = new StringBuilder();
		ArrayList<Recode> tableTypes = table.getTable();
		Iterator<Recode> it = tableTypes.iterator();
		boolean first = true;
		while(it.hasNext()){
			if(!first)
			{
				columns.append(",");
				value.append(",");
			}else
				first = false;
			Recode recode = it.next();
			columns.append(recode.getColumn());
			value.append(getValue(recode));
			
		}
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(tableName + " (");
		sql.append(columns.toString()+ " ) VALUES ( ");
		sql.append(value.toString() + " )");
                System.out.println(sql.toString());
		return executeLocal(sql.toString());
		
	}
    
    public boolean executeLocal(String sql) {
		// creating objects
		boolean result = false;
		Statement statement = null;
		try {

			statement = conn.createStatement();
			statement.execute(sql);
                        return true;

		} catch (Exception e) {
                        System.out.println("sql statement executing error");
			return false;
		}


	}



}
