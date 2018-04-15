package Model;
import java.util.Random;

public class Map {
    public static int ROW_SIZE = 4;
    public static int COLUMN_SIZE = 4;
    Tile[] [] map;

    public Map(int ROW_SIZE, int COLUMN_SIZE){
        map = new Tile[ROW_SIZE][COLUMN_SIZE];
        for(int i=0;i<ROW_SIZE;i++){
            for(int j=0;j<COLUMN_SIZE;j++){
                map[i][j]=new Tile(new Coordinate(),i,j);
            }
        }
    }
    public void randomize(int numberOfPotHoles){
        reset();
        Random random = new Random();
        map[random.nextInt(ROW_SIZE)%ROW_SIZE][0].setTileType(new StartTile());
        Tile startingTile = getStartingTile();
        for(int i=0;i<numberOfPotHoles;i++) {
            int rowPosition = random.nextInt(ROW_SIZE) % ROW_SIZE;
            int columnPosition = random.nextInt(COLUMN_SIZE) % COLUMN_SIZE;
            while(map[rowPosition][columnPosition]==startingTile || map[rowPosition][columnPosition].getTileType().getClass().equals(PotHole.class)){
                rowPosition = random.nextInt(ROW_SIZE) % ROW_SIZE;
                columnPosition = random.nextInt(COLUMN_SIZE) % COLUMN_SIZE;
            }
            map[rowPosition][columnPosition].setTileType(new PotHole());
        }
    }
    public void reset() {
        for(int i=0;i<ROW_SIZE;i++){
            for (int j=0;j<COLUMN_SIZE;j++){
                map[i][j].setTileType(new Coordinate());
            }
        }
    }
    public int getRowSize(){
        return ROW_SIZE;
    }

    public int getColumnSize(){
        return COLUMN_SIZE;
    }
    public Tile getTile(int rowPosition, int columnPosition){
        return map[rowPosition][columnPosition];
    }
    public Tile getStartingTile() {
        for(int i=0;i<ROW_SIZE;i++){
            for(int j=0;j<COLUMN_SIZE;j++){
                TileType currentTileType= map[i][j].getTileType();
                if(currentTileType.getClass().equals(StartTile.class)){
                    return map[i][j];
                }
            }
        }
        return null;
    }
}
