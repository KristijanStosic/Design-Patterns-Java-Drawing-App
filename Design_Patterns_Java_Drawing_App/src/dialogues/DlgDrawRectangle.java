package dialogues;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DlgDrawRectangle extends JDialog {

	private final JPanel pnlNorth = new JPanel();
	private boolean isOk;
	private JButton btnDraw;
	private JButton btnCancel;
	private JLabel lblRectangle;
	private JLabel lblWidth;
	private JLabel lblHeight;
	private JTextField txtWidthRectangle;
	private JTextField txtHeightRectangle;

	public static void main(String[] args) {
		try {
			DlgDrawRectangle dialog = new DlgDrawRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgDrawRectangle() {
		setBounds(100, 100, 320, 250);
		setTitle("Draw rectangle");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlNorth.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlNorth, BorderLayout.CENTER);
		lblRectangle = new JLabel("Rectangle");
		lblRectangle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWidth = new JLabel("Width:");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHeight = new JLabel("Height:");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtWidthRectangle = new JTextField();
		txtWidthRectangle.setColumns(10);

		txtHeightRectangle = new JTextField();
		txtHeightRectangle.setColumns(10);
		GroupLayout gl_pnlNorth = new GroupLayout(pnlNorth);
		gl_pnlNorth.setHorizontalGroup(gl_pnlNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNorth.createSequentialGroup().addGap(102).addComponent(lblRectangle)
						.addContainerGap(126, Short.MAX_VALUE))
				.addGroup(gl_pnlNorth.createSequentialGroup().addGap(32)
						.addGroup(gl_pnlNorth.createParallelGroup(Alignment.LEADING).addComponent(lblWidth)
								.addComponent(lblHeight))
						.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
						.addGroup(gl_pnlNorth.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtHeightRectangle)
								.addComponent(txtWidthRectangle, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
						.addContainerGap(39, Short.MAX_VALUE)));
		gl_pnlNorth.setVerticalGroup(gl_pnlNorth.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlNorth
				.createSequentialGroup().addContainerGap().addComponent(lblRectangle)
				.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
				.addGroup(gl_pnlNorth
						.createParallelGroup(Alignment.BASELINE).addComponent(lblWidth).addComponent(txtWidthRectangle,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(33)
				.addGroup(gl_pnlNorth.createParallelGroup(Alignment.BASELINE).addComponent(lblHeight).addComponent(
						txtHeightRectangle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(23)));
		pnlNorth.setLayout(gl_pnlNorth);
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnDraw = new JButton("Draw");
				btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnDraw.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							validate(txtWidthRectangle.getText(), txtHeightRectangle.getText());
							if (txtWidthRectangle.getText().trim().equals("")
									|| txtHeightRectangle.getText().trim().equals("")) {
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Some fields are empty!", "Error",
										JOptionPane.ERROR_MESSAGE, null);
								isOk = false;
								return;
							} else if (Integer.parseInt(txtWidthRectangle.getText()) < 1
									|| Integer.parseInt(txtHeightRectangle.getText()) < 1) {
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Width or height are less than 1! Try again!",
										"Error", JOptionPane.ERROR_MESSAGE, null);
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
				btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isOk = false;
						dispose();
					}
				});
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnCancel.setActionCommand("Cancel");
			}
			GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
			gl_pnlSouth.setHorizontalGroup(
					gl_pnlSouth.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlSouth.createSequentialGroup()
							.addGap(66).addComponent(btnDraw).addGap(38).addComponent(btnCancel).addGap(78)));
			gl_pnlSouth
					.setVerticalGroup(
							gl_pnlSouth.createParallelGroup(Alignment.TRAILING)
									.addGroup(
											gl_pnlSouth.createSequentialGroup()
													.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
															.addComponent(btnCancel).addComponent(btnDraw))
													.addContainerGap()));
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}

	public void validate(String width, String height) {
		String supp = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if (!width.matches(supp) || !height.matches(supp)) {
			throw new NumberFormatException();
		}
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public JTextField getTxtWidthRectangle() {
		return txtWidthRectangle;
	}

	public void setTxtWidthRectangle(JTextField txtWidthRectangle) {
		this.txtWidthRectangle = txtWidthRectangle;
	}

	public JTextField getTxtHeightRectangle() {
		return txtHeightRectangle;
	}

	public void setTxtHeightRectangle(JTextField txtHeightRectangle) {
		this.txtHeightRectangle = txtHeightRectangle;
	}

}
