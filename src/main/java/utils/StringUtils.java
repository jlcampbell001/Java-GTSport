package utils;

/**
 * My methods for dealing with strings.
 *
 * @author jonathan
 */
public class StringUtils {

    /**
     * Creates a new string padded with the pad character on the left to the
     * passed length.
     *
     * @param stringToPad the original string
     * @param finalLength the length of the final string
     * @param padCharacter the character to pad the string with
     * @return the string padded with the pad character
     */
    public final static String padLeft(String stringToPad, int finalLength, char padCharacter) {
        StringBuilder padString = new StringBuilder(stringToPad);

        while (padString.length() < finalLength) {
            padString.insert(0, padCharacter);
        }
        return padString.toString();
    }
}
