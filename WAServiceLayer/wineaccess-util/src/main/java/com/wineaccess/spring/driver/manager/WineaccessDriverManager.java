package com.wineaccess.spring.driver.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.wineaccess.crypto.util.CryptoUtil;

/**
 * Overriding the default {@code DriverManagerDataSource} to introduce the encrypted password of database in the file.
 */
public class WineaccessDriverManager extends DriverManagerDataSource {

	public WineaccessDriverManager() {
	}

	public WineaccessDriverManager(String url) {
		setUrl(url);
	}

	public WineaccessDriverManager(String url, String username, String password) {
		setUrl(url);
		setUsername(username);
		try {
			setPassword(CryptoUtil.decrypt(password));
		} catch(Exception ex) {
			throw new RuntimeException("Error in setting database password");
		}
	}

	public WineaccessDriverManager(String url, Properties conProps) {
		setUrl(url);
		setConnectionProperties(conProps);
	}

	/**
	 * @deprecated Method DriverManagerDataSource is deprecated
	 */

	public WineaccessDriverManager(String driverClassName, String url, String username, String password) {
		setDriverClassName(driverClassName);
		setUrl(url);
		setUsername(username);
		try {
			setPassword(CryptoUtil.decrypt(password));
		} catch(Exception ex) {
			throw new RuntimeException("Error in setting database password");
		}
	}
	
	@Override
	protected Connection getConnectionFromDriverManager(String url, Properties props) throws SQLException {
		try {
			props.put("password", CryptoUtil.decrypt(props.getProperty("password")));
		} catch(Exception ex) {
			throw new RuntimeException("Error in setting database password");
		}
		return DriverManager.getConnection(url, props);
	}
}
