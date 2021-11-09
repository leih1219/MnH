public abstract class BoardGame {
    protected boolean gameOver;
    BoardGame(){
    }
    public abstract void play();

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}