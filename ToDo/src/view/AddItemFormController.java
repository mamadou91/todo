package view;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import animation.FadeTrans;
import animation.ScaleTrans;
import database.DatabaseHandler;
import generalForControllers.GeneralCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modell.Task;

public class AddItemFormController extends GeneralCode {

	// hold the userId of the current user
	private int userid;

	@FXML
	private Label useridLabel;

	@FXML
	private Label welcomeLabel;

	@FXML
	private TextField taskTextField;
	@FXML
	private TextField descriptionTextField;

	@FXML
	private ImageView backImage;

	@FXML
	void handleBackImageMouseEntered(MouseEvent event) {
		ScaleTrans st = new ScaleTrans(backImage);
		st.scale();
	}

	@FXML
	void handleBackImageMousePressed(MouseEvent event) throws IOException, ClassNotFoundException, SQLException {

		// get connection

		DatabaseHandler dbHandler = new DatabaseHandler();
		userid = Integer.parseInt(useridLabel.getText());
		ResultSet rset = dbHandler.getUsersTask(userid);
		String[] tasks = new String[5];

		int counter = 0;
		while (rset.next()) {
			counter++;
		}

		if (counter > 0) {
			FXMLLoader loader = getFxmlLoader("ListTasks.fxml");
			Parent root = loader.load();
			ListTasksController controller = loader.getController();

			// get the result of the SQL statement
			rset = dbHandler.getUsersTask(userid);

			// copy the result in a array
			tasks = copyTasksIntoArray(rset);

			// set the text of the labels in the ListTask file
			controller.setWelcomeLabel(welcomeLabel.getText(), Integer.toString(userid));
			controller.writeTasks(tasks);

			// Animation
			FadeTrans rootTransition = new FadeTrans(root, 2000);
			rootTransition.fade();

			changeScene(event, root);
		} else {
			// Get Scene and take the user to another Screen
			FXMLLoader loader = getFxmlLoader("AddItem.fxml");
			Parent root = loader.load();

			// Animation
			FadeTrans rootTransition = new FadeTrans(root, 2000);
			rootTransition.fade();

			// Set the welcomeLabel in the AddItem scene
			AddItemController controller = loader.getController();
			controller.setWelcomLabel(welcomeLabel.getText() );

			// get the the userId
			controller.setUserid(Integer.toString(userid));

			// change scene
			changeScene(event, root);
		}
	}

	// Event Listener on Button.onAction
	@FXML
	public void handleSaveBtn(ActionEvent event) throws IOException {

		if (!taskTextField.getText().trim().equals("") && !descriptionTextField.getText().trim().equals("")) {

			// Get the current date
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);

			// Get the userId
			userid = Integer.parseInt(useridLabel.getText());

			DatabaseHandler dbHandler = new DatabaseHandler();
			Task task = new Task(userid, date, descriptionTextField.getText(), taskTextField.getText());
			
			try {
				dbHandler.saveTask(task);
	
		
			backToTaskList(event,  dbHandler);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/***
	 * Set the text of the WelcomeLabel
	 * 
	 * @param name
	 */
	public void setWelcomLabel(String name) {
		welcomeLabel.setText(name);
	}

	/***
	 * Set the text of the useridLabel
	 * 
	 * @param id
	 */
	public void setUserid(String id) {
		useridLabel.setText(id);
	}
	
	private void backToTaskList(ActionEvent event ,DatabaseHandler dbHandler) throws IOException, ClassNotFoundException, SQLException {
	
		String[]tasks = new String[5];
		FXMLLoader loader = getFxmlLoader("ListTasks.fxml");
		Parent root = loader.load();
		ListTasksController controller = loader.getController();

		// get the result of the SQL statement
		userid = Integer.parseInt(useridLabel.getText());
	   ResultSet	rset = dbHandler.getUsersTask(userid);

		// copy the result in a array
		tasks = copyTasksIntoArray(rset);

		// set the text of the labels in the ListTask file
		controller.setWelcomeLabel(welcomeLabel.getText(), Integer.toString(userid));
		controller.writeTasks(tasks);

		// Animation
		FadeTrans rootTransition = new FadeTrans(root, 2000);
		rootTransition.fade();

		changeScene(event, root);
	}
}
