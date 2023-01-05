package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static game.CellStatus.*;
import static game.GameStatus.*;

public class MinesweeperLogicTest {
  private MinesweeperLogic minesweeperLogic = new MinesweeperLogic();

  @Test
  public void canaryTest() {
    assertTrue(true);
  }

  @Test
  public void testInitialCellState() {
    assertEquals(UNEXPOSED, minesweeperLogic.getCellState(0, 0));
  }

  @Test
  public void testExposeAnUnExposedCell() {
    minesweeperLogic.expose(0, 1);

    assertEquals(EXPOSED, minesweeperLogic.getCellState(0, 1));
  }

  @Test
  public void testExposeAnExposedCell() {
    minesweeperLogic.expose(1, 0);

    minesweeperLogic.expose(1, 0);

    assertEquals(EXPOSED, minesweeperLogic.getCellState(1, 0));
  }

  @Test
  public void testSealAnUnExposedCell() {
    minesweeperLogic.toggleSeal(0, 0);

    assertEquals(SEALED, minesweeperLogic.getCellState(0, 0));
  }

  @Test
  public void testUnsealASealedCell() {
    minesweeperLogic.toggleSeal(1, 1);

    minesweeperLogic.toggleSeal(1, 1);

    assertEquals(UNEXPOSED, minesweeperLogic.getCellState(0, 0));
  }

  @Test
  public void testSealAnExposedCell() {
    minesweeperLogic.expose(0, 0);

    minesweeperLogic.toggleSeal(0, 0);

    assertEquals(EXPOSED, minesweeperLogic.getCellState(0, 0));
  }

  @Test
  public void testExposeASealedCell() {
    minesweeperLogic.toggleSeal(1, 3);

    minesweeperLogic.expose(1, 3);

    assertEquals(SEALED, minesweeperLogic.getCellState(1, 3));
  }

  @Test
  public void testNonMinePosition() {
    assertFalse(minesweeperLogic.isMineAt(3, 2));
  }

  @Test
  public void testMinePosition() {
    minesweeperLogic.setMine(3, 2);

    assertTrue(minesweeperLogic.isMineAt(3, 2));
  }

  @Test
  public void testSetMineAndGetAdjacentMineCountAtSamePosition() {
    MinesweeperLogic minesweeper = new MinesweeperLogic();

    minesweeper.setMine(3, 4);

    assertEquals(0, minesweeper.getMinedNeighborsCount(3, 4));
  }

  @Test
  public void testSetMineAndGetAdjacentMinesCountAtAdjacentPosition() {
    MinesweeperLogic minesweeper = new MinesweeperLogic();

    minesweeper.setMine(3, 4);

    assertEquals(1, minesweeper.getMinedNeighborsCount(3, 5));
  }

  @Test
  public void testSetTwoMinesAndGetAdjacentMinesCount() {
    MinesweeperLogic minesweeper = new MinesweeperLogic();

    minesweeper.setMine(3, 4);

    minesweeper.setMine(2, 6);

    assertEquals(2, minesweeper.getMinedNeighborsCount(3, 5));
  }

  @Test
  public void testSetMineAtTopLeftCornerAndGetAdjacentMinesCount() {
    MinesweeperLogic minesweeper = new MinesweeperLogic();

    minesweeper.setMine(0, 1);

    assertEquals(1, minesweeper.getMinedNeighborsCount(0, 0));
  }

  @Test
  public void testSetMineAtBottomRightCornerAndGetAdjacentMinesCount() {
    MinesweeperLogic minesweeper = new MinesweeperLogic();

    minesweeper.setMine(9, 8);

    assertEquals(1, minesweeper.getMinedNeighborsCount(9, 9));
  }

  @Test
  public void testGetGameStatusToReturnInprogress() {
    assertEquals(INPROGRESS, minesweeperLogic.computeGameStatus());
  }

  @Test
  public void testExposeAMinedCellAndGetGameStatusToReturnLost() {
    minesweeperLogic.setMine(3, 2);

    minesweeperLogic.expose(3, 2);

    assertEquals(LOST, minesweeperLogic.computeGameStatus());
  }

  @Test
  public void testGameInProgressAfterAllMinesSealedButCellsRemainUnexposed() {
    for (int i = 0; i < 4; ++i) {
      minesweeperLogic.setMine(i, 2);
      minesweeperLogic.toggleSeal(i, 2);
    }

    assertEquals(INPROGRESS, minesweeperLogic.computeGameStatus());
  }

  @Test
  public void testGameInProgressAfterAllMinesSealedButAnEmptyCellIsSealed() {

    final int SIZE = 10;
    for (int i = 0; i < 4; ++i) {
      minesweeperLogic.setMine(i, 0);
      minesweeperLogic.toggleSeal(i, 0);
    }

    minesweeperLogic.toggleSeal(3, 4);

    assertEquals(INPROGRESS, minesweeperLogic.computeGameStatus());
  }

  @Test
  public void testGameInProgressAfterAllMinesSealedButAnAdjacentCellIsUnexposed() {
    for (int i = 1; i < 5; ++i) {
      minesweeperLogic.setMine(i, 0);
      minesweeperLogic.toggleSeal(i, 0);
    }

    assertEquals(INPROGRESS, minesweeperLogic.computeGameStatus());
  }

  @Test
  public void testGameWonAfterAllMinesAreSealedAndAllOtherCellsExposed() {
    MinesweeperLogic minesweeper = new MinesweeperLogic();
    int size = 10;

    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        if (i == j) {
          minesweeper.setMine(i, j);
        }
      }
    }

    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        if (i == j) {
          minesweeper.toggleSeal(i, j);
        } else {
          minesweeper.expose(i, j);
        }
      }
    }

    assertEquals(WON, minesweeper.computeGameStatus());
  }
}
