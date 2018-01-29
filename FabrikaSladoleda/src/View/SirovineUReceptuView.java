package View;


import Dao.ReceptSirovinaDao;
import Entities.ReceptSirovina;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SirovineUReceptuView extends HBox{
	private ReceptSirovina recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private ReceptSirovinaDao receptSirovinaDao;



	public SirovineUReceptuView(ReceptSirovina rs) {
		this.recordObj = rs;
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
				new SirovineUReceptuForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				receptSirovinaDao = new ReceptSirovinaDao();
				receptSirovinaDao.delete(recordObj);
				SirovinaList sirView = (SirovinaList) ProjectSingleton.getInstance().getOpenedSecondStage();
				sirView.createList();
			}
		});
	}
}
