import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinks() throws IOException{
        List<String> toReturn =  Arrays.asList("https://something.com", "some-page.html");
        assertEquals(toReturn, MarkdownParse.getLinks(Files.readString(Path.of("test-file.md"))));
        assertEquals(toReturn, MarkdownParse.getLinks(Files.readString(Path.of("test-file2.md"))));
        assertEquals(new ArrayList<String>(), MarkdownParse.getLinks(Files.readString(Path.of("test-file3.md"))));
        assertEquals(new ArrayList<String>(), MarkdownParse.getLinks(Files.readString(Path.of("test-file4.md"))));
        assertEquals(new ArrayList<String>(), MarkdownParse.getLinks(Files.readString(Path.of("test-file5.md"))));
        assertEquals(new ArrayList<String>(), MarkdownParse.getLinks(Files.readString(Path.of("test-file5.md"))));
        assertEquals(new ArrayList<String>(), MarkdownParse.getLinks(Files.readString(Path.of("test-file6.md")))); // FAIL
        assertEquals(new ArrayList<String>(), MarkdownParse.getLinks(Files.readString(Path.of("test-file7.md"))));
        assertEquals(new ArrayList<String>(), MarkdownParse.getLinks(Files.readString(Path.of("test-file8.md"))));


        // assertEquals(toReturn, MarkdownParse.getLinks(Files.readString(Path.of("new-test.md"))));
        // assertEquals(toReturn, MarkdownParse.getLinks(Files.readString(Path.of("test-error.md"))));
        // assertEquals(toReturn, MarkdownParse.getLinks(Files.readString(Path.of("test4.md"))));
    }
}
