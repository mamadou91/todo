package animation;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class ScaleTrans {

	ScaleTransition st;
	
	public ScaleTrans(Node node) {
		st = new ScaleTransition(Duration.millis(500),node);
		st.setByX(0.1f);
		st.setByY(0.1f);
		st.setCycleCount(2);
		st.setAutoReverse(true);
		
	}
	
	public void scale() {
		st.play();
	}
}
