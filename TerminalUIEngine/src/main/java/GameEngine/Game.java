package GameEngine;
import java.io.FileNotFoundException;
import java.lang.Thread.State;
/**
 * get most of its code from hw2
 */
public abstract class Game<T> {
    /**
     * get the winner of the Game
     * 
     * @return winner or null
     */
    public abstract T getWinner();

    /**
     * actions for game start,like print welcome massage;
     */
    protected abstract void onGameStart();

    /**
     * actions on game tie, like say 'game tie'
     */
    protected abstract void onGameTie();

    /**
     * actions for game has a winner
     */
    protected abstract void onGameHasWinner();

    /**
     * actions before next round;
     */
    protected abstract void onNextRound();
    /**
     * start/restart the game
     * @throws FileNotFoundException
     */
    public abstract void start() throws Exception;

    /**
     * stop the game
     */
    public abstract void stop();

    /**
     * pause the game
     * 
     * @throws InterruptedException
     */
    public abstract void pause();

    /**
     * resume the paused game
     */
    public abstract void resume();
    /**
     * check game state
     * @return game state;
     */
    public abstract State getState();
}
