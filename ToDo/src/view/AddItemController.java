package view;

import java.io.IOException;

import animation.Shaker;
import generalForControllers.GeneralCode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AddItemController extends GeneralCode {

	@FXML
	private Label useridLabel;

	@FXML
	private Label welcomeLabel;

	@FXML
	private AnchorPane rootAnchorPane;

	@FXML
	private Label addItemTaskLbl;

	@FXML
	private ImageView addItemAdd;

	// Event Listener on ImageView[#addItemAdd].onMouseClicked
	@FXML
	public void handleAddBtn(MouseEvent event) throws IOException {
		Shaker shakeAddItemAdd = new Shaker(addItemAdd);
		shakeAddItemAdd.shake();

		FXMLLoader loader = getFxmlLoader("AddItemForm.fxml");
		Parent root = loader.load();

		// Set the welcomeLabel in the AddItem scene
		AddItemFormController controller = loader.getController();
		controller.setWelcomLabel(welcomeLabel.getText());

		// get the the userId
		controller.setUserid(useridLabel.getText());
		
		changeScene(event, root);

	}

	// you are repeating yourself
	public void setWelcomLabel(String name) {
		welcomeLabel.setText(name);
	}

	public void setUserid(String id) {
		useridLabel.setText(id);
		useridLabel.setVisible(false);
	}


}
