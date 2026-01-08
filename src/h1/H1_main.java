package h1;

public class H1_main {
    public static void main(String[] args) {
        
    }
}

class Grid {

    private Cell[][] gridArray;

    
    public Cell[][] getGridArray() {
        return gridArray;
    }

    public void setGridArray(Cell[][] gridArray) {
        this.gridArray = gridArray;
    }

    
    public Grid(Cell[] cells, int gridRows, int gridCols) {

       
        gridArray = new Cell[gridRows][gridCols];

       
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridCols; c++) {
                gridArray[r][c] = new Cell(r, c, false); 
            }
        }

        
        if (cells != null) {
            for (Cell cell : cells) {
                if (cell == null) continue;

                int r = cell.getIndexRow();
                int c = cell.getIndexCol();

                
                if (r < 0 || r >= gridRows || c < 0 || c >= gridCols) {
                    continue;
                }

                
                gridArray[r][c].setAlive(true);
            }
        }

        
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridCols; c++) {
                gridArray[r][c].countLivingNeighbors(gridArray);
            }
        }
    }

   
    public void computeNextGen() {
        int rows = gridArray.length;
        int cols = gridArray[0].length;

       
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                gridArray[r][c].countLivingNeighbors(gridArray);
            }
        }

      
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                gridArray[r][c].setAlive(gridArray[r][c].isAliveNextGen());
            }
        }

        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                gridArray[r][c].countLivingNeighbors(gridArray);
            }
        }
    }

    
    public void computeGeneration(int n) {
        for (int i = 0; i < n; i++) {
            computeNextGen();
        }
       
    }
}

class Cell {

    private int indexRow;
    private int indexCol;
    private boolean alive;
    private int numLivingNeighbors;
    private boolean isAliveNextGen;

   
    public Cell(int indexRow, int indexCol, boolean alive) {
        this.indexRow = indexRow;
        this.indexCol = indexCol;
        this.alive = alive;
        this.numLivingNeighbors = 0;
        this.isAliveNextGen = false;
    }

   
    public Cell(int indexRow, int indexCol) {
        this(indexRow, indexCol, false);
    }

    
    public int getIndexRow() {
        return indexRow;
    }

    public void setIndexRow(int indexRow) {
        this.indexRow = indexRow;
    }

    public int getIndexCol() {
        return indexCol;
    }

    public void setIndexCol(int indexCol) {
        this.indexCol = indexCol;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getNumLivingNeighbors() {
        return numLivingNeighbors;
    }

    public void setNumLivingNeighbors(int numLivingNeighbors) {
        this.numLivingNeighbors = numLivingNeighbors;
    }

    public boolean isAliveNextGen() {
        return isAliveNextGen;
    }

    public void setAliveNextGen(boolean aliveNextGen) {
        isAliveNextGen = aliveNextGen;
    }

   
    public void countLivingNeighbors(Cell[][] gridArray) {
        int rows = gridArray.length;
        int cols = gridArray[0].length;

        int count = 0;

        for (int r = indexRow - 1; r <= indexRow + 1; r++) {
            for (int c = indexCol - 1; c <= indexCol + 1; c++) {

                
                if (r < 0 || r >= rows || c < 0 || c >= cols) {
                    continue;
                }

             
                if (r == indexRow && c == indexCol) {
                    continue;
                }

                if (gridArray[r][c].isAlive()) {
                    count++;
                }
            }
        }

        this.numLivingNeighbors = count;
        decideNextStatus();
    }

    
    private void decideNextStatus() {
        if (alive) {
            isAliveNextGen = (numLivingNeighbors == 2 || numLivingNeighbors == 3);
        } else {
            isAliveNextGen = (numLivingNeighbors == 3);
        }
    }
}
