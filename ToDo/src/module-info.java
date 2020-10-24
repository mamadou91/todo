module project.todo {
	requires transitive javafx.controls;
	requires javafx.fxml;

	exports view;

	opens view to javafx.fxml;

	requires transitive java.sql;
	requires javafx.graphics;

	exports database;
	exports modell;

}