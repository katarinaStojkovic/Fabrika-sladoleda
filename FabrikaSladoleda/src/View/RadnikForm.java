package View;


import javax.swing.JOptionPane;

import Dao.RadnikDao;
import Entities.Radnik;
import application.ProjectSingleton;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RadnikForm extends Stage{

	private TextField tfIme, tfPrezime,tfUsename;
	private Button btnUnos, btnPregled;
	private Radnik radnik;
	private CheckBox chkSef;
	private RadnikDao radnikDao;
	

	
	
	
	
	public RadnikForm(){
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	

	public RadnikForm(Radnik ts){
		this.radnik = ts;
		setupGUI();
        addUpdateListener();
        btnUnos.setText("Azuriraj");
        if(btnUnos.getText().equals("Azuriraj")){
        	tfIme.setText(radnik.getIme());
        	tfPrezime.setText(radnik.getPrezime());
        	tfUsename.setText(radnik.getUsername());
        	
        }
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	
	
	public void setupGUI(){
		BorderPane root = new BorderPane();
	
		GridPane center = new GridPane();
		Text txtIme = new Text("Ime radnika");
		Text txtPrezime = new Text("Prezime radnika");
		Text txtUserName = new Text("Username radnika");
		Text txtSef = new Text("Sef");
		tfIme = new TextField();
		tfPrezime = new TextField();
		tfUsename = new TextField();
		chkSef = new CheckBox();
		center.add(txtIme, 0, 1);
		center.add(tfIme, 1,1);
		center.add(txtPrezime, 0, 2);
		center.add(tfPrezime, 1,2);
		center.add(txtUserName, 0, 3);
		center.add(tfUsename, 1,3);
		center.add(txtSef, 0, 4);
		center.add(chkSef, 1,4);
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
		setTitle("Kreiraj tip sladoleda");
		setResizable(false);
		setScene(scene);
		show();
	}
	
	
	
	
	
	public void prepareBindingForUnos() {
        BooleanBinding binding = new BooleanBinding() {
            {
                super.bind(tfIme.textProperty(),tfPrezime.textProperty(),tfUsename.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return tfIme.getText().isEmpty()
                		|| tfPrezime.getText().isEmpty()
                		|| tfUsename.getText().isEmpty();
            }
        };
        btnUnos.disableProperty().bind(binding);
    }
	
	
	
	
 	private void addInsertListener() {
		 btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                    radnik = new Radnik(tfIme.getText(), tfPrezime.getText(), tfUsename.getText(), chkSef.isSelected());
	                	radnikDao = new RadnikDao();
	                	radnikDao.insertRecept(radnik);
	                    tfIme.clear();
	                    tfPrezime.clear();
	                    tfUsename.clear();
	                    chkSef.setAllowIndeterminate(false);
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
			btnPregled.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new RadnikList();

				}
			});
	    }
	   
 
	   
	
	private void addUpdateListener() {
		btnUnos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					radnik.setIme(tfIme.getText());
					radnik.setPrezime(tfPrezime.getText());
					radnik.setUsername(tfUsename.getText());
					radnik.setSef(chkSef.isSelected());
					radnikDao = new RadnikDao();
					radnikDao.update(radnik);
					tfIme.clear();
                    tfPrezime.clear();
                    tfUsename.clear();
                    chkSef.setAllowIndeterminate(false);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
				}
			}
		});
		btnPregled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new RadnikList();

			}
		});
		
		
	
	}
}
