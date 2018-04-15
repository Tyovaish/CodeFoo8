package View;

import MainController.MainController;
import Model.Map;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;


public class MapView extends Canvas {
    static int ROW_SIZE = Map.ROW_SIZE;
    static int COLUMN_SIZE = Map.COLUMN_SIZE;
    static Color POTHOLE_COLOR_COLOR=Color.BLACK;
    static Color STARTING_TILE_COLOR=Color.GREEN;
    static Color COORDINATE_TILE_COLOR=Color.BLUE;
    static Color VALIDPATH_TILE_COLOR=Color.GOLD;

    int spaceBetweenTiles=25;
    MainController mainController;
    ArrayList<TileView> tileViewList = new ArrayList<TileView>();
    public MapView(MainController mainController, int width, int height) {
        super(width,height);
        this.mainController=mainController;
        for(int i=0;i<ROW_SIZE;i++){
            for(int j=0;j<COLUMN_SIZE;j++){
                TileView currentTileView = mainController.getTileView(i,j);
                currentTileView.setXPosition(j*(TileView.TILE_WIDTH+spaceBetweenTiles));
                currentTileView.setYPosition(i*(TileView.TILE_HEIGHT+spaceBetweenTiles));
                currentTileView.setGraphicsContext(getGraphicsContext2D());
                tileViewList.add(currentTileView);
                currentTileView.draw();
            }
        }

        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for(int i=0;i<tileViewList.size();i++){
                    if(tileViewList.get(i).intersect(event.getX(),event.getY())){
                        mainController.changeTileType(tileViewList.get(i).getRowPosition(),tileViewList.get(i).getColumnPosition());
                        return;
                    }
                }
            }
        });
    }
}
