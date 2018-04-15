package View;

import Model.*;
import javafx.scene.canvas.GraphicsContext;

public class TileView implements Observer{
    public static int TILE_WIDTH = 100;
    public static int TILE_HEIGHT = 100;
    double xPosition;
    double yPosition;
    int rowPosition;
    int columnPosition;
    GraphicsContext graphicsContext;

    public TileView(int rowPosition,int columnPosition){
        this.rowPosition=rowPosition;
        this.columnPosition=columnPosition;
    }
    boolean intersect(double clickXPosition, double clickYPosition){
        return (clickXPosition>=getXPosition() && clickXPosition<=getXPosition()+TILE_WIDTH)
                && (clickYPosition>=getYPosition() && clickYPosition<=getYPosition()+TILE_HEIGHT);
    }
    public void draw(){
        graphicsContext.setFill(MapView.COORDINATE_TILE_COLOR);
        graphicsContext.fillRect(xPosition,yPosition,TILE_WIDTH,TILE_HEIGHT);
    }
    public void draw(TileType tileType){
        if(tileType.getClass().equals(StartTile.class)){
            graphicsContext.setFill(MapView.STARTING_TILE_COLOR);
        } else if(tileType.getClass().equals(PotHole.class)){
            graphicsContext.setFill(MapView.POTHOLE_COLOR_COLOR);
        } else if(tileType.getClass().equals(Coordinate.class)){
            graphicsContext.setFill(MapView.COORDINATE_TILE_COLOR);
        } else if(tileType.getClass().equals(ValidPathTile.class)){
            graphicsContext.setFill(MapView.VALIDPATH_TILE_COLOR);
        }
        graphicsContext.fillRect(xPosition,yPosition,TILE_WIDTH,TILE_HEIGHT);
    }
    @Override
    public void update(TileType tileType) {
        draw(tileType);
    }
    public void setYPosition(int yPosition){
        this.yPosition=yPosition;
    }
    public void setXPosition(int xPosition){
        this.xPosition=xPosition;
    }
    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public double getXPosition(){
        return xPosition;
    }
    public double getYPosition(){
        return yPosition;
    }
    public int getRowPosition(){
        return rowPosition;
    }
    public int getColumnPosition(){
        return columnPosition;
    }
}
