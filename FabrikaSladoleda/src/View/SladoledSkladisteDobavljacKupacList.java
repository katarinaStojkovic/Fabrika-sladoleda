package View;

import java.util.ArrayList;

import Dao.SladoledSkladisteDobavljacKupacDao;
import Entities.Prodaja;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SladoledSkladisteDobavljacKupacList extends Stage{
	private VBox root;
	private SladoledSkladisteDobavljacKupacDao slskdkDao;

	
	public SladoledSkladisteDobavljacKupacList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Prodaja");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}



	public void createList() {
		root.getChildren().clear();
		slskdkDao = new SladoledSkladisteDobavljacKupacDao();
		ArrayList<Prodaja> list = (ArrayList<Prodaja>) slskdkDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new SladoledSkladisteDobavljacKupacView(list.get(i)));
		}
	}
}
