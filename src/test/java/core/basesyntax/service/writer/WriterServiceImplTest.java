package core.basesyntax.service.writer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WriterServiceImplTest {
    private static final String OUTPUT_FILE_PATH = "src/test/resources/report.csv";
    private static final String NONEXISTENT_PATH = "src/test/resources/re.csv";
    private static WriterService writerService;

    @BeforeAll
    static void beforeAll() {
        writerService = new WriterServiceImpl();
    }

    @AfterAll
    static void afterAll() throws IOException {
        Files.delete(Path.of(OUTPUT_FILE_PATH));
        Files.delete(Path.of(NONEXISTENT_PATH));
    }

    @Test
    void writeToFile_writesDataToFile_ok() throws IOException {
        String actual = "Test write report data";
        writerService.writeToFile(actual, OUTPUT_FILE_PATH);
        String expected = new String(Files.readAllBytes(Paths.get(OUTPUT_FILE_PATH)));
        assertEquals(actual, expected);
    }

    @Test
    void writeToFile_nonexistentPath_ok() {
        assertDoesNotThrow(() ->
                writerService.writeToFile("new file report", NONEXISTENT_PATH));
    }
}
