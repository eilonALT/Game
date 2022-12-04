package test;

public class Tile {

    public final char letter;
    public final int score;

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    // Singleton pattern for bag of tiles (tiles are immutable)
    public static class Bag {
        public static Bag bag;
        private int[] amounts = { 9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1 };
        private int[] maxAmounts = { 9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1 };
        private Tile[] tiles = { new Tile('A', 1), new Tile('B', 3), new Tile('C', 3), new Tile('D', 2),
                new Tile('E', 1),
                new Tile('F', 4), new Tile('G', 2), new Tile('H', 4), new Tile('I', 1), new Tile('J', 8),
                new Tile('K', 5),
                new Tile('L', 1), new Tile('M', 3), new Tile('N', 1), new Tile('O', 1), new Tile('P', 3),
                new Tile('Q', 10),
                new Tile('R', 1), new Tile('S', 1), new Tile('T', 1), new Tile('U', 1), new Tile('V', 4),
                new Tile('W', 4),
                new Tile('X', 8), new Tile('Y', 4), new Tile('Z', 10) };

        public static Bag getBag() {
            if (bag == null) {
                bag = new Bag();
            }
            return bag;
        }

        // private method to check if the bag is empty
        private boolean isEmpty() {
            for (int i = 0; i < amounts.length; i++) {
                if (amounts[i] > 0) {
                    return false;
                }
            }
            return true;
        }

        // returns a random tile from the bag
        public Tile getRand() {
            int randNum = (int) (Math.random() * 26); // 0-25
            if (amounts[randNum] > 0) {
                amounts[randNum]--;
                return tiles[randNum];
            } else if (isEmpty()) {
                return null;
            } else {
                return getRand();
            }
        }

        // returns a specific tile from the bag
        public Tile getTile(char letter) {
            // check if letter is valid
            if (letter < 'A' || letter > 'Z') {
                return null;
            }
            int index = letter - 'A'; // A = 0, B = 1, C = 2, etc.
            if (amounts[index] > 0) {
                amounts[index]--;
                return tiles[index];
            } else {
                return null;
            }
        }

        // put a tile back in the bag
        public void put(Tile tile) {
            int index = tile.letter - 'A';
            if (amounts[index] < maxAmounts[index]) {
                amounts[index]++;
            }
        }

        // returns the size of the bag
        public int size() {
            int size = 0;
            for (int i = 0; i < amounts.length; i++) {
                size += amounts[i];
            }
            return size;
        }

        // returns copy of the bag amounts
        public int[] getQuantities() {
            int[] copy = new int[amounts.length];
            for (int i = 0; i < amounts.length; i++) {
                copy[i] = amounts[i];
            }
            return copy;
        }
    }

}
