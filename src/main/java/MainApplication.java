public class MainApplication {
    public static void main(String[] args) throws Exception {
        var userInput = "to the laden bin osama";
        var fileBlacklist =  "src/main/resources/names.txt";
        var fileNoiseWords =  "src/main/resources/noise_words.txt";

        var noiseWords = new NoiseWords(fileNoiseWords);
        var userInputList = noiseWords.removeNoiseWords(userInput);

        var processor = new Processor(fileBlacklist);

        System.out.println(processor.getBestPossibleMatch(userInputList));
    }
}
