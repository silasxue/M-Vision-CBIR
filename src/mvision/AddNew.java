package mvision;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddNew extends JDialog implements ActionListener {
	JLabel l1, l2, l3, label;

	JButton select, add;

	JTextField name, description, path;

	UserDAO UD = new UserDAO();

	public AddNew(JFrame frame, JLabel label) {

		super(frame, "Dialog");
		this.label = label;
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);
		GridBagConstraints constraints;
		constraints = new GridBagConstraints();
		l1 = new JLabel("  Name", JLabel.LEFT);
		constraints.anchor = GridBagConstraints.WEST;
		cons(constraints, 0, 1, 1, 1, 1, 40);
		gridbag.setConstraints(l1, constraints);
		add(l1);
		name = new JTextField(15);
		cons(constraints, 1, 1, 1, 1, 1, 40);
		gridbag.setConstraints(name, constraints);
		add(name);
		l2 = new JLabel("  Select Image", JLabel.LEFT);
		cons(constraints, 0, 2, 1, 1, 1, 40);
		constraints.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(l2, constraints);
		add(l2);
		path = new JTextField(15);
		path.setEditable(false);
		cons(constraints, 1, 2, 1, 1, 1, 40);
		gridbag.setConstraints(path, constraints);
		add(path);
		select = new JButton("Browse");
		select.addActionListener(this);
		cons(constraints, 2, 2, 1, 1, 1, 40);
		gridbag.setConstraints(select, constraints);
		add(select);

		l3 = new JLabel("  Description");
		cons(constraints, 0, 3, 1, 1, 1, 40);
		constraints.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(l3, constraints);
		add(l3);

		description = new JTextField(15);
		cons(constraints, 1, 3, 1, 1, 1, 40);
		constraints.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(description, constraints);
		add(description);

		add = new JButton("Insert");
		add.addActionListener(this);
		cons(constraints, 1, 4, 1, 1, 1, 40);
		constraints.anchor = GridBagConstraints.CENTER;
		gridbag.setConstraints(add, constraints);
		add(add);
		setResizable(false);
		setLocation(100, 100);
		setSize(400, 170);
		// pack();
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		Object object = ae.getSource();
		if (object == select) {
			JFileChooser jfc = new JFileChooser(".");
			int returnValue = jfc.showOpenDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				path.setText(jfc.getSelectedFile().getPath());
				label.setIcon(new ImageIcon(getImage(path.getText())));
			}
		}
		if (object == add && !path.getText().equals("")
				&& !name.getText().equals("")
				&& !description.getText().equals("")) {
			if (isImage(path.getText())) {
				if (UD.insert(name.getText(), description.getText(), path
						.getText())) {
					JOptionPane.showMessageDialog(this, "Insert Sucessfully",
							"warning", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}

			} else {
				JOptionPane.showMessageDialog(this,
						"Please select Image Files", "warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/*
	 * public static void main(String args[]) { JFrame frame=new JFrame("hi");
	 * frame.setSize(500,500); frame.setVisible(true); new AddNew(frame); }
	 */
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
			System.out.println(extension);
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

	void cons(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx,
			int wy) {
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridwidth = gw;
		gbc.gridheight = gh;
		gbc.weightx = wx;
		gbc.weighty = wy;
	}

	public boolean isImage(String path) {
		String extension = "";
		for (int i = (path.length() - 1); i >= 0; i--) {
			String ch = String.valueOf(path.charAt(i));
			if (ch.equals(".")) {
				break;
			}
			extension += ch;
		}
		//if (extension.equalsIgnoreCase("PMB")) {
			//return true;
	//}
	if (extension.equalsIgnoreCase("gpj")) {
			return true;
		}

		else if (extension.equalsIgnoreCase("fig"))
			return true;
		else
			return false;
	}

}
