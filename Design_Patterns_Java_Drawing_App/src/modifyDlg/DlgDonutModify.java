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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class DlgDonutModify extends JDialog {

	private final JPanel pnlCenter = new JPanel();
	private JButton btnModify;
	private JButton cancelButton;
	private JLabel lblModifyDonut;
	private JLabel lblCenterXCoordinate;
	private JLabel lblCenterYCoordinate;
	private JLabel lblRadius;
	private JLabel lblInnerRadius;
	private JButton btnEdgeColor;
	private JButton btnInnerColor;
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;

	private Color edgeColor = new Color(0, 0, 0);
	private Color innerColor = new Color(255, 255, 255);
	private boolean isOk;

	public static void main(String[] args) {
		try {
			DlgDonutModify dialog = new DlgDonutModify();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgDonutModify() {
		setBounds(100, 100, 450, 375);
		setTitle("Modify Donut");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		pnlCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		lblModifyDonut = new JLabel("Donut Modify ");
		lblModifyDonut.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCenterXCoordinate = new JLabel("Center X coordinate:");
		lblCenterXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCenterYCoordinate = new JLabel("Center Y coordinate:");
		lblCenterYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRadius = new JLabel("Radius:");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInnerRadius = new JLabel("Inner radius:");
		lblInnerRadius.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnEdgeColor = new JButton("Edge color");
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edgeColor = JColorChooser.showDialog(null, "Choose Donut color", edgeColor);
				if (edgeColor != null) {
					btnEdgeColor.setBackground(edgeColor);
				}
			}
		});
		btnEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnInnerColor = new JButton("Inner color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose Donut inner color", innerColor);
				if (innerColor != null) {
					btnInnerColor.setBackground(innerColor);
				}
			}
		});
		btnInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		txtCenterX = new JTextField();
		txtCenterX.setColumns(10);

		txtCenterY = new JTextField();
		txtCenterY.setColumns(10);

		txtRadius = new JTextField();
		txtRadius.setColumns(10);

		txtInnerRadius = new JTextField();
		txtInnerRadius.setColumns(10);
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup().addGap(154).addComponent(lblModifyDonut))
				.addGroup(gl_pnlCenter.createSequentialGroup().addGap(20).addComponent(lblCenterXCoordinate).addGap(117)
						.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlCenter.createSequentialGroup().addGap(20).addComponent(lblCenterYCoordinate).addGap(116)
						.addComponent(txtCenterY, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_pnlCenter.createSequentialGroup().addGap(20).addComponent(lblRadius)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING,
								gl_pnlCenter.createSequentialGroup().addContainerGap().addComponent(btnInnerColor,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlCenter.createSequentialGroup().addContainerGap().addComponent(btnEdgeColor,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlCenter.createSequentialGroup().addGap(20).addComponent(lblInnerRadius).addGap(168)
										.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE))));
		gl_pnlCenter.setVerticalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCenter
				.createSequentialGroup().addGap(11).addComponent(lblModifyDonut).addGap(18)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING).addComponent(lblCenterXCoordinate)
						.addGroup(gl_pnlCenter.createSequentialGroup().addGap(1).addComponent(txtCenterX,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING).addComponent(lblCenterYCoordinate)
						.addGroup(gl_pnlCenter.createSequentialGroup().addGap(1).addComponent(txtCenterY,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING).addComponent(lblRadius).addComponent(
						txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(12)
				.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCenter.createSequentialGroup().addGap(7).addComponent(lblInnerRadius))
						.addComponent(txtInnerRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(btnEdgeColor).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnInnerColor).addGap(8)));
		pnlCenter.setLayout(gl_pnlCenter);
		{
			JPanel pnlSouth = new JPanel();
			getContentPane().add(pnlSouth, BorderLayout.SOUTH);
			{
				btnModify = new JButton("Modify");
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							validation(txtCenterX.getText(), txtCenterY.getText(), txtRadius.getText(),
									txtInnerRadius.getText());
							if (txtCenterX.getText().trim().equals("") || txtCenterY.getText().trim().equals("")
									|| txtRadius.getText().trim().equals("")
									|| txtInnerRadius.getText().trim().equals("")) {
								JOptionPane.showMessageDialog(null, "Fill in all the fields!", "ERROR",
										JOptionPane.ERROR_MESSAGE, null);
								return;
							} else if (Integer.parseInt(txtRadius.getText()) < 0
									|| Integer.parseInt(txtInnerRadius.getText()) < 0) {
								JOptionPane.showMessageDialog(null, "Radius(outer,inner) can't be less than 1!",
										"ERROR", JOptionPane.ERROR_MESSAGE, null);
								return;
							} else {
								isOk = true;
								setVisible(false);
							}
						} catch (NumberFormatException exc) {
							JOptionPane.showMessageDialog(null, "Invalid data type inserted!", "ERROR",
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
					public void actionPerformed(ActionEvent e) {
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
							.addGap(146).addComponent(btnModify).addGap(18).addComponent(cancelButton).addGap(158)));
			gl_pnlSouth.setVerticalGroup(gl_pnlSouth.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlSouth.createSequentialGroup()
							.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE).addComponent(btnModify)
									.addComponent(cancelButton))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			pnlSouth.setLayout(gl_pnlSouth);
		}
	}

	public void validation(String x, String y, String radius, String innerRadius) {

		String supp2 = "^(([+-])?([1-9]{1})([0-9]+)?)$";
		if (x.matches("") || y.matches("") || radius.matches("") || innerRadius.matches("")) {

		} else if (!x.matches(supp2) || !y.matches(supp2) || !radius.matches(supp2) || !innerRadius.matches(supp2)) {
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

	public JTextField getTxtCenterX() {
		return txtCenterX;
	}

	public void setTxtCenterX(JTextField txtCenterX) {
		this.txtCenterX = txtCenterX;
	}

	public JTextField getTxtCenterY() {
		return txtCenterY;
	}

	public void setTxtCenterY(JTextField txtCenterY) {
		this.txtCenterY = txtCenterY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public JTextField getTxtInnerRadius() {
		return txtInnerRadius;
	}

	public void setTxtInnerRadius(JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
	}

	public boolean isOk() {
		return isOk;
	}
}
