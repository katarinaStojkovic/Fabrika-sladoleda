package application;

import Entities.Radnik;
import View.ReceptForm;
import View.SkladisteForm;
import View.SladoledForm;
import View.Tip_SladoledaForm;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrikazZaRadnika extends Stage{
	private Button btnRecept;
	private Button btnSkladisteSladoleda;
	private Button btnSladoled;
	private Button btnTipSladoleda;
	BorderPane root = new BorderPane();
	private Radnik radnik;
	
	
	/**
	 * Instantiates a new administrator record osoba.
	 */
	public PrikazZaRadnika(Radnik r){
		this.radnik = r;
		setupGUI();
		ProjectSingleton.getInstance().closeOpenedSecondStage();
		ProjectSingleton.getInstance().setOpenedSecondStage(this);
	}
	

	
	/**
	 * Setup GUI.
	 */
	public void setupGUI(){
		HBox top = new HBox();
		Text txtPocekat = new Text("Dobrodosli u fabriku sladoleda");
		top.getChildren().add(txtPocekat);
		top.setAlignment(Pos.CENTER);
		root.setTop(top);
		
		VBox center = new VBox();
		btnRecept = new Button("Kreiraj recept");
		btnSkladisteSladoleda = new Button("Skladiste sladoleda");
		btnSladoled = new Button("KreirajSladoled");
		btnTipSladoleda = new Button("Tip sladoleda");
		center.getChildren().addAll(btnRecept,btnSkladisteSladoleda,btnSladoled,btnTipSladoleda);
		center.setAlignment(Pos.CENTER);
		center.setSpacing(5);
		root.setCenter(center);
		
		
		Scene scene = new Scene(root,600, 600);
		
		
		
		btnRecept.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new ReceptForm();
					
			}
		});
		
		btnSkladisteSladoleda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new SkladisteForm(radnik);
				
			}
		});
		btnSladoled.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new SladoledForm();
				
			}
		});
		
		btnTipSladoleda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new Tip_SladoledaForm();
				
			}
			
		});
		
		
		
		
		setTitle("Fabrika Sladoleda");
		setResizable(false);
		setScene(scene);
		show();
	}
	
}
