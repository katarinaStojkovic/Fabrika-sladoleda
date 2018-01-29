package View;

import javax.swing.JOptionPane;

import Dao.TipSladoledaDao;
import Entities.TipSladoleda;
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

public class Tip_SladoledaForm extends Stage{

	private TextField tfTipIme;
	private Button btnUnos, btnPregled;
	private TipSladoleda tipSladoleda;
	private TipSladoledaDao tipSladoledaDao;
	

	
	
	
	
	public Tip_SladoledaForm(){
		setupGUI();
		prepareBindingForUnos();
		addInsertListener();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	

	public Tip_SladoledaForm(TipSladoleda ts){
		this.tipSladoleda = ts;
		setupGUI();
        addUpdateListener();
        btnUnos.setText("Azuriraj");
        if(btnUnos.getText().equals("Azuriraj")){
        	tfTipIme.setText(tipSladoleda.getImeTipa());
        }
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	
	
	
	public void setupGUI(){
		BorderPane root = new BorderPane();
	
		GridPane center = new GridPane();
		Text txtTipIme = new Text("Ime tipa sladoleda");
		tfTipIme = new TextField();
		center.add(txtTipIme, 0, 1);
		center.add(tfTipIme, 1,1);
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
                super.bind(tfTipIme.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return tfTipIme.getText().isEmpty();
            }
        };
        btnUnos.disableProperty().bind(binding);
    }
	
	
	
	
 	private void addInsertListener() {
		 btnUnos.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                try {
	                    tipSladoleda = new TipSladoleda(tfTipIme.getText());
	                	tipSladoledaDao = new TipSladoledaDao();
	                	tipSladoledaDao.insertTipSladoleda(tipSladoleda);
	                    tfTipIme.clear();
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
	                }
	            }
	        });
			btnPregled.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new TipSladoledaList();

				}
			});
	    }
	   
 
	   
	
	private void addUpdateListener() {
		btnUnos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					tipSladoleda.setImeTipa(tfTipIme.getText());
					tipSladoledaDao = new TipSladoledaDao();
					tipSladoledaDao.update(tipSladoleda);
					tfTipIme.clear();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Niste plavilno izvršili unos.");
				}
			}
		});
		btnPregled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new TipSladoledaList();

			}
		});
	
	}
}
