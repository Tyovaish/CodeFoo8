package View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LegendBox extends HBox {
    public LegendBox(Color legendColor, String legendName){
        setAlignment(Pos.TOP_LEFT);
        setSpacing(30);
        Rectangle legendValue = new Rectangle(20,20);
        legendValue.setFill(legendColor);
        Label legendInfo = new Label();
        legendInfo.setText(legendName);
        getChildren().addAll(legendValue,legendInfo);
    }
}
