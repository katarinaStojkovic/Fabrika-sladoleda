package application;


import Entities.Radnik;
import View.DobavljacKupacForm;
import View.DobavljacKupacSirovinaForm;
import View.RadnikForm;
import View.ReceptForm;
import View.SirovinaForm;
import View.SirovineUReceptuForm;
import View.SkladisteForm;
import View.SladoledForm;
import View.SladoledSkladisteDobavljacKupacForm;
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

public class PrikazZaSefa extends Stage{
		
		private Button btnRecept;
		private Button btnSirovina;
		private Button btnSkladisteSladoleda;
		private Button btnDobavljacKupac;
		private Button btnSladoled;
		private Button btnReceptSirovina;
		private Button btnSlSkDk;
		private Button btnTipSladoleda;
		private Button btnDobavljaciSirovina;
		private Button btnRadnik;
		BorderPane root = new BorderPane();
		private Radnik radnik;
		
		
		
		/**
		 * Instantiates a new administrator record osoba.
		 */
		public PrikazZaSefa(Radnik r){
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
			btnSirovina = new Button("Kreiraj sirovinu");
			btnSkladisteSladoleda = new Button("Skladiste sladoleda");
			btnDobavljacKupac = new Button("Kreiraj(DobavljaÄ�a/Kupca)");
			btnSladoled = new Button("KreirajSladoled");
			btnReceptSirovina = new Button("Sirovina u receptu");
			btnSlSkDk = new Button("Dobavljaci i skladista");
			btnTipSladoleda = new Button("Tip sladoleda");
			btnDobavljaciSirovina = new Button("Dobavljaci sirovina");
			btnRadnik = new Button("Radnik");
			center.getChildren().addAll(btnRecept,btnSirovina,btnSkladisteSladoleda,btnDobavljacKupac,btnSladoled,btnReceptSirovina,btnSlSkDk,btnTipSladoleda,btnDobavljaciSirovina,btnRadnik);
			center.setAlignment(Pos.CENTER);
			center.setSpacing(5);
			root.setCenter(center);
			
			
			Scene scene = new Scene(root,600, 600);
			
			btnSirovina.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					new SirovinaForm();
					
				}
			});
			btnDobavljacKupac.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new DobavljacKupacForm();
					
				}
			});
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
			btnReceptSirovina.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new SirovineUReceptuForm();
					
				}
			});
			btnSlSkDk.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new SladoledSkladisteDobavljacKupacForm();
					
				}
			});
			
			btnTipSladoleda.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new Tip_SladoledaForm();
					
				}
				
			});
			btnDobavljaciSirovina.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new DobavljacKupacSirovinaForm();
					
				}
			});
		
			btnRadnik.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					new RadnikForm();
				}
			});
			
			
			
			setTitle("Fabrika Sladoleda");
			setResizable(false);
			setScene(scene);
			show();
		}
			   
}
