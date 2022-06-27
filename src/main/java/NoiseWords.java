import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NoiseWords {
    private String file;

    public NoiseWords(String file) {
        this.file = file;
    }

    private List<String> readWords() throws Exception {
        try (var br = new BufferedReader(new FileReader(this.file, StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.toList());
        }
        catch (IOException ex){
            throw new Exception("Can't read noise words file", ex);
        }
    }

    /**
     *
     * Removes all noise words from input and returns a list separated by spaces and commas.
     *
     * @param userInput
     * @return
     * @throws Exception Throws exception when noise_words.txt can not be read.
     */
    public ArrayList<String> removeNoiseWords(String userInput) throws Exception {
        var noiseWords = this.readWords();
        ArrayList<String> allWords =
                Stream.of(userInput.toLowerCase().split("[ ,]+"))
                        .collect(Collectors.toCollection(ArrayList<String>::new));

        allWords.removeAll(noiseWords);

        return allWords;
    }
}