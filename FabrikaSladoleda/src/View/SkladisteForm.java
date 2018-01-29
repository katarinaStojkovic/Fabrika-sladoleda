package View;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import Dao.SirovinaDao;
import Dao.SkladisteSladoledaDao;
import Dao.SkladistelogDao;
import Dao.SladoledDao;
import Entities.Radnik;
import Entities.Recept;
import Entities.ReceptSirovina;
import Entities.Sirovina;
import Entities.SkladisteSladoleda;
import Entities.Skladistelog;
import Entities.Sladoled;
import application.ProjectSingleton;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SkladisteForm extends Stage {
	private TextField tfProizvedenaKolicina;
	private DatePicker dpDatumProizvodnje;
	private Button btnUnos, btnPregled;
	private SkladisteSladoleda skladiste;
	private SkladisteSladoledaDao skladisteSladoledaDao;
	private Skladistelog skladisteLog;
	private SkladistelogDao skladisteLogDao;
	private SladoledDao sladoledDao;
	private ChoiceBox<String> cbSladoled;
	private Sladoled sladoled;
	private ArrayList<Sladoled> sladoledi;
	private SimpleBooleanProperty sladolesfSelect;
	private Radnik radnik;

	/**
	 * Instantiates a new administrator record osoba.
	 */
	public SkladisteForm() {
		sladolesfSelect = new SimpleBooleanProperty(false);
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		readSladoledi();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	
	
	public SkladisteForm(Radnik r) {
		this.radnik = r;
		sladolesfSelect = new SimpleBooleanProperty(false);
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		readSladoledi();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}

	/**
	 * Instantiates a new administrator record osoba.
	 *
	 * @param o
	 *            the o
	 */
	public SkladisteForm(SkladisteSladoleda s) {
		this.skladiste = s;
		setupGUI();
		addUpdateListener(s);
		btnUnos.setText("Azuriraj");
		if (btnUnos.getText().equals("Azuriraj")) {
			tfProizvedenaKolicina.setText(skladiste.getProizvedenaKolicina() + "");
			dpDatumProizvodnje.setValue(skladiste.getDatumProizvodnje().toLocalDate());
			cbSladoled.setValue(skladiste.getSladoled().toString());
			cbSladoled.setItems(FXCollections.observableArrayList(skladiste.getSladoled().toString()));
			cbSladoled.getSelectionModel().selectFirst();
		}
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}

	/**
	 * Setup GUI.
	 */
	public void setupGUI() {
		BorderPane root = new BorderPane();

		GridPane center = new GridPane();
		Text txtProizvedenaKolicina = new Text("Proizvedena količina");
		Text txtDatumProizvodnje = new Text("Datum proizvodnje");
		Text txtSladoled = new Text("Sladoled");
		tfProizvedenaKolicina = new TextField();
		dpDatumProizvodnje = new DatePicker();
		cbSladoled = new ChoiceBox<>();
		center.add(txtProizvedenaKolicina, 0, 1);
		center.add(tfProizvedenaKolicina, 1, 1);
		center.add(txtDatumProizvodnje, 0, 2);
		center.add(dpDatumProizvodnje, 1, 2);
		center.add(txtSladoled, 0, 3);
		center.add(cbSladoled, 1, 3);
		center.setAlignment(Pos.CENTER);
		center.setHgap(10);
		center.setVgap(10);

		root.setCenter(center);

		HBox bottom = new HBox();
		btnUnos = new Button("Unos");
		btnPregled = new Button("Pregled");
		bottom.getChildren().addAll(btnUnos, btnPregled);
		bottom.setAlignment(Pos.CENTER);
		bottom.setSpacing(5);
		root.setBottom(bottom);

		Scene scene = new Scene(root, 300, 300);
		setTitle("Kreiraj Skladiste");
		setResizable(false);
		setScene(scene);
		show();
	}

	/**
	 * Prepare binding for unos.
	 */
	public void prepareBindingForUnos() {
		BooleanBinding binding = new BooleanBinding() {
			{
				super.bind(tfProizvedenaKolicina.textProperty(), sladolesfSelect);
			}

			@Override
			protected boolean computeValue() {
				return tfProizvedenaKolicina.getText().isEmpty() || !sladolesfSelect.get();
			}
		};
		btnUnos.disableProperty().bind(binding);
	}

	private void readSladoledi() {
		sladoledDao = new SladoledDao();
		sladoledi = (ArrayList<Sladoled>) sladoledDao.readAll();
		List<String> sladoledString = sladoledi.stream().map(sl -> (sl.getSladoledIme() + " " + sl.getCenaSladoleda()))
				.collect(Collectors.toList());
		cbSladoled.setItems(FXCollections.observableArrayList(sladoledString));
	}

	/**
	 * Adds the insert listener.
	 */
	private void addInsertListener() {
		btnUnos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					SirovinaDao sirovinaDao = new SirovinaDao();
					List<ReceptSirovina> listaReceptSirovina = new ArrayList<>();
					List<Sirovina> listaSirovina = new ArrayList<>();
					double proizvedenaKolicina = Double.parseDouble(tfProizvedenaKolicina.getText());
					double novaKolicinaSirovine = 0;
					Recept recept = new Recept();
					recept.setReceptid(sladoled.getRecept().getReceptid());
					recept.setReceptIme(sladoled.getRecept().getReceptIme());
					recept.setOpis(sladoled.getRecept().getOpis());
					skladisteSladoledaDao = new SkladisteSladoledaDao();
					listaReceptSirovina = skladisteSladoledaDao.listaReceptSirovinaPoReceptu(recept);

					for (ReceptSirovina rs : listaReceptSirovina) {
						listaSirovina.add(rs.getSirovina());
					}

					int brojKolicina = 0;
					for (Sirovina sir : listaSirovina) {
						for (ReceptSirovina rsN : listaReceptSirovina) {
							novaKolicinaSirovine = sir.getKolicina()
									- (rsN.getKolicinaSirovineRecept() * proizvedenaKolicina);
							if (novaKolicinaSirovine > 0) {
								sir.setKolicina(novaKolicinaSirovine);
								sirovinaDao.update(sir);
								brojKolicina++;
								break;
							} else {
								break;
							}

						}

					}

				
					if (brojKolicina == listaSirovina.size()) {
						skladiste = new SkladisteSladoleda(radnik,sladoled, proizvedenaKolicina,
								Date.valueOf(dpDatumProizvodnje.getValue()));
						skladisteSladoledaDao = new SkladisteSladoledaDao();
						skladisteSladoledaDao.insertSkladisteSladoleda(skladiste);
						tfProizvedenaKolicina.clear();
						dpDatumProizvodnje.setDisable(true);
						cbSladoled.setDisable(true);
						JOptionPane.showMessageDialog(null, "Uspešno ste uneli sladoled u skladiste");
					} else {
						skladisteLog = new Skladistelog(radnik,sladoled, proizvedenaKolicina,
								Date.valueOf(dpDatumProizvodnje.getValue()));
						skladisteLogDao = new SkladistelogDao();
						skladisteLogDao.insertSkladisteSladoleda(skladisteLog);
						tfProizvedenaKolicina.clear();
						dpDatumProizvodnje.setDisable(true);
						cbSladoled.setDisable(true);
						JOptionPane.showMessageDialog(null,
								"Nemate dovoljno sirovina da unesete dati tip sladoleda u skladiste");
					}

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
				}
			}
		});

		cbSladoled.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				sladoled = new Sladoled();
				sladolesfSelect.set(true);
				sladoled = sladoledi.get((int) newValue);

			}
		});
		btnPregled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new SkladisteList();

			}
		});
	}

	private void addUpdateListener(SkladisteSladoleda s) {
		btnUnos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					double proizvedenaKolicina = Double.parseDouble(tfProizvedenaKolicina.getText());
					// skladiste = new SkladisteSladoleda(proizvedenaKolicina,
					// prodataKolicina, datumProizvodnje,
					// datumIsporuke,sladoled);
					sladoled = new Sladoled();
					skladiste.setProizvedenaKolicina(proizvedenaKolicina);
					skladiste.setDatumProizvodnje(Date.valueOf(dpDatumProizvodnje.getValue()));
					sladoled.setSladoledid(s.getSladoled().getSladoledid());
					sladoled.setSladoledIme(s.getSladoled().getSladoledIme());
					sladoled.setCenaSladoleda(s.getSladoled().getCenaSladoleda());
					sladoled.setTipSladoleda(s.getSladoled().getTipSladoleda());
					skladiste.setSladoled(sladoled);
					skladisteSladoledaDao = new SkladisteSladoledaDao();
					skladisteSladoledaDao.update(skladiste);
					tfProizvedenaKolicina.clear();
					dpDatumProizvodnje.setDisable(true);
					cbSladoled.setDisable(true);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
				}
			}
		});

		
		btnPregled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new SkladisteList();

			}
		});
	}
}
