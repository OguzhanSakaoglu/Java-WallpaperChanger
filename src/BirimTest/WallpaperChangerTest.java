package BirimTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import Uygulama.WallpaperChanger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class WallpaperChangerTest 
{
    private WallpaperChanger wallpaperChanger;
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final PrintStream originalErr = System.err;
    
    @BeforeAll
    public static void redirectSystemErr() 
    {
        System.setErr(new PrintStream(outputStream));
    }
    @BeforeEach
    void setUp() 
    {
        wallpaperChanger = new WallpaperChanger();
    }
    
    @Test
    void testSetWallpaperFromUrlWithValidUrl() throws IOException 
    {
    	String imageUrl = "https://github.com/OguzhanSakaoglu/wallpaperApi/blob/main/wallpaper-img/wp2815442.jpg?raw=true";
    	assertTrue(wallpaperChanger.setWallpaperFromUrl(imageUrl));
    }

    @Test
    void testSetWallpaperFromUrlWithInvalidUrl() 
    {
        String invalidUrl = "https://example.com/image.jpg";
        assertFalse(wallpaperChanger.setWallpaperFromUrl(invalidUrl));
        String output = outputStream.toString();
    }
}
