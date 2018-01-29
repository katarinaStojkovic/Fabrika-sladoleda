package View;


import Dao.SkladisteSladoledaDao;
import Entities.SkladisteSladoleda;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Skladisteiew extends HBox{
	private SkladisteSladoleda recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private SkladisteSladoledaDao skladisteSladoleDao;



	public Skladisteiew(SkladisteSladoleda skl) {
		this.recordObj = skl;
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
				new SkladisteForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				skladisteSladoleDao = new SkladisteSladoledaDao();
				skladisteSladoleDao.delete(recordObj);
				SkladisteList sirView = (SkladisteList) ProjectSingleton.getInstance().getOpenedSecondStage();
				sirView.createList();
			}
		});
	}
}
