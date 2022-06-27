public class PossibleMatch {
    final String word;
    final double score;

    public PossibleMatch(String word, double score) {
        this.word = word;
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
