package br.controledeacesso.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		// new SchemaExport(configuration).create(false, true);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	// public static SessionFactory getSessionFactory() {
	// Configuration configuration = new Configuration();
	// configuration.configure();
	// // new SchemaExport(configuration).create(false, true);
	// serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	// configuration.getProperties()).build();
	// sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	// return sessionFactory;
	// }
}
