package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author Mohamed Hossam
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true if the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /*********************** Fourth Task *************************
     * Tilt the board toward SIDE. Return true iff this changes the board.
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     *
     * Points:
     * We will loop over the board row by row for a single column
     * The function validRow() will return the valid row that a tile can move to
     * The board.move() will return true if a merged operation happened and this when
     * we will update the score if the return is true
     * */
    public int validRow(int c, int r, Tile t) {
        int size = board.size();
        int index = -1;
        for (int row = r + 1; row < size; ++row) {
            Tile tile = board.tile(c, row);
            if (tile != null && tile.value() != t.value())
                break;
            index = row;
        }
        return index;
    }

    public boolean tilt(Side side) {
        boolean changed;
        changed = false;
        board.setViewingPerspective(side);

        //todo: tricky merge part

        int size = board.size();
        for (int c = 0; c < size; ++c) {
            for (int r = size-2; r >= 0; --r) {
                Tile t = board.tile(c, r);
                if (t == null)
                    continue;
                int vRow = validRow(c, r, t);
                if (vRow >= 0) {
                    boolean merged = board.move(c, vRow, t);
                    if (merged) {
                        this.score += board.tile(c, vRow).value();
                    }
                    changed = true;
                }
            }
        }
        checkGameOver();
        if (changed) {
            setChanged();
        }
        board.setViewingPerspective(Side.NORTH);
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /************* First Task ****************
     *  Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     *  Looping over the 2D board of tiles and check if the tile is empty
     *  The Board class has a private instance of the Tile class
     *  so we cannot access directly, we will use the tile method that
     *  returns an object of type Tile
     * */
    public static boolean emptySpaceExists(Board b) {
        int size = b.size();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Tile tile = b.tile(j, i);
                if(tile == null)
                    return true;
            }
        }
        return false;
    }

    /************* Second Task ****************
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     * Same as the previous task, we will loop over the 2D board
     * and check if the tile value is equal to the MAX_PIECE
     * We must check also if the tile is null so we don't get nullPointerException
     */
    public static boolean maxTileExists(Board b) {
        int size = b.size();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Tile tile = b.tile(j, i);
                if (tile == null)
                    continue;
                if (tile.value() == MAX_PIECE)
                    return true;
            }
        }
        return false;
    }

    /************* Third Task ****************
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     * A helper method isValidPos() to check if the pos is valid or not
     * Two direction arrays dr & dc to check the 4-neighbors for every tile
     */
    public static boolean isValidPos(int col, int row, Board b){
        int size = b.size();
        if ( row < 0 || row >= size)
            return false;
        else if (col < 0 || col >= size)
            return false;
        return true;
    }
    public static boolean atLeastOneMoveExists(Board b) {
        if(emptySpaceExists(b))
            return true;

        int[] dr = new int[] {-1, 0, 1, 0};
        int[] dc = new int[] {0, -1, 0, 1};
        int size = b.size();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Tile tile = b.tile(j, i);
                if(tile == null)
                    continue;
                for (int d = 0; d < 4; ++d) {
                    int nRow = i + dr[d];
                    int nCol = j + dc[d];
                    if(isValidPos(nCol, nRow, b)){
                        Tile newTile = b.tile(nCol, nRow);
                        if(tile.value() == newTile.value())
                            return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
