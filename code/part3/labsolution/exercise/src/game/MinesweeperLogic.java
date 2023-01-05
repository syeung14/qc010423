package game;

import java.util.stream.IntStream;

enum GameStatus { INPROGRESS, WON, LOST }
enum CellStatus { UNEXPOSED, EXPOSED, SEALED }

/*
What are some of the poor qualities we see in this code:
JC: duplicate code in expose and seal
JV: status may be minimized
AM: violates SLAP
GA: violates SRP
GA: shared mutability
GA: poor naming conventions
JC: high cyclomatic complexity
U1: Missing {
GA Too many states
VS: Inconsistent spacing
JK: final static instead of final for SIZE
VS: return values of expose, etc. not really used
 */

//The major issues are:
//1. Failure of SRP in different methods
//2. Failure of SLAP in different methods
//3. Violation of DRY
//4. Keeping the gameState as a field actually increased the complexity, results in more code
//We may not be able to avoid state, but certainly we should minimize it.

//constructor names do not tell what we are doing except we are doing something when the instance is created.

public class MinesweeperLogic {
  private final int SIZE = 10;
  private CellStatus[][] cells = new CellStatus[SIZE][SIZE];
  private boolean[][] bombs = new boolean[SIZE][SIZE];

  public MinesweeperLogic() {
    initializeCells();
  }

  private void initializeCells() {
    for(int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        cells[i][j] = CellStatus.UNEXPOSED;
      }
    }
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

  public void expose(int row, int column) {
    if(cells[row][column] == CellStatus.UNEXPOSED) {
      cells[row][column] = CellStatus.EXPOSED;
    }
  }

  public void toggleSeal(int row, int column) {
    cells[row][column] = switch (cells[row][column]) {
      case UNEXPOSED -> CellStatus.SEALED;
      case SEALED -> CellStatus.UNEXPOSED;
      default -> cells[row][column];
    };
  }

  public GameStatus computeGameStatus() {
    var inProgress = false;

    for(int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if(bombs[i][j] && cells[i][j] == CellStatus.EXPOSED) {
          return GameStatus.LOST;
        }

        if (bombs[i][j] && cells[i][j] != CellStatus.SEALED ||
          !bombs[i][j] && cells[i][j] != CellStatus.EXPOSED) {
          inProgress = true;
        }
      }
    }

    return inProgress ? GameStatus.INPROGRESS : GameStatus.WON;
  }

  int getMinedNeighborsCount(int row, int column) {
    var count = (int) IntStream.rangeClosed(row - 1, row + 1)
      .mapToLong(rowIndex -> IntStream.rangeClosed(column - 1, column + 1)
        .filter(columnIndex -> isBombAt(rowIndex, columnIndex))
        .count())
      .sum();

    return isBombAt(row, column) ? count - 1 : count;
  }

  private boolean isBombAt(int row, int column) {
    return row >= 0 && row < SIZE && column >= 0 && column < SIZE && bombs[row][column];
  }
}
