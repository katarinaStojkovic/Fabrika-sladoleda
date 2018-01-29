package View;


import Dao.TipSladoledaDao;
import Entities.TipSladoleda;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TipSladoledaView extends HBox{
	private TipSladoleda recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private TipSladoledaDao tipSladoledaDao;



	public TipSladoledaView(TipSladoleda t) {
		this.recordObj = t;
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
				new Tip_SladoledaForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tipSladoledaDao = new TipSladoledaDao();
				tipSladoledaDao.delete(recordObj);
				TipSladoledaList tsView = (TipSladoledaList) ProjectSingleton.getInstance().getOpenedSecondStage();
				tsView.createList();
			}
		});
	}
}
