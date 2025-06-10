package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
	static DataSource ds;

	public Connection getConnection() throws Exception{
		if(ds==null){
			InitialContext ic=new InitialContext();
			//データベースの都合上変わる可能性あり
			ds=(DataSource)ic.lookup("java:/comp/env/jdbc/JavaSDDB");
		}

		return ds.getConnection();
	}
}
