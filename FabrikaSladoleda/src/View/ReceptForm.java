package View;

import javax.swing.JOptionPane;

import Dao.ReceptDao;
import Entities.Recept;
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

public class ReceptForm extends Stage{
	private TextField tfReceptIme;
	private TextField tfOpis;
	private Button btnUnos, btnPregled;
	private Recept recept;
	private ReceptDao receptDao;
	

	
	
	
	
	public ReceptForm(){
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	

	public ReceptForm(Recept r){
		this.recept = r;
		setupGUI();
        addUpdateListener();
        btnUnos.setText("Azuriraj");
        if(btnUnos.getText().equals("Azuriraj")){
        	tfReceptIme.setText(recept.getReceptIme());
        	tfOpis.setText(recept.getOpis());
        }
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	
	
	public void setupGUI(){
		BorderPane root = new BorderPane();
	
		GridPane center = new GridPane();
		Text txtReceptIme = new Text("Ime Recepta");
		Text txtOpis = new Text("Opis");
		tfReceptIme = new TextField();
		tfOpis = new TextField();
		center.add(txtReceptIme, 0, 1);
		center.add(tfReceptIme, 1,1);
		center.add(txtOpis, 0, 2);
		center.add(tfOpis, 1, 2);
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
		setTitle("Kreiraj Recept");
		setResizable(false);
		setScene(scene);
		show();
	}
	
	
	
	
	
	public void prepareBindingForUnos() {
        BooleanBinding binding = new BooleanBinding() {
            {
                super.bind(tfReceptIme.textProperty(),
                		tfOpis.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return tfReceptIme.getText().isEmpty()
                        || tfOpis.getText().isEmpty();
            }
        };
        btnUnos.disableProperty().bind(binding);
    }
	
	
	
	
 	private void addInsertListener() {
		 btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                    recept = new Recept(tfReceptIme.getText(), tfOpis.getText());
	                	receptDao = new ReceptDao();
	                	receptDao.insertRecept(recept);
	                    tfReceptIme.clear();
	                    tfOpis.clear();
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
			btnPregled.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new ReceptList();

				}
			});
	    }
	   
 
	   
	
	private void addUpdateListener() {
		btnUnos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					recept.setReceptIme(tfReceptIme.getText());
					recept.setOpis(tfOpis.getText());
					receptDao = new ReceptDao();
					receptDao.update(recept);
					tfReceptIme.clear();
					tfOpis.clear();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
				}
			}
		});
		btnPregled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new ReceptList();

			}
		});
	
	}
	    
}
