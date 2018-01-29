package View;

import java.util.ArrayList;

import Dao.ReceptDao;
import Entities.Recept;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceptList extends Stage{
	private VBox root;
	private ReceptDao receptDao;

	
	public ReceptList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Recept");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}



	public void createList() {
		root.getChildren().clear();
		receptDao = new ReceptDao();
		ArrayList<Recept> list = (ArrayList<Recept>) receptDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new ReceptView(list.get(i)));
		}
	}
}
