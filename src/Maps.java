public class Maps {
    String inGame;
    String plainText;

    public Maps(String inGame, String plainText){
        this.inGame = inGame;
        this.plainText = plainText;
    }

    public String getInGame() { return this.inGame; }

    public String getPlainText() { return this.plainText; }

    public String toString() { return this.inGame + "," + this.plainText; }
}
