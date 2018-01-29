package View;

import Dao.DobavljacKupacSirovinaDao;
import Entities.DobavljacKupacSirovina;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DobavljacKupacSirovinaView extends HBox{
	private DobavljacKupacSirovina recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private DobavljacKupacSirovinaDao dbSirovinaDao;



	public DobavljacKupacSirovinaView(DobavljacKupacSirovina dbs) {
		this.recordObj = dbs;
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
				new DobavljacKupacSirovinaForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dbSirovinaDao = new DobavljacKupacSirovinaDao();
				dbSirovinaDao.delete(recordObj);
				DobavljacKupacSirovinaList sirView = (DobavljacKupacSirovinaList) ProjectSingleton.getInstance().getOpenedSecondStage();
				sirView.createList();
			}
		});
	}
}
