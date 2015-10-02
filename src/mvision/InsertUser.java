package mvision;




import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import java.awt.*;
import java.awt.event.*;

public class InsertUser extends JFrame implements ActionListener{

	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel usernamelbl = null;
	private JTextField userName = null;
	
	private JLabel rolelbl = null;
	private JLabel passwordlbl = null;
	private JPasswordField password = null;
	private JLabel cpasswordlbl = null;
	private JPasswordField cpassword = null;
	private JButton submit = null;
	private JButton reset = null;
	private JComboBox role = null;
	Container content;
	//private JLabel emaillbl = null;
	//private JTextField email = null;
	/**
	 * This method initializes 
	 * 
	 */
	
	public InsertUser() {
		super();
		initialize();
		content = getContentPane();
		content.setBackground(Color.PINK);
		try{
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
        this.setSize(new java.awt.Dimension(600,500));
        this.setContentPane(getJPanel());
        this.setTitle("Add New User !");
		this.setVisible(true);	
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getJPanel1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			//emaillbl = new JLabel();
			//emaillbl.setBounds(new java.awt.Rectangle(31,341,181,30));
			//emaillbl.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			//emaillbl.setText("Email Address :");
			//emaillbl.setForeground(new java.awt.Color(200,118,20));
			cpasswordlbl = new JLabel();
			cpasswordlbl.setBounds(new java.awt.Rectangle(31,272,156,30));
			cpasswordlbl.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			cpasswordlbl.setText("Conform Password :");
			cpasswordlbl.setForeground(new java.awt.Color(200,118,20));
			passwordlbl = new JLabel();
			passwordlbl.setBounds(new java.awt.Rectangle(32,197,156,30));
			passwordlbl.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			passwordlbl.setText("Password :");
			passwordlbl.setForeground(new java.awt.Color(200,118,20));
			rolelbl = new JLabel();
			rolelbl.setBounds(new java.awt.Rectangle(32,121,156,30));
			rolelbl.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			rolelbl.setText("User Role :");
			rolelbl.setForeground(new java.awt.Color(200,118,20));
			
			/*password=new JPasswordField(15);
			password.setBounds(new java.awt.Rectangle(256,52,210,33));
			
			cpassword=new JPasswordField(15);
			cpassword.setBounds(new java.awt.Rectangle(213,272,205,25));*/
			
			reset = new JButton();
			reset.setBounds(new java.awt.Rectangle(345,340,91,27));
			reset.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			reset.setText("Reset");
			reset.setForeground(new java.awt.Color(200,118,20));
			reset.addActionListener(this);
			
			submit = new JButton();
			submit.setBounds(new java.awt.Rectangle(245,340,91,27));
			submit.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			submit.setText("Submit");
			submit.setForeground(new java.awt.Color(200,118,20));
			submit.addActionListener(this);
			
			usernamelbl = new JLabel();
			usernamelbl.setBounds(new java.awt.Rectangle(32,45,156,30));
			usernamelbl.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			usernamelbl.setText("User ID :");
			usernamelbl.setForeground(new java.awt.Color(200,118,20));
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setBounds(new java.awt.Rectangle(22,35,540,400));
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add New User !", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14), java.awt.Color.red));
			jPanel1.add(usernamelbl, null);
			jPanel1.add(getUserName(), null);
			jPanel1.add(rolelbl, null);
			//jPanel1.add(password,null);
			//jPanel1.add(cpassword,null);
			jPanel1.add(passwordlbl, null);
			jPanel1.add(getPassword(), null);
			jPanel1.add(cpasswordlbl, null);
			jPanel1.add(getCpassword(), null);
			jPanel1.add(submit, null);
			jPanel1.add(reset, null);
			jPanel1.add(getRole(), null);
			content = getContentPane();
			content.setBackground(Color.PINK);
			//jPanel1.add(emaillbl, null);
			//jPanel1.add(getEmail(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes userName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUserName() {
		if (userName == null) {
			userName = new JTextField();
			userName.setBounds(new java.awt.Rectangle(256,45,210,33));
		}
		return userName;
	}
	
	/**
	 * This method initializes password	
	 * 	/
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPassword() {
		if (password == null) {
			password = new JPasswordField();
			password.setBounds(new java.awt.Rectangle(258,196,210,33));
		}
		return password;
	}

	/**
	 * This method initializes cpassword	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getCpassword() {
		if (cpassword == null) {
			cpassword = new JPasswordField();
			cpassword.setBounds(new java.awt.Rectangle(258,271,210,33));
		}
		return cpassword;
	}

	/**
	 * This method initializes submit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	private JComboBox getRole() {
		if (role == null) {
			role = new JComboBox();
			role.setBounds(new java.awt.Rectangle(258,126,210,33));
			role.addItem("Select Role");
			role.addItem("a");
			role.addItem("m");
			
		}
		return role;
	}
	
	
	
	
	
	
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Submit"))
		{
			String userID=userName.getText();
			String role1=role.getSelectedItem().toString();
			String Password=password.getText();
			String Cpassword=cpassword.getText();
			
			System.out.println("actionPerformed()");
			System.out.println("xxxxx"+userID);
			System.out.println("yyyyy"+role1);
			System.out.println("zzzzz"+Password);
			System.out.println("aaaaa"+Cpassword);
			
			// TODO Auto-generated Event stub actionPerformed()
			UserDAO UD = new UserDAO();
			if(!userID.equals("")&&!role1.equals("Select Role")&&!Password.equals("")&&!Cpassword.equals(""))
			{
				if(Password.equals(Cpassword))
				{
					
					System.out.println("actionPerformed()");	//String[] values={userName.getText(),role.getSelectedItem().toString(),password.getText()};
					UD.aduser(userID,Password,role1);
					JOptionPane.showMessageDialog(null,"User Added successfully");
						password.setText("");
						cpassword.setText("");
						userName.setText("");
						role.setSelectedIndex(0);
						userName.requestFocus();
					}
					else
					{
					JOptionPane.showMessageDialog(null,"Mismatch Password");
					userName.setText("");
					userName.requestFocus();

					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Enter All the Fields");
				}
			return;
			}
			
		else
		
	{
		if(ae.getActionCommand().equals("Reset"))
		{
			System.out.println("actionPerformed()"); 
			userName.setText("");
			role.setSelectedIndex(0);
			password.setText("");
			cpassword.setText("");
					
		}

			
		return;
	}
		
	}
}
	
