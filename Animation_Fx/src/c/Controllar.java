package c;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.Animation.Status;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Controllar implements Initializable{

	@FXML
    private AnchorPane pane;
	
	@FXML
    private Slider radiusSlider;

    @FXML
    private Slider rateSlider;

    @FXML
    private Circle circel;
    
    @FXML
    private Label catchCount;

    @FXML
    private Label looseCount;
    
    private int loose;
    private int win;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		circel.radiusProperty().bind(radiusSlider.valueProperty());
		
		
		TranslateTransition up = new TranslateTransition(Duration.millis(500),circel);
		up.rateProperty().bind(rateSlider.valueProperty());
		up.setAutoReverse(false);
		up.setFromY(0);
		up.setToY(-480);
		up.setCycleCount(1);
		
		TranslateTransition down = new TranslateTransition(Duration.millis(500),circel);
		down.rateProperty().bind(rateSlider.valueProperty());
		down.setAutoReverse(false);
		down.setFromY(-480);
		down.setToY(0);
		down.setCycleCount(1);
		
		SequentialTransition trans = new SequentialTransition(up,down);
		trans.rateProperty().bind(rateSlider.valueProperty());
		trans.setAutoReverse(false);
		trans.setCycleCount(Timeline.INDEFINITE);
		
		circel.setOnMouseClicked(e -> {
			--loose;
			if(up.getStatus()== Status.RUNNING) {
				up.pause();
				catchCount.setText(String.valueOf(++win));
			}
		});
		
		pane.setOnMouseClicked(e -> {
			++loose;
			trans.play();
			looseCount.setText(String.valueOf(loose));
		});
		
	}

}
