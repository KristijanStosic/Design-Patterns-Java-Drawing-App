package dialogues;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class DlgDrawHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRadius;
	private boolean isOk;
	private JButton btnDraw;
	private JButton cancelButton;

	public static void main(String[] args) {
		try {
			DlgDrawHexagon dialog = new DlgDrawHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgDrawHexagon() {
		setBounds(100, 100, 300, 200);
		setTitle("Draw hexagon");
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		JLabel lblDrawHexagon = new JLabel("Hexagon");
		lblDrawHexagon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(
						gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(36).addComponent(lblRadius)
										.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
										.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 110,
												GroupLayout.PREFERRED_SIZE)
										.addGap(23))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(76)
										.addComponent(lblDrawHexagon).addContainerGap(127, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap().addComponent(lblDrawHexagon).addGap(31)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblRadius).addComponent(
						txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(55, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDraw = new JButton("Draw");
				btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnDraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							validate(txtRadius.getText());
							if (txtRadius.getText().trim().equals("")) {
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Field is empty! Please insert radius", "Error",
										JOptionPane.ERROR_MESSAGE, null);
								isOk = false;
								return;
							} else if (Integer.parseInt(txtRadius.getText()) < 0) {
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Radius can't be negative number!", "Error",
										JOptionPane.ERROR_MESSAGE, null);
								isOk = false;
								return;
							} else {
								isOk = true;
								dispose();
							}
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Invalid data type inserted!", "Error",
									JOptionPane.ERROR_MESSAGE, null);
							isOk = false;
							return;
						}
					}
				});
				btnDraw.setActionCommand("OK");
				getRootPane().setDefaultButton(btnDraw);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isOk = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(74).addComponent(btnDraw)
							.addPreferredGap(ComponentPlacement.RELATED).addComponent(cancelButton).addGap(92)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
							.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE).addComponent(btnDraw)
									.addComponent(cancelButton))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	public void validate(String radius) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if (!radius.matches(supp)) {
			throw new NumberFormatException();
		}
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
}
