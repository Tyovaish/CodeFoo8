package Model;


public class Tile {
    int rowPosition;
    int columnPosition;
    TileType tileType;
    Observer observerOfTile;

    Tile(TileType tileType,int rowPosition, int columnPosition){
        this.rowPosition=rowPosition;
        this.columnPosition=columnPosition;
        this.tileType=tileType;
    }
    public boolean isValidMove(){
        return tileType.isValidMove();
    }

    public void setTileObserver(Observer observerOfTile){
        this.observerOfTile=observerOfTile;
    }
    public TileType getTileType(){
        return tileType;
    }
    public int getRowPosition(){
        return rowPosition;
    }
    public int getColumnPosition(){
        return columnPosition;
    }
    public void setRowPosition(int rowPosition){
        this.rowPosition=rowPosition;
    }
    public void setColumnPosition(int columnPosition){
        this.columnPosition=columnPosition;
    }
    public void setTileType(TileType tileType){
        this.tileType=tileType;
        updateObserver();
    }
    public void updateObserver(){
        observerOfTile.update(tileType);
    }
    public void accept(Visitor pathVisitor){
        pathVisitor.visitTile(this);
    }
}
