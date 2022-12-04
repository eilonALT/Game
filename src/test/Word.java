package test;

public class Word {
    private Tile[] tiles;
    private int row;
    private int col;
    private boolean vertical;

    public Word(Tile[] tiles, int row, int col, boolean vertical) {
        this.tiles = tiles;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVertical() {
        return vertical;
    }

    // to string method
    @Override
    public String toString() {
        String word = "";
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] != null) {
                word += tiles[i].letter;
            }
        }
        return word;
    }

    // auto-generated equals method
    @Override
    public boolean equals(Object obj) {
        if (this.toString().equals(obj.toString())) {
            return true;
        }
        return false;
    }
}
