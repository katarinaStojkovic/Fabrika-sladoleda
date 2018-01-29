package View;

import javax.swing.JOptionPane;


import Dao.DobavljacKupacDao;
import Entities.DobavljacKupac;
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

public class DobavljacKupacForm extends Stage{
	private TextField tfNaziv;
	private TextField tfAdresa;
	private Button btnUnos;
	private Button btnPregled;
	private DobavljacKupac dobavljacKupac;
	private DobavljacKupacDao dobavljacKupacDao;
	

	
	
	
	/**
	 * Instantiates a new administrator record osoba.
	 */
	public DobavljacKupacForm(){
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		addPregledListener();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	/**
	 * Instantiates a new administrator record osoba.
	 *
	 * @param o the o
	 */
	public DobavljacKupacForm(DobavljacKupac d){
		System.err.println("D:" +d);
		this.dobavljacKupac = d;
		System.err.println("dobavlj: "+dobavljacKupac);
		setupGUIForUpdate();
        addUpdateListener();
        btnUnos.setText("Azuriraj");
        addPregledListener();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	public void setupGUIForUpdate(){
		BorderPane root = new BorderPane();
	
		GridPane center = new GridPane();
		Text txtNaziv = new Text("Naziv");
		Text txtAdresa = new Text("Adresa");
		tfNaziv = new TextField();
		tfAdresa = new TextField();
		tfNaziv.setText(dobavljacKupac.getNaziv());
		tfAdresa.setText(dobavljacKupac.getAdresa());
		center.add(txtNaziv, 0, 1);
		center.add(tfNaziv, 1,1);
		center.add(txtAdresa, 0, 2);
		center.add(tfAdresa, 1, 2);
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
		setTitle("Kreiraj Dobavljač/Kupac");
		setResizable(false);
		setScene(scene);
		show();
	}
	
	/**
	 * Setup GUI.
	 */
	public void setupGUI(){
		BorderPane root = new BorderPane();
	
		GridPane center = new GridPane();
		Text txtNaziv = new Text("Naziv");
		Text txtAdresa = new Text("Adresa");
		tfNaziv = new TextField();
		tfAdresa = new TextField();
		center.add(txtNaziv, 0, 1);
		center.add(tfNaziv, 1,1);
		center.add(txtAdresa, 0, 2);
		center.add(tfAdresa, 1, 2);
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
		setTitle("Kreiraj Dobavljač/Kupac");
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
                super.bind(tfNaziv.textProperty(),
                		tfAdresa.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return tfNaziv.getText().isEmpty()
                        || tfAdresa.getText().isEmpty();
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
	                    dobavljacKupac = new DobavljacKupac(tfNaziv.getText(), tfAdresa.getText());
	                    dobavljacKupacDao = new DobavljacKupacDao();
	                    dobavljacKupacDao.insertDobavljacKupac(dobavljacKupac);
	                    tfNaziv.clear();
	                    tfAdresa.clear();
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
		 
			
	    }
	   
   	
	   
	   
	   /**
   	 * Adds the update listener.
   	 */
   	private void addUpdateListener() {
		   btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                		dobavljacKupac.setNaziv(tfNaziv.getText());
	                		dobavljacKupac.setAdresa(tfAdresa.getText());
	                	 	dobavljacKupacDao = new DobavljacKupacDao();
		                    dobavljacKupacDao.update(dobavljacKupac);
		                    System.err.println("!!!1"+dobavljacKupac);
		                    tfNaziv.clear();
		                    tfAdresa.clear();
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });

		  
	    }
   	private void addPregledListener(){
   		btnPregled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new DobavljacKupacList();
				
			}
		});
   	}
	   
}
