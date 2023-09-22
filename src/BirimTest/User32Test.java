package BirimTest;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import Uygulama.User32;

public class User32Test 
{
    @Test
    void testUser32Instance() 
    {
        User32 user32 = User32.INSTANCE;
        assertNotNull(user32);
    }
    
    @Test
    void testConstants() 
    {
        assertEquals(20, User32.SPI_SETDESKWALLPAPER);
        assertEquals(0x01, User32.SPIF_UPDATEINIFILE);
        assertEquals(0x02, User32.SPIF_SENDCHANGE);
    }
}
