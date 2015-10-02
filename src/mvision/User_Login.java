package mvision;



import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.color.*;
import java.awt.Rectangle;

public class User_Login extends JFrame{

	
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JPanel loginpanel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	
	private JLabel userNamelabel = null;
	private JTextField userName = null;
	private JLabel passwordlabel = null;
	private JButton submit = null;
	private JButton reset = null;
	private JPasswordField password = null;
	private JLabel errorlabel = null;

	/**
	 * This method initializes 
	 * 
	 */
	public User_Login() {
		super();
		initialize();
		try{
			System.out.println("Conditional Shortest Path Routing In Delay Tolerant Networks");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new java.awt.Dimension(750,600));
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getJPanel());
        this.setTitle("Robust Content Based Image Retrieval System Login Page");
        
			
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(269, 28, 800, 27));
			jLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 23));
			jLabel.setText("M-Vision Sleam");
			jLabel.setForeground(new java.awt.Color(150,57,120));
			
			
			  
			
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, null);
		
			
			jPanel.add(getLoginpanel(), null);
			jPanel.setBackground(Color.LIGHT_GRAY);
		}
		return jPanel;
	}

	/**
	 * This method initializes loginpanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLoginpanel() {
		if (loginpanel == null) {
			//loginpanel.setBounds(new java.awt.Rectangle(120,190));
			errorlabel = new JLabel();
			errorlabel.setBounds(new java.awt.Rectangle(125,240,345,33));
			errorlabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			errorlabel.setForeground(new java.awt.Color(249,57,4));
			errorlabel.setText("");
			passwordlabel = new JLabel();
			passwordlabel.setBounds(new java.awt.Rectangle(76,105,103,27));
			passwordlabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			passwordlabel.setText("User Pass :");
			passwordlabel.setForeground(java.awt.Color.blue);
			
			
			userNamelabel = new JLabel();
			userNamelabel.setBounds(new java.awt.Rectangle(74,49,106,26));
			userNamelabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			userNamelabel.setForeground(java.awt.Color.blue);
			userNamelabel.setText("User ID :");
			loginpanel = new JPanel();
			loginpanel.setLayout(null);
			loginpanel.setBounds(new java.awt.Rectangle(125,173,473,278));
			loginpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Login", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18), new java.awt.Color(150,57,120)));
			//loginpanel.setForeground(new java.awt.Color(150,57,4));
			
			
			loginpanel.add(userNamelabel, null);
			loginpanel.add(getUserName(), null);
			loginpanel.add(passwordlabel, null);
			
			loginpanel.add(getSubmit(), null);
			loginpanel.add(getReset(), null);
			loginpanel.add(getPassword(), null);

			loginpanel.add(errorlabel, null);
		}
		return loginpanel;
	}

	/**
	 * This method initializes userName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUserName() {
		if (userName == null) {
			userName = new JTextField();
			userName.setBounds(new java.awt.Rectangle(213,52,205,25));
		}
		return userName;
	}

	/**
	 * This method initializes sunmit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmit() {
		if (submit == null) {
			submit = new JButton();
			submit.setBounds(new java.awt.Rectangle(213,215,86,26));
			submit.setText("Login");
			submit.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
			submit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					UserDAO UD = new UserDAO();
					if(!userName.getText().equals("")&&!password.getText().equals(""))
					{
						
						try
						{
						if(UD.verifyUser(userName.getText(),password.getText()))
						{
							if(UD.getRole().equals("a"))
							{
								
								int flag=1;
								if(flag==1)
								{
									System.out.println("run...");
							    //new Admin(userName.getText()).setVisible(true);
							    new CBIR(userName.getText()).setVisible(true);
								}
								else
								{
									System.out.println("Hardware Problem !");
								}
							    dispose();
							}
							else if(UD.getRole().equals("m"))
							{
								int flag=1;
								if(flag==1)
								{
									System.out.println("run...");
									//new User_Home(userName.getText()).setVisible(true);
									new CBIR2(userName.getText()).setVisible(true);
								}
								else
								{
									System.out.println("Hardware Problem !");
								}
																
								dispose();
							}

						}
						else
						{
							password.setText("");
							errorlabel.setText("Invalid User Id or Password");
						}
						}
						catch(Exception exception)
						{
							exception.printStackTrace();
						}
					
					}
					else
						errorlabel.setText("Enter Your User Id And Password");
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				
				}
			});
		}
		return submit;
	}

	/**
	 * This method initializes reset	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReset() {
		if (reset == null) {
			reset = new JButton();
			reset.setBounds(new java.awt.Rectangle(331,215,68,26));
			reset.setText("Reset");
			reset.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
			reset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				    userName.setText("");
				    password.setText("");
				}
			});
		}
		return reset;
	}

	/**
	 * This method initializes password	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPassword() {
		if (password == null) {
			password = new JPasswordField();
			password.setBounds(new java.awt.Rectangle(213,106,205,24));
		}
		return password;
	}
	public static void main(String args[])
	{
		
		System.out.println("Robust Content Based Image Retrieval System Login Page");
		new User_Login().setVisible(true);
		
	}

}
