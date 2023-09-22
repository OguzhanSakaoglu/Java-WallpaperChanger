package Uygulama;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Wallpaper {
	private JFrame mainFrame;
	private JPanel imagePanel;
	private JFrame imageFrame;
	private JOptionPane successMessagePane;
	private JButton changeButton;

	public void start() {
		mainFrame = new JFrame("Duvar Kagıdı Degistirici");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		imagePanel = new JPanel();
		imagePanel.setLayout(new GridLayout(0, 3, 10, 10));

		FetchImagesTask fetchImagesTask = new FetchImagesTask(imagePanel);
		fetchImagesTask.execute();

		JScrollPane scrollPane = new JScrollPane(imagePanel);
		mainFrame.add(scrollPane);

		mainFrame.setSize(955, 650);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}

	public void dispose() {
		mainFrame.dispose();
	}

	public JPanel getImagePanel() {
		return imagePanel;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public JFrame getImageFrame() {
		return imageFrame;
	}

	public JButton getChangeButton() {
		return changeButton;
	}

	public JOptionPane getSuccessMessagePane() {
		return successMessagePane;
	}

	public class FetchImagesTask extends SwingWorker<Void, ImageLabel> {
		private JPanel panelToUpdate;

		public FetchImagesTask(JPanel panelToUpdate) {
			this.panelToUpdate = panelToUpdate;
		}

		@Override
		public Void doInBackground() throws Exception {
			try {
				String jsonUrl = "https://raw.githubusercontent.com/OguzhanSakaoglu/wallpaperApi/main/api/app.json";
				String jsonText = readUrl(jsonUrl);

				JSONArray jsonArray = new JSONArray(jsonText);

				for (int i = 0; i < jsonArray.length(); i++) {
					if (i % 3 == 0) {
						publish();
					}

					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String imageUrl = jsonObject.getString("imgUrl");
					ImageLabel imageLabel = new ImageLabel(imageUrl);
					imageLabel.addMouseListener(new ImageClickListener(imageUrl));
					publish(imageLabel);
				}
			} catch (IOException | JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public void process(List<ImageLabel> chunks) {
			for (ImageLabel imageLabel : chunks) {
				panelToUpdate.add(imageLabel);
			}
			panelToUpdate.revalidate();
		}
	}

	public class ImageLabel extends JLabel {
		public ImageLabel(String imageUrl) throws IOException {
			ImageIcon imageIcon = new ImageIcon(new URL(imageUrl));
			Image scaledImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			setIcon(new ImageIcon(scaledImage));
		}
	}

	public class ImageClickListener extends MouseAdapter {
		private String imageUrl;

		public ImageClickListener(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			openImageInNewFrame(imageUrl);
		}

		private void openImageInNewFrame(String imageUrl) {
			if (imageFrame != null) {
				imageFrame.dispose();
			}

			imageFrame = new JFrame("Fotograf Gosterici");
			imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			try {
				Image scaledImage = new ImageIcon(new URL(imageUrl)).getImage().getScaledInstance(1360, 768,
						Image.SCALE_SMOOTH);
				ImageIcon scaledIcon = new ImageIcon(scaledImage);
				JLabel imageLabel = new JLabel(scaledIcon);
				JPanel buttonPane = new JPanel();
				changeButton = new JButton("Degistir");
				changeButton.setBackground(Color.WHITE);
				changeButton.setForeground(Color.RED);

				imageFrame.setLayout(new BorderLayout(10, 10));
				buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
				buttonPane.add(Box.createHorizontalGlue());
				buttonPane.add(changeButton);
				buttonPane.add(Box.createHorizontalGlue());
				changeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						WallpaperChanger wallpaperChanger = new WallpaperChanger();
						wallpaperChanger.setWallpaperFromUrl(imageUrl);
						imageFrame.dispose();
					}
				});
				imageFrame.add(imageLabel, BorderLayout.CENTER);
				imageFrame.add(buttonPane, BorderLayout.SOUTH);
				imageFrame.setSize(1366, 768);
				imageFrame.setLocationRelativeTo(null);
				imageFrame.setResizable(false);
				imageFrame.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String readUrl(String urlString) throws IOException {
		try (Scanner scanner = new Scanner(new URL(urlString).openStream())) {
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		}
	}
}