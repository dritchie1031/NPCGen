package gui;

import character.NPC;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Runner extends Application {
	@Override
	public void start (Stage stage) throws Exception{
		//Creating check boxes for the two current options
		CheckBox classCheck = new CheckBox("Classed");
		CheckBox combatCheck = new CheckBox("Combatant");
		
		//Creating the button to initiate NPC generation
		Button generateButton = new Button("Generate NPC");
		
		//Creating the button event handler and adding it to the button
		EventHandler<MouseEvent> nextScene  = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				NPC character = new NPC(combatCheck.isSelected(),classCheck.isSelected());
				Text characterText = new Text(character.toString());
				System.out.println(character);
				characterText.setX(50);
				characterText.setY(50);
				Group root = new Group(characterText);
				Scene scene = new Scene(root, 1200, 600);
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			}
		};
		generateButton.addEventFilter(MouseEvent.MOUSE_CLICKED, nextScene);
		
		//Creating the gridpane, setting the size, padding, and gap between elements
		GridPane gridpane = new GridPane();
		gridpane.setMinSize(500, 500);
		gridpane.setPadding(new Insets(10,10,10,10));
		gridpane.setVgap(20);
		gridpane.setHgap(20);
		gridpane.setAlignment(Pos.CENTER);
		
		//Adding the checkbox nodes to the gridpane
		gridpane.add(classCheck, 0, 0);
		gridpane.add(combatCheck, 0, 1);
		gridpane.add(generateButton, 0, 2);
		
		//Creating a scene object
		Scene scene = new Scene(gridpane);
		
		//Setting title to the stage
		stage.setTitle("NPC Generator");
		//Adding scene to the stage
		stage.setScene(scene);
		
		//Displaying the contents of the stage
		stage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
