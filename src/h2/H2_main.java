package h2;

public class H2_main {

    public static void main(String[] args) {
        
    }
}

class Spielbrett {

    private Feld[][] brett;
    private int dim;

    public Spielbrett(int dim) {
        this.dim = dim;
        this.brett = new Feld[dim][dim];
    }

    public Feld[][] getBrett() {
        return brett;
    }

    public void setBrett(Feld[][] brett) {
        this.brett = brett;
    }

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
        this.brett = new Feld[dim][dim];
    }
}

class Feld {

    private boolean boese;
    private char direction;

    public Feld(boolean boese, char direction) {
        this.boese = boese;
        this.direction = direction;
    }

    public boolean isBoese() {
        return boese;
    }

    public void setBoese(boolean boese) {
        this.boese = boese;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}

class Spielstein {

    private int currentRow;
    private int currentCol;
    private Spielbrett brett;

    public Spielstein(Spielbrett brett, int indexRow, int indexCol) {
        this.brett = brett;
        this.currentRow = indexRow;
        this.currentCol = indexCol;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    public Spielbrett getBrett() {
        return brett;
    }

    public void setBrett(Spielbrett brett) {
        this.brett = brett;
    }

    private boolean movesOut() {
        char dir = brett.getBrett()[currentRow][currentCol].getDirection();
        int dim = brett.getDim();

        if (dir == 'U') return currentRow == 0;
        if (dir == 'D') return currentRow == dim - 1;
        if (dir == 'L') return currentCol == 0;
        if (dir == 'R') return currentCol == dim - 1;

        return false; 
    }

    public void go(int n) {
        for (int i = 0; i < n; i++) {

            Feld f = brett.getBrett()[currentRow][currentCol];

           
            if (f.isBoese()) {
                continue;
            }

            char dir = f.getDirection();
            int dim = brett.getDim();

            
            if (dir == 'U' && currentRow > 0) {
                currentRow--;
            } else if (dir == 'D' && currentRow < dim - 1) {
                currentRow++;
            } else if (dir == 'L' && currentCol > 0) {
                currentCol--;
            } else if (dir == 'R' && currentCol < dim - 1) {
                currentCol++;
            }
        }
    }
}
