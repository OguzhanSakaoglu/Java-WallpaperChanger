package SistemTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Uygulama.WallpaperChanger;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class WallpaperChangerSystemTest {

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
    void testSetWallpaperFromUrl_SaveFile() {
        String imageUrl = "https://github.com/OguzhanSakaoglu/wallpaperApi/blob/main/wallpaper-img/matthew-henry-49707-unsplash.jpg?raw=true";

        boolean success = wallpaperChanger.setWallpaperFromUrl(imageUrl);
        assertTrue(success, "Duvar kağıdı ayarlanaması başarısız.");
        
        String projectPath = System.getProperty("user.dir");
        String srcPath = projectPath + "\\src\\Temp";
        File saveFolder = new File(srcPath);
        String fileName = "temp_wallpaper.jpg";
        File saveFile = new File(saveFolder, fileName);
        assertTrue(saveFile.exists(), "Ayarlanan duvar kağıdı mevcut değil.");
    }
}
