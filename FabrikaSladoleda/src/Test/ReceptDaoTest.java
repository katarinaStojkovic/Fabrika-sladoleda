package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Dao.ReceptDao;
import Entities.Recept;

public class ReceptDaoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDelete() {
		Recept recept = new Recept("Neki", "Neko");
		ReceptDao receptDao = new ReceptDao();
		receptDao.insertRecept(recept);
		receptDao.delete(recept);
		ArrayList<Recept> list = (ArrayList<Recept>) receptDao.readAll();
		assertNotNull(list);
	}

	@Test
	public void testUpdate() {
		Recept recept = new Recept("Neki", "Neko");
		ReceptDao receptDao = new ReceptDao();
		receptDao.update(recept);
        assertEquals(recept.getReceptIme(), "Neki");
        assertEquals(recept.getOpis(), "Neko");
	}

	@Test
	public void testInsertRecept() {
		Recept recept = new Recept("Neki", "Neko");
		ReceptDao receptDao = new ReceptDao();
		receptDao.insertRecept(recept);
        assertEquals(recept.getReceptIme(), "Neki");
        assertEquals(recept.getOpis(), "Neko");
	}

	@Test
	public void testReadAll() {
		ReceptDao receptDao = new ReceptDao();
		ArrayList<Recept> list = (ArrayList<Recept>)receptDao.readAll();
		assertEquals(list.isEmpty(), false);
	}

}
