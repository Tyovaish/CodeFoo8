package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;

public class ValidPathVisitor implements Visitor {
    Map map;
    ArrayList<Tile> path = new ArrayList<>();
    ArrayList<ArrayList<Tile>> storedValidPaths = new ArrayList<>();
    HashSet<Tile> visitedTiles = new HashSet<>();
    public ValidPathVisitor(Map map){
        this.map=map;
    }
    public void visitTile(Tile tile){
        if(tile==null || !tile.isValidMove()){
            return;
        }
        if(tile.getColumnPosition()==map.getColumnSize()-1){
            path.add(tile);
            addValidPath();
            path.remove(tile);
            return;
        }
        path.add(tile);
        visitedTiles.add(tile);
        ArrayList<Tile> validCoordinates=getValidCoordinates(tile.getRowPosition(),tile.getColumnPosition());
        for(int i=0;i<validCoordinates.size();i++){
            validCoordinates.get(i).accept(this);
        }
        visitedTiles.remove(tile);
        path.remove(tile);
    }

    private ArrayList<Tile> getValidCoordinates(int rowPosition, int columnPosition){
            ArrayList<Tile> validCoordinates=new ArrayList<Tile>();
            if(rowPosition-1>=0&&!isAlreadyVisited(map.getTile(rowPosition-1,columnPosition))){
                validCoordinates.add(map.getTile(rowPosition-1,columnPosition));
            }
            if(columnPosition+1<map.getColumnSize()&&!isAlreadyVisited(map.getTile(rowPosition,columnPosition+1))){
                validCoordinates.add(map.getTile(rowPosition,columnPosition+1));
            }
            if(rowPosition+1<map.getRowSize()&&!isAlreadyVisited(map.getTile(rowPosition+1,columnPosition))){
                validCoordinates.add(map.getTile(rowPosition+1,columnPosition));
            }

            return validCoordinates;
    }


    private void addValidPath() {
        ArrayList<Tile> validPath = new ArrayList<>();
        storedValidPaths.add(validPath);
        for(int i=0;i<path.size();++i){
            validPath.add(path.get(i));
        }
    }

    private boolean isAlreadyVisited(Tile tile){
        return visitedTiles.contains(tile);
    }
    public ListIterator<ArrayList<Tile>>getValidPathIterator(){
        return storedValidPaths.listIterator();
    }
    public int getNumberOfValidPaths(){
        return storedValidPaths.size();
    }

}
