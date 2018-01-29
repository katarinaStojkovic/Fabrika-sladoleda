package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entities.Sladoled;
import hibernate.util.HibernateUtil;

public class SladoledDao {


	private static SessionFactory factory = null;
	private static Session session = null;
	 
	private static Transaction tx = null;
	 
	
	public SladoledDao(){
		 factory = HibernateUtil.getSessionFactory();
	 }
	 

	
	 

	public void delete(Sladoled sladoledToDelete) {
			try {
				session = factory.openSession();
				tx = session.beginTransaction();
				Sladoled sladoled = (Sladoled) session.get(Sladoled.class, sladoledToDelete.getSladoledid());
				if (sladoled != null) {
					session.delete(sladoled);
					tx.commit();
					System.out.println("Uspesno brisanje.");
				} else {
					System.out.println("Sladole se ne nalazi u bazi.");
				}
			} catch (HibernateException e) {
				tx.rollback();
				System.out.println("Dogodio se izuzetak...");
				e.printStackTrace();
			} finally {
				session.close();
			}
		}

	public void update(Sladoled s){
		 try{
			 session = factory.openSession();
			 tx = session.beginTransaction();
			 session.update(s);
			 tx.commit();
		 }catch(HibernateException ex){
			 ex.printStackTrace();
			 tx.rollback();
		 }finally {
			session.close();
		}
	 }
	 
	
	 
	
	 

	
	/*public List<Osoba> searchOsobaByImePrezimeAdresa(String ime, String prezime, String adresa){
		 try{
			 session = factory.openSession();
			 tx = session.beginTransaction();
			 List<Osoba> osobe =
					 session.createCriteria(Osoba.class).add(Restrictions.eq("ime", ime)).add(Restrictions.eq("prezime", prezime)).add(Restrictions.eq("adresa", adresa)).list();
			 tx.commit();
			 return osobe;
		 }catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		 return null;
	 }*/
	 
	 /**
	 * Insetovanje osobe preko objekta osoba.
	 *
	 * @param osoba the osoba
	 */
	public void insertSladoled(Sladoled sladoled) {
		 try {
	        	session = factory.openSession();
	            tx = session.beginTransaction();
	            session.save(sladoled);
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
	 
	

	@SuppressWarnings({ "unchecked", "deprecation" })
	public ArrayList<Sladoled> readAll() {
        List<Sladoled> sladoledi = new ArrayList<>();
        try {
        	session = factory.openSession();
            tx = session.beginTransaction();
            System.err.println("ses: "+session);
            System.err.println("tx: "+tx);
            System.err.println(sladoledi);
            sladoledi = session.createCriteria(Sladoled.class).list();
            //System.err.println(sladoledi);
            tx.commit();
            System.out.println("Uspesno ucitavanje.");
            
        } catch (HibernateException e) {
         tx.rollback();
            System.out.println("Hibernate exception happend...");
            e.printStackTrace();
        } finally {
         try{
          session.close();
         }catch(Exception ex){
          System.out.println("greska je: ");
         }
        }
        return (ArrayList<Sladoled>) sladoledi;
    }
	 
	/**
	 * Metoda getlastinput uzima poslednjeg unešebig tako što uzima sve i listi i iz liste èita poslednjeg.
	 *
	 * @return the last input osoba
	 */
	/*public  Osoba getLastInputOsoba(){
		 try {
			session = factory.openSession();
			tx = session.beginTransaction();
		List<Osoba>	osobe = 
		       session.createCriteria(Osoba.class).list();
		tx.commit();    
		return osobe.get(osobe.size()-1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		return null;
		
		 
	 }*/
}
