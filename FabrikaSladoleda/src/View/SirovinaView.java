package View;


import Dao.SirovinaDao;
import Entities.Sirovina;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SirovinaView extends HBox{
	private Sirovina recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private SirovinaDao sirovinaDao;



	public SirovinaView(Sirovina s) {
		this.recordObj = s;
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
				new SirovinaForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sirovinaDao = new SirovinaDao();
				sirovinaDao.delete(recordObj);
				SirovinaList sirView = (SirovinaList) ProjectSingleton.getInstance().getOpenedSecondStage();
				sirView.createList();
			}
		});
	}
}
