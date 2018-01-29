package Dao;



import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entities.Skladistelog;
import hibernate.util.HibernateUtil;

public class SkladistelogDao {


	private static SessionFactory factory = null;
	private static Session session = null;
	 
	private static Transaction tx = null;
	 
	
	public SkladistelogDao(){
		 factory = HibernateUtil.getSessionFactory();
	 }
	 

	 /**
	 * Insetovanje osobe preko objekta osoba.
	 *
	 * @param osoba the osoba
	 */
	public void insertSkladisteSladoleda(Skladistelog skladiste) {
		 try {
	        	session = factory.openSession();
	            tx = session.beginTransaction();
	            session.save(skladiste);
	            tx.commit();
	            JOptionPane.showMessageDialog(null, "Uspesan upis u bazu.");
	        } catch (HibernateException e) {
	        	tx.rollback();
	        	JOptionPane.showMessageDialog(null, "Hibernate exception happend...");
	            e.printStackTrace();
	        } finally {
	            //session.close();
	        }
	}
	 
	
	
}
