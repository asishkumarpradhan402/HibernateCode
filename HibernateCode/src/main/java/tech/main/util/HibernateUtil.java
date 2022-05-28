package tech.main.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static Session session;
	
	private HibernateUtil() {
		
	}
	
	public static Session getSession(ArrayList<Class<?>> listOfClassName) throws Exception {
		if (session == null) {
			Configuration configuration = new Configuration();
			Properties properties = new Properties();
			
//			Hard-Coded values
//			properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//			properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
//			properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate");
//			properties.put("hibernate.connection.username", "root");
//			properties.put("hibernate.connection.password", "root");
//			properties.put("hibernate.show_sql", "true");
//			properties.put("hibernate.format_sql", "true");
//			properties.put("hibernate.hbm2ddl.auto", "update");
			
			Properties hibernateProperties =  new Properties();
			try {
				InputStream resourceAsStream = HibernateUtil.class.getClassLoader().getResourceAsStream("application.properties");
				hibernateProperties.load(resourceAsStream);
				if (hibernateProperties.isEmpty()) {
					throw new Exception("Data is required in application.properties file for database interaction");
				}
			} catch (Exception e) {
				throw new Exception("Unable to read the properties file");
			}
			properties.put("hibernate.dialect", hibernateProperties.get("hibernate.dialect"));
			properties.put("hibernate.connection.driver_class", hibernateProperties.get("hibernate.connection.driver_class"));
			properties.put("hibernate.connection.url", hibernateProperties.get("hibernate.connection.url"));
			properties.put("hibernate.connection.username", hibernateProperties.get("hibernate.connection.username"));
			properties.put("hibernate.connection.password", hibernateProperties.get("hibernate.connection.password"));
			properties.put("hibernate.show_sql", hibernateProperties.get("hibernate.show_sql"));
			properties.put("hibernate.format_sql", hibernateProperties.get("hibernate.format_sql"));
			properties.put("hibernate.hbm2ddl.auto", hibernateProperties.get("hibernate.hbm2ddl.auto"));
			
			configuration.addProperties(properties);
			StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			for (Class<?> className : listOfClassName) {
	            configuration.addAnnotatedClass(className);
	        }
			SessionFactory sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
			try {
				Session currentSession = sessionFactory.getCurrentSession();
				session = currentSession;
			} catch (Exception e) {
				try {
					session = sessionFactory.openSession();
				} catch (Exception e2) {
					e.printStackTrace();
				}
			}
			return session;
		} else {
			return session;
		}
	}
}
