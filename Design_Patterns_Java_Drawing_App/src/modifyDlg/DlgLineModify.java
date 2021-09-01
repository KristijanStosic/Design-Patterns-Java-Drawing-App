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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class DlgLineModify extends JDialog {

	private final JPanel pnlCenter = new JPanel();
	private JButton btnModify;
	private JButton cancelButton;
	private JButton btnEdgeColor;
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JTextField txtEndPointX;
	private JTextField txtEndPointY;

	private Color edgeColor = new Color(0, 0, 0);
	private boolean isOk;

	public static void main(String[] args) {
		try {
			DlgLineModify dialog = new DlgLineModify();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgLineModify() {
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Modify Line");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		JLabel lblLineModify = new JLabel("Line Modify");
		lblLineModify.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblStartPointX = new JLabel("Start point X coordinate:");
		lblStartPointX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblStartPointY = new JLabel("Start point Y coordinate:");
		lblStartPointY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblEndPointX = new JLabel("End point X coordiante:");
		lblEndPointX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JLabel lblEndPointY = new JLabel("End point Y coordinate:");
		lblEndPointY.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnEdgeColor = new JButton("Color");
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeColor = JColorChooser.showDialog(null, "Choose LINE color", edgeColor);
				if (edgeColor != null) {
					btnEdgeColor.setBackground(edgeColor);
				}

			}
		});
		btnEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtStartPointX = new JTextField();
		txtStartPointX.setColumns(10);

		txtStartPointY = new JTextField();
		txtStartPointY.setColumns(10);

		txtEndPointX = new JTextField();
		txtEndPointX.setColumns(10);

		txtEndPointY = new JTextField();
		txtEndPointY.setColumns(10);
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup().addGap(10)
						.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCenter.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblStartPointX))
								.addGroup(gl_pnlCenter.createSequentialGroup().addGap(169).addComponent(lblLineModify))
								.addGroup(gl_pnlCenter.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblStartPointY))
								.addGroup(gl_pnlCenter.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblEndPointX))
								.addGroup(gl_pnlCenter.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblEndPointY)))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(
								gl_pnlCenter.createParallelGroup(Alignment.LEADING)
										.addComponent(txtEndPointX, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(gl_pnlCenter.createSequentialGroup()
														.addComponent(txtStartPointY, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGroup(gl_pnlCenter.createSequentialGroup()
														.addComponent(txtStartPointX, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(65)))
										.addComponent(txtEndPointY, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(260))
				.addGroup(
						gl_pnlCenter
								.createSequentialGroup().addGap(68).addComponent(btnEdgeColor,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(349)));
		gl_pnlCenter.setVerticalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCenter
				.createSequentialGroup().addContainerGap().addComponent(lblLineModify).addGap(18)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE).addComponent(lblStartPointX)
						.addComponent(txtStartPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE).addComponent(lblStartPointY)
						.addComponent(txtStartPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE).addComponent(lblEndPointX).addComponent(
						txtEndPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE).addComponent(lblEndPointY).addComponent(
						txtEndPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE).addComponent(btnEdgeColor)
				.addGap(20)));
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnModify = new JButton("Modify");
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (txtStartPointX.getText().trim().equals("") || txtStartPointY.getText().trim().equals("")
									|| txtEndPointX.getText().trim().equals("")
									|| txtEndPointY.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "All fields must be filled in!", "Error",
										JOptionPane.ERROR_MESSAGE, null);
								return;
							} else {
								isOk = true;
								setVisible(false);
							}
						} catch (NumberFormatException exc) {
							JOptionPane.showMessageDialog(null, "Incorrect data type inserted, please insert again!",
									"Error", JOptionPane.ERROR_MESSAGE, null);
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
					public void actionPerformed(ActionEvent e) {
						isOk = false;
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				cancelButton.setActionCommand("btnCancel");
			}
			GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
			gl_pnlSouth.setHorizontalGroup(
					gl_pnlSouth.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlSouth.createSequentialGroup()
							.addGap(146).addComponent(btnModify).addGap(27).addComponent(cancelButton).addGap(159)));
			gl_pnlSouth
					.setVerticalGroup(
							gl_pnlSouth.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
									gl_pnlSouth.createSequentialGroup()
											.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
													.addComponent(btnModify).addComponent(cancelButton))
											.addContainerGap()));
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}

	public boolean isOk() {
		return isOk;
	}

	public JTextField getTxtStartPointX() {
		return txtStartPointX;
	}

	public void setTxtStartPointX(JTextField txtStartPointX) {
		this.txtStartPointX = txtStartPointX;
	}

	public JTextField getTxtStartPointY() {
		return txtStartPointY;
	}

	public void setTxtStartPointY(JTextField txtStartPointY) {
		this.txtStartPointY = txtStartPointY;
	}

	public JTextField getTxtEndPointX() {
		return txtEndPointX;
	}

	public void setTxtEndPointX(JTextField txtEndPointX) {
		this.txtEndPointX = txtEndPointX;
	}

	public JTextField getTxtEndPointY() {
		return txtEndPointY;
	}

	public void setTxtEndPointY(JTextField txtEndPointY) {
		this.txtEndPointY = txtEndPointY;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}


	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

}
