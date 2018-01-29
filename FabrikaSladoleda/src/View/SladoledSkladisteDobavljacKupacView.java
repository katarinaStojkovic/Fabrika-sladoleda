package View;


import Dao.SladoledSkladisteDobavljacKupacDao;
import Entities.Prodaja;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SladoledSkladisteDobavljacKupacView extends HBox{
	private Prodaja recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private SladoledSkladisteDobavljacKupacDao sladoledSklaDao;



	public SladoledSkladisteDobavljacKupacView(Prodaja sl) {
		this.recordObj = sl;
		setSpacing(10);
		btnEdit = new Button("Edit");
		btnDelete = new Button("Delete");
		txtRecord = new Text(recordObj.toString());
		txtRecord.setWrappingWidth(220);
		btnDelete.setPrefWidth(60);
		btnEdit.setPrefWidth(60);
		addListeners();
		getChildren().addAll(txtRecord, btnEdit, btnDelete);
	}

	

	
	public void addListeners() {
		btnEdit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new SladoledSkladisteDobavljacKupacForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sladoledSklaDao = new SladoledSkladisteDobavljacKupacDao();
				sladoledSklaDao.delete(recordObj);
				SladoledSkladisteDobavljacKupacList sirView = (SladoledSkladisteDobavljacKupacList) ProjectSingleton.getInstance().getOpenedSecondStage();
				sirView.createList();
			}
		});
	}
}
