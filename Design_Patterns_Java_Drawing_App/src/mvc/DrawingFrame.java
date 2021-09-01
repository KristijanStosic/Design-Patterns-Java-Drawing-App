package mvc;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;

public class DrawingFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private DrawingView view = new DrawingView();
	private DrawingController controller;

	private ButtonGroup btnGroupShapes = new ButtonGroup();

	private Color edgeColor = new Color(0, 0, 0);
	private Color innerColor = new Color(255, 255, 255);

	private JToggleButton tglBtnPoint, tglBtnLine, tglBtnCircle, tglBtnDonut, tglBtnRectangle, tglBtnHexagon,
			tglBtnSelect;

	private JButton btnModify, btnDelete, btnLoadNext, btnUndo, btnRedo, btnToFront, btnToBack, btnBringToFront,
			btnBringToBack, btnInnerColor, btnEdgeColor;

	private JMenuBar menuBar;
	private JMenu menuMain;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel lblXYCoordinates;

	public DrawingFrame() {
		setBounds(100, 100, 1000, 700);

		/*
		 * **************************************************** PANELS
		 * *************************************************
		 */

		/* *************************** PANEL ACTIONS *************************** */
		JPanel pnlActions = new JPanel();
		pnlActions.setBorder(new TitledBorder(
				new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
						new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
				"Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		/* *************************** PANEL SHAPES *************************** */
		JPanel pnlShapes = new JPanel();
		pnlShapes.setBorder(new TitledBorder(
				new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
						new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
				"Shapes", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		/* *************************** PANEL COLORS *************************** */
		JPanel pnlColors = new JPanel();
		pnlColors.setBorder(new TitledBorder(
				new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
						new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
				"Colors", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		/* *************************** PANEL MAIN *************************** */
		JPanel pnlMain = new JPanel();
		pnlMain.setBorder(new TitledBorder(
				new CompoundBorder(
						new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192),
								new Color(192, 192, 192), new Color(192, 192, 192)),
						new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
				"Paint", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMain.setBackground(Color.WHITE);
		/*
		 * **************************************************** PANELS
		 * *************************************************
		 */

		tglBtnPoint = new JToggleButton("");
		tglBtnPoint.setToolTipText("Point");
		tglBtnPoint.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/cross-shaped-target.png")));

		tglBtnLine = new JToggleButton("");
		tglBtnLine.setToolTipText("Line");
		tglBtnLine.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/line.png")));

		tglBtnCircle = new JToggleButton("");
		tglBtnCircle.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/dry-clean.png")));
		tglBtnCircle.setToolTipText("Circle");

		tglBtnDonut = new JToggleButton("");
		tglBtnDonut.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/donutBigger.png")));
		tglBtnDonut.setToolTipText("Donut");

		tglBtnRectangle = new JToggleButton("");
		tglBtnRectangle.setIcon(
				new ImageIcon(DrawingFrame.class.getResource("/images/rectangular-geometrical-shape-outline.png")));
		tglBtnRectangle.setToolTipText("Rectangle");

		tglBtnHexagon = new JToggleButton("");
		tglBtnHexagon.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/hexagon.png")));
		tglBtnHexagon.setToolTipText("Hexagon");

		tglBtnSelect = new JToggleButton("");
		tglBtnSelect.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/cursor.png")));
		tglBtnSelect.setToolTipText("Select");
		tglBtnSelect.setEnabled(false);

		btnModify = new JButton("");
		btnModify.setEnabled(false);
		btnModify.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/pencil.png")));
		btnModify.setToolTipText("Modify");

		btnDelete = new JButton("");
		btnDelete.setEnabled(false);
		btnDelete.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/delete.png")));
		btnDelete.setToolTipText("Delete");

		btnInnerColor = new JButton("");
		btnInnerColor.setToolTipText("Inner Color");
		btnInnerColor.setBackground(Color.WHITE);

		btnEdgeColor = new JButton("");
		btnEdgeColor.setBackground(Color.BLACK);
		btnEdgeColor.setForeground(UIManager.getColor("Button.focus"));
		btnEdgeColor.setToolTipText("Edge Color");

		btnLoadNext = new JButton("");
		btnLoadNext.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/right-arrow.png")));
		btnLoadNext.setToolTipText("Load Next");
		btnLoadNext.setEnabled(false);

		/*
		 * ********************* ****** PANEL COMMANDS ***************************
		 */
		JPanel pnlCommands = new JPanel();
		pnlCommands.setBorder(new TitledBorder(
				new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
						new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
				"Commands", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel pnlDisplayLog = new JPanel();
		pnlDisplayLog.setBackground(Color.WHITE);
		view.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				lblXYCoordinates
						.setText("X: " + Integer.toString(e.getX()) + ", " + "Y: " + Integer.toString(e.getY()));
			}
		});

		/* *************************** PANEL COORDINATES *************************** */
		JPanel pnlXYCoordinates = new JPanel();
		pnlXYCoordinates.setBorder(new TitledBorder(
				new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
						new BevelBorder(BevelBorder.LOWERED, null, null, null, null)),
				"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		/* *************************** PANEL COORDINATES *************************** */

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(pnlShapes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 706,
												Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(pnlActions, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(pnlColors, 0, 0, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(pnlCommands,
														GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(pnlXYCoordinates, GroupLayout.PREFERRED_SIZE, 231,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(pnlDisplayLog, Alignment.LEADING, 0, 0, Short.MAX_VALUE).addComponent(
										pnlMain, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)))
				.addContainerGap(28, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(pnlActions, 0, 0, Short.MAX_VALUE)
										.addComponent(pnlColors, GroupLayout.PREFERRED_SIZE, 65, Short.MAX_VALUE)
										.addComponent(pnlCommands, GroupLayout.PREFERRED_SIZE, 65, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(pnlShapes, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addComponent(pnlXYCoordinates, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(pnlDisplayLog, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

		lblXYCoordinates = new JLabel("X: Y:");
		lblXYCoordinates.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));

		GroupLayout gl_pnlXYCoordinates = new GroupLayout(pnlXYCoordinates);
		gl_pnlXYCoordinates.setHorizontalGroup(gl_pnlXYCoordinates.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlXYCoordinates.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlXYCoordinates.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnLoadNext, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 203,
										Short.MAX_VALUE)
								.addComponent(lblXYCoordinates, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 203,
										Short.MAX_VALUE))
						.addContainerGap()));
		gl_pnlXYCoordinates.setVerticalGroup(gl_pnlXYCoordinates.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlXYCoordinates.createSequentialGroup().addContainerGap()
						.addComponent(lblXYCoordinates, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btnLoadNext, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
						.addContainerGap()));
		pnlXYCoordinates.setLayout(gl_pnlXYCoordinates);

		scrollPane = new JScrollPane();

		GroupLayout gl_pnlDisplayLog = new GroupLayout(pnlDisplayLog);
		gl_pnlDisplayLog.setHorizontalGroup(gl_pnlDisplayLog.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE));
		gl_pnlDisplayLog.setVerticalGroup(gl_pnlDisplayLog.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE));

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		/* *************************** PANEL COMMANDS *************************** */

		/* *************************** MENU *************************** */
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		menuMain = new JMenu("File");
		menuBar.add(menuMain);

		JMenuItem menuItemSavePainting = new JMenuItem("Save Painting");
		menuItemSavePainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.savePainting();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		menuMain.add(menuItemSavePainting);

		JMenuItem menuItemOpenPainting = new JMenuItem("Open Painting");
		menuItemOpenPainting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openPainting();
				} catch (ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		menuMain.add(menuItemOpenPainting);

		JMenuItem menuItemSaveLog = new JMenuItem("Save Log");
		menuItemSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveLog();
			}
		});
		menuMain.add(menuItemSaveLog);

		JMenuItem menuItemOpenLog = new JMenuItem("Open Log");
		menuItemOpenLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openLog();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		menuMain.add(menuItemOpenLog);
		pnlDisplayLog.setLayout(gl_pnlDisplayLog);
		/* *************************** LOG - TEXT AREA *************************** */

		/* *************************** UNDO *************************** */
		btnUndo = new JButton("");
		btnUndo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/undoIcon.png")));
		btnUndo.setToolTipText("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});

		/* *************************** REDO *************************** */
		btnRedo = new JButton("");
		btnRedo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/redoIcon (2).png")));
		btnRedo.setToolTipText("Redo");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});

		/* *************************** TO FRONT *************************** */
		btnToFront = new JButton("");
		btnToFront.setEnabled(false);
		btnToFront.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/Bring front (1).png")));
		btnToFront.setToolTipText("To Front");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});

		/* *************************** TO BACK *************************** */
		btnToBack = new JButton("");
		btnToBack.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/bring back (1).png")));
		btnToBack.setToolTipText("To Back");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});

		/* *************************** BRING TO FRONT *************************** */
		btnBringToFront = new JButton("");
		btnBringToFront.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/Bring to front (2).png")));
		btnBringToFront.setToolTipText("Bring To Front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});

		/* *************************** BRING TO BACK *************************** */
		btnBringToBack = new JButton("");
		btnBringToBack.setIcon(new ImageIcon(DrawingFrame.class.getResource("/images/Bring to back (1).png")));
		btnBringToBack.setToolTipText("Bring To Back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});

		/* *************************** LOAD NEXT *************************** */
		btnLoadNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loadNext();
			}
		});
		/* *************************** LOAD NEXT *************************** */

		/*
		 * *************************** ADDING UNDO/REDO TO MENU BAR
		 * ***************************
		 */
		menuBar.add(btnUndo);
		menuBar.add(btnRedo);

		GroupLayout gl_pnlCommands = new GroupLayout(pnlCommands);
		gl_pnlCommands.setHorizontalGroup(gl_pnlCommands.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCommands
				.createSequentialGroup().addContainerGap()
				.addComponent(btnToFront, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(btnToBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnBringToFront, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBringToBack).addGap(19)));
		gl_pnlCommands.setVerticalGroup(gl_pnlCommands.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCommands.createSequentialGroup()
						.addGroup(gl_pnlCommands.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCommands.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnToBack, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnBringToFront, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnBringToBack, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(btnToFront, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
						.addContainerGap()));
		pnlCommands.setLayout(gl_pnlCommands);

		pnlMain.setLayout(new BorderLayout(0, 0));

		GroupLayout gl_pnlColors = new GroupLayout(pnlColors);
		gl_pnlColors.setHorizontalGroup(gl_pnlColors.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlColors.createSequentialGroup().addContainerGap()
						.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnEdgeColor, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE).addContainerGap()));
		gl_pnlColors.setVerticalGroup(gl_pnlColors.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlColors
				.createSequentialGroup()
				.addGroup(gl_pnlColors.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEdgeColor, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(btnInnerColor, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		pnlColors.setLayout(gl_pnlColors);

		GroupLayout gl_pnlShapes = new GroupLayout(pnlShapes);
		gl_pnlShapes.setHorizontalGroup(gl_pnlShapes.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlShapes
				.createSequentialGroup().addContainerGap()
				.addComponent(tglBtnPoint, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(tglBtnLine, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(tglBtnCircle, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(tglBtnDonut, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(tglBtnRectangle, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(tglBtnHexagon, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(19, Short.MAX_VALUE)));
		gl_pnlShapes.setVerticalGroup(gl_pnlShapes.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlShapes
				.createSequentialGroup()
				.addGroup(gl_pnlShapes.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING,
								gl_pnlShapes.createSequentialGroup().addContainerGap().addComponent(tglBtnHexagon,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlShapes.createSequentialGroup().addContainerGap().addComponent(tglBtnRectangle,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlShapes.createSequentialGroup().addContainerGap().addComponent(tglBtnDonut,
										GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlShapes.createSequentialGroup().addContainerGap().addComponent(tglBtnCircle,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlShapes.createSequentialGroup().addContainerGap().addComponent(tglBtnLine,
										GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING,
								gl_pnlShapes.createSequentialGroup().addContainerGap().addComponent(tglBtnPoint,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addContainerGap()));
		pnlShapes.setLayout(gl_pnlShapes);

		GroupLayout gl_pnlActions = new GroupLayout(pnlActions);
		gl_pnlActions.setHorizontalGroup(gl_pnlActions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlActions.createSequentialGroup().addContainerGap()
						.addComponent(tglBtnSelect, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(11, Short.MAX_VALUE)));
		gl_pnlActions.setVerticalGroup(gl_pnlActions.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlActions
				.createSequentialGroup()
				.addGroup(gl_pnlActions.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(btnDelete, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
						.addComponent(tglBtnSelect, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
				.addContainerGap()));
		pnlActions.setLayout(gl_pnlActions);
		getContentPane().setLayout(groupLayout);

		/* ********************** EDGE COLOR COLOR ******************** */
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Choose color", Color.RED);
				if (temp != null) {
					edgeColor = temp;
					btnEdgeColor.setBackground(edgeColor);
				}
			}
		});

		/* ********************** INNER COLOR ******************** */
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(null, "Choose color", Color.RED);
				if (temp != null) {
					innerColor = temp;
					btnInnerColor.setBackground(innerColor);
				}
			}
		});

		/* ********************** CURSOR POINTER ******************** */
		tglBtnSelect.addItemListener(new ItemListener() {
			@SuppressWarnings("deprecation")
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					view.setCursor(new Cursor(HAND_CURSOR));
				} else {
					view.setCursor(new Cursor(DEFAULT_CURSOR));
				}
			}
		});

		/* ********************** BUTTON GROUP ******************** */
		btnGroupShapes.add(tglBtnPoint);
		btnGroupShapes.add(tglBtnLine);
		btnGroupShapes.add(tglBtnCircle);
		btnGroupShapes.add(tglBtnDonut);
		btnGroupShapes.add(tglBtnRectangle);
		btnGroupShapes.add(tglBtnHexagon);
		btnGroupShapes.add(tglBtnSelect);

		/* ********************* VIEW ******************************** */
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.addShape(e, edgeColor, innerColor);
			}
		});

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});

		pnlMain.add(view, GroupLayout.DEFAULT_SIZE);
		view.setBackground(Color.WHITE);
		view.setPreferredSize(new Dimension(1000, 800));
	}
	/* ********************* VIEW ******************************** */

	/* ********************* GETTERS/SETTERS ******************************** */

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public JToggleButton getTglBtnPoint() {
		return tglBtnPoint;
	}

	public void setTglBtnPoint(JToggleButton tglBtnPoint) {
		this.tglBtnPoint = tglBtnPoint;
	}

	public JToggleButton getTglBtnLine() {
		return tglBtnLine;
	}

	public void setTglBtnLine(JToggleButton tglBtnLine) {
		this.tglBtnLine = tglBtnLine;
	}

	public JToggleButton getTglBtnCircle() {
		return tglBtnCircle;
	}

	public void setTglBtnCircle(JToggleButton tglBtnCircle) {
		this.tglBtnCircle = tglBtnCircle;
	}

	public JToggleButton getTglBtnDonut() {
		return tglBtnDonut;
	}

	public void setTglBtnDonut(JToggleButton tglBtnDonut) {
		this.tglBtnDonut = tglBtnDonut;
	}

	public JToggleButton getTglBtnRectangle() {
		return tglBtnRectangle;
	}

	public void setTglBtnRectangle(JToggleButton tglBtnRectangle) {
		this.tglBtnRectangle = tglBtnRectangle;
	}

	public JToggleButton getTglBtnHexagon() {
		return tglBtnHexagon;
	}

	public void setTglBtnHexagon(JToggleButton tglBtnHexagon) {
		this.tglBtnHexagon = tglBtnHexagon;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}

	public JToggleButton getTglBtnSelect() {
		return tglBtnSelect;
	}

	public void setTglBtnSelect(JToggleButton tglBtnSelect) {
		this.tglBtnSelect = tglBtnSelect;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getBtnLoadNext() {
		return btnLoadNext;
	}
}
