package mvision;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;



public class CBIR extends JFrame implements ActionListener {
	
	JLabel l1, l2, l3;

	JButton next, previous;

	String path;

	JMenu user,fileMenu, histogram, texture, shape, newM, add;

	JMenuBar bar;

	JMenuItem averageRGB, coOccerence, global, open, addNew, addNewFolder,
			edgeFrequency, localColor, geometricMoment, zernike,addUser,deleteUser;
	//JScrollPane scr=new JScrollPane();;

	GridBagLayout grid;
	
	GridBagConstraints gbc;

	Container content;
	

	// ImagePanel ipanel;

	File file;

	Viewer2 view;

	JPanel bottom, panel_header;

	FeatureTester tester;

	ArrayList outputList, list;
	public String uid=null;
	private JLabel lblNewLabel;
	private JLabel label;

	public CBIR(String uname) {
		//super("CBIR");
		
		super();
		uid=uname;
		//initialize(uname);
		
		
		content = getContentPane();
		content.setBackground(Color.LIGHT_GRAY);
		Rectangle d = content.getBounds();
		content.setBounds((int) d.getX(), (int) d.getY(), 2000, 150);
		content.setLayout(new BorderLayout());
		bar = new JMenuBar();
		setJMenuBar(bar);
		//1 
		user = new JMenu("User");
		fileMenu = new JMenu("File");
		newM = new JMenu("New");
		histogram = new JMenu("Color Histogram");
		
		shape = new JMenu("Shape");
		//2 
		bar.add(user);
		bar.add(fileMenu);
		bar.add(histogram);
		
		bar.add(newM);
		
		
		averageRGB = new JMenuItem("AverageRGB");
		
		global = new JMenuItem("Global color Histogram");
		localColor = new JMenuItem("Local color Histogram");
		
		geometricMoment = new JMenuItem("Geometric Moment");
		//zernike = new JMenuItem("Zernike Moment");
		open = new JMenuItem("Open");
		
		//3 
		addUser=new JMenuItem("Add New User");
		//4 
		deleteUser = new JMenuItem("Delete User");
		
		addNew = new JMenuItem("Add New Image");
		addNewFolder = new JMenuItem("Add New Folder");

		averageRGB.addActionListener(this);
		
		global.addActionListener(this);
		open.addActionListener(this);
		addNew.addActionListener(this);
		//5 
		addUser.addActionListener(this);
		//6 
		deleteUser.addActionListener(this);
		localColor.addActionListener(this);
		//zernike.addActionListener(this);
		addNewFolder.addActionListener(this);

		//7 
		user.add(addUser);
		//8 
		user.add(deleteUser);
		
		newM.add(addNew);
		newM.add(addNewFolder);
		
		
		fileMenu.add(open);
		histogram.add(averageRGB);
		histogram.add(localColor);
		histogram.add(global);
		
		

		
		JPanel jp = new JPanel();
		jp.setBackground(Color.LIGHT_GRAY);
		jp.setLayout(new FlowLayout());
		JPanel jp1 = new JPanel();
		jp1.setBackground(Color.LIGHT_GRAY);
		jp1.setLayout(new FlowLayout());
		ImageIcon icon = new ImageIcon("CBIR.jpg");
		
		
		
		l1 = new JLabel(icon);
		l2 = new JLabel();
		l3 = new JLabel();
		/*pbQuery.setBounds(jp.getWidth()-127,60,500,500);
		pbQuery.setTitle("QueryImage");
		pbQuery.setSelectionEnabled(false);
		jp.add(pbQuery);*/
		//scr.setBounds(5,5,470,400);
		
		

		l2.setBounds(new java.awt.Rectangle(74, 0, 106, 26));
		l2.setFont(new java.awt.Font("TimesNewRoman", java.awt.Font.BOLD, 18));
		l3.setBackground(Color.pink);
		l3.setBounds(new java.awt.Rectangle(109, 500, 106, 109));
		l3.setFont(new java.awt.Font("TimesNewRoman", java.awt.Font.BOLD, 18));

		jp.add(l2);
		jp.add(l1);
		jp1.add(l3);

		content.add("West", jp1);
		content.add("North", jp);
		view = new Viewer2();
		content.add("Center", view);
		
		lblNewLabel = new JLabel("New label");
		view.add(lblNewLabel);
		bottom = new JPanel();
		
		bottom.add(next = new JButton("Next"));
		bottom.add(previous = new JButton("Previous"));
		next.addActionListener(this);
		previous.addActionListener(this);
		geometricMoment.addActionListener(this);
		previous.setEnabled(false);
		next.setEnabled(false);
		content.add("South", bottom);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(Color.GRAY);
		getContentPane().add(label, BorderLayout.CENTER);
		label.setIcon(new ImageIcon("C:\\Users\\bilel\\Desktop\\aa.PNG"));
		setSize(650, 500);
		setVisible(true);
		// setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {
		Object object = ae.getSource();
		if (object == open) {
			JFileChooser jfc = new JFileChooser(".");
			int approve = jfc.showOpenDialog(this);
			if (approve == JFileChooser.APPROVE_OPTION) {
				file = jfc.getSelectedFile();
				path = file.getPath();
				String ext[] = { "jpg", "JPG" };
				
				if(path.endsWith( ext[0])||path.endsWith( ext[1]))
				{
				
			        l2.setText("*** Input Image ***");
			        l1.setIcon(new ImageIcon(getImage(path)));
			    
				}
				else
				{
					JOptionPane.showMessageDialog(this, " Select Image Files only");
				}

			}
		}
		if(object==addUser)
		{
			new InsertUser();
		}
		if(object==deleteUser)
		{
			
			new DeleteUser();
		}
		
		if (object == addNew) {
			new AddNew(this, l1);
		}

		if (object == addNewFolder) {
			JFileChooser jfc = new JFileChooser(".");
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int approve = jfc.showOpenDialog(null);
			if (approve == JFileChooser.APPROVE_OPTION) {
				file = jfc.getSelectedFile();
				String dir = file.getPath();
				String ext[] = { "jpg", "JPG" };
				File[] f = file.listFiles(new Filter(ext));
				UserDAO UD = new UserDAO();
				for (int i = 0; i < f.length; i++) {
					if (UD.preInsert(f[i].getPath())) {
						UD.insert(f[i].getName(), "Image", f[i].getPath());
					}

				}
				JOptionPane.showMessageDialog(this, dir
						+ " Insert successfully");
			}
		}

		if (object == averageRGB) {
			if (path != null && !path.equals("")) {
				double d = 15, d1 = 0;
				String[] argv = { "AverageRGB", path };
				Date startTime = new Date();
				tester = new FeatureTester(argv, d, d1, view);
				outputList = tester.getOutputList();
				list = new ArrayList();
				for (int i = 0; i < outputList.size(); i++) {
					if (i == 7)
						break;
					list.add((String) outputList.get(i));
				}
				view.list = list;

				l3.setText("Output Image");
				System.out.println("Output Image");
				System.out.println("-------------------------" + list);
				view.repaint();

				Date currentTime = new Date();
				long millisecond = currentTime.getTime() - startTime.getTime();
				String s = "Total Time used = " + String.valueOf(millisecond)
						+ " ms";
				JOptionPane.showMessageDialog(view, s);
				next.setEnabled(true);
			} else
				JOptionPane.showMessageDialog(this,
						"Please select input image file");
		}

		if (object == localColor) {
			if (path != null && !path.equals("")) {
				// double d=0.075,d1=0;
				double d = 0.095, d1 = 0;
				String[] argv = { "LocalColorHistogram", path };
				Date startTime = new Date();
				tester = new FeatureTester(argv, d, d1, view);
				outputList = tester.getOutputList();
				list = new ArrayList();
				for (int i = 0; i < outputList.size(); i++) {
					if (i == 7)
						break;
					list.add((String) outputList.get(i));
				}
				view.list = list;
				l3.setText("Output Image");
				System.out.println("-------------------------" + list);
				view.repaint();
				Date currentTime = new Date();
				long millisecond = currentTime.getTime() - startTime.getTime();
				String s = "Total Time used = " + String.valueOf(millisecond)
						+ " ms";
				JOptionPane.showMessageDialog(view, s);
				next.setEnabled(true);
			} else
				JOptionPane.showMessageDialog(this,
						"Please select input image file");
		}
		if (object == global) {
			if (path != null && !path.equals("")) {
				// double d=0.035,d1=0;
				double d = 0.07, d1 = 0;
				String[] argv = { "GlobalColorHistogram", path };
				Date startTime = new Date();
				tester = new FeatureTester(argv, d, d1, view);
				outputList = tester.getOutputList();
				list = new ArrayList();
				for (int i = 0; i < outputList.size(); i++) {
					if (i == 7)
						break;
					list.add((String) outputList.get(i));
				}
				view.list = list;
				l3.setText("Output Image");
				System.out.println("-------------------------" + list);
				view.repaint();
				Date currentTime = new Date();
				long millisecond = currentTime.getTime() - startTime.getTime();
				String s = "Total Time used = " + String.valueOf(millisecond)
						+ " ms";
				JOptionPane.showMessageDialog(view, s);
				next.setEnabled(true);

			} else
				JOptionPane.showMessageDialog(this,
						"Please select input image file");
		}

		

		if (object == next && !(list.size() == 0)) {
			int index = outputList.indexOf(list.get(list.size() - 1));
			if (index != outputList.size() - 1) {

				list = null;
				list = new ArrayList();
				for (int i = index + 1; i < outputList.size(); i++) {
					if (list.size() == 7)
						break;
					list.add((String) outputList.get(i));
					// System.out.println(i);
				}
				view.list = list;
				// System.out.println("+++++++++++++++++"+list+"+++++++++++++++++");
				view.repaint();
				repaint();
				previous.setEnabled(true);
			} else
				next.setEnabled(false);

		}

		if (object == previous && !(list.size() == 0)) {
			int index = outputList.indexOf(list.get(list.size() - 1));
			if (index > 0) {
				list = null;
				list = new ArrayList();
				for (int i = index - 1; i >= 0; i--) {
					if (list.size() == 7)
						break;
					list.add((String) outputList.get(i));
					// System.out.println(i);
				}
				view.list = list;
				// System.out.println("*****************"+list+"*****************");
				view.repaint();
				repaint();
				next.setEnabled(true);
			} else
				previous.setEnabled(false);

		}

	}

	public static void main(String args[]) {
		String hai="";
		new CBIR(hai);
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
