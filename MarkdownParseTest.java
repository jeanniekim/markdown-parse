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
        assertEquals(Arrays.asList("page.com"), MarkdownParse.getLinks(Files.readString(Path.of("test-file6.md")))); // !
        assertEquals(new ArrayList<String>(), MarkdownParse.getLinks(Files.readString(Path.of("test-file7.md"))));
        assertEquals(Arrays.asList("a link on the first line"), MarkdownParse.getLinks(Files.readString(Path.of("test-file8.md"))));

        assertEquals(Arrays.asList("page.com", "page.com", "page.com", "page.com", "page.com"), 
            MarkdownParse.getLinks(Files.readString(Path.of("test-long.md"))));

        assertEquals(new ArrayList<String>(), 
            MarkdownParse.getLinks(Files.readString(Path.of("test-break.md"))));
        // assertEquals(toReturn, MarkdownParse.getLinks(Files.readString(Path.of("new-test.md"))));
        // assertEquals(toReturn, MarkdownParse.getLinks(Files.readString(Path.of("test-error.md"))));
        // assertEquals(toReturn, MarkdownParse.getLinks(Files.readString(Path.of("test4.md"))));
    }
}
