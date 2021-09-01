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
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DlgHexagonModify extends JDialog {


	private final JPanel contentPanel = new JPanel();
	private JButton btnModify;
	private JButton btnCancel;
	JButton btnEdgeColor;
	JButton btnInnerColor;
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtRadius;

	private Color edgeColor = new Color(0, 0, 0);
	private Color innerColor = new Color(255, 255, 255);
	private boolean isOk;

	public static void main(String[] args) {
		try {
			DlgHexagonModify dialog = new DlgHexagonModify();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgHexagonModify() {
		setBounds(100, 100, 420, 370);
		setTitle("Modify Hexagon");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblCenterXCoordinate = new JLabel("Center X Coordinate:");
		lblCenterXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblCenterYCoordinate = new JLabel("Center Y Coordinate:");
		lblCenterYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnEdgeColor = new JButton("Edge Color");
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeColor = JColorChooser.showDialog(null, "SELECT HEXAGON COLOR", edgeColor);
				if (edgeColor != null) {
					btnEdgeColor.setBackground(edgeColor);
				}
			}
		});
		btnEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnInnerColor = new JButton("Inner Color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "SELECT HEXAGON COLOR", innerColor);
				if (innerColor != null) {
					btnInnerColor.setBackground(innerColor);
				}
			}
		});
		btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtXCoordinate = new JTextField();
		txtXCoordinate.setColumns(10);

		txtYCoordinate = new JTextField();
		txtYCoordinate.setColumns(10);

		txtRadius = new JTextField();
		txtRadius.setColumns(10);

		JLabel lblHexagonModify = new JLabel("Hexagon Modify");
		lblHexagonModify.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(141).addComponent(lblHexagonModify)
						.addContainerGap(135, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						gl_contentPanel.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnInnerColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370,
												Short.MAX_VALUE)
										.addComponent(btnEdgeColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 370,
												Short.MAX_VALUE)
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblCenterXCoordinate)
														.addComponent(lblCenterYCoordinate).addComponent(lblRadius))
												.addPreferredGap(ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_contentPanel.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(txtXCoordinate, GroupLayout.DEFAULT_SIZE,
																		118, Short.MAX_VALUE))
														.addComponent(txtYCoordinate).addComponent(txtRadius))))
								.addGap(24)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap().addComponent(lblHexagonModify).addGap(40)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblCenterXCoordinate)
						.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblCenterYCoordinate)
						.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblRadius).addComponent(
						txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(btnEdgeColor).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnInnerColor).addGap(50)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModify = new JButton("Modify");
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							validation(txtXCoordinate.getText(), txtYCoordinate.getText(), txtRadius.getText());
							if (txtXCoordinate.getText().trim().equals("") || txtYCoordinate.getText().trim().equals("")
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
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
					gl_buttonPane.createParallelGroup(Alignment.LEADING).addGroup(gl_buttonPane.createSequentialGroup()
							.addGap(111).addComponent(btnModify).addGap(18).addComponent(btnCancel).addGap(115)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
							.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE).addComponent(btnModify)
									.addComponent(btnCancel))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	public void validation(String x, String y, String radius) {
		String exp2 = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if (x.matches("") || y.matches("") || radius.matches("")) {

		} else if (!x.matches(exp2) || !y.matches(exp2) || !radius.matches(exp2)) {
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

	public JTextField getTxtXCoordinate() {
		return txtXCoordinate;
	}

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
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
}
