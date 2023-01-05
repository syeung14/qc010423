package game;

enum GameStatus { INPROGRESS, WON, LOST }
enum CellStatus { UNEXPOSED, EXPOSED, SEALED }

public class MinesweeperLogic {
  private final int SIZE = 10;
  private CellStatus[][] cells = new CellStatus[SIZE][SIZE];
  private boolean[][] bombs = new boolean[SIZE][SIZE];

  private GameStatus gameStatus = GameStatus.INPROGRESS;

  public MinesweeperLogic() {
    for(int i = 0; i < SIZE; i++)
      for(int j = 0; j < SIZE; j++)
        cells[i][j] = CellStatus.UNEXPOSED;
  }

  public CellStatus getCellState(int row, int column) {
    return cells[row][column];
  }

  public boolean isMineAt(int row, int column){
    return bombs[row][column];
  }

  public void setMine(int row, int column) {
    bombs[row][column] = true;
  }

  public boolean expose(int row, int column) {
    if(bombs[row][column]) gameStatus = GameStatus.LOST;
    if(cells[row][column] == CellStatus.UNEXPOSED)
      cells[row][column] = CellStatus.EXPOSED;
    boolean result = cells[row][column] == CellStatus.EXPOSED;

    boolean won = true;
    for(int i = 0; i < SIZE; i++)
      for(int j = 0; j < SIZE; j++)
        if(bombs[i][j] && cells[i][j] != CellStatus.SEALED ||
          !bombs[i][j] && cells[i][j] != CellStatus.EXPOSED)
          won = false;

    if(won) gameStatus = GameStatus.WON;

    return result;
  }

  public boolean seal(int row, int column) {
    if(cells[row][column] == CellStatus.UNEXPOSED)
      cells[row][column] = CellStatus.SEALED;
    boolean result = cells[row][column] == CellStatus.SEALED;

    boolean won = true;
    for(int i = 0; i < SIZE; i++)
      for(int j = 0; j < SIZE; j++)
        if(bombs[i][j] && cells[i][j] != CellStatus.SEALED ||
          !bombs[i][j] && cells[i][j] != CellStatus.EXPOSED)
          won = false;

    if(won) gameStatus = GameStatus.WON;

    return result;
  }

  public boolean unseal(int row, int column) {
    if(cells[row][column] == CellStatus.SEALED)
      cells[row][column] = CellStatus.UNEXPOSED;
    return cells[row][column] == CellStatus.UNEXPOSED;
  }

  public GameStatus getGameStatus() {
    return gameStatus;
  }

  int getMinedNeighborsCount(int row, int column) {
    int count = 0;

    for(int i = row - 1; i <= row + 1; i++)
      for(int j = column - 1; j <= column + 1; j++)
        if(i >= 0 && i < SIZE && j >= 0 && j < SIZE && bombs[i][j]) count++;

    return bombs[row][column] ? count - 1 : count;
  }
}
