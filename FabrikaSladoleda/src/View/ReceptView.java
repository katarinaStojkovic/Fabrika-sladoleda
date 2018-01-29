package View;


import Dao.ReceptDao;
import Entities.Recept;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ReceptView extends HBox{
	private Recept recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private ReceptDao receptDao;



	public ReceptView(Recept r) {
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
				new ReceptForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				receptDao = new ReceptDao();
				receptDao.delete(recordObj);
				ReceptList recView = (ReceptList) ProjectSingleton.getInstance().getOpenedSecondStage();
				recView.createList();
			}
		});
	}
}
