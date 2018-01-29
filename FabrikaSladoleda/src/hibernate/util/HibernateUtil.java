package hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 * Klasa HibernateUtil nam služi za povezivanje sa hibernatom i u noj se nalazi
 * SessionFactory za povezivanje sa istim.
 */
public class HibernateUtil {
	
	/** The registry. */
	private static StandardServiceRegistry registry;
	
	/** The session factory. */
	private static SessionFactory sessionFactory;

	  /**
  	 * Get metoda preko koje otvaramo sesiju za konekciju sa hibernatom.
  	 *
  	 * @return the session factory
  	 */
  	public static SessionFactory getSessionFactory() {
	    if (sessionFactory == null) {
	      try {
	        // Create registry
	        registry = new StandardServiceRegistryBuilder()
	            .configure()
	            .build();

	        // Create MetadataSources
	        MetadataSources sources = new MetadataSources(registry);

	        // Create Metadata
	        Metadata metadata = sources.getMetadataBuilder().build();

	        // Create SessionFactory
	        sessionFactory = metadata.getSessionFactoryBuilder().build();

	      } catch (Exception e) {
	        e.printStackTrace();
	        if (registry != null) {
	          StandardServiceRegistryBuilder.destroy(registry);
	        }
	      }
	    }
	    return sessionFactory;
	  }

	 
  	public static void shutdown() {
	    if (registry != null) {
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }
}
