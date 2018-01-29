package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Entities.Recept;
import Entities.ReceptSirovina;
import Entities.SkladisteSladoleda;
import hibernate.util.HibernateUtil;

public class SkladisteSladoledaDao {


	private static SessionFactory factory = null;
	private static Session session = null;
	 
	private static Transaction tx = null;
	 
	
	public SkladisteSladoledaDao(){
		 factory = HibernateUtil.getSessionFactory();
	 }
	 

	
	 

	public void delete(SkladisteSladoleda skladisteToDelete) {
			try {
				session = factory.openSession();
				tx = session.beginTransaction();
				SkladisteSladoleda skladiste = (SkladisteSladoleda) session.get(SkladisteSladoleda.class, skladisteToDelete.getSkladisteSladoledid());
				if (skladiste != null) {
					session.delete(skladiste);
					tx.commit();
					System.out.println("Uspesno brisanje.");
				} else {
					System.out.println("Skladiste se ne nalazi u bazi.");
				}
			} catch (HibernateException e) {
				tx.rollback();
				System.out.println("Dogodio se izuzetak...");
				e.printStackTrace();
			} finally {
				session.close();
			}
		}

	public void update(SkladisteSladoleda s){
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
	 
	
	 
	
	 

	

	 
	 /**
	 * Insetovanje osobe preko objekta osoba.
	 *
	 * @param osoba the osoba
	 */
	public void insertSkladisteSladoleda(SkladisteSladoleda skladiste) {
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
	 
	
	/**
	 * Metoda ReadALL preko createCriteria uzima sve podatke iz klase osobe i ubacuje ih u arraylist 
	 * kako bismo tu listu koju metoda vraæa kasnije koristili za ispis u labelama .
	 *
	 * @return the array list
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ArrayList<SkladisteSladoleda> readAll() {
        List<SkladisteSladoleda> skladista = new ArrayList<>();
        try {
         session = factory.openSession();
            tx = session.beginTransaction();
            skladista = (ArrayList<SkladisteSladoleda>) session.createCriteria(SkladisteSladoleda.class).list();
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
        return (ArrayList<SkladisteSladoleda>) skladista;
    }
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<ReceptSirovina> listaReceptSirovinaPoReceptu(Recept recept) {
        List<ReceptSirovina> lista = new ArrayList<ReceptSirovina>();   
		try {
        	session = factory.openSession();
            tx = session.beginTransaction();
           	lista = session.createCriteria(ReceptSirovina.class).add(Restrictions.eq("recept", recept)).list();
            tx.commit(); 
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
       return lista;
    }

}
