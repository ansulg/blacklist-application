import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Processor {
    private String file;

    public Processor(String file) {
        this.file = file;
    }

    /**
     *
     * Checks if any word from input matches with a name in the blacklist.
     *
     * @return
     * @throws Exception Throws exception when names.txt can not be read.
     */
    protected List<PossibleMatch> getPossibleMatches(List<String> userInputList) throws Exception {
        var matchesList = new ArrayList<PossibleMatch>();

        try (var fileReader = new FileReader(this.file, StandardCharsets.UTF_8)) {
            var br = new BufferedReader(fileReader);
            String line;

            while ((line = br.readLine()) != null) {
                var matchInBlacklist = userInputList.stream().anyMatch(line.toLowerCase(Locale.ROOT)::contains);

                if (matchInBlacklist) {
                    List<String> matchList = Stream.of(line.toLowerCase().split("[ ,]+"))
                            .collect(Collectors.toCollection(ArrayList<String>::new));
                    var score = Score.getScore(matchList, userInputList);

                    matchesList.add(new PossibleMatch(line, score));
                };
            }

            return matchesList;
        }
        catch (IOException ex){
            throw new Exception("Can't read blacklist file", ex);
        }
    }

    public String getBestPossibleMatch(List<String> userInputList) throws Exception {
        var result = this.getPossibleMatches(userInputList);

        if (result.isEmpty()) {
            return "No matches in blacklist";
        }

        PossibleMatch maxScore = Collections.max(result, Comparator.comparing(PossibleMatch::getScore));

        return String.format("Found possible match '%s' with a score %s %n", maxScore.word , maxScore.score);
    }
}