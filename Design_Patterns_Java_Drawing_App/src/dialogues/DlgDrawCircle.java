package dialogues;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DlgDrawCircle extends JDialog {

	private JTextField txtRadius;
	private boolean isOk;

	public static void main(String[] args) {
		try {
			DlgDrawCircle dialog = new DlgDrawCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgDrawCircle() {
		setBounds(100, 100, 300, 200);
		setTitle("Draw circle");
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel pnlSouth = new JPanel();
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);

		JButton btnDraw = new JButton("Draw");
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
		}

		);
		btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlSouth.add(btnDraw);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isOk = false;
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlSouth.add(btnCancel);

		JPanel pnlCenter = new JPanel();
		getContentPane().add(pnlCenter, BorderLayout.CENTER);

		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtRadius = new JTextField();
		txtRadius.setColumns(10);

		JLabel lblDrawCircle = new JLabel("Circle");
		lblDrawCircle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter
				.setHorizontalGroup(
						gl_pnlCenter.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlCenter.createSequentialGroup().addContainerGap(52, Short.MAX_VALUE)
										.addComponent(lblRadius).addGap(46)
										.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE)
										.addGap(36))
								.addGroup(Alignment.LEADING, gl_pnlCenter.createSequentialGroup().addGap(105)
										.addComponent(lblDrawCircle).addContainerGap(147, Short.MAX_VALUE)));
		gl_pnlCenter.setVerticalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup().addContainerGap().addComponent(lblDrawCircle).addGap(26)
						.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRadius))
						.addContainerGap(54, Short.MAX_VALUE)));
		pnlCenter.setLayout(gl_pnlCenter);
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
