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
public class DlgCircleModify extends JDialog {

	private final JPanel pnlCenter = new JPanel();
	private JButton btnModify;
	private JButton cancelButton;
	private JButton btnEdgeColor;
	private JButton btnInnerColor;
	private JLabel lblCenterOfX;
	private JLabel lblCenterYCoordinate;
	private JLabel lblRadius;
	private JTextField txtXcoordinate;
	private JTextField txtYcoordinate;
	private JTextField txtRadius;

	private Color edgeColor = new Color(0, 0, 0);
	private Color innerColor = new Color(255, 255, 255);
	private boolean isOk;

	public static void main(String[] args) {
		try {
			DlgCircleModify dialog = new DlgCircleModify();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgCircleModify() {
		setBounds(100, 100, 400, 370);
		setTitle("Modify Circle");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);

		JLabel lblCircleModify = new JLabel("Circle Modify");
		lblCircleModify.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCenterOfX = new JLabel("Center X coordinate:");
		lblCenterOfX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCenterYCoordinate = new JLabel("Center Y coordinate:");
		lblCenterYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRadius = new JLabel("Radius:");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtXcoordinate = new JTextField();
		txtXcoordinate.setColumns(10);

		txtYcoordinate = new JTextField();
		txtYcoordinate.setColumns(10);

		txtRadius = new JTextField();
		txtRadius.setColumns(10);

		btnEdgeColor = new JButton("Edge Color");
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeColor = JColorChooser.showDialog(null, "SELECT EDGE CIRCLE COLOR", edgeColor);
				if (edgeColor != null) {
					btnEdgeColor.setBackground(edgeColor);
				}

			}
		});
		btnEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "SELECT INNER CIRCLE COLOR", innerColor);
				if (innerColor != null) {
					btnInnerColor.setBackground(innerColor);
				}
			}
		});
		btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCenter
				.createSequentialGroup()
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlCenter.createSequentialGroup().addGap(142).addComponent(lblCircleModify))
						.addGroup(gl_pnlCenter.createSequentialGroup().addContainerGap()
								.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING).addComponent(lblCenterOfX)
										.addComponent(lblCenterYCoordinate).addComponent(lblRadius))
								.addGap(106)
								.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtRadius).addComponent(txtYcoordinate)
										.addComponent(txtXcoordinate, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
						.addGroup(gl_pnlCenter.createSequentialGroup().addContainerGap()
								.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
										.addComponent(btnEdgeColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnInnerColor, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))))
				.addContainerGap(10, Short.MAX_VALUE)));
		gl_pnlCenter.setVerticalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCenter
				.createSequentialGroup().addContainerGap().addComponent(lblCircleModify).addGap(18)
				.addGroup(gl_pnlCenter
						.createParallelGroup(Alignment.BASELINE).addComponent(lblCenterOfX).addComponent(txtXcoordinate,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE).addComponent(lblCenterYCoordinate)
						.addComponent(txtYcoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE).addComponent(lblRadius).addComponent(
						txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(30).addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
				.addGap(26).addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
				.addGap(23)));
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnModify = new JButton("Modify");
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							validation(txtXcoordinate.getText(), txtYcoordinate.getText(), txtRadius.getText());
							if (txtXcoordinate.getText().trim().equals("") || txtYcoordinate.getText().trim().equals("")
									|| txtRadius.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Fields are empty!", "ERROR",
										JOptionPane.ERROR_MESSAGE, null);
								return;
							} else if (Integer.parseInt(txtRadius.getText()) < 1) {
								JOptionPane.showMessageDialog(null, "Radius can't be less than 1!", "ERROR",
										JOptionPane.ERROR_MESSAGE, null);
								return;
							} else {
								isOk = true;
								setVisible(false);
							}
						} catch (NumberFormatException exc) {
							JOptionPane.showMessageDialog(null, "Invalid data inserted!", "ERROR",
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
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						isOk = false;
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
			gl_pnlSouth.setHorizontalGroup(
					gl_pnlSouth.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlSouth.createSequentialGroup()
							.addGap(126).addComponent(btnModify).addGap(27).addComponent(cancelButton).addGap(91)));
			gl_pnlSouth.setVerticalGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlSouth.createSequentialGroup()
							.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE).addComponent(cancelButton)
									.addComponent(btnModify))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}

	public void validation(String x, String y, String radius) {
		String exp2 = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if (x.matches("") || y.matches("") || radius.matches("")) {

		} else if (!x.matches(exp2) || !y.matches(exp2) || !radius.matches(exp2)) {
			throw new NumberFormatException();
		}
	}

	public JButton getBtnChooseEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnChooseEdgeColor(JButton btnChooseEdgeColor) {
		this.btnEdgeColor = btnChooseEdgeColor;
	}

	public JButton getBtnChooseInnerColor() {
		return btnInnerColor;
	}

	public void setBtnChooseInnerColor(JButton btnChooseInnerColor) {
		this.btnInnerColor = btnChooseInnerColor;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public JTextField getTxtXcoordinate() {
		return txtXcoordinate;
	}

	public void setTxtXcoordinate(JTextField txtXcoordinate) {
		this.txtXcoordinate = txtXcoordinate;
	}

	public JTextField getTxtYcoordinate() {
		return txtYcoordinate;
	}

	public void setTxtYcoordinate(JTextField txtYcoordinate) {
		this.txtYcoordinate = txtYcoordinate;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
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
}
