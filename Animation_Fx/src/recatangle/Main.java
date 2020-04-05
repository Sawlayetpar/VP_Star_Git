package recatangle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application implements Initializable {

	@FXML
	private Rectangle node;

	private RotateTransition trans;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("RotateReactangle.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RotateTransition rotate = new RotateTransition(Duration.millis(500), node);
		rotate.setAutoReverse(false);
		rotate.setCycleCount(Timeline.INDEFINITE);
		rotate.setByAngle(180);
		
		TranslateTransition translate = new TranslateTransition(Duration.millis(500),node);
		translate.setFromX(0);
		translate.setToX(200);
		translate.setAutoReverse(true);
		translate.setCycleCount(Timeline.INDEFINITE);
		
		FillTransition fill = new FillTransition(Duration.millis(500),node);
		fill.setAutoReverse(true);
		fill.setCycleCount(Timeline.INDEFINITE);
		fill.setFromValue(Color.RED);
		fill.setToValue(Color.GREEN);
		
		FadeTransition fade = new FadeTransition(Duration.millis(500),node);
		fade.setAutoReverse(true);
		fade.setCycleCount(Timeline.INDEFINITE);
		fade.setFromValue(0.5);
		fade.setToValue(1);
		ParallelTransition para = new ParallelTransition(rotate,translate,fill);

		node.setOnMouseClicked(e -> {
			if (para.getStatus() == Status.RUNNING)
				para.pause();
			else
				para.play();
		});

	}
}
