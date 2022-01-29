// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        ArrayList<Integer> openParenIndexes = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        // get all (
        while(currentIndex < markdown.lastIndexOf("(")) {
            int openParenIndex = markdown.indexOf("(", currentIndex);
            openParenIndexes.add(openParenIndex);
            currentIndex = openParenIndex + 1;
        }
        // for each (, if the character preceding it is not a ], remove it
        for(int i = 0; i < openParenIndexes.size(); i++){
            if(markdown.charAt(openParenIndexes.get(i) - 1) != ']'){
                openParenIndexes.remove(i);
                i--;
            }
        }

        // get the links
        for(int openParen:openParenIndexes){
            // we checking links now
            String link = "";

            try {
               link = markdown.substring(openParen+1, markdown.indexOf(")",openParen));
            }
            catch (IndexOutOfBoundsException e){}

            // invalid char source: https://tinyurl.com/339ncmvh            
            String[] invalidLinkChars = {" ", "{", "}", "|", "\\", "^", "~", "[", "]", "`"};

            // note: definition of what makes a "valid link" is kind of arbitrary
            
            // https://stackoverflow.com/questions/8992100/test-if-a-string-contains-any-of-the-strings-from-an-array
            // if valid link (doesn't contain any invalid chars)
            if (!Arrays.stream(invalidLinkChars).anyMatch(link::contains)){
                toReturn.add(link);
            }
        }
        return toReturn;
    }

    //https://stackoverflow.com/questions/8992100/test-if-a-string-contains-any-of-the-strings-from-an-array
    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }

    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}