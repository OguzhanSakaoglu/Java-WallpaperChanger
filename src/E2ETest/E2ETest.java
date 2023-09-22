package E2ETest;

import org.junit.jupiter.api.*;

import Uygulama.Wallpaper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static org.junit.jupiter.api.Assertions.*;
public class E2ETest {
	private Wallpaper WP;

    @BeforeEach
    void setUp() {
        SwingUtilities.invokeLater(() -> {
        	WP = new Wallpaper();
        	WP.start();
        });
    }
    @AfterEach
    void tearDown() {
        if (WP != null) {
        	WP.dispose();
        }
    }
    @Test
    void testWallpaperChangeSystem() {
        try {
            Thread.sleep(10000);
            assertNotNull(WP.getMainFrame());
            assertNotNull(WP.getImagePanel());

            JPanel imageLabel = WP.getImagePanel();
            assertNotNull(imageLabel);

            JFrame imageFrame = WP.getImageFrame();
            assertNotNull(imageFrame);

            JButton changeButton = WP.getChangeButton();
            assertNotNull(changeButton);

            boolean[] successFlag = {false}; 
            changeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    successFlag[0] = true;
                }
            });

            changeButton.doClick();
            assertTrue(successFlag[0]);

            imageFrame.dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}