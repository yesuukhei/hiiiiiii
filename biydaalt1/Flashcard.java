

public class Flashcard {
    private String question;
    private String answer;
    private int incorrectAttempts;
    private long lastIncorrectTimestamp;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.incorrectAttempts = 0;
        this.lastIncorrectTimestamp = 0;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getIncorrectAttempts() {
        return incorrectAttempts;
    }

    public long getLastIncorrectTimestamp() {
        return lastIncorrectTimestamp;
    }

    public void incrementIncorrectAttempts() {
        this.incorrectAttempts++;
        lastIncorrectTimestamp = System.currentTimeMillis();
    }

    public void setLastIncorrectTimestamp(long timestamp) {
        this.lastIncorrectTimestamp = timestamp;
    }
}
