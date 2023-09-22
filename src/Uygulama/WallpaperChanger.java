package Uygulama;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.DWORD;

public class WallpaperChanger 
{
	public boolean setWallpaperFromUrl(String imageUrl) 
	{
		
		try {
			URL url = new URL(imageUrl);
			String projectPath = System.getProperty("user.dir");
			String srcPath = projectPath + "\\src\\Temp";
			BufferedImage bufferedImage = ImageIO.read(url);
			File saveFolder = new File(srcPath);
			String fileName = "temp_wallpaper.jpg";
			File saveFile = new File(saveFolder, fileName);
			String extension = imageUrl.substring(imageUrl.lastIndexOf("."));
			
			if (bufferedImage == null) 
			{
				System.err.println("Fotograf URL'den okunurken hata olustu: " + imageUrl);
				return false;
			}

			if (!saveFolder.exists()) 
			{
				saveFolder.mkdirs();
			}
			
			if (saveFile.exists()) 
			{
				saveFile.delete();
			}
			
			if (extension.equalsIgnoreCase(".jpg?raw=true")) 
			{
				ImageIO.write(bufferedImage, "jpg", saveFile);
			} 
			else if (extension.equalsIgnoreCase(".jpeg?raw=true")) 
			{
				ImageIO.write(bufferedImage, "jpeg", saveFile);
			} 
			else if (extension.equalsIgnoreCase(".png?raw=true")) 
			{
				ImageIO.write(bufferedImage, "png", saveFile);
			} 
			else 
			{
				System.err.println("Desteklenmeyen dosya uzantısı: " + extension);
			}

			User32 user32 = User32.INSTANCE;
			boolean success = user32.SystemParametersInfo(new DWORD(User32.SPI_SETDESKWALLPAPER), new DWORD(0),
					new WString(saveFile.getAbsolutePath()),
					new DWORD(User32.SPIF_UPDATEINIFILE | User32.SPIF_SENDCHANGE));
			if (!success) 
			{
				System.err.println("Duvar Kagidi Ayarlanamadi!!");
			}
			return success;
		} 
		catch (IOException error) 
		{
			error.printStackTrace();
			return false;
		}
	}
}