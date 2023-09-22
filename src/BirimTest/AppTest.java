package BirimTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import Uygulama.App;

public class AppTest {
    @Test
    void testMainMethod() 
    {
        assertDoesNotThrow(() -> App.main(new String[0]));
    }
}