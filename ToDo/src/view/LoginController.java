package view;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import animation.FadeTrans;
import animation.Shaker;
import database.DatabaseHandler;
import generalForControllers.GeneralCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController extends GeneralCode {

	// Hold the name of the user for greeting
	private String name;

	
	private String helloname;
	
	// Hold the userId of the current user for showing user´s specific Tasks
	private int userid;
	private int taskid;
	private String[] tasks = new String[5];

	public String getName() {
		return name;
	}
	
	private FXMLLoader loader;
	private FadeTrans rootTransition;

	@FXML
	private AnchorPane rootAnchorePane;

	@FXML
	private Label loginLabel;

	@FXML
	private TextField loginUsernameField;
	@FXML
	private PasswordField loginPasswordField;
	@FXML
	private Button loginLoginBtb;
	@FXML
	private Button loginSignUpBtn;

	// Event Listener on Button[#loginLoginBtb].onAction
	@FXML
	public void handleLoginLoginBtn(ActionEvent event) throws ClassNotFoundException, SQLException {
		DatabaseHandler dbHandler = new DatabaseHandler();

		if (!loginUsernameField.getText().trim().equals("") || !loginPasswordField.getText().trim().equals("")) {

			ResultSet rset = dbHandler.loginUser(loginUsernameField.getText(), loginPasswordField.getText());

			int counter = 0;
			while (rset.next()) {
				counter++;
				name = rset.getString("firstname");
				userid = rset.getInt("userid");
			}

			// greetings to the user
			helloname = "Hello "+ name +"!";
			
			
			// the user entered correct data
			if (counter == 1) {

				try {

					// verify if the user has tasks
					counter = 0;
					rset = dbHandler.getUsersTask(userid);
					while (rset.next()) {
						counter++;
						taskid = rset.getInt("idtask");
					}
					
					if (counter > 0) {

						loader = getFxmlLoader("ListTasks.fxml");
						Parent root = loader.load();
						ListTasksController controller = loader.getController();

						// get the result of the SQL statement
						rset = dbHandler.getUsersTask(userid);
				

						// copy the result in a array
						tasks = copyTasksIntoArray(rset);
						
						// set the text of the labels in the ListTask file
						
						controller.setWelcomeLabel(helloname, Integer.toString(userid),Integer.toString(taskid));
						controller.writeTasks(tasks);

						// This a way to get the taskIds of the current user so that we can delete a task
					rset = dbHandler.getUsersTask(userid);
					controller.copyIdtaskIntoArray(rset);
						
						
						// Animation
						rootTransition = new FadeTrans(root, 2000);
						rootTransition.fade();

						rootAnchorePane.getChildren().setAll(root);
					}

					// the user does not has any task
					else {

						// Get Scene and take the user to another Screen
						loader = getFxmlLoader("AddItem.fxml");
						Parent root = loader.load();
						

						// Animation
						rootTransition = new FadeTrans(root, 2000);
						rootTransition.fade();

						// Set the welcomeLabel in the AddItem scene
						AddItemController controller = loader.getController();
						controller.setWelcomLabel(helloname);

						// get the the userId
						controller.setUserid(Integer.toString(userid));
						
						// change scene
						changeScene(event, root);
					}

				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			// the entered data are not correct
			else {
				Shaker usernameShaker = new Shaker(loginUsernameField);
				Shaker passwordShaker = new Shaker(loginPasswordField);
				usernameShaker.shake();
				passwordShaker.shake();

			}

		}

	}

	@FXML
	private void handleLoginSignupBtn(ActionEvent event) throws IOException {
		// loginSignUpBtn.getScene().getWindow().hide();
		changeScene(event, "SignUp.fxml");

	}


	/***
	 * get the user´s firstname in the SignUp form
	 * 
	 * @param name
	 */
	public void getUserName(String name) {
		loginLabel.setText("Welcome " + name);
	}
	
	
	/***
	 * hide the sign up button after the user has logged in
	 */
	public void hideSignUpButton() {
		loginSignUpBtn.setVisible(false);
	}

	
	
	
	


}
