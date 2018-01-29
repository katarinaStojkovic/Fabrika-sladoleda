package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Entities.Prodaja;
import hibernate.util.HibernateUtil;

public class SladoledSkladisteDobavljacKupacDao {

	private static SessionFactory factory = null;
	private static Session session = null;
	 
	private static Transaction tx = null;
	 
	
	public SladoledSkladisteDobavljacKupacDao(){
		 factory = HibernateUtil.getSessionFactory();
	 }
	 

	
	 

	public void delete(Prodaja slskdkToDelete) {
			try {
				session = factory.openSession();
				tx = session.beginTransaction();
				Prodaja slskdk = (Prodaja) session.get(Prodaja.class, slskdkToDelete.getSladoledSkladisteDobavljacKupacid());
				if (slskdk != null) {
					session.delete(slskdk);
					tx.commit();
					System.out.println("Uspesno brisanje.");
				} else {
					System.out.println("Recept se ne nalazi u bazi.");
				}
			} catch (HibernateException e) {
				tx.rollback();
				System.out.println("Dogodio se izuzetak...");
				e.printStackTrace();
			} finally {
				session.close();
			}
		}

	public void update(Prodaja slskdk){
		 try{
			 session = factory.openSession();
			 tx = session.beginTransaction();
			 session.update(slskdk);
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
	public void insertSlSkDk(Prodaja slskdk) {
		 try {
	        	session = factory.openSession();
	            tx = session.beginTransaction();
	            session.save(slskdk);
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
	 
	
	/**
	 * Metoda ReadALL preko createCriteria uzima sve podatke iz klase osobe i ubacuje ih u arraylist 
	 * kako bismo tu listu koju metoda vraæa kasnije koristili za ispis u labelama .
	 *
	 * @return the array list
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ArrayList<Prodaja> readAll() {
        List<Prodaja> slskdk = new ArrayList<>();
        try {
         session = factory.openSession();
            tx = session.beginTransaction();
            slskdk = (ArrayList<Prodaja>) session.createCriteria(Prodaja.class).list();
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
        return (ArrayList<Prodaja>) slskdk;
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
