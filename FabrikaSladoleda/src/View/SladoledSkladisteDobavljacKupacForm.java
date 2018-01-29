package View;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import Dao.DobavljacKupacDao;
import Dao.SkladisteSladoledaDao;
import Dao.SladoledSkladisteDobavljacKupacDao;
import Entities.DobavljacKupac;
import Entities.Prodaja;
import Entities.SkladisteSladoleda;
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

public class SladoledSkladisteDobavljacKupacForm extends Stage{
	private TextField tfProdataKolicina;
	private DatePicker dpDatumIsporuke;
	private ChoiceBox<String> cbSladoledSkladiste;
	private ChoiceBox<String> cbDobavljacKupac;
	private TextField tfStatus;
	private ArrayList<SkladisteSladoleda> skladista;
	private ArrayList<DobavljacKupac> dobavljaci;
	private SkladisteSladoleda skladiste;
	private DobavljacKupac dobavljacKupac;
	private Button btnUnos,btnPregled;
	private SkladisteSladoledaDao skladisteSladoledaDao;
	private DobavljacKupacDao dobavljacKupacDao;
	private Prodaja slskdk;
    private SladoledSkladisteDobavljacKupacDao slskdkDao;
	private SimpleBooleanProperty skladistefSelect;
	private SimpleBooleanProperty dobavljacifSelect;
	
	
	
	
	public SladoledSkladisteDobavljacKupacForm(){
		skladistefSelect = new SimpleBooleanProperty(false);
		dobavljacifSelect = new SimpleBooleanProperty(false);
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		readSladoledSkladiste();
		reaDobavljac();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	public SladoledSkladisteDobavljacKupacForm(Prodaja slskdk){
		this.slskdk = slskdk;
		skladistefSelect = new SimpleBooleanProperty(false);
		dobavljacifSelect = new SimpleBooleanProperty(false);
		setupGUI();
		addUpdateListener(slskdk);
		btnUnos.setText("Azuriraj");
        if(btnUnos.getText().equals("Azuriraj")){
			tfProdataKolicina.setText(slskdk.getProdataKolicina()+"");
			dpDatumIsporuke.setValue(slskdk.getDatumIsporucenja().toLocalDate());
			cbDobavljacKupac.setItems(FXCollections.observableArrayList(slskdk.getDobavljacKupac().toString()));
			cbDobavljacKupac.getSelectionModel().selectFirst();
			cbSladoledSkladiste.setItems(FXCollections.observableArrayList(slskdk.getSkladisteSladoleda().toString()));
			cbSladoledSkladiste.getSelectionModel().selectFirst();
			tfStatus.setText(slskdk.getStatusIsporuke());
		}
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	public void setupGUI(){
		BorderPane root = new BorderPane();
	
		GridPane center = new GridPane();
		Text txtProdataKolicina = new Text("Prodata količina");
		Text txtDatumIsporuke = new Text("Prodata količina");
		Text txtSladoledSkladiste = new Text("Sladoledi u skladistu");
		Text txtDobavljacKupac = new Text("Dobavljač/Kupac");
		Text txtStatus = new Text("Status isporuke");
		tfProdataKolicina = new TextField();
		dpDatumIsporuke = new DatePicker();
		cbSladoledSkladiste = new ChoiceBox<>();
		cbDobavljacKupac = new ChoiceBox<>();
		tfStatus = new TextField();
		center.add(txtProdataKolicina, 0, 1);
		center.add(tfProdataKolicina, 1,1);
		center.add(txtDatumIsporuke, 0, 2);
		center.add(dpDatumIsporuke, 1,2);
		center.add(txtSladoledSkladiste, 0, 3);
		center.add(cbSladoledSkladiste, 1, 3);
		center.add(txtDobavljacKupac, 0, 4);
		center.add(cbDobavljacKupac, 1, 4);
		center.add(txtStatus, 0, 5);
		center.add(tfStatus, 1, 5);
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
		
		
		
		Scene scene = new Scene(root, 600, 600);
		setTitle("Kreiraj skladiste");
		setResizable(false);
		setScene(scene);
		show();
	}
	private void readSladoledSkladiste() {
		skladisteSladoledaDao = new SkladisteSladoledaDao();
		skladista = (ArrayList<SkladisteSladoleda>) skladisteSladoledaDao.readAll();
		List<String> sklString =
		skladista.stream().map(skl -> (skl.getSladoled()+" "+skl.getProizvedenaKolicina()  
		+ " " + skl.getDatumProizvodnje())).collect(Collectors.toList());
		cbSladoledSkladiste.setItems(FXCollections.observableArrayList(sklString));
	}
	
	private void reaDobavljac() {
		dobavljacKupacDao = new DobavljacKupacDao();
		dobavljaci = (ArrayList<DobavljacKupac>) dobavljacKupacDao.readAll();
		List<String> dobavljaciString =
				dobavljaci.stream().map(dob -> (dob.getNaziv() +" " +dob.getAdresa())).collect(Collectors.toList());
		cbDobavljacKupac.setItems(FXCollections.observableArrayList(dobavljaciString));
	}
	
	/**
	 * Prepare binding for unos.
	 */
	public void prepareBindingForUnos() {
        BooleanBinding binding = new BooleanBinding() {
            {
                super.bind(tfProdataKolicina.textProperty(),skladistefSelect,dobavljacifSelect,tfStatus.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return 	tfProdataKolicina.getText().isEmpty() ||
                		!skladistefSelect.get()
                        || !dobavljacifSelect.get()
                        || tfStatus.getText().isEmpty();
            }
        };
        btnUnos.disableProperty().bind(binding);
    }
	
	
	
	
 	private void addInsertListener() {
		 btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                	double prodataKolicina = Double.parseDouble(tfProdataKolicina.getText());
	                                       slskdk = new Prodaja(dobavljacKupac, skladiste, prodataKolicina, Date.valueOf(dpDatumIsporuke.getValue()),tfStatus.getText());
	                	slskdkDao = new SladoledSkladisteDobavljacKupacDao();
	                	slskdkDao.insertSlSkDk(slskdk);
	                    cbSladoledSkladiste.setDisable(true);
	                    cbDobavljacKupac.setDisable(true);
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
		 
		 cbSladoledSkladiste.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					skladiste = new SkladisteSladoleda();
					skladistefSelect.set(true);
					skladiste = skladista.get((int)newValue);
					
				}
			});
		
		 cbDobavljacKupac.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
			 @Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					dobavljacKupac = new DobavljacKupac();
					dobavljacifSelect.set(true);
					dobavljacKupac = dobavljaci.get((int)newValue);
					
				}
		 });
		 btnPregled.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new SladoledSkladisteDobavljacKupacList();

				}
			});
	    }
 	
	private void addUpdateListener(Prodaja sl) {
		 btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                	double prodataKolicina = Double.parseDouble(tfProdataKolicina.getText());
	                                      // slskdk = new SladoledSkladisteDobavljacKupac(dobavljacKupac, skladiste, prodataKolicina, Date.valueOf(dpDatumIsporuke.getValue()),tfStatus.getText());
	                	dobavljacKupac = new DobavljacKupac();
	                	dobavljacKupac.setDobavljacid(sl.getDobavljacKupac().getDobavljacid());
	                	skladiste = new SkladisteSladoleda();
	                	skladiste.setSkladisteSladoledid(sl.getSkladisteSladoleda().getSkladisteSladoledid());
	                	slskdk.setDobavljacKupac(dobavljacKupac);
	                	slskdk.setSkladisteSladoleda(skladiste);
	                	slskdk.setProdataKolicina(prodataKolicina);
	                	slskdk.setDatumIsporucenja(Date.valueOf(dpDatumIsporuke.getValue()));
	                	slskdk.setStatusIsporuke(tfStatus.getText());
	                	slskdkDao = new SladoledSkladisteDobavljacKupacDao();
	                	slskdkDao.update(slskdk);
	                    cbSladoledSkladiste.setDisable(true);
	                    cbDobavljacKupac.setDisable(true);
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
		 
	
		 btnPregled.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new SladoledSkladisteDobavljacKupacList();

				}
			});
	    }
	   
	   
}
