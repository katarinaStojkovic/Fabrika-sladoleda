package View;

import java.util.ArrayList;

import Dao.DobavljacKupacSirovinaDao;
import Entities.DobavljacKupacSirovina;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DobavljacKupacSirovinaList extends Stage{

	private VBox root;
	private DobavljacKupacSirovinaDao dobavljacKupacSirovinaDao;

	
	public DobavljacKupacSirovinaList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Dobavljac/Kupac po sirovini");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}


	public void createList() {
		root.getChildren().clear();
		dobavljacKupacSirovinaDao = new DobavljacKupacSirovinaDao();
		ArrayList<DobavljacKupacSirovina> list = (ArrayList<DobavljacKupacSirovina>) dobavljacKupacSirovinaDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new DobavljacKupacSirovinaView(list.get(i)));
		}
	}
}
