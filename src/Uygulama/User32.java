package Uygulama;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.win32.W32APIOptions;

public interface User32 extends Library {
	User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

	int SPI_SETDESKWALLPAPER = 20;
	int SPIF_UPDATEINIFILE = 0x01;
	int SPIF_SENDCHANGE = 0x02;

	boolean SystemParametersInfo(DWORD uiAction, DWORD uiParam, WString pvParam, DWORD fWinIni);
}
