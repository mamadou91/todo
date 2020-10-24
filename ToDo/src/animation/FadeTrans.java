package animation;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeTrans {

	private FadeTransition ft;
	
	public FadeTrans(Node node, double duration) {
		ft = new FadeTransition(Duration.millis(duration), node);
	
		ft.setFromValue(0f);
		ft.setToValue(1f);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
	}
	
	public void  fade() {
		
		ft.play();
	}
}
