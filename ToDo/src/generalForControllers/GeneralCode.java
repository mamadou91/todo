package generalForControllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modell.Task;

public class GeneralCode {
	
	
/***
 * you can use this method when you need to pass values to another
 * controller before you change the scene.
 * @param fxmlPage the FXML file to load
 * @return returns a type FXMLLoader
 */
	protected FXMLLoader getFxmlLoader(String fxmlPage) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(fxmlPage));
		return loader;
	}
	
	
	
	/***
	 * Note: This method is on useful for ActionEvent
	 * call the getFxmlLoader method and pass the fxmlPage as parameter to
	 * the method
	 * It will then change the Scene to the fxmlPage
	 * 
	 * @param event
	 * @param root
	 * @param fxmlPage
	 * @throws IOException
	 */
	protected void changeScene(ActionEvent event,Parent root) throws IOException {
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
	}
	
	

	/***
	 * Note: This method is only useful for MouseEvent
	 * call the getFxmlLoader method and pass the fxmlPage as parameter to
	 * the method
	 * It will then change the Scene to the fxmlPage
	 * 
	 * @param event
	 * @param root
	 * @param fxmlPage
	 * @throws IOException
	 */
	protected void changeScene(MouseEvent event,Parent root) throws IOException {
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
	}
	
	
	/***
	 * Note ActionEvent
	 * change the scene
	 * @param event
	 * @param fxmlPage
	 * @throws IOException
	 */
	protected void changeScene(ActionEvent event,String fxmlPage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(fxmlPage));
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
	}
	
	
	
	/***
	 * Note ActionEvent
	 * change the scene
	 * @param event
	 * @param fxmlPage
	 * @throws IOException
	 */
	protected void changeScene(MouseEvent event,String fxmlPage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(fxmlPage));
		Scene scene = new Scene(root);
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(scene);
	}
	
	
	/***
	 * copy the result of a SQL query in a array
	 * @param rset
	 * @return
	 * @throws SQLException
	 */
	public String[] copyTasksIntoArray(ResultSet rset) throws SQLException {

		String tasks[] = new String[5];
		int i = 0;

		while (rset.next()) {

			// Variables to hold task informations

			Date date = rset.getDate("dateCreated");
			String description = rset.getString("description");
			String task = rset.getString("task");

			Task t = new Task();
			t.setDateCreated(date);
			t.setDescription(description);
			t.setTask(task);

			tasks[i] = t.writeTask();
			i++;
		}
		return tasks;
	}
}
