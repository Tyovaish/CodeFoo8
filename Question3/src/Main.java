import MainController.MainController;
import View.ControlView;
import View.MapView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController mainController = new MainController();
        MapView mapView = new MapView(mainController,500,500);
        ControlView controlView = new ControlView(mainController);
        mainController.getMap();
        Group root = new Group();
        HBox pane = new HBox();
        pane.getChildren().addAll(mapView,controlView);
        root.getChildren().addAll(pane);

        Scene scene = new Scene(root,700,500);

        primaryStage.setTitle("CodeFoo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
