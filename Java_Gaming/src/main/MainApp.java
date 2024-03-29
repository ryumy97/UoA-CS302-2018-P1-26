package main;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {
	//declare classes
	private Group root;
	private Canvas canvas;
	private Stage primaryStage;
	private GameController control;
	private GraphicsContext graphic;
	private AnimationTimer gameLoop;
	
	//initialise window through primary stage
	public void init(Stage primaryStage) {
		//declare primary stage
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Planet Escape (Java Gaming - InJae)");

		//init canvas and control class
		root = new Group();
		canvas = new Canvas();
		canvas.setWidth(1280);
		canvas.setHeight(720);
		graphic = canvas.getGraphicsContext2D();
		control = new GameController(graphic, this.primaryStage);
		root.getChildren().add(canvas);
		
		//init scene
		Scene scene = new Scene(root);
		this.primaryStage.setScene(scene);
		this.primaryStage.setWidth(1280);
		this.primaryStage.setHeight(720);
		this.primaryStage.setResizable(false);
		
		//show window
		this.primaryStage.show();
		
		//upon window closing show quit confirmation window
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainApp.class.getResource("view/quitConfirmationWIndow.fxml"));
					Stage newWindow = new Stage();
					newWindow.setScene(new Scene((BorderPane)loader.load()));
					QuitController controller = loader.getController();
					controller.setEvent(event);
					controller.setStage(newWindow);
					newWindow.setTitle("Quit Confirmation");
					newWindow.setResizable(false);
					newWindow.initModality(Modality.WINDOW_MODAL);
					newWindow.initOwner(primaryStage);
					newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
						@Override
						public void handle(WindowEvent e) {
							event.consume();
						}
					});
			        newWindow.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	//connect canvas event and controller class
	public void initEvent(Canvas canvas, GameController control) {
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        control.takeMouseClicked(event.getX(),event.getY());
		    }
		    });
		canvas.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        control.takeMouseMoved(event.getX(),event.getY());
		    }
		    });
		canvas.setFocusTraversable(true);
		canvas.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
		    public void handle(KeyEvent event) {
		        control.takeKeyPressed(event.getCode());
		    }
		    });
		canvas.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				control.takeKeyReleased(event.getCode());
			}
		});
		
	}
	
	//Application start
	@Override
	public void start(Stage primaryStage) {
		//initialise functions
		init(primaryStage);
		initEvent(canvas, control);
		
		//game rendering loop
		gameLoop = new AnimationTimer() {
			
			@Override
			public void handle(long now) {	
				
				control.gameUpdate();
			}
		};
		gameLoop.start();
	}
	
	//Application launch
	public static void main(String[] args) {
		launch(args);
	}
}