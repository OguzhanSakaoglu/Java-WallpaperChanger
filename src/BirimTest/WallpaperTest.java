package BirimTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Uygulama.Wallpaper;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;

public class WallpaperTest 
{
    private Wallpaper wallpaper;

    @BeforeEach
    void setUp() 
    {
        wallpaper = new Wallpaper();
    }

    @Test
    void testStartMethod() 
    {
        assertDoesNotThrow(() -> wallpaper.start());
    }
    
    @Test
    void testImageLabel() throws IOException 
    {
    	String imageUrl = "https://github.com/OguzhanSakaoglu/wallpaperApi/blob/main/wallpaper-img/13.jpg?raw=true";
        Wallpaper.ImageLabel imageLabel = wallpaper.new ImageLabel(imageUrl);
        assertNotNull(imageLabel);
    }
}