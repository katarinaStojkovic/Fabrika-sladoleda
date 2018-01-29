package View;

import java.util.ArrayList;

import Dao.ReceptSirovinaDao;
import Entities.ReceptSirovina;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SirovineUReceptuList extends Stage{
	private VBox root;
	private ReceptSirovinaDao receptSirovinaDao;

	
	public SirovineUReceptuList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Sirovine po receptu");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}



	public void createList() {
		root.getChildren().clear();
		receptSirovinaDao = new ReceptSirovinaDao();
		ArrayList<ReceptSirovina> list = (ArrayList<ReceptSirovina>) receptSirovinaDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new SirovineUReceptuView(list.get(i)));
		}
	}
}
