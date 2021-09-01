package modifyDlg;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DlgRectangleModify extends JDialog {

	private final JPanel pnlCenter = new JPanel();
	private JButton btnModify;
	private JButton btnCancel;
	private JLabel lblRectangleModify;
	private JLabel lblUpperLeftPoint;
	private JLabel lblUpperLeftPoint_1;
	private JLabel lblWidth;
	private JLabel lblHeight;
	private JButton btnEdgeColor;
	private JButton btnInnerColor;
	private JTextField txtUpperLeftX;
	private JTextField txtUpperLeftY;
	private JTextField txtWidth;
	private JTextField txtHeight;

	private Color edgeColor = new Color(0, 0, 0);
	private Color innerColor = new Color(255, 255, 255);
	private boolean isOk;

	public static void main(String[] args) {
		try {
			DlgRectangleModify dialog = new DlgRectangleModify();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgRectangleModify() {
		setBounds(100, 100, 450, 470);
		setTitle("Modify Rectangle");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		lblRectangleModify = new JLabel("Rectangle Modify");
		lblRectangleModify.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUpperLeftPoint = new JLabel("Upper left point X coordinate:");
		lblUpperLeftPoint.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUpperLeftPoint_1 = new JLabel("Upper left point Y coordinate:");
		lblUpperLeftPoint_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWidth = new JLabel("Width:");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHeight = new JLabel("Height:");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnEdgeColor = new JButton("Edge color");
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeColor = JColorChooser.showDialog(null, "Choose RECTANGLE color", edgeColor);
				if (edgeColor != null) {
					btnEdgeColor.setBackground(edgeColor);
				}
			}
		});
		btnEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnInnerColor = new JButton("Inner color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose RECTANGLE color", innerColor);
				if (innerColor != null) {
					btnInnerColor.setBackground(innerColor);
				}
			}
		});
		btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtUpperLeftX = new JTextField();
		txtUpperLeftX.setColumns(10);

		txtUpperLeftY = new JTextField();
		txtUpperLeftY.setColumns(10);

		txtWidth = new JTextField();
		txtWidth.setColumns(10);

		txtHeight = new JTextField();
		txtHeight.setColumns(10);
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup().addContainerGap(161, Short.MAX_VALUE)
						.addComponent(lblRectangleModify).addGap(138))
				.addGroup(gl_pnlCenter.createSequentialGroup().addGap(26)
						.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnInnerColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnEdgeColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_pnlCenter.createSequentialGroup()
										.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
												.addComponent(lblUpperLeftPoint).addComponent(lblUpperLeftPoint_1)
												.addComponent(lblWidth).addComponent(lblHeight))
										.addGap(47)
										.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
												.addComponent(txtHeight).addComponent(txtWidth)
												.addComponent(txtUpperLeftY).addComponent(txtUpperLeftX,
														GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_pnlCenter.setVerticalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCenter
				.createSequentialGroup().addContainerGap().addComponent(lblRectangleModify).addGap(28)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE).addComponent(lblUpperLeftPoint)
						.addComponent(txtUpperLeftX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(42)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING).addComponent(lblUpperLeftPoint_1)
						.addComponent(txtUpperLeftY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(39)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE).addComponent(lblWidth).addComponent(
						txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(34)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING).addComponent(lblHeight).addComponent(
						txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(28).addComponent(btnEdgeColor).addGap(19).addComponent(btnInnerColor)
				.addContainerGap(23, Short.MAX_VALUE)));
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnModify = new JButton("Modify");
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							validation(txtUpperLeftX.getText(), txtUpperLeftY.getText(), txtWidth.getText(),
									txtHeight.getText());
							if (txtUpperLeftX.getText().trim().equals("") || txtUpperLeftY.getText().trim().equals("")
									|| txtWidth.getText().trim().equals("") || txtHeight.getText().trim().equals("")) {
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Fields are empty! Insert values!", "ERROR",
										JOptionPane.ERROR_MESSAGE, null);
								return;
							} else if (Integer.parseInt(txtWidth.getText()) < 0
									|| Integer.parseInt(txtHeight.getText()) < 0) {
								getToolkit().beep();
								JOptionPane.showMessageDialog(null, "Width/Height must be greater than 0!", "ERROR",
										JOptionPane.ERROR_MESSAGE, null);
								return;
							} else {
								isOk = true;
								setVisible(false);
							}
						} catch (NumberFormatException exc) {
							getToolkit().beep();
							JOptionPane.showMessageDialog(null, "Invalid data, please check and correct data!", "ERROR",
									JOptionPane.ERROR_MESSAGE, null);
							return;
						}
					}
				});
				btnModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnModify.setActionCommand("OK");
				getRootPane().setDefaultButton(btnModify);
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
							.addGap(134).addComponent(btnModify).addGap(27).addComponent(btnCancel).addGap(161)));
			gl_pnlSouth
					.setVerticalGroup(
							gl_pnlSouth.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
									gl_pnlSouth.createSequentialGroup()
											.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
													.addComponent(btnModify).addComponent(btnCancel))
											.addContainerGap()));
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}

	public void validation(String x, String y, String width, String height) {
		String supp1 = "^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$";
		if (x.matches("") || y.matches("") || width.matches("") || height.matches("")) {

		} else if (!x.matches(supp1) || !y.matches(supp1) || !width.matches(supp1) || !height.matches(supp1)) {
			throw new NumberFormatException();
		}
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JTextField getTxtUpperLeftX() {
		return txtUpperLeftX;
	}

	public void setTxtUpperLeftX(JTextField txtUpperLeftX) {
		this.txtUpperLeftX = txtUpperLeftX;
	}

	public JTextField getTxtUpperLeftY() {
		return txtUpperLeftY;
	}

	public void setTxtUpperLeftY(JTextField txtUpperLeftY) {
		this.txtUpperLeftY = txtUpperLeftY;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public boolean isOk() {
		return isOk;
	}

}
