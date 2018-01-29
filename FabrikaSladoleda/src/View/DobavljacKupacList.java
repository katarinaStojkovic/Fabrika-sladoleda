package View;

import java.util.ArrayList;

import Dao.DobavljacKupacDao;
import Entities.DobavljacKupac;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DobavljacKupacList extends Stage{
	
	private VBox root;
	private DobavljacKupacDao dobavljacKupacDao;

	
	public DobavljacKupacList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Dobavljac/Kupac");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}


	public void createList() {
		root.getChildren().clear();
		dobavljacKupacDao = new DobavljacKupacDao();
		ArrayList<DobavljacKupac> list = (ArrayList<DobavljacKupac>) dobavljacKupacDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new DobavljacKupacView(list.get(i)));
		}
	}
}
