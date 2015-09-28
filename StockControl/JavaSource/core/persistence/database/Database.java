/*
 * Copyright (C) 2013 Judah Holanda Correia Lima <judahholanda7@gmail.com>.
 *
 * All Rights Reserved.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * 
 * Copyright (C) 2013 Judah Holanda Correia Lima <judahholanda7@gmail.com>.
 *
 * Todos os direitos reservados.
 * Esse programa é um software livre: você pode redistribuí-lo e/ou modificá-lo
 * dentro dos termos da Licença Pública Geral GNU como publicada pela 
 * Fundação do Software Livre (FSF), na versão 3 da Licença, ou 
 * (na sua opinião) qualquer versão.
 *
 * Este programa é distribuído na esperança de que possa ser útil, 
 * mas SEM NENHUMA GARANTIA; sem uma garantia implícita de
 * ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a 
 * Veja a Licença Pública Geral GNU para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU 
 * junto com este programa, se não, veja <http://www.gnu.org/licenses/>.
 */
package core.persistence.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import core.Item;
import core.persistence.PersistentDataManagerAdapter;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class Database implements Serializable, PersistentDataManagerAdapter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String url;
	protected String port;
	protected String name;
	protected String user;
	protected String password;
	protected Connection connection;
	protected Statement statement;
	protected ResultSet resultSet;

	public Database(String url, int port, String name, String user, String password) {
		this.url = url;
		this.port = Integer.toString(port);
		this.name = name;
		this.user = user;
		this.password = password;
		this.connection = null;
		this.statement = null;
		this.resultSet = null;
	}

	public String getUrl() {
		return url;
	}

	public String getPort() {
		return port;
	}

	public String getName() {
		return name;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public Connection getConnection() {
		return connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void updateItem(Item item){
		String dbUrl = "jdbc:mysql://" + this.url + ":" + this.port + "/" + this.name + "?user=" + this.user + "&password=" + this.password;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl);
			statement = connection.createStatement();
			String query;
			query = "UPDATE StockControl.TableItem SET varchar50Name='"+item.getName()+"' , varchar50Category='"+item.getCategory()+"' , intQuantity="+item.getQuantity()+" WHERE intIdentifierTableItem=" + item.getIdentifier() + ";";
			System.out.println(query);
			statement.executeUpdate(query);
		} catch (ClassNotFoundException | SQLException exception) {
			System.out.println(exception);
		}
	}

	public ArrayList<Item> getItemList() {
		String dbUrl = "jdbc:mysql://" + this.url + ":" + this.port + "/" + this.name + "?user=" + this.user + "&password=" + this.password;
		ArrayList<Item> item = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl);
			statement = connection.createStatement();
			String query = "select intIdentifierTableItem, varchar50Name, varchar50Category, intQuantity "
					+ "FROM StockControl.TableItem";
			System.out.println(query);
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int identifier = resultSet.getInt("intIdentifierTableItem");
				String name = resultSet.getString("varchar50Name");
				String category = resultSet.getString("varchar50Category");
				int quantity = resultSet.getInt("intQuantity");
				Item itemA= new Item(name, category, quantity);
				itemA.setIdentifier(identifier);
				item.add(itemA);
			}
		} catch (ClassNotFoundException | SQLException exception) {
			System.err.println(exception);
		}
		return item;
	}

	public void insertItem(Item item) {
		String dbUrl = "jdbc:mysql://" + this.url + ":" + this.port + "/" + this.name + "?user=" + this.user + "&password=" + this.password;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl);
			statement = connection.createStatement();
			String query = "INSERT INTO StockControl.TableItem (`varchar50Name`,`varchar50Category`,`intQuantity`) VALUES ('" + item.getName() + "','" + item.getCategory() + "','" + item.getQuantity() + "');";
			System.out.println(query);
			statement.executeUpdate(query, statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();  
			rs.next();  
			item.setIdentifier((int) rs.getLong(1));
		} catch (ClassNotFoundException | SQLException exception) {
			System.out.println(exception);
		}
	}

	public void removeItem(Item item) {
		String dbUrl = "jdbc:mysql://" + this.url + ":" + this.port + "/" + this.name + "?user=" + this.user + "&password=" + this.password;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl);
			statement = connection.createStatement();
			String query = "DELETE FROM StockControl.TableItem WHERE `intIdentifierTableItem`='" + item.getIdentifier() + "';";
			System.out.println(query);
			statement.executeUpdate(query);
		} catch (ClassNotFoundException | SQLException exception) {
			System.out.println(exception);
		}
	}
	
	public void clearItemList() {
		String dbUrl = "jdbc:mysql://" + this.url + ":" + this.port + "/" + this.name + "?user=" + this.user + "&password=" + this.password;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl);
			statement = connection.createStatement();
			String query = "TRUNCATE TABLE StockControl.TableItem;";
			System.out.println(query);
			statement.executeUpdate(query);
		} catch (ClassNotFoundException | SQLException exception) {
			System.out.println(exception);
		}
	}
}
