package EntegrasyonTest;

import com.sun.jna.platform.win32.WinDef.DWORD;

import Uygulama.User32;

import com.sun.jna.WString;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class User32IntegrationTest {
	
    @Test
    void testSystemParametersInfo() {
    	String projectPath = System.getProperty("user.dir");
    	String imagePath = projectPath + "\\src\\Temp\\Test.jpg";
    	
        User32 user32 = User32.INSTANCE;
        DWORD uiAction = new DWORD(User32.SPI_SETDESKWALLPAPER);
        
        DWORD uiParam = new DWORD(0);WString pvParam =  new WString(imagePath);
        DWORD fWinIni = new DWORD(User32.SPIF_UPDATEINIFILE | User32.SPIF_SENDCHANGE);

        boolean success = user32.SystemParametersInfo(uiAction, uiParam, pvParam, fWinIni);

        assertTrue(success, "Duvar kağıdı ayarı başarısız.");
    }
}