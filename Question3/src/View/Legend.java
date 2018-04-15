package View;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Legend extends VBox {
    public Legend(){
        setSpacing(10);
        setPrefWidth(150);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(new Label("LEGEND"),new LegendBox(MapView.COORDINATE_TILE_COLOR,"COORDINATE"),
                new LegendBox(MapView.POTHOLE_COLOR_COLOR,"POTHOLE"),
                new LegendBox(MapView.STARTING_TILE_COLOR,"START"),
                new LegendBox(MapView.VALIDPATH_TILE_COLOR,"VALIDPATH"));

    }
}
