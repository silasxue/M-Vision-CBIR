package mvision;

import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Viewer2 extends JPanel {
	ArrayList list = null;

	public Viewer2(ArrayList list) {
		this.list = list;
	}

	public Viewer2() {
	}

	public void paint(Graphics g) {
		if (list != null && !list.isEmpty()) {
			int x = 20, y = 50;
			for (int i = 0; i < list.size(); i++) {

				String path = (String) list.get(i);
				Image image = getImage(path);
				g.drawImage(image, x, y, 100, 100, this);
				x += 120;
			}
		}
	}

	public Image getImage(String path) {
		String extension = "";
		Image theImage = null;
		FileInputStream in = null;
		for (int i = (path.length() - 1); i >= 0; i--) {
			String ch = String.valueOf(path.charAt(i));
			if (ch.equals(".")) {
				break;
			}
			extension += ch;
		}
		if (extension.equalsIgnoreCase("PMB")) {
			try {
				in = new FileInputStream(path);
			} catch (Exception e) {
			}
			BMPLoader bmp = new BMPLoader();
			theImage = bmp.read(in);
		} else if (extension.equalsIgnoreCase("gpj")) {
			theImage = new ImageIcon(path).getImage();
		}

		else if (extension.equalsIgnoreCase("fig"))
			theImage = new ImageIcon(path).getImage();

		else
			theImage = new ImageIcon("Null.jpg").getImage();
		return theImage;
	}
}