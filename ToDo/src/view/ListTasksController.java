package view;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import animation.FadeTrans;
import animation.ScaleTrans;
import animation.Shaker;
import database.DatabaseHandler;
import generalForControllers.GeneralCode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class ListTasksController extends GeneralCode {
	private static final int MAX_VALUE = 5;
	@FXML
	private Label welcomeLabel;
	@FXML
	private Label useridLabel;
    @FXML
    private Label taskidLabel;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;
	@FXML
	private Label label5;
	@FXML
	private ImageView deleteImage;
	@FXML
	private ImageView addImage;

	@FXML
	private Circle circle;

	Label[] label = new Label[MAX_VALUE];
	
	// identify labels
	private int identifyLabel;
	
	private int userid;
	private int[] taskIds = new int[MAX_VALUE];
	
	DatabaseHandler dbHandler = new DatabaseHandler();

	
	
	
	
	// Label1
	@FXML
	void handleLabel1MouseEntered(MouseEvent event) {

		shakerMouseEntered(label1);
	}

	@FXML
	void handleLabel1MouseExit(MouseEvent event) {
		setColorMouseExited(label1);
	}

	@FXML
	void handleLabel1MousePressed(MouseEvent event) {
		identifyLabel = 0;
		setColorMouseExited(label1);
		ScaleTrans sc = new ScaleTrans(deleteImage);
		sc.scale();
		

	}

	
	
	
	
	// Label2
	@FXML
	void handleLabel2MouseEntered(MouseEvent event) {
		shakerMouseEntered(label2);
		
	}

	@FXML
	void handleLabel2MouseExited(MouseEvent event) {
		setColorMouseExited(label2);
	}

	@FXML
	void handleLabel2MousePressed(MouseEvent event) {
		identifyLabel = 1;
		setColorMouseExited(label2);
		ScaleTrans sc = new ScaleTrans(deleteImage);
		sc.scale();
	}

	
	
	
	
	// Label3
	@FXML
	void handleLabel3MouseEntered(MouseEvent event) {
		shakerMouseEntered(label3);
	}

	@FXML
	void handleLabel3MouseExited(MouseEvent event) {
		setColorMouseExited(label3);
	}


    @FXML
    void handleLabel3MousePressed(MouseEvent event) {
    	identifyLabel = 2;
		setColorMouseExited(label1);
		ScaleTrans sc = new ScaleTrans(deleteImage);
		sc.scale();
    }
	
    
    
    
    
    
	// Label4
	@FXML
	void handleLabel4MouseEntered(MouseEvent event) {
		shakerMouseEntered(label4);
	}

	@FXML
	void handleLabel4MouseExited(MouseEvent event) {
		setColorMouseExited(label4);
	}

	  @FXML
	    void handleLabel4MousePressed(MouseEvent event) {
			identifyLabel = 3;
			setColorMouseExited(label1);
			ScaleTrans sc = new ScaleTrans(deleteImage);
			sc.scale();
	    }
	  
	  
	
	    
	    
	// Label5
	@FXML
	void handleLabel5MouseEntered(MouseEvent event) {
		shakerMouseEntered(label5);
	}

	@FXML
	void handleLabel5MouseExited(MouseEvent event) {
		setColorMouseExited(label5);
	}

    @FXML
    void handleLabel5MousePressed(MouseEvent event) {
    	identifyLabel = 4;
		setColorMouseExited(label1);
		ScaleTrans sc = new ScaleTrans(deleteImage);
		sc.scale();
    }
    
    
    
	// addImage MouseEnteredEvent
	@FXML
	void handleAddImageMouseEntered(MouseEvent event) {
		ScaleTrans st = new ScaleTrans(addImage);
		st.scale();
	}

	// AddImage mousePressedEvent
	@FXML
	void handleAddImageMousePressed(MouseEvent event) throws IOException {

		FXMLLoader loader = getFxmlLoader("AddItemForm.fxml");
		Parent root = loader.load();

		AddItemFormController controller = loader.getController();
		controller.setWelcomLabel(welcomeLabel.getText());
		controller.setUserid(useridLabel.getText());

		changeScene(event, root);

	}

	
	
	// deleteImage handler
    @FXML
    void handleDeleteIconMousePressed(MouseEvent event) throws ClassNotFoundException, SQLException, IOException {
		dbHandler.deteteUserTask(taskIds[identifyLabel]);
		backToTaskList(event, dbHandler);
    }
	
	/***
	 * fill the user´s tasks in the labels
	 * 
	 * @param task
	 */
	public void writeTasks(String[] task) {

		label[0] = label1;
		label[1] = label2;
		label[2] = label3;
		label[3] = label4;
		label[4] = label5;
		for (int i = 0; i < task.length; i++) {
			String taskText = (task[i] == "") ? "" : task[i];

			label[i].setText(taskText);
		}
	}
	
	/***
	 * get tasksIds of the current user
	 * @param rset
	 */
	
	public void copyIdtaskIntoArray(ResultSet rset) {
		int i =0;
		try {
			while(rset.next()) {
				taskIds[i]= rset.getInt("idtask");
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/***
	 * set the welcomeLabel text with the username
	 * 
	 * @param name is the name of the current user
	 */
	public void setWelcomeLabel(String name, String userid, String taskid) {
		welcomeLabel.setText(name);
		taskidLabel.setText(taskid);
		useridLabel.setText(userid);
		useridLabel.setVisible(false);
//		taskidLabel.setVisible(false);
	}
	
	public void setWelcomeLabel(String name, String userid) {
		welcomeLabel.setText(name);
		useridLabel.setText(userid);
		useridLabel.setVisible(false);
	
	}

	/***
	 * take a label as parameter and change the background-color it also shake the
	 * trash icon
	 * 
	 * @param label
	 */
	private void shakerMouseEntered(Label label) {
	
			label.setStyle("-fx-background-color:#bc477b");
			Shaker deleteImageShaker = new Shaker(deleteImage);
			deleteImageShaker.shake();

	}

	/***
	 * take a label as parameter and change the background-color
	 * 
	 * @param label
	 */
	private void setColorMouseExited(Label label) {
		label.setStyle("-fx-background-color:#880e4f");

	}

	private void backToTaskList(MouseEvent event ,DatabaseHandler dbHandler) throws IOException, ClassNotFoundException, SQLException {
		
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

		
		// This a way to get the taskIds of the current user so that we can delete a task
		rset = dbHandler.getUsersTask(userid);
		controller.copyIdtaskIntoArray(rset);
		
		// Animation
		FadeTrans rootTransition = new FadeTrans(root, 2000);
		rootTransition.fade();

		changeScene(event, root);
	}
}
