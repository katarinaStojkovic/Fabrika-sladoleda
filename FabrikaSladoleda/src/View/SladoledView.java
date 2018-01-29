package View;


import Dao.SladoledDao;
import Entities.Sladoled;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SladoledView extends HBox{
	private Sladoled recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private SladoledDao sladoledDao;



	public SladoledView(Sladoled sl) {
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
				new SladoledForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sladoledDao = new SladoledDao();
				sladoledDao.delete(recordObj);
				SirovinaList sirView = (SirovinaList) ProjectSingleton.getInstance().getOpenedSecondStage();
				sirView.createList();
			}
		});
	}
}
