package mvision;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.FlowLayout;

public class CBIRLogin extends JPanel {
	public Image backgroundImage;

	public static JButton button = null;

	public CBIRLogin() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		try {
			File file = new File("C:\\Users\\bilel\\Desktop\\aa.PNG");
			this.backgroundImage = ImageIO.read(file);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Background Image Test");
		frame.setContentPane(new CBIRLogin());
		frame.getContentPane().setLayout(null);

		//button.addActionListener(this);
		frame.getContentPane().add(getSubmit());
		frame.setSize(700, 525);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 135, 50, null);
	}

	private static JButton getSubmit() {
		if (button == null) {
			button = new JButton();
			button.setText("Enter");
			button.setSize(button.getPreferredSize());
			button.setLocation(300, 400);
			button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					String hai="";
				
       	new User_Login().setVisible(true);
					
					System.out.println("xxxxx actionPerformed()");
				//new CBIR(hai);
					//dispose();
				}
				
			});
		}
		
		return button;
	}
}
