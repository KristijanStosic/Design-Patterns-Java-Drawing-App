package dlgdraw;

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
import javax.swing.border.EtchedBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		pnlSouth.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
		getRootPane().setDefaultButton(btnDraw);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isOk = false;
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
		gl_pnlSouth.setHorizontalGroup(
			gl_pnlSouth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSouth.createSequentialGroup()
					.addGap(111)
					.addComponent(btnDraw)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancel)
					.addGap(19))
		);
		gl_pnlSouth.setVerticalGroup(
			gl_pnlSouth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSouth.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnDraw)))
		);
		pnlSouth.setLayout(gl_pnlSouth);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);

		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtRadius = new JTextField();
		txtRadius.setColumns(10);

		JLabel lblDrawCircle = new JLabel("Circle");
		lblDrawCircle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlCenter.createSequentialGroup()
					.addContainerGap(42, Short.MAX_VALUE)
					.addComponent(lblRadius)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDrawCircle)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
					.addGap(18))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDrawCircle)
					.addGap(26)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE))
		);
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
