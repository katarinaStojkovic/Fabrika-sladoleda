package application;
	


import java.util.ArrayList;
import java.util.List;

import Dao.RadnikDao;
import Entities.Radnik;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class Main extends Application {
	
	private TextField tfUserName;
	private Button btnUnos;
	BorderPane root = new BorderPane();
	private RadnikDao radnikDao;
	
	@Override
	public void start(final Stage primaryStage) {
		try {
			
			final HBox top = new HBox();
			final Text txtPocekat = new Text("Dobrodosli u fabriku sladoleda");
			top.getChildren().add(txtPocekat);
			top.setAlignment(Pos.CENTER);
			root.setTop(top);
			
			final GridPane center = new GridPane();
			final Text txtUserName = new Text("User name");
			tfUserName = new TextField();
			center.add(txtUserName, 0, 1);
			center.add(tfUserName, 1,1);;
			center.setAlignment(Pos.CENTER);
			center.setHgap(10);
			center.setVgap(10);
			
			root.setCenter(center);

			final Scene scene = new Scene(root,600, 600);
			
			final HBox bottom = new HBox();
			btnUnos = new Button("Unos");
			
			btnUnos.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(final ActionEvent event) {
					radnikDao = new RadnikDao();
					List<Radnik> lista = new ArrayList<>();
					lista = radnikDao.readAll();
					for (final Radnik r : lista) {
						System.err.println("r: " + r.getUsername());
						if (tfUserName.getText().equals(r.getUsername())) {

							System.err.println("r: " + r.getUsername());
							if (r.getSef() == true) {
								new PrikazZaSefa(r);
							} else {
								new PrikazZaRadnika(r);
							}
						}

					}
				}
			});

			bottom.getChildren().addAll(btnUnos);
			bottom.setAlignment(Pos.CENTER);
			bottom.setSpacing(5);
			root.setBottom(bottom);
			
			
			primaryStage.setTitle("Fabrika sladoleda");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(final Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(final String[] args) {
		launch(args);
	}
}
