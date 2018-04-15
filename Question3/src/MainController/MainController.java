package MainController;

import Model.*;
import View.ControlBox;
import View.TileView;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainController {
    Map map = new Map(4,4);
    ListIterator<ArrayList<Tile>> validPathIterator;
    ControlBox controlBox = new ControlBox(this);
    Label solutionSetSize = new Label("SIZE: 0");

    public Map getMap(){
        return map;
    }

    public void changeTileType(int rowPosition, int columnPosition){
        Tile tileTypeToChange=map.getTile(rowPosition,columnPosition);
        TileType currentTileType=tileTypeToChange.getTileType();
        if(columnPosition!=0){
            if(currentTileType.getClass().equals(Coordinate.class)){
                tileTypeToChange.setTileType(new PotHole());
            } else {
                tileTypeToChange.setTileType(new Coordinate());
            }
        } else {
            if(currentTileType.getClass().equals(Coordinate.class)){
                tileTypeToChange.setTileType(new PotHole());
            } else if(currentTileType.getClass().equals(PotHole.class) && map.getStartingTile()==null) {
                tileTypeToChange.setTileType(new StartTile());
            } else {
                tileTypeToChange.setTileType(new Coordinate());
            }
        }

    }
    public TileView getTileView(int rowPosition, int columnPosition){
        Tile tile = map.getTile(rowPosition,columnPosition);
        TileView tileView = new TileView(rowPosition,columnPosition);
        tile.setTileObserver(tileView);
        return tileView;
    }
    public Label getSolutionSetSize(){
        return solutionSetSize;
    }
    public void resetMap(){
        map.reset();
    }
    public void solveMap(){
        clearValidPath();
        ValidPathVisitor validPathVisitor = new ValidPathVisitor(map);
        Tile startingTile = map.getStartingTile();
        validPathVisitor.visitTile(startingTile);
        validPathIterator=validPathVisitor.getValidPathIterator();
        if(validPathVisitor.getNumberOfValidPaths()==0){
            controlBox.setText("NONE");
            solutionSetSize.setText("SIZE: 0");
        } else {
            nextValidPath(controlBox);
            solutionSetSize.setText("SIZE: "+String.valueOf(validPathVisitor.getNumberOfValidPaths()));
        }
    }
    public ControlBox getControlBox(){
        return controlBox;
    }
    public void nextValidPath(ControlBox controlBox){
        if(validPathIterator == null || map.getStartingTile()==null){
            controlBox.setText("NONE");
            clearValidPath();
            return;
        }
        if(!validPathIterator.hasNext()){
            return;
        }
        controlBox.setText(String.valueOf(validPathIterator.nextIndex()+1));
        clearValidPath();
        displayValidPath(validPathIterator.next());
    }
    public void previousValidPath(ControlBox controlBox){
        if(validPathIterator == null || map.getStartingTile()==null){
            controlBox.setText("NONE");
            clearValidPath();
            return;
        }
        if(!validPathIterator.hasPrevious()){
            return;
        }
        controlBox.setText(String.valueOf(validPathIterator.previousIndex()+1));
        clearValidPath();
        displayValidPath(validPathIterator.previous());
    }

    private void displayValidPath(ArrayList<Tile> validPath) {
       for(int i=1;i<validPath.size();i++){
           Tile tileInValidPath=validPath.get(i);
           map.getTile(tileInValidPath.getRowPosition(),tileInValidPath.getColumnPosition()).setTileType(new ValidPathTile());
       }
    }
    private void clearValidPath(){
        for(int i=0;i<map.getRowSize();i++){
            for(int j=0;j<map.getColumnSize();j++){
                TileType currentTileType = map.getTile(i,j).getTileType();
                if(currentTileType.getClass().equals(ValidPathTile.class)){
                    map.getTile(i,j).setTileType(new Coordinate());
                }
            }
        }
    }
}
