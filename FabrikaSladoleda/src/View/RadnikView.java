package View;


import Dao.RadnikDao;
import Entities.Radnik;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class RadnikView extends HBox{
	private Radnik recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private RadnikDao radnikDao;



	public RadnikView(Radnik r) {
		this.recordObj = r;
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
				new RadnikForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				radnikDao = new RadnikDao();
				radnikDao.delete(recordObj);
				RadnikList recView = (RadnikList) ProjectSingleton.getInstance().getOpenedSecondStage();
				recView.createList();
			}
		});
	}
}
