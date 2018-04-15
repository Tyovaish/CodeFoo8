package View;

import MainController.MainController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControlView extends VBox {
    MainController mainController;
    public ControlView(MainController mainController){
        this.mainController=mainController;
        setAlignment(Pos.TOP_CENTER);
        setPrefWidth(200);
        setSpacing(25);
        setTitle();
        setRandomMap();
        setResetMap();
        setSolveMap();
        setSolutionDisplay();
        setLegend();
    }
    private void setTitle(){
        Label title = new Label("CODE FOO");
        title.setFont(Font.font("Times New Roman",20));
        getChildren().add(title);
    }
    private void setRandomMap(){
        Button randomMap = new Button("RANDOM");
        randomMap.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.setAlignment(Pos.TOP_CENTER);
                Label potholeTitle= new Label("NUMBER OF POTHOLES");
                potholeTitle.setFont(Font.font("Helvetica",15));
                TextField numberOfPotHoles = new TextField();
                numberOfPotHoles.setPrefSize(25,25);
                numberOfPotHoles.setAlignment(Pos.CENTER);
                Button submit = new Button("SUBMIT");
                submit.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        try {
                            int potholes=Integer.valueOf(numberOfPotHoles.getText());
                            mainController.getMap().randomize(Integer.valueOf(numberOfPotHoles.getText()));
                        } catch (NumberFormatException n){

                        }
                    }
                });
                dialogVbox.getChildren().addAll(potholeTitle,numberOfPotHoles,submit);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                submit.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent event) {
                        try {
                            int potholes=Integer.valueOf(numberOfPotHoles.getText());
                            if(potholes<MapView.ROW_SIZE*MapView.COLUMN_SIZE) {
                                mainController.getMap().randomize(Integer.valueOf(numberOfPotHoles.getText()));
                            }
                            dialog.close();
                        } catch (NumberFormatException n){
                        }
                    }
                });
                dialog.show();
            }
        });
        getChildren().add(randomMap);
    }
    private void setLegend() {
        getChildren().add(new Legend());
    }

    private void setSolutionDisplay() {
        Label solutionTitle = new Label();
        solutionTitle.setText("SOLUTION");
        getChildren().addAll(solutionTitle,mainController.getSolutionSetSize(),mainController.getControlBox());
    }

    private void setSolveMap() {
        Button solveMap = new Button("SOLVE");
        solveMap.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                mainController.solveMap();
            }
        });
        getChildren().add(solveMap);
    }

    private void setResetMap() {
        Button resetMap = new Button("RESET");
        resetMap.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                mainController.resetMap();
            }
        });
        getChildren().add(resetMap);
    }

}
