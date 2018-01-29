package View;

import java.util.ArrayList;

import Dao.SladoledDao;
import Entities.Sladoled;
import application.ProjectSingleton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SladoledList extends Stage{
	private VBox root;
	private SladoledDao sladoleDao;

	
	public SladoledList() {
		root = new VBox(10);
		root.setPadding(new Insets(10));

		createList();

		Scene scene = new Scene(root, 400, 400);

		setTitle("Sladoled");
		setScene(scene);
		show();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}



	public void createList() {
		root.getChildren().clear();
		sladoleDao = new SladoledDao();
		ArrayList<Sladoled> list = (ArrayList<Sladoled>) sladoleDao.readAll();
		for (int i = 0; i < list.size(); i++) {
			root.getChildren().add(new SladoledView(list.get(i)));
		}
	}
}
