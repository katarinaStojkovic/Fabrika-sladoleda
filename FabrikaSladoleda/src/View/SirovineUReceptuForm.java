package View;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import Dao.ReceptDao;
import Dao.ReceptSirovinaDao;
import Dao.SirovinaDao;
import Entities.Recept;
import Entities.ReceptSirovina;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SirovineUReceptuForm extends Stage{
	private TextField tfKolicina;
	private ChoiceBox<String> cbRecepti;
	private ChoiceBox<String> cbSirovine;
	private ArrayList<Recept> recepti;
	private ArrayList<Sirovina> sirovine;
	private Recept recept;
	private Sirovina sirovina;
	private Button btnUnos,btnPregled;
	private ReceptDao receptiDao;
	private SirovinaDao sirovinaDao;
	private ReceptSirovina receptSirovina;
    private ReceptSirovinaDao receptSirovinaDao;
	private SimpleBooleanProperty recetpfSelect;
	private SimpleBooleanProperty sirovinafSelect;
	
	
	
	
	public SirovineUReceptuForm(){
		recetpfSelect = new SimpleBooleanProperty(false);
		sirovinafSelect = new SimpleBooleanProperty(false);
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		readRecepti();
		readSirovina();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	public SirovineUReceptuForm(ReceptSirovina rs){
		this.receptSirovina = rs;
		recetpfSelect = new SimpleBooleanProperty(false);
		sirovinafSelect = new SimpleBooleanProperty(false);
		setupGUI();
		addUpdateListener(rs);
        btnUnos.setText("Azuriraj");
        if(btnUnos.getText().equals("Azuriraj")){
			cbRecepti.setItems(FXCollections.observableArrayList(receptSirovina.getRecept().toString()));
			cbRecepti.getSelectionModel().selectFirst();
			cbSirovine.setItems(FXCollections.observableArrayList(receptSirovina.getSirovina().toString()));
			cbSirovine.getSelectionModel().selectFirst();
			tfKolicina.setText(receptSirovina.getKolicinaSirovineRecept()+"");
		}
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	public void setupGUI(){
		BorderPane root = new BorderPane();
	
		GridPane center = new GridPane();
		Text txtRecept = new Text("Recept");
		Text txtSirovina = new Text("Sirovina");
		Text txtKolicina = new Text("Količina po receptu");
		cbRecepti = new ChoiceBox<>();
		cbSirovine = new ChoiceBox<>();
		tfKolicina = new TextField();
		center.add(txtRecept, 0, 1);
		center.add(cbRecepti, 1,1);
		center.add(txtSirovina, 0, 2);
		center.add(cbSirovine, 1, 2);
		center.add(txtKolicina, 0, 3);
		center.add(tfKolicina, 1, 3);
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
		setTitle("Kreiraj skladiste");
		setResizable(false);
		setScene(scene);
		show();
	}
	private void readRecepti() {
		receptiDao = new ReceptDao();
		recepti = (ArrayList<Recept>) receptiDao.readAll();
		List<String> receptString =
		recepti.stream().map(rec -> (rec.getReceptid()  + " "+rec.getReceptIme()
		+ " " + rec.getOpis())).collect(Collectors.toList());
		cbRecepti.setItems(FXCollections.observableArrayList(receptString));
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
                super.bind(recetpfSelect,sirovinafSelect,tfKolicina.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return 	!recetpfSelect.get()
                        || !sirovinafSelect.get()
                        || tfKolicina.getText().isEmpty();
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
	                	double kolicina = Double.parseDouble(tfKolicina.getText());
	                   receptSirovina = new ReceptSirovina(recept, sirovina, kolicina);
	                	receptSirovinaDao = new ReceptSirovinaDao();
	                	receptSirovinaDao.insertReceptSirovina(receptSirovina);
	                    tfKolicina.clear();
	                    cbRecepti.setDisable(true);
	                    cbSirovine.setDisable(true);
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
		 
		 cbRecepti.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					recept = new Recept();
					recetpfSelect.set(true);
					recept = recepti.get((int)newValue);
					
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
					new SirovineUReceptuList();

				}
			});
	    }
	   
 	/**
 	 * Adds the insert listener.
 	 */
 	private void addUpdateListener(ReceptSirovina rs) {
		 btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                	double kolicina = Double.parseDouble(tfKolicina.getText());
	                	recept = new Recept();
	                	recept.setReceptid(rs.getRecept().getReceptid());
	                	sirovina = new Sirovina();
	                	sirovina.setSirovinaid(rs.getSirovina().getSirovinaid());
	                   receptSirovina.setRecept(recept);
	                   receptSirovina.setSirovina(sirovina);
	                   receptSirovina.setKolicinaSirovineRecept(kolicina);
	                	receptSirovinaDao = new ReceptSirovinaDao();
	                	receptSirovinaDao.update(receptSirovina);
	                    tfKolicina.clear();
	                    cbRecepti.setDisable(true);
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
