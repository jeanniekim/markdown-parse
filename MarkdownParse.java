// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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

        // get the links after )
        for(int openParen:openParenIndexes){
            try {
                toReturn.add(markdown.substring(openParen+1, markdown.indexOf(")",openParen)));
            }
            catch (IndexOutOfBoundsException e){
                break;
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}