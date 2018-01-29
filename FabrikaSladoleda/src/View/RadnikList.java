package View;

import java.util.ArrayList;

import Dao.RadnikDao;
import Entities.Radnik;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadnikList extends Stage{
	private VBox root;
	private RadnikDao radnikDao;

	
	public RadnikList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Radnik");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}



	public void createList() {
		root.getChildren().clear();
		radnikDao = new RadnikDao();
		ArrayList<Radnik> list = (ArrayList<Radnik>) radnikDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new RadnikView(list.get(i)));
		}
	}
}
