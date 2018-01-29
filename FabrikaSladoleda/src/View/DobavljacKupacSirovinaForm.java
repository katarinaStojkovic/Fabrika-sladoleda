package View;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import Dao.DobavljacKupacDao;
import Dao.DobavljacKupacSirovinaDao;
import Dao.SirovinaDao;
import Entities.DobavljacKupac;
import Entities.DobavljacKupacSirovina;
import Entities.Sirovina;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DobavljacKupacSirovinaForm extends Stage{
	private ChoiceBox<String> cbDobavljacKupac;
	private ChoiceBox<String> cbSirovine;
	private DatePicker dpDatumpPristeca;
	private ArrayList<DobavljacKupac> dobavljaci;
	private ArrayList<Sirovina> sirovine;
	private DobavljacKupac dobavljacKupac;
	private Sirovina sirovina;
	private Button btnUnos,btnPregled;
	private DobavljacKupacDao dobavljacKupacDao;
	private SirovinaDao sirovinaDao;
	private DobavljacKupacSirovina dbSirovina;
    private DobavljacKupacSirovinaDao dbSirovinaDao;
	private SimpleBooleanProperty dbfSelect;
	private SimpleBooleanProperty sirovinafSelect;
	
	
	public DobavljacKupacSirovinaForm(){
		dbfSelect = new SimpleBooleanProperty(false);
		sirovinafSelect = new SimpleBooleanProperty(false);
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		readDobavljacKupac();
		readSirovina();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	public DobavljacKupacSirovinaForm(DobavljacKupacSirovina dbs){
		this.dbSirovina = dbs;
		dbfSelect = new SimpleBooleanProperty(false);
		sirovinafSelect = new SimpleBooleanProperty(false);
		setupGUI();
		addUpdateListener(dbs);
        btnUnos.setText("Azuriraj");
        if(btnUnos.getText().equals("Azuriraj")){
        	cbDobavljacKupac.setItems(FXCollections.observableArrayList(dbs.getDobavljacKupac().toString()));
        	cbDobavljacKupac.getSelectionModel().selectFirst();
			cbSirovine.setItems(FXCollections.observableArrayList(dbs.getSirovina().toString()));
			cbSirovine.getSelectionModel().selectFirst();
			dpDatumpPristeca.setValue(dbs.getDatumPristeca().toLocalDate());
		}
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	public void setupGUI(){
		BorderPane root = new BorderPane();
	
		GridPane center = new GridPane();
		Text txtDobavljacKupac = new Text("Dobavljač");
		Text txtSirovina = new Text("Sirovina");
		Text txtDatumPristeca = new Text("Datum pristeća");
		cbDobavljacKupac = new ChoiceBox<>();
		cbSirovine = new ChoiceBox<>();
		dpDatumpPristeca = new DatePicker();
		center.add(txtDobavljacKupac, 0, 1);
		center.add(cbDobavljacKupac, 1,1);
		center.add(txtSirovina, 0, 2);
		center.add(cbSirovine, 1, 2);
		center.add(txtDatumPristeca, 0, 3);
		center.add(dpDatumpPristeca, 1, 3);
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
		setTitle("Dobavljači sirovina");
		setResizable(false);
		setScene(scene);
		show();
	}
	private void readDobavljacKupac() {
		dobavljacKupacDao = new DobavljacKupacDao();
		dobavljaci = (ArrayList<DobavljacKupac>) dobavljacKupacDao.readAll();
		List<String> receptString =
				dobavljaci.stream().map(db -> (db.getNaziv()
		+ " " + db.getAdresa())).collect(Collectors.toList());
		cbDobavljacKupac.setItems(FXCollections.observableArrayList(receptString));
	}
	
	private void readSirovina() {
		sirovinaDao = new SirovinaDao();
		sirovine = (ArrayList<Sirovina>) sirovinaDao.readAll();
		List<String> sirovinaString =
				sirovine.stream().map(sir -> (sir.getNazivSirovine() +" " +sir.getKolicina()
		+ " " + sir.getCenaSirovine())).collect(Collectors.toList());
		cbSirovine.setItems(FXCollections.observableArrayList(sirovinaString));
	}
	
	/**
	 * Prepare binding for unos.
	 */
	public void prepareBindingForUnos() {
        BooleanBinding binding = new BooleanBinding() {
            {
                super.bind(dbfSelect,sirovinafSelect);
            }

            @Override
            protected boolean computeValue() {
                return 	!dbfSelect.get()
                        || !sirovinafSelect.get();
            }
        };
        btnUnos.disableProperty().bind(binding);
    }
	
	
	
	 /**
 	 * Adds the insert listener.
 	 */
 	private void addInsertListener() {
		 btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                   dbSirovina = new DobavljacKupacSirovina(dobavljacKupac, sirovina, Date.valueOf(dpDatumpPristeca.getValue()));
	                	dbSirovinaDao = new DobavljacKupacSirovinaDao();
	                	dbSirovinaDao.insertDobavljacKupacSirovina(dbSirovina);
	                    cbDobavljacKupac.setDisable(true);
	                    cbSirovine.setDisable(true);
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
		 
		 cbDobavljacKupac.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					dobavljacKupac = new DobavljacKupac();
					dbfSelect.set(true);
					dobavljacKupac = dobavljaci.get((int)newValue);
					
				}
			});
		 cbSirovine.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			 @Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					sirovina = new Sirovina();
					sirovinafSelect.set(true);
					sirovina = sirovine.get((int)newValue);
					
				}
		 });
		 btnPregled.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new DobavljacKupacSirovinaList();

				}
			});
	    }
	   
 	/**
 	 * Adds the insert listener.
 	 */
 	private void addUpdateListener(DobavljacKupacSirovina db) {
		 btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                	dobavljacKupac = new DobavljacKupac();
	                	dobavljacKupac.setDobavljacid(db.getDobavljacKupac().getDobavljacid());
	                	sirovina = new Sirovina();
	                	sirovina.setSirovinaid(db.getSirovina().getSirovinaid());
	                	dbSirovina.setDobavljacKupac(dobavljacKupac);
	                	dbSirovina.setSirovina(sirovina);
	                	dbSirovina.setDatumPristeca(Date.valueOf(dpDatumpPristeca.getValue()));
	                	dbSirovinaDao = new DobavljacKupacSirovinaDao();
	                	dbSirovinaDao.update(dbSirovina);
	                    cbDobavljacKupac.setDisable(true);
	                    cbSirovine.setDisable(true);
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
		 
		 btnPregled.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new SirovineUReceptuList();

				}
			});
	    }
	   
}
