package dlgdraw;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class DlgDrawSquare extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtSide;
	private boolean isOk;
	
	public static void main(String[] args) {
		try {
			DlgDrawSquare dialog = new DlgDrawSquare();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public DlgDrawSquare() {
		setBounds(100, 100, 300, 200);
		setTitle("Draw square");
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
					validate(txtSide.getText());
					if (txtSide.getText().trim().equals("")) {
						getToolkit().beep();
						JOptionPane.showMessageDialog(null, "Field is empty! Please insert radius", "Error",
								JOptionPane.ERROR_MESSAGE, null);
						isOk = false;
						return;
					} else if (Integer.parseInt(txtSide.getText()) < 0) {
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

		JLabel lblSide = new JLabel("Side:");
		lblSide.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtSide = new JTextField();
		txtSide.setColumns(10);

		JLabel lblDrawCircle = new JLabel("Square");
		lblDrawCircle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlCenter.createSequentialGroup()
					.addContainerGap(42, Short.MAX_VALUE)
					.addComponent(lblSide)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDrawCircle)
						.addComponent(txtSide, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
					.addGap(18))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDrawCircle)
					.addGap(26)
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSide)
						.addComponent(txtSide, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		pnlCenter.setLayout(gl_pnlCenter);
	}

	public void validate(String side) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if (!side.matches(supp)) {
			throw new NumberFormatException();
		}
	}

	public JTextField getTxtSide() {
		return txtSide;
	}

	public void setTxtSide(JTextField txtSide) {
		this.txtSide = txtSide;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

}
