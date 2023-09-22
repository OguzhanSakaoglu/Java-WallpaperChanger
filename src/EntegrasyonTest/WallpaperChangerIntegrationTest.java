package EntegrasyonTest;

import org.junit.jupiter.api.*;

import Uygulama.WallpaperChanger;

import static org.junit.jupiter.api.Assertions.*;

public class WallpaperChangerIntegrationTest {

    private static final String TEST_IMAGE_URL = "https://github.com/OguzhanSakaoglu/wallpaperApi/blob/main/wallpaper-img/pexels-tobi-675763.jpg?raw=true";
    private WallpaperChanger wallpaperChanger;

    @BeforeEach
    void setUp() {
        wallpaperChanger = new WallpaperChanger();
    }

    @AfterEach
    void tearDown() {
        wallpaperChanger = null;
    }

    @Test
    void testSetWallpaperFromUrl() {
        boolean success = wallpaperChanger.setWallpaperFromUrl(TEST_IMAGE_URL);
        assertTrue(success, "Duvar kağıdı ayarlama işlemi başarısız.");
    }
}
