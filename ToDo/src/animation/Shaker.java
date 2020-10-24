package animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shaker {

	private TranslateTransition tt;
	
	public Shaker(Node node) {
		tt = new TranslateTransition(Duration.millis(80), node);
		tt.setFromX(0f);
		tt.setByX(10f);
		tt.setCycleCount(2);
		tt.setAutoReverse(true);
	}
	
	public void shake() {
		tt.playFromStart();
	}
}
