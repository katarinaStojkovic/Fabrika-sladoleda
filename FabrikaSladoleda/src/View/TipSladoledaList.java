package View;

import java.util.ArrayList;

import Dao.TipSladoledaDao;
import Entities.TipSladoleda;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TipSladoledaList extends Stage{
	private VBox root;
	private TipSladoledaDao tipSladoledaDao;

	
	public TipSladoledaList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Tip sladoleda");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}



	public void createList() {
		root.getChildren().clear();
		tipSladoledaDao = new TipSladoledaDao();
		ArrayList<TipSladoleda> list = (ArrayList<TipSladoleda>) tipSladoledaDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new TipSladoledaView(list.get(i)));
		}
	}
}
