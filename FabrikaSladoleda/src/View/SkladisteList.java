package View;

import java.util.ArrayList;

import Dao.SkladisteSladoledaDao;
import Entities.SkladisteSladoleda;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SkladisteList extends Stage{
	private VBox root;
	private SkladisteSladoledaDao skladisteSladoleDao;

	
	public SkladisteList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Skladiste");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}



	public void createList() {
		root.getChildren().clear();
		skladisteSladoleDao = new SkladisteSladoledaDao();
		ArrayList<SkladisteSladoleda> list = (ArrayList<SkladisteSladoleda>) skladisteSladoleDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new Skladisteiew(list.get(i)));
		}
	}
}
