package View;

import MainController.MainController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ControlBox extends HBox {
    Label text = new Label();
    Button previous = new Button();
    Button next = new Button();
    MainController mainController;
    public ControlBox(MainController mainController){
        this.mainController=mainController;
        setAlignment(Pos.CENTER);
        setSpacing(10);
        text.setText("NONE");
        previous.setText("<");
        previous.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                previousValidPath();
            }
        });
        next.setText(">");
        next.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nextValidPath();
            }
        });
        getChildren().addAll(previous,text,next);
    }

    public void setText(String text) {
        this.text.setText(text);
    }
    public void nextValidPath(){
            mainController.nextValidPath(this);
    }
    public void previousValidPath(){
            mainController.previousValidPath(this);
    }


}
