package View;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import Dao.ReceptDao;
import Dao.SladoledDao;
import Dao.TipSladoledaDao;
import Entities.Recept;
import Entities.Sladoled;
import Entities.TipSladoleda;
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

public class SladoledForm extends Stage{
	private TextField tfSladoledIme;
	private TextField tfCenaSladoleda;
	private ChoiceBox<String> cbRecepti;
	private ArrayList<Recept> recepti;
	private ChoiceBox<String> cbTipoviSladoleda;
	private ArrayList<TipSladoleda> tipoviSladoleda;
	private Recept recept;
	private Button btnUnos, btnPregled;
	private Sladoled sladoled;
	private SladoledDao sladoledDao;
	private ReceptDao receptiDao;
	private TipSladoleda tipSladoleda;
	private TipSladoledaDao tipSladoledaDao;
	private SimpleBooleanProperty recetpfSelect;
	private SimpleBooleanProperty tipSladoledafSelect;
	

	
	
	

	public SladoledForm(){
		recetpfSelect = new SimpleBooleanProperty(false);
		tipSladoledafSelect = new SimpleBooleanProperty(false);
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		readRecepti();
		readTipSladoleda();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	public SladoledForm(Sladoled sl) {
		this.sladoled = sl;
		recetpfSelect = new SimpleBooleanProperty(false);
		tipSladoledafSelect = new SimpleBooleanProperty(false);
		this.sladoled = sl;
		setupGUI();
		addUpdateListener(sl);
		btnUnos.setText("Azuriraj");
		if(btnUnos.getText().equals("Azuriraj")){
			tfSladoledIme.setText(sladoled.getSladoledIme());
			tfCenaSladoleda.setText(sladoled.getCenaSladoleda()+"");
			cbRecepti.setItems(FXCollections.observableArrayList(sladoled.getRecept().toString()));
			cbRecepti.getSelectionModel().selectFirst();
			cbTipoviSladoleda.setItems(FXCollections.observableArrayList(sladoled.getTipSladoleda().toString()));
			cbTipoviSladoleda.getSelectionModel().selectFirst();
		}
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	
	public void setupGUI(){
		BorderPane root = new BorderPane();
	
		GridPane center = new GridPane();
		Text txtIme = new Text("Ime sladoleda");
		Text txtCena = new Text("Cena");
		Text txtRecept = new Text("Recept");
		Text txtTipoviSladoleda = new Text("Tip sladoleda");
		tfSladoledIme = new TextField();
		tfCenaSladoleda = new TextField();
		cbRecepti = new ChoiceBox<>();
		cbTipoviSladoleda = new ChoiceBox<>();
		center.add(txtIme, 0, 1);
		center.add(tfSladoledIme, 1,1);
		center.add(txtCena, 0, 2);
		center.add(tfCenaSladoleda, 1, 2);
		center.add(txtRecept,0,3);
		center.add(cbRecepti, 1, 3);
		center.add(txtTipoviSladoleda,0,4);
		center.add(cbTipoviSladoleda, 1, 4);
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
		setTitle("Kreiraj sladoled");
		setResizable(false);
		setScene(scene);
		show();
	}
	
	
	
	private void readTipSladoleda() {
		tipSladoledaDao = new TipSladoledaDao();
		tipoviSladoleda = (ArrayList<TipSladoleda>) tipSladoledaDao.readAll();
		List<String> TipSladoledaString =
				tipoviSladoleda.stream().map(ts -> (ts.getImeTipa())).collect(Collectors.toList());
		cbTipoviSladoleda.setItems(FXCollections.observableArrayList(TipSladoledaString));
	}
	
	
	private void readRecepti() {
		receptiDao = new ReceptDao();
		recepti = (ArrayList<Recept>) receptiDao.readAll();
		List<String> receptString =
		recepti.stream().map(rec -> (rec.getReceptid()  + " "+rec.getReceptIme()
		+ " " + rec.getOpis())).collect(Collectors.toList());
		cbRecepti.setItems(FXCollections.observableArrayList(receptString));
	}
	
	
	
	/**
	 * Prepare binding for unos.
	 */
	public void prepareBindingForUnos() {
        BooleanBinding binding = new BooleanBinding() {
            {
                super.bind(tfSladoledIme.textProperty(),
                		tfCenaSladoleda.textProperty(),
                		recetpfSelect);
            }

            @Override
            protected boolean computeValue() {
                return tfSladoledIme.getText().isEmpty()
                        || tfCenaSladoleda.getText().isEmpty()
                        || !recetpfSelect.get();
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
	                	double cenaSladoleda = Double.parseDouble(tfCenaSladoleda.getText());
	                   sladoled = new Sladoled(recept,tipSladoleda ,tfSladoledIme.getText(), cenaSladoleda);
	                	sladoledDao = new SladoledDao();
	                	sladoledDao.insertSladoled(sladoled);
	                    tfSladoledIme.clear();
	                    tfCenaSladoleda.clear();
	                    cbRecepti.setDisable(true);
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
		 
		 cbTipoviSladoleda.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					tipSladoleda = new TipSladoleda();
					tipSladoledafSelect.set(true);
					tipSladoleda = tipoviSladoleda.get((int)newValue);
					
				}
			});
			btnPregled.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new SladoledList();

				}
			});
	
	    }
	   
 	private void addUpdateListener(Sladoled s) {
		 btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                	double cenaSladoleda = Double.parseDouble(tfCenaSladoleda.getText());
	                	recept = new Recept();
	                	recept.setReceptid(s.getRecept().getReceptid());
	                	tipSladoleda = new TipSladoleda();
	                	tipSladoleda.setId(s.getTipSladoleda().getId());
	                	sladoled.setSladoledIme(tfSladoledIme.getText());
	                	sladoled.setCenaSladoleda(cenaSladoleda);
	                	sladoled.setRecept(recept);
	                	sladoled.setTipSladoleda(tipSladoleda);
	                	sladoledDao = new SladoledDao();
	                	sladoledDao.update(sladoled);
	                    tfSladoledIme.clear();
	                    tfCenaSladoleda.clear();
	                    cbRecepti.setDisable(true);
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
		 btnPregled.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new SladoledList();

				}
			});
 	}

}
