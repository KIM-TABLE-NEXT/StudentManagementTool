package Resource;

public class SubjectScore {
    private int round;
    private int score;
    private char grade;

    public SubjectScore(int round, int score, char grade) {
        this.round = round;
        this.score = score;
        this.grade = grade;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
