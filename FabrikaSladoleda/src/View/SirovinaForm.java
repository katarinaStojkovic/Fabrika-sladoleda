package View;

import javax.swing.JOptionPane;

import Dao.SirovinaDao;
import Entities.Sirovina;
import application.ProjectSingleton;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SirovinaForm extends Stage {

	private TextField tfNaziv;
	private TextField tfKolicina;
	private TextField tfCena;
	private Button btnUnos, btnPregled;
	private Sirovina sirovina;
	private SirovinaDao sirovinaDao;

	
	public SirovinaForm() {
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	

	public SirovinaForm(Sirovina s) {
		this.sirovina = s;
		setupGUI();
		addUpdateListener();
		btnUnos.setText("Azuriraj");
		if(btnUnos.getText().equals("Azuriraj")){
			tfNaziv.setText(sirovina.getNazivSirovine());
			tfKolicina.setText(sirovina.getKolicina()+"");
			tfCena.setText(sirovina.getCenaSirovine()+"");
		}
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}

	public void setupGUI() {
		BorderPane root = new BorderPane();

		GridPane center = new GridPane();
		Text txtNaziv = new Text("Naziv");
		Text txtKolicina = new Text("Količina");
		Text txtCena = new Text("Cena");
		tfNaziv = new TextField();
		tfKolicina = new TextField();
		tfCena = new TextField();
		center.add(txtNaziv, 0, 1);
		center.add(tfNaziv, 1, 1);
		center.add(txtKolicina, 0, 2);
		center.add(tfKolicina, 1, 2);
		center.add(txtCena, 0, 3);
		center.add(tfCena, 1, 3);
		center.setAlignment(Pos.CENTER);
		center.setHgap(10);
		center.setVgap(10);

		root.setCenter(center);

		HBox bottom = new HBox();
		btnUnos = new Button("Unos");
		btnPregled = new Button("Pregled");
		bottom.getChildren().addAll(btnUnos,btnPregled);
		bottom.setAlignment(Pos.CENTER);
		bottom.setSpacing(5);
		root.setBottom(bottom);

		Scene scene = new Scene(root, 300, 300);
		setTitle("Kreirajte Sirovinu");
		setResizable(false);
		setScene(scene);
		show();
	}

	
	public void prepareBindingForUnos() {
		BooleanBinding binding = new BooleanBinding() {
			{
				super.bind(tfNaziv.textProperty(), tfKolicina.textProperty(), tfCena.textProperty());
			}

			@Override
			protected boolean computeValue() {
				return tfNaziv.getText().isEmpty() || tfKolicina.getText().isEmpty() || tfCena.getText().isEmpty();
			}
		};
		btnUnos.disableProperty().bind(binding);
	}


	private void addInsertListener() {
		btnUnos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					Double kolicina = Double.parseDouble(tfKolicina.getText());
					Double cena = Double.parseDouble(tfCena.getText());
					sirovina = new Sirovina(tfNaziv.getText(), kolicina, cena);
					sirovinaDao = new SirovinaDao();
					sirovinaDao.insertSirovina(sirovina);
					tfNaziv.clear();
					tfKolicina.clear();
					tfCena.clear();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
				}
			}
		});
		btnPregled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new SirovinaList();

			}
		});
	}


	
	private void addUpdateListener() {
		btnUnos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Double kolicina = Double.parseDouble(tfKolicina.getText());
					Double cena = Double.parseDouble(tfCena.getText());
					sirovina.setNazivSirovine(tfNaziv.getText());
					sirovina.setKolicina(kolicina);
					sirovina.setCenaSirovine(cena);
					sirovinaDao = new SirovinaDao();
					sirovinaDao.update(sirovina);
					tfNaziv.clear();
					tfKolicina.clear();
					tfCena.clear();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
				}
			}
		});
		btnPregled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new SirovinaList();

			}
		});
	}

}
