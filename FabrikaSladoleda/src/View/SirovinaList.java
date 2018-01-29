package View;

import java.util.ArrayList;

import Dao.SirovinaDao;
import Entities.Sirovina;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SirovinaList extends Stage{
	private VBox root;
	private SirovinaDao sirovinaDao;

	
	public SirovinaList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Sirovine");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}



	public void createList() {
		root.getChildren().clear();
		sirovinaDao = new SirovinaDao();
		ArrayList<Sirovina> list = (ArrayList<Sirovina>) sirovinaDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new SirovinaView(list.get(i)));
		}
	}
}
