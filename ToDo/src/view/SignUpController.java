package view;

import java.io.IOException;
import java.sql.SQLException;

import animation.FadeTrans;
import animation.Shaker;
import database.DatabaseHandler;
import generalForControllers.GeneralCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modell.User;

public class SignUpController extends GeneralCode {
	@FXML
	private TextField signUpLocationField;
	@FXML
	private CheckBox signUpMalCbx;
	@FXML
	private CheckBox signUpFemaleCbx;
	@FXML
	private PasswordField signUpPasswordField;
	@FXML
	private TextField signUpFirstnameTextField;
	@FXML
	private TextField signUpLastNameTextField;
	@FXML
	private TextField signUpUsernameTextField;

	// Event Listener on Button.onAction
	@FXML
	public void handleSignupSignupBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

		if (!signUpLocationField.getText().equals("") && !signUpPasswordField.getText().trim().equals("")
				&& !signUpFirstnameTextField.getText().equals("") && !signUpLastNameTextField.getText().equals("")
				&& !signUpUsernameTextField.getText().equals("")) {

			createUser();
			// change to login scene
			try {

				FXMLLoader loader = getFxmlLoader("Login.fxml");
				Parent root = loader.load();

				// set the Welcoming Message in the login file
				LoginController controller = loader.getController();
				controller.getUserName(signUpFirstnameTextField.getText());

				// hide the sign up button
				controller.hideSignUpButton();
				
				
				// Animation
				FadeTrans rootTrans = new FadeTrans(root, 3000);
				rootTrans.fade();

				changeScene(event, root);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
// user has not entered all data
		else {
			Shaker shakeFirtname = new Shaker(signUpFirstnameTextField);
			Shaker shakeLastname = new Shaker(signUpLastNameTextField);
			Shaker shakeLocation = new Shaker(signUpLocationField);
			Shaker shakeUsername = new Shaker(signUpUsernameTextField);
			Shaker shakerPassword = new Shaker(signUpPasswordField);

			shakeFirtname.shake();
			shakeLastname.shake();
			shakeLocation.shake();
			shakerPassword.shake();
			shakeUsername.shake();
		}

	}

	/***
	 * go back to Login Scene without animation
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void handleLoginButton(ActionEvent event) throws IOException {

		changeScene(event, "Login.fxml");
	}

	/***
	 * Save the user´s data in the database
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void createUser() throws ClassNotFoundException, SQLException {
		DatabaseHandler db = new DatabaseHandler();

		String firstname = signUpFirstnameTextField.getText();
		String lastname = signUpLastNameTextField.getText();
		String username = signUpUsernameTextField.getText();
		String password = signUpPasswordField.getText();
		String location = signUpLocationField.getText();
		String gender = "";

		if (signUpMalCbx.isSelected()) {
			gender = "Male";
		} else {
			gender = "Female";
		}

		User newUser = new User(firstname, lastname, username, password, location, gender);

		db.signUpUser(newUser);

	}

}
