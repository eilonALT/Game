package test;

public class BoardBox {
    private Tile tile;
    private boolean isOccupied;
    private final int scoreBonus;

    public BoardBox() {
        this.tile = null;
        this.isOccupied = false;
        this.scoreBonus = 1;
    }

    public BoardBox(int scoreBonus) {
        this.tile = null;
        this.scoreBonus = scoreBonus;
        this.isOccupied = false;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        isOccupied = true;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public int getScoreBonus() {
        return scoreBonus;
    }

}
