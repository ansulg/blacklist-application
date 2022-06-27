import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Score {

    public static double getScore(List<String> matchList, List<String> inputWithoutNoise) {
        double score;

        var similarities = matchList.stream()
                .filter(inputWithoutNoise::contains)
                .collect(Collectors.toList());

        score = (double)similarities.size()/matchList.size() ;

        score += calculateDifferencesScore(matchList, inputWithoutNoise, similarities);

        return score;
    }

    protected static double calculateDifferencesScore(List<String> matchList, List<String> inputList, List<String> similarities) {
        var difBlackList = new ArrayList<>(matchList);
        var difInput = new ArrayList<>(inputList);

        difBlackList.removeAll(similarities);
        difInput.removeAll(similarities);

        var difFromBlackList = String.join("", difBlackList);
        var difFromInput = String.join("", difInput);

        return (double)difBlackList.size()/(matchList.size() + calculateDistance(difFromBlackList, difFromInput));
    }

    /**
     *
     * Calculates two words Levenshtein distance. Uses code from https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java.
     *
     * @param source
     * @param target
     * @kk
     * @return
     */
    protected static int calculateDistance(String source, String target) {
        int len0 = source.length() + 1;
        int len1 = target.length() + 1;

        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        for (int i = 0; i < len0; i++) cost[i] = i;

        for (int j = 1; j < len1; j++) {
            newcost[0] = j;

            for(int i = 1; i < len0; i++) {
                int match = (source.charAt(i - 1) == target.charAt(j - 1)) ? 0 : 1;

                int cost_replace = cost[i - 1] + match;
                int cost_insert  = cost[i] + 1;
                int cost_delete  = newcost[i - 1] + 1;
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            int[] swap = cost; cost = newcost; newcost = swap;
        }

        return cost[len0 - 1];
    }
}
