package View;


import Dao.DobavljacKupacDao;
import Entities.DobavljacKupac;
import application.ProjectSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DobavljacKupacView extends HBox{

	private DobavljacKupac recordObj;
	private Text txtRecord;
	private Button btnEdit;
	private Button btnDelete;
	private DobavljacKupacDao dobavljacKupacDao;

	/**
	 * Instantiates a new administrator record firma view.
	 *
	 * @param f the f
	 */

	public DobavljacKupacView(DobavljacKupac d) {
		this.recordObj = d;
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

	

	/**
	 * Adds the listeners.
	 */
	public void addListeners() {
		btnEdit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.err.println("R: " +recordObj);
				new DobavljacKupacForm(recordObj);
			}
		});
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dobavljacKupacDao = new DobavljacKupacDao();
				dobavljacKupacDao.delete(recordObj);
				DobavljacKupacList firmView = (DobavljacKupacList) ProjectSingleton.getInstance().getOpenedSecondStage();
				firmView.createList();
			}
		});
	}
}
