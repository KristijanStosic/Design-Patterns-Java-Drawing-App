package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdCircleModify;
import command.CmdDonutModify;
import command.CmdHexagonModify;
import command.CmdLineModify;
import command.CmdPointModify;
import command.CmdRectangleModify;
import command.CmdShapeAdd;
import command.CmdShapeDeselect;
import command.CmdShapeRemove;
import command.CmdShapeSelect;
import command.CmdToBack;
import command.CmdToFront;
import command.Command;
import dialogues.DlgDrawCircle;
import dialogues.DlgDrawDonut;
import dialogues.DlgDrawHexagon;
import dialogues.DlgDrawRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import modifyDlg.DlgCircleModify;
import modifyDlg.DlgDonutModify;
import modifyDlg.DlgHexagonModify;
import modifyDlg.DlgLineModify;
import modifyDlg.DlgPointModify;
import modifyDlg.DlgRectangleModify;
import observer.BtnObserver;
import observer.BtnObserverUpdate;
import strategy.SaveLog;
import strategy.SaveManager;
import strategy.SavePainting;

public class DrawingController {
	
	private DrawingModel model;
	private DrawingFrame frame;

	private Point startPoint, endPoint;

	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private ArrayList<Shape> undoShapesList = new ArrayList<Shape>();
	private ArrayList<Shape> redoShapesList = new ArrayList<Shape>();
	
	private int undoCounter = 0;
	private int redoCounter = 0;

	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();

	private Command command;

	// Observer
	private BtnObserver btnObserver = new BtnObserver();
	private BtnObserverUpdate btnObserverUpdate;

	// Log
	private ArrayList<String> logList = new ArrayList<String>();
	private int logCounter = 0;

	// Dialogs
	DlgPointModify dlgPoint = new DlgPointModify();
	DlgLineModify dlgLine = new DlgLineModify();
	DlgCircleModify dlgCircle = new DlgCircleModify();
	DlgRectangleModify dlgRectangle = new DlgRectangleModify();
	DlgDonutModify dlgDonut = new DlgDonutModify();
	DlgHexagonModify dlgHexagon = new DlgHexagonModify();

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		btnObserverUpdate = new BtnObserverUpdate(frame);
		btnObserver.addPropertyChangeListener(btnObserverUpdate);
	}

	/* ****************************** ADD SHAPE ****************************** */
	public void addShape(MouseEvent e, Color edgeColor, Color innerColor) {
		Point thirdPoint = new Point(e.getX(), e.getY());
		
		Shape isSelectedShape;
		Shape selected;

		if (frame.getTglBtnSelect().isSelected()) {
			isSelectedShape = null; // prolazi kroz listu i uzima vrednost svakog shapea dok ne dodje do onog na  koji sam kliknuo
			selected = null; // kada nadje onog na koji sam kliknuo uzima vrednost

			Command command = null;
			ListIterator<Shape> it = model.getShapes().listIterator();
			
			while (it.hasNext()) {

				isSelectedShape = it.next();

				if (isSelectedShape.contains(e.getX(), e.getY())) {

					selected = isSelectedShape;
				}
			}

			if (selected != null) {
				if (selected.isSelected()) {
					command = new CmdShapeDeselect(this, selected);
					command.execute();
					frame.getTextArea().append(command.toString());
					undoStack.push(command);
				} else {
					command = new CmdShapeSelect(this, selected);
					command.execute();
					frame.getTextArea().append(command.toString());
					undoStack.push(command);
				}
				undoCounter++;
			}
			
			undoRedoButtons();
			enableDisableButtons();
			frame.getView().repaint();

		} else {
			if (frame.getTglBtnPoint().isSelected()) {
				Point p = new Point(e.getX(), e.getY());
				p.setColor(edgeColor);
				command = new CmdShapeAdd(model, p);
				command.execute();
				frame.getTextArea().append(command.toString());
				undoCounter++;
				undoStack.push(command);
				redoStack.clear();
			} else if (frame.getTglBtnLine().isSelected()) {
				if (startPoint == null) {
					startPoint = new Point(e.getX(), e.getY());
					return;
				}
				endPoint = new Point(e.getX(), e.getY());
				Line l = new Line(startPoint, endPoint, false);
				l.setColor(edgeColor);
				command = new CmdShapeAdd(model, l);
				command.execute();
				frame.getTextArea().append(command.toString());
				undoCounter++;
				undoStack.push(command);
				redoStack.clear();
				startPoint = null;
			} else if (frame.getTglBtnRectangle().isSelected()) {
				DlgDrawRectangle drawRectangle = new DlgDrawRectangle();
				drawRectangle.setVisible(true);

				if (drawRectangle.isOk()) {
					Rectangle r = new Rectangle(thirdPoint, Integer.parseInt(drawRectangle.getTxtWidthRectangle().getText()), Integer.parseInt(drawRectangle.getTxtHeightRectangle().getText()));
					r.setColor(edgeColor);
					r.setInnerColor(innerColor);
					command = new CmdShapeAdd(model, r);
					command.execute();
					frame.getTextArea().append(command.toString());
					undoCounter++;
					undoStack.push(command);
					redoStack.clear();
				}
			} else if (frame.getTglBtnCircle().isSelected()) {
				DlgDrawCircle drawCircle = new DlgDrawCircle();
				drawCircle.setVisible(true);

				if (drawCircle.isOk()) {
					Circle c = new Circle(thirdPoint, Integer.parseInt(drawCircle.getTxtRadius().getText()));
					c.setColor(edgeColor);
					c.setInnerColor(innerColor);
					command = new CmdShapeAdd(model, c);
					command.execute();
					frame.getTextArea().append(command.toString());
					undoCounter++;
					undoStack.push(command);
					redoStack.clear();
				}
			} else if (frame.getTglBtnDonut().isSelected()) {
				DlgDrawDonut drawDonut = new DlgDrawDonut();
				drawDonut.setVisible(true);

				if (drawDonut.isOk()) {
					Donut d = new Donut(thirdPoint, Integer.parseInt(drawDonut.getTxtDonutRadius().getText()), Integer.parseInt(drawDonut.getTxtDonutInnerRadius().getText()));
					d.setColor(edgeColor);
					d.setInnerColor(innerColor);
					command = new CmdShapeAdd(model, d);
					command.execute();
					frame.getTextArea().append(command.toString());
					undoCounter++;
					undoStack.push(command);
					redoStack.clear();
				}
			} else if (frame.getTglBtnHexagon().isSelected()) {
				DlgDrawHexagon drawHexagon = new DlgDrawHexagon();
				drawHexagon.setVisible(true);
				if (drawHexagon.isOk()) {
					HexagonAdapter hexagonAdapter = new HexagonAdapter(thirdPoint, Integer.parseInt(drawHexagon.getTxtRadius().getText()));
					hexagonAdapter.setHexagonBorderColor(edgeColor);
					hexagonAdapter.setHexagonInnerColor(innerColor);
					command = new CmdShapeAdd(model, hexagonAdapter);
					command.execute();
					frame.getTextArea().append(command.toString());
					undoCounter++;
					undoStack.push(command);
					redoStack.clear();
				}
			}
		}
		undoRedoButtons();
		enableDisableButtons();
		frame.getView().repaint();
	}

	/* ****************************** MODIFY ****************************** */
	public void modify() {
		if (selectedShapes.get(0) instanceof Point) {

			Point pointHelp = (Point) selectedShapes.get(0);
			dlgPoint.getTxtCX().setText(Integer.toString(pointHelp.getX()));
			dlgPoint.getTxtCY().setText(Integer.toString(pointHelp.getY()));
			dlgPoint.getBtnEdgeColor().setBackground(pointHelp.getColor());

			dlgPoint.setVisible(true);

			if (dlgPoint.isOk()) {
				Point point = new Point(Integer.parseInt(dlgPoint.getTxtCX().getText()), Integer.parseInt(dlgPoint.getTxtCY().getText()), true, dlgPoint.getBtnEdgeColor().getBackground());
				command = new CmdPointModify(pointHelp, point);
				command.execute();
				frame.getTextArea().append(command.toString());
				undoStack.push(command);
				undoCounter++;
				redoStack.clear();
			}

		} else if (selectedShapes.get(0) instanceof Line) {

			Line lineHelp = (Line) selectedShapes.get(0);
			dlgLine.getTxtStartPointX().setText(Integer.toString(lineHelp.getStartPoint().getX()));
			dlgLine.getTxtStartPointY().setText(Integer.toString(lineHelp.getStartPoint().getY()));
			dlgLine.getTxtEndPointX().setText(Integer.toString(lineHelp.getEndPoint().getX()));
			dlgLine.getTxtEndPointY().setText(Integer.toString(lineHelp.getEndPoint().getY()));
			dlgLine.getBtnEdgeColor().setBackground(lineHelp.getColor());

			dlgLine.setVisible(true);

			if (dlgLine.isOk()) {
				Line line = new Line(
						new Point(Integer.parseInt(dlgLine.getTxtStartPointX().getText()),
								Integer.parseInt(dlgLine.getTxtStartPointY().getText())),
						new Point(Integer.parseInt(dlgLine.getTxtEndPointX().getText()),
								Integer.parseInt(dlgLine.getTxtEndPointY().getText())),
						dlgLine.getBtnEdgeColor().getBackground());
				command = new CmdLineModify(lineHelp, line);
				command.execute();
				frame.getTextArea().append(command.toString());
				undoCounter++;
				undoStack.push(command);
				redoStack.clear();
			}

		} else if (selectedShapes.get(0) instanceof Donut) {
			Donut donutHelp = (Donut) selectedShapes.get(0);
			dlgDonut.getTxtCenterX().setText(Integer.toString(donutHelp.getCenter().getX()));
			dlgDonut.getTxtCenterY().setText(Integer.toString(donutHelp.getCenter().getY()));
			dlgDonut.getTxtRadius().setText(Integer.toString(donutHelp.getRadius()));
			dlgDonut.getTxtInnerRadius().setText(Integer.toString(donutHelp.getInnerRadius()));
			dlgDonut.getBtnEdgeColor().setBackground(donutHelp.getColor());
			dlgDonut.getBtnInnerColor().setBackground(donutHelp.getInnerColor());

			dlgDonut.setVisible(true);

			if (dlgDonut.isOk()) {
				Donut donut = new Donut(
						new Point(Integer.parseInt(dlgDonut.getTxtCenterX().getText()),
								Integer.parseInt(dlgDonut.getTxtCenterY().getText())),
						Integer.parseInt(dlgDonut.getTxtRadius().getText()),
						Integer.parseInt(dlgDonut.getTxtInnerRadius().getText()), true,
						dlgDonut.getBtnEdgeColor().getBackground(), dlgDonut.getBtnInnerColor().getBackground());

				command = new CmdDonutModify(donutHelp, donut);
				command.execute();
				frame.getTextArea().append(command.toString());
				undoCounter++;
				undoStack.push(command);
				redoStack.clear();
			}
		} else if (selectedShapes.get(0) instanceof Circle) {
			Circle circleHelp = (Circle) selectedShapes.get(0);
			dlgCircle.getTxtXcoordinate().setText(Integer.toString(circleHelp.getCenter().getX()));
			dlgCircle.getTxtYcoordinate().setText(Integer.toString(circleHelp.getCenter().getY()));
			dlgCircle.getTxtRadius().setText(Integer.toString(circleHelp.getRadius()));
			dlgCircle.getBtnEdgeColor().setBackground(circleHelp.getColor());
			dlgCircle.getBtnInnerColor().setBackground(circleHelp.getInnerColor());

			dlgCircle.setVisible(true);

			if (dlgCircle.isOk()) {
				Circle circle = new Circle(
						new Point(Integer.parseInt(dlgCircle.getTxtXcoordinate().getText()),
								Integer.parseInt(dlgCircle.getTxtYcoordinate().getText())),
						Integer.parseInt(dlgCircle.getTxtRadius().getText()), true,
						dlgCircle.getBtnEdgeColor().getBackground(), dlgCircle.getBtnInnerColor().getBackground());

				command = new CmdCircleModify(circleHelp, circle);
				command.execute();
				frame.getTextArea().append(command.toString());
				undoCounter++;
				undoStack.push(command);
				redoStack.clear();
			}
		} else if (selectedShapes.get(0) instanceof Rectangle) {
			Rectangle rectangleHelp = (Rectangle) selectedShapes.get(0);
			dlgRectangle.getTxtUpperLeftX().setText(Integer.toString(rectangleHelp.getUpperLeftPoint().getX()));
			dlgRectangle.getTxtUpperLeftY().setText(Integer.toString(rectangleHelp.getUpperLeftPoint().getY()));
			dlgRectangle.getTxtHeight().setText(Integer.toString(rectangleHelp.getHeight()));
			dlgRectangle.getTxtWidth().setText(Integer.toString(rectangleHelp.getWidth()));
			dlgRectangle.getBtnEdgeColor().setBackground(rectangleHelp.getColor());
			dlgRectangle.getBtnInnerColor().setBackground(rectangleHelp.getInnerColor());

			dlgRectangle.setVisible(true);

			if (dlgRectangle.isOk()) {
				Rectangle rectangle = new Rectangle(
						new Point(Integer.parseInt(dlgRectangle.getTxtUpperLeftX().getText()),
								Integer.parseInt(dlgRectangle.getTxtUpperLeftY().getText())),
						Integer.parseInt(dlgRectangle.getTxtWidth().getText()),
						Integer.parseInt(dlgRectangle.getTxtHeight().getText()), true,
						dlgRectangle.getBtnEdgeColor().getBackground(),
						dlgRectangle.getBtnInnerColor().getBackground());
				command = new CmdRectangleModify(rectangleHelp, rectangle);
				command.execute();
				frame.getTextArea().append(command.toString());
				undoCounter++;
				undoStack.push(command);
				redoStack.clear();
			}
		} else if (selectedShapes.get(0) instanceof HexagonAdapter) {
			HexagonAdapter hexagonHelp = (HexagonAdapter) selectedShapes.get(0);
			dlgHexagon.getTxtXCoordinate().setText(Integer.toString(hexagonHelp.getHexagon().getX()));
			dlgHexagon.getTxtYCoordinate().setText(Integer.toString(hexagonHelp.getHexagon().getY()));
			dlgHexagon.getTxtRadius().setText(Integer.toString(hexagonHelp.getHexagonRadius()));
			dlgHexagon.getBtnEdgeColor().setBackground(hexagonHelp.getHexagonBorderColor());
			dlgHexagon.getBtnInnerColor().setBackground(hexagonHelp.getHexagonInnerColor());

			dlgHexagon.setVisible(true);

			if (dlgHexagon.isOk()) {
				HexagonAdapter hexagonAdapter = new HexagonAdapter(
						new Point(Integer.parseInt(dlgHexagon.getTxtXCoordinate().getText()),
								Integer.parseInt(dlgHexagon.getTxtYCoordinate().getText())),
						Integer.parseInt(dlgHexagon.getTxtRadius().getText()), true,
						dlgHexagon.getBtnEdgeColor().getBackground(), dlgHexagon.getBtnInnerColor().getBackground());

				command = new CmdHexagonModify(hexagonHelp, hexagonAdapter);
				command.execute();
				frame.getTextArea().append(command.toString());
				undoCounter++;
				undoStack.push(command);
				redoStack.clear();
			}
		}
		frame.getBtnLoadNext().setEnabled(false);
		undoRedoButtons();
		enableDisableButtons();
		frame.getView().repaint();
	}

	/* *************************************************** DELETE ******************************************************** */
	public void delete() {
		Shape shape;

		for (int i = selectedShapes.size() - 1; i >= 0; i--) {
			shape = selectedShapes.get(0);
			command = new CmdShapeRemove(model, shape, model.getShapes().indexOf(shape));
			command.execute();
			frame.getTextArea().append(command.toString());
			selectedShapes.remove(shape);
			undoShapesList.add(shape);
			undoStack.push(command);
			undoCounter++;
		}
		redoStack.clear();
		undoRedoButtons();
		enableDisableButtons();
		frame.getView().repaint();
	}

	/* *************************************************** UNDO REDO BUTTONS CONTROL ******************************************************** */
	public void undoRedoButtons() {
		if (undoCounter < 1) {
			frame.getBtnUndo().setEnabled(false);
		} else {
			frame.getBtnUndo().setEnabled(true);
		}

		if (redoCounter < 1 || redoStack.isEmpty()) {
			frame.getBtnRedo().setEnabled(false);
		} else {
			frame.getBtnRedo().setEnabled(true);
		}
	}

	/* *************************************************** UNDO ******************************************************** */
	public void undo() {
		command = undoStack.peek();
		command.unexecute();
		if (command instanceof CmdShapeRemove) {
			redoShapesList.add(undoShapesList.get(undoShapesList.size() - 1));
			selectedShapes.add(undoShapesList.get(undoShapesList.size() - 1));
			undoShapesList.remove(undoShapesList.size() - 1);
		}
		frame.getTextArea().append("Undo " + undoStack.peek().toString());
		undoCounter--;
		redoCounter++;
		frame.getView().repaint();
		undoStack.pop();
		redoStack.push(command);
		undoRedoButtons();
		enableDisableButtons();
	}

	/* *************************************************** REDO ******************************************************** */
	public void redo() {
		command = redoStack.peek();
		command.execute();
		if (command instanceof CmdShapeRemove) {
			undoShapesList.add(redoShapesList.get(redoShapesList.size() - 1));
			selectedShapes.remove(redoShapesList.get(redoShapesList.size() - 1));
			redoShapesList.remove(redoShapesList.size() - 1);
		}
		frame.getTextArea().append("Redo " + redoStack.peek().toString());
		undoCounter++;
		redoCounter--;
		frame.getView().repaint();
		redoStack.pop();
		undoStack.push(command);
		undoRedoButtons();
		enableDisableButtons();
	}

	/* *************************************************** TO BACK ******************************************************** */
	public void toBack() {
		Shape shape = selectedShapes.get(0);
		CmdToBack cmdToBack = new CmdToBack(model, shape);
		cmdToBack.execute();
		frame.getTextArea().append(cmdToBack.toString());
		undoStack.push(cmdToBack);
		undoCounter++;
		redoStack.clear();
		undoRedoButtons();
		enableDisableButtons();
		frame.getView().repaint();
	}

	/* *************************************************** BRING TO BACK ******************************************************** */
	public void bringToBack() {
		Shape shape = selectedShapes.get(0);
		CmdBringToBack cmdBringToBack = new CmdBringToBack(model, shape);
		cmdBringToBack.execute();
		frame.getTextArea().append(cmdBringToBack.toString());
		undoStack.push(cmdBringToBack);
		undoCounter++;
		redoStack.clear();
		undoRedoButtons();
		enableDisableButtons();	
		frame.getView().repaint();
	}

	/* *************************************************** TO FRONT ******************************************************** */
	public void toFront() {
		Shape shape = selectedShapes.get(0);
		CmdToFront cmdToFront = new CmdToFront(model, shape);
		cmdToFront.execute();
		frame.getTextArea().append(cmdToFront.toString());
		undoStack.push(cmdToFront);
		undoCounter++;
		redoStack.clear();
		undoRedoButtons();
		enableDisableButtons();	
		frame.getView().repaint();
	}

	/* *************************************************** BRING TO FRONT ******************************************************** */
	public void bringToFront() {
		Shape shape = selectedShapes.get(0);
		CmdBringToFront cmdBringToFront = new CmdBringToFront(model, shape);
		cmdBringToFront.execute();
		frame.getTextArea().append(cmdBringToFront.toString());
		undoStack.push(cmdBringToFront);
		undoCounter++;
		redoStack.clear();
		undoRedoButtons();
		enableDisableButtons();
		frame.getView().repaint();
	}
	/* *************************************************** BUTTONS CONTROL WITH OBSERVER ******************************************************** */
	public void enableDisableButtons() {

		if (model.getShapes().size() != 0) {
			btnObserver.setSelectBtnActivated(true);
			if (selectedShapes.size() != 0) {
				if (selectedShapes.size() == 1)// 1
				{
					btnObserver.setModifyBtnActivated(true);
					btnUpdate();

				} else {
					btnObserver.setModifyBtnActivated(false);

					btnObserver.setBringToBackBtnActivated(false);
					btnObserver.setBringToFrontBtnActivated(false);
					btnObserver.setToBackBtnActivated(false);
					btnObserver.setToFrontBtnActivated(false);
				}
				btnObserver.setDeleteBtnActivated(true);
			} else {
				btnObserver.setModifyBtnActivated(false);
				btnObserver.setDeleteBtnActivated(false);

				btnObserver.setBringToBackBtnActivated(false);
				btnObserver.setBringToFrontBtnActivated(false);
				btnObserver.setToBackBtnActivated(false);
				btnObserver.setToFrontBtnActivated(false);
			}
		} else {
			btnObserver.setSelectBtnActivated(false);
			btnObserver.setModifyBtnActivated(false);
			btnObserver.setDeleteBtnActivated(false);

			btnObserver.setBringToBackBtnActivated(false);
			btnObserver.setBringToFrontBtnActivated(false);
			btnObserver.setToBackBtnActivated(false);
			btnObserver.setToFrontBtnActivated(false);
		}

	}
	/* *************************************************** TO FRONT/TO BACK/BRING TO FRONT/BRING TO BACK ******************************************************** */
	public void btnUpdate() {

		Iterator<Shape> it = this.model.getShapes().iterator();
		Shape shape;

		while (it.hasNext()) {
			shape = it.next();

			if (shape.isSelected()) {
				if (this.model.getShapes().size() == 1) {
					btnObserver.setToFrontBtnActivated(false);
					btnObserver.setToBackBtnActivated(false);
					btnObserver.setBringToFrontBtnActivated(false);
					btnObserver.setBringToBackBtnActivated(false);
				} else {
					if (shape.equals(model.getOneShape(this.model.getShapes().size() - 1))) {
						btnObserver.setToFrontBtnActivated(false);
						btnObserver.setToBackBtnActivated(true);
						btnObserver.setBringToFrontBtnActivated(false);
						btnObserver.setBringToBackBtnActivated(true);
					} else if (shape.equals(model.getOneShape(0))) {
						btnObserver.setToFrontBtnActivated(true);
						btnObserver.setToBackBtnActivated(false);
						btnObserver.setBringToFrontBtnActivated(true);
						btnObserver.setBringToBackBtnActivated(false);
					} else {
						btnObserver.setToFrontBtnActivated(true);
						btnObserver.setToBackBtnActivated(true);
						btnObserver.setBringToFrontBtnActivated(true);
						btnObserver.setBringToBackBtnActivated(true);
					}
				}
			}
		}
	}

	/* *************************************************** SAVE PAINTING ******************************************************** */
	public void savePainting() throws IOException, NotSerializableException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save painting");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".bin", "bin");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(filter);

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File paintingToSave = fileChooser.getSelectedFile();
			File logToSave;
			String filePath = paintingToSave.getAbsolutePath();
			if (!filePath.endsWith(".bin") && !filePath.contains(".")) {
				paintingToSave = new File(filePath + ".bin");
				logToSave = new File(filePath + ".txt");
			}

			String filename = paintingToSave.getPath();
			System.out.println("Painting saved at: " + paintingToSave.getAbsolutePath());
			System.out.println(filename.substring(filename.lastIndexOf("."), filename.length()));
			if (filename.substring(filename.lastIndexOf("."), filename.length()).contains(".bin")) {
				filename = paintingToSave.getAbsolutePath().substring(0, filename.lastIndexOf(".")) + ".txt";
				logToSave = new File(filename);
				SaveManager savePainting = new SaveManager(new SavePainting());
				SaveManager saveLog = new SaveManager(new SaveLog());
				savePainting.save(model, paintingToSave);
				saveLog.save(frame, logToSave);
			} else {
				JOptionPane.showMessageDialog(null, "Wrong file extension!");
			}
		}
	}

	/* *************************************************** OPEN PAINTING ******************************************************** */
	public void openPainting() throws IOException, ClassNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(".bin", "bin");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(fileNameExtensionFilter);

		fileChooser.setDialogTitle("Open painting");
		int userSelection = fileChooser.showOpenDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File paintingToLoad = fileChooser.getSelectedFile();
			loadPainting(paintingToLoad);

		}
	}

	/* *************************************************** LOAD PAINTING ******************************************************** */
	@SuppressWarnings("unchecked")
	public void loadPainting(File paintingToLoad) throws FileNotFoundException, IOException, ClassNotFoundException {
		frame.getTextArea().setText("");

		File file = new File(paintingToLoad.getAbsolutePath().replace("bin", "txt"));

		if (file.length() == 0) {
			System.out.println("\"" + paintingToLoad.getName() + "\" file is empty!");
			return;
		}

		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String logLine;

		while ((logLine = bufferedReader.readLine()) != null) {
			frame.getTextArea().append(logLine + "\n");
		}
		bufferedReader.close();

		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(paintingToLoad));
		try {

			model.getShapes().addAll((ArrayList<Shape>) objectInputStream.readObject());
			objectInputStream.close();

		} catch (InvalidClassException ice) {
			ice.printStackTrace();
		} catch (SocketTimeoutException ste) {
			ste.printStackTrace();
		} catch (EOFException eofe) {
			eofe.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		frame.getView().repaint();
	}

	/* *************************************************** SAVE LOG ******************************************************** */
	public void saveLog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save log");
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(".txt", "txt");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(fileNameExtensionFilter);

		if (fileChooser.showSaveDialog(frame.getParent()) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Successfully saved " + fileChooser.getSelectedFile().getName() + " file!");
			File file = fileChooser.getSelectedFile();
			String filePath = file.getAbsolutePath();
			File logToSave = new File(filePath + ".txt");

			SaveManager manager = new SaveManager(new SaveLog());
			manager.save(frame, logToSave);
		}
		frame.getView().repaint();
	}

	/* *************************************************** OPEN LOG ******************************************************** */
	public void openLog() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open log");
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(".txt", "txt");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(fileNameExtensionFilter);

		int userSelection = fileChooser.showOpenDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File logToLoad = fileChooser.getSelectedFile();
			loadLog(logToLoad);
		}
	}
	
	/* *************************************************** LOAD LOG ******************************************************** */
	public void loadLog(File logToLoad) throws IOException {
		try {
			frame.getTextArea().setText("");
			if (logToLoad.length() == 0) {
				System.out.println("\"" + logToLoad.getName() + "\" file is empty!");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(logToLoad));
			String stringLine;
			/* read log line by line */
			while ((stringLine = br.readLine()) != null) {
				logList.add(stringLine);
			}
			br.close();
			frame.getBtnLoadNext().setEnabled(true);

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	/* *************************************************** LOAD NEXT ******************************************************** */
	public void loadNext() {
		Shape shape = null;

		if (logCounter < logList.size()) {

			String line = logList.get(logCounter);

			if (line.contains("Point")) {	
				int x = Integer.parseInt(line.substring(findLeftBracket(1, line) + 1, findComma(1, line)));
				int y = Integer.parseInt(line.substring(findComma(1, line) + 2, findRightBracket(1, line)));
				int edgeColor = Integer.parseInt(line.substring(findLeftBracket(2, line) + 1, findRightBracket(2, line)));

				shape = new Point(x, y, new Color(edgeColor));

			} else if (line.contains("Line")) {
				int startPointX = Integer.parseInt(line.substring(findLeftBracket(1, line) + 1, findComma(1, line)));
				int startPointY = Integer.parseInt(line.substring(findComma(1, line) + 2, findRightBracket(1, line)));
				int endPointX = Integer.parseInt(line.substring(findLeftBracket(2, line) + 1, findComma(2, line)));
				int endPointY = Integer.parseInt(line.substring(findComma(2, line) + 2, findRightBracket(2, line)));
				int color = Integer.parseInt(line.substring(findLeftBracket(3, line) + 1, findRightBracket(3, line)));
				
				shape = new Line(new Point(startPointX, startPointY), new Point(endPointX, endPointY), new Color(color));

			} else if (line.contains("Rectangle")) {

				int upperLeftPointX = Integer.parseInt(line.substring(findLeftBracket(1, line) + 1, findComma(1, line)));
				int upperLeftPointY = Integer.parseInt(line.substring(findComma(1, line) + 2, findRightBracket(1, line)));
				int width = Integer.parseInt(line.substring(findEqualSign(1, line) + 1, findComma(3, line)));
				int height = Integer.parseInt(line.substring(findEqualSign(2, line) + 1, findComma(4, line)));
				int edgeColor = Integer.parseInt(line.substring(findLeftBracket(2, line) + 1, findRightBracket(2, line)));
				int innerColor = Integer.parseInt(line.substring(findLeftBracket(3, line) + 1, findRightBracket(3, line)));

				shape = new Rectangle(new Point(upperLeftPointX, upperLeftPointY), width, height, new Color(edgeColor), new Color(innerColor));
				
			} else if (line.contains("Circle")) {

				int x = Integer.parseInt(line.substring(findLeftBracket(1, line) + 1, findComma(1, line)));
				int y = Integer.parseInt(line.substring(findComma(1, line) + 2, findRightBracket(1, line)));
				int radius = Integer.parseInt(line.substring(findEqualSign(1, line) + 1, findComma(3, line)));
				int edgeColor = Integer.parseInt(line.substring(findLeftBracket(2, line) + 1, findRightBracket(2, line)));
				int innerColor = Integer.parseInt(line.substring(findLeftBracket(3, line) + 1, findRightBracket(3, line)));

				shape = new Circle(new Point(x, y), radius, new Color(edgeColor), new Color(innerColor));
			} else if (line.contains("Donut")) {

				int x = Integer.parseInt(line.substring(findLeftBracket(1, line) + 1, findComma(1, line)));
				int y = Integer.parseInt(line.substring(findComma(1, line) + 2, findRightBracket(1, line)));
				int radius = Integer.parseInt(line.substring(findEqualSign(1, line) + 1, findComma(3, line)));
				int innerRadius = Integer.parseInt(line.substring(findEqualSign(2, line) + 1, findComma(4, line))); 
				int edgeColor = Integer.parseInt(line.substring(findLeftBracket(2, line) + 1, findRightBracket(2, line)));
				int innerColor = Integer.parseInt(line.substring(findLeftBracket(3, line) + 1, findRightBracket(3, line)));

				shape = new Donut(new Point(x, y), radius, innerRadius, new Color(edgeColor), new Color(innerColor));
				
			} else if (line.contains("Hexagon")) {

				int x = Integer.parseInt(line.substring(findLeftBracket(1, line) + 1, findComma(1, line)));
				int y = Integer.parseInt(line.substring(findComma(1, line) + 2, findRightBracket(1, line)));
				int radius = Integer.parseInt(line.substring(findEqualSign(1, line) + 1, findComma(3, line)));
				int edgeColor = Integer.parseInt(line.substring(findLeftBracket(2, line) + 1, findRightBracket(2, line)));
				int innerColor = Integer.parseInt(line.substring(findLeftBracket(3, line) + 1, findRightBracket(3, line)));
				
				shape = new HexagonAdapter(new Point(x, y), radius, new Color(edgeColor), new Color(innerColor));
			}
			/* *************************************************** ADDED ******************************************************** */
			if(line.contains("Added")) {	
				CmdShapeAdd cmdShapeAdd;
				
				if(line.contains("Undo")) {
					cmdShapeAdd = (CmdShapeAdd) undoStack.peek();
					cmdShapeAdd.unexecute();
					undoStack.pop();
					redoStack.push(cmdShapeAdd);
					frame.getTextArea().append("Undo " + cmdShapeAdd.toString());
				} else if (line.contains("Redo")) {
					cmdShapeAdd = (CmdShapeAdd) redoStack.peek();
					cmdShapeAdd.execute(); 
					redoStack.pop();
					undoStack.push(cmdShapeAdd);
					frame.getTextArea().append("Redo " + cmdShapeAdd.toString());
				} else {
					cmdShapeAdd = new CmdShapeAdd(model, shape);
					cmdShapeAdd.execute();
					undoStack.push(cmdShapeAdd);
					redoStack.clear(); 
					frame.getTextArea().append(cmdShapeAdd.toString());
				}
			/* *************************************************** SELECTED ******************************************************** */
			} else if(line.contains("Selected")) {
				CmdShapeSelect cmdShapeSelect;
				
				if (line.contains("Undo")) {
					cmdShapeSelect = (CmdShapeSelect) undoStack.peek();
					cmdShapeSelect.unexecute(); 
					undoStack.pop();
					redoStack.push(cmdShapeSelect);
					frame.getTextArea().append("Undo " + cmdShapeSelect.toString());
				} else if (line.contains("Redo")) {
					cmdShapeSelect = (CmdShapeSelect) redoStack.peek();
					cmdShapeSelect.execute(); 
					redoStack.pop();
					undoStack.push(cmdShapeSelect);
					frame.getTextArea().append("Undo " + cmdShapeSelect.toString());
				} else {
					shape = model.getShapes().get(model.getShapes().indexOf(shape));
					cmdShapeSelect = new CmdShapeSelect(this, shape);
					cmdShapeSelect.execute(); 
					undoStack.push(cmdShapeSelect);
					redoStack.clear();
					frame.getTextArea().append(cmdShapeSelect.toString());
				}
			/* *************************************************** DESELECTED ******************************************************** */
			} else if (line.contains("Deselected")) {
				CmdShapeDeselect cmdShapeDeselect;
				
				if(line.contains("Undo")) {
					cmdShapeDeselect = (CmdShapeDeselect) undoStack.peek();
					cmdShapeDeselect.unexecute(); 
					undoStack.pop();
					redoStack.push(cmdShapeDeselect);
					frame.getTextArea().append("Undo " + cmdShapeDeselect.toString());
				} else if (line.contains("Redo")) {
					cmdShapeDeselect = (CmdShapeDeselect) redoStack.peek();
					cmdShapeDeselect.execute(); 
					redoStack.pop();
					undoStack.push(cmdShapeDeselect);
					frame.getTextArea().append("Redo " + cmdShapeDeselect.toString());
				} else {
					shape = selectedShapes.get(selectedShapes.indexOf(shape));
					cmdShapeDeselect = new CmdShapeDeselect(this, shape);
					cmdShapeDeselect.execute(); 
					undoStack.push(cmdShapeDeselect);
					redoStack.clear(); 
					frame.getTextArea().append(cmdShapeDeselect.toString());
				}
			/* *************************************************** REMOVED ******************************************************** */
			} else if (line.contains("Removed")) {
				CmdShapeRemove cmdShapeRemove;
				
				if(line.contains("Undo")) {
					cmdShapeRemove = (CmdShapeRemove) undoStack.peek();
					cmdShapeRemove.unexecute(); 
					redoShapesList.add(undoShapesList.get(undoShapesList.size() - 1));
					selectedShapes.add(undoShapesList.get(undoShapesList.size() - 1));
					undoShapesList.remove(undoShapesList.size() - 1);
					undoStack.pop();
					redoStack.push(cmdShapeRemove);
					frame.getTextArea().append("Undo " + cmdShapeRemove.toString());
				} else if (line.contains("Redo")) {
					cmdShapeRemove = (CmdShapeRemove) redoStack.peek();
					cmdShapeRemove.execute(); 
					undoShapesList.add(redoShapesList.get(redoShapesList.size() - 1));
					selectedShapes.remove(redoShapesList.get(redoShapesList.size() - 1));
					redoShapesList.remove(redoShapesList.size() - 1);
					redoStack.pop();
					undoStack.push(cmdShapeRemove);
					frame.getTextArea().append("Redo " + cmdShapeRemove.toString());
				} else {
					shape = selectedShapes.get(0);
					cmdShapeRemove = new CmdShapeRemove(model, shape, model.getShapes().indexOf(shape));
					cmdShapeRemove.execute();
					selectedShapes.remove(shape);
					undoShapesList.add(shape);
					undoStack.push(cmdShapeRemove);
					redoStack.clear();
					frame.getTextArea().append(cmdShapeRemove.toString());
				}
			/* *************************************************** TO BACK/TO FRONT/BRING TO BACK/BRING TO FRONT ******************************************************** */
			} else if (line.contains("Moved to back")) {
				CmdToBack cmdToBack;
				
				if (line.contains("Undo")) {
					cmdToBack = (CmdToBack) undoStack.peek();
					cmdToBack.unexecute();
					undoStack.pop();
					redoStack.push(cmdToBack);
					frame.getTextArea().append("Undo " + cmdToBack.toString());
				} else if (line.contains("Redo")) {
					cmdToBack = (CmdToBack) redoStack.peek();
					cmdToBack.execute();
					redoStack.pop();
					undoStack.push(cmdToBack);
					frame.getTextArea().append("Redo " + cmdToBack.toString());
				} else {
					shape = selectedShapes.get(0);
					cmdToBack = new CmdToBack(model, shape);
					cmdToBack.execute(); 
					undoStack.push(cmdToBack);
					redoStack.clear();
					frame.getTextArea().append(cmdToBack.toString());
				}
			} else if (line.contains("Moved to front")) {
				CmdToFront cmdToFront;
				
				if(line.contains("Undo")) {
					cmdToFront = (CmdToFront) undoStack.peek();
					cmdToFront.unexecute(); 
					undoStack.pop();
					redoStack.push(cmdToFront);
					frame.getTextArea().append("Undo " + cmdToFront.toString());
				} else if (line.contains("Redo")) {
					cmdToFront = (CmdToFront) redoStack.peek();
					cmdToFront.execute(); 
					redoStack.pop();
					undoStack.push(cmdToFront);
					frame.getTextArea().append("Redo " + cmdToFront.toString());
				} else {
					shape = selectedShapes.get(0);
					cmdToFront = new CmdToFront(model, shape);
					cmdToFront.execute(); 
					undoStack.push(cmdToFront);
					redoStack.clear();
					frame.getTextArea().append(cmdToFront.toString());
				}
			} else if (line.contains("Bringed to back")) {
				CmdBringToBack cmdBringToBack;

				if (line.contains("Undo")) {
					cmdBringToBack = (CmdBringToBack) undoStack.peek();
					cmdBringToBack.unexecute();
					undoStack.pop();
					redoStack.push(cmdBringToBack);
					frame.getTextArea().append("Undo " + cmdBringToBack.toString());
				} else if (line.contains("Redo")) {
					cmdBringToBack = (CmdBringToBack) redoStack.peek();
					cmdBringToBack.execute();
					redoStack.pop();
					undoStack.push(cmdBringToBack);
					frame.getTextArea().append("Redo " + cmdBringToBack.toString());
				} else {
					shape = selectedShapes.get(0);
					cmdBringToBack = new CmdBringToBack(model, shape);
					cmdBringToBack.execute();
					undoStack.push(cmdBringToBack);
					redoStack.clear();
					frame.getTextArea().append(cmdBringToBack.toString());
				}
			} else if (line.contains("Bringed to front")) {
				CmdBringToFront cmdBringToFront;

				if (line.contains("Undo")) {
					cmdBringToFront = (CmdBringToFront) undoStack.peek();
					cmdBringToFront.unexecute();
					undoStack.pop();
					redoStack.push(cmdBringToFront);
					frame.getTextArea().append("Undo " + cmdBringToFront.toString());
				} else if (line.contains("Redo")) {
					cmdBringToFront = (CmdBringToFront) redoStack.peek();
					cmdBringToFront.execute();
					redoStack.pop();
					undoStack.push(cmdBringToFront);
					frame.getTextArea().append("Redo " + cmdBringToFront.toString());
				} else {
					shape = selectedShapes.get(0);
					cmdBringToFront = new CmdBringToFront(model, shape);
					cmdBringToFront.execute();
					undoStack.push(cmdBringToFront);
					redoStack.clear();
					frame.getTextArea().append(cmdBringToFront.toString());
				}
			/* *************************************************** MODIFY ******************************************************** */
			} else if (line.contains("Modified")) {
				if (shape instanceof Point) {
					CmdPointModify cmdPointModify;
					
					if(line.contains("Undo")) {
						cmdPointModify = (CmdPointModify) undoStack.peek();
						cmdPointModify.unexecute();
						undoStack.pop();
						redoStack.push(cmdPointModify);
						frame.getTextArea().append("Undo " + cmdPointModify.toString());
					} else if (line.contains("Redo")) {
						cmdPointModify = (CmdPointModify) redoStack.peek();
						cmdPointModify.execute(); 
						redoStack.pop();
						undoStack.push(cmdPointModify);
						frame.getTextArea().append("Redo " + cmdPointModify.toString());
					} else {
						shape = selectedShapes.get(0);
						Point newPoint = new Point();
						
						newPoint.setX(Integer.parseInt(line.substring(findLeftBracket(3, line) + 1, findComma(3, line))));
						newPoint.setY(Integer.parseInt(line.substring(findComma(3, line) + 2, findRightBracket(3, line))));
						newPoint.setColor(new Color(Integer.parseInt(line.substring(findLeftBracket(4, line) + 1, findRightBracket(4, line)))));

						cmdPointModify = new CmdPointModify((Point) shape, newPoint);
						cmdPointModify.execute();
						undoStack.push(cmdPointModify);
						redoStack.clear();
						frame.getTextArea().append(cmdPointModify.toString());
					}
				} else if (shape instanceof Line) {
					CmdLineModify cmdLineModify;
					
					if(line.contains("Undo")) {
						cmdLineModify = (CmdLineModify) undoStack.peek();
						cmdLineModify.unexecute();
						undoStack.pop();
						redoStack.push(cmdLineModify);
						frame.getTextArea().append("Undo " + cmdLineModify.toString());
					} else if (line.contains("Redo")) {
						cmdLineModify = (CmdLineModify) redoStack.peek();
						cmdLineModify.execute(); 
						redoStack.pop();
						undoStack.push(cmdLineModify);
						frame.getTextArea().append("Redo " + cmdLineModify.toString());
					} else {
						shape = selectedShapes.get(0);
						Point newStartPoint = new Point();
						Point newEndPoint = new Point();
						
						newStartPoint.setX(Integer.parseInt(line.substring(findLeftBracket(4, line) + 1, findComma(4, line))));
						newStartPoint.setY(Integer.parseInt(line.substring(findComma(4, line) + 2, findRightBracket(4, line))));
						newEndPoint.setX(Integer.parseInt(line.substring(findLeftBracket(5, line) + 1, findComma(5, line))));
						newEndPoint.setY(Integer.parseInt(line.substring(findComma(5, line) + 2, findRightBracket(5, line))));
						
						Line newLine = new Line(newStartPoint, newEndPoint);
						newLine.setColor(new Color(Integer.parseInt(line.substring(findLeftBracket(6, line) + 1, findRightBracket(6, line)))));

						cmdLineModify = new CmdLineModify((Line) shape, newLine);
						cmdLineModify.execute();
						undoStack.push(cmdLineModify);
						redoStack.clear();
						frame.getTextArea().append(cmdLineModify.toString());
					}
				} else if (shape instanceof HexagonAdapter) {
					CmdHexagonModify cmdHexagonModify;
					
					if(line.contains("Undo")) {
						cmdHexagonModify = (CmdHexagonModify) undoStack.peek();
						cmdHexagonModify.unexecute(); 
						undoStack.pop();
						redoStack.push(cmdHexagonModify);
						frame.getTextArea().append("Undo " + cmdHexagonModify.toString());
					} else if (line.contains("Redo")) {
						cmdHexagonModify = (CmdHexagonModify) redoStack.peek();
						cmdHexagonModify.execute(); 
						redoStack.pop();
						undoStack.push(cmdHexagonModify);
						frame.getTextArea().append("Redo " + cmdHexagonModify.toString());
					} else {
						shape = selectedShapes.get(0);
						Point center = new Point();
						int radius, edgeColor, innerColor;
						
						center.setX(Integer.parseInt(line.substring(findLeftBracket(4, line) + 1, findComma(5, line))));
						center.setY(Integer.parseInt(line.substring(findComma(5, line) + 2, findRightBracket(4, line))));
						radius = (Integer.parseInt(line.substring(findEqualSign(2, line) + 1, findComma(7 ,line))));
						innerColor = (Integer.parseInt(line.substring(findLeftBracket(5, line) + 1, findRightBracket(5, line))));
						edgeColor = (Integer.parseInt(line.substring(findLeftBracket(6, line) + 1, findRightBracket(6, line))));
						
						HexagonAdapter newHexagon = new HexagonAdapter(center, radius, new Color(edgeColor), new Color(innerColor));
						
						cmdHexagonModify = new CmdHexagonModify((HexagonAdapter) shape, newHexagon);
						cmdHexagonModify.execute();
						undoStack.push(cmdHexagonModify);
						redoStack.clear();
						frame.getTextArea().append(cmdHexagonModify.toString());
					}
				} else if (shape instanceof Rectangle) {
					CmdRectangleModify cmdRectangleModify;
					
					if (line.contains("Undo")) {
						cmdRectangleModify = (CmdRectangleModify) undoStack.peek();
						cmdRectangleModify.unexecute();
						undoStack.pop();
						redoStack.push(cmdRectangleModify);
						frame.getTextArea().append(cmdRectangleModify.toString());
					} else if (line.contains("Redo")) {
						cmdRectangleModify = (CmdRectangleModify) redoStack.peek();
						cmdRectangleModify.execute(); 
						redoStack.pop();
						undoStack.push(cmdRectangleModify);
						frame.getTextArea().append("Redo " + cmdRectangleModify.toString());
					} else {
						shape = selectedShapes.get(0);
						Point upperLeftPoint = new Point();
						int width, height, edgeColor, innerColor;
						
						upperLeftPoint.setX(Integer.parseInt(line.substring(findLeftBracket(4, line) + 1, findComma(6, line))));
						upperLeftPoint.setY(Integer.parseInt(line.substring(findComma(6, line) + 2, findRightBracket(4, line))));
						
						width = Integer.parseInt(line.substring(findEqualSign(3, line) + 1, findComma(8, line)));
						height = Integer.parseInt(line.substring(findEqualSign(4, line) + 1, findComma(9, line)));
						
						innerColor = Integer.parseInt(line.substring(findLeftBracket(5, line) + 1, findRightBracket(5, line)));
						edgeColor = Integer.parseInt(line.substring(findLeftBracket(6, line) +1, findRightBracket(6, line)));						
					
						Rectangle newRectangle = new Rectangle(upperLeftPoint, width, height, new Color(edgeColor), new Color(innerColor));
						
						cmdRectangleModify = new CmdRectangleModify((Rectangle) shape, newRectangle);
						cmdRectangleModify.execute(); 
						undoStack.push(cmdRectangleModify);
						redoStack.clear();
						frame.getTextArea().append(cmdRectangleModify.toString());
					}
				} else if (shape instanceof Donut) {
					CmdDonutModify cmdDonutModify;
					
					if(line.contains("Undo")) {
						cmdDonutModify = (CmdDonutModify) undoStack.peek();
						cmdDonutModify.unexecute(); 
						undoStack.pop();
						redoStack.push(cmdDonutModify);
						frame.getTextArea().append("Undo " + cmdDonutModify.toString());
					} else if (line.contains("Redo")) {
						cmdDonutModify = (CmdDonutModify) redoStack.peek();
						cmdDonutModify.execute(); 
						redoStack.pop();
						undoStack.push(cmdDonutModify);
						frame.getTextArea().append("Redo " + cmdDonutModify.toString());
					} else {
						shape = selectedShapes.get(0);
						Point center = new Point();
						int radius, innerRadius, edgeColor, innerColor;
						
						center.setX(Integer.parseInt(line.substring(findLeftBracket(4, line) + 1, findComma(6, line))));
						center.setY(Integer.parseInt(line.substring(findComma(6, line) + 2, findRightBracket(4, line))));
						
						radius = Integer.parseInt(line.substring(findEqualSign(3, line) + 1, findComma(8, line)));
						innerRadius = Integer.parseInt(line.substring(findEqualSign(4, line) + 1, findComma(9, line)));
						
						edgeColor = Integer.parseInt(line.substring(findLeftBracket(5, line) + 1, findRightBracket(5, line)));
						innerColor = Integer.parseInt(line.substring(findLeftBracket(6, line) + 1, findRightBracket(6, line)));
						
						Donut newDonut = new Donut(center, radius, innerRadius, new Color(edgeColor), new Color(innerColor));
						
						cmdDonutModify = new CmdDonutModify((Donut) shape, newDonut);
						cmdDonutModify.execute(); 
						undoStack.push(cmdDonutModify);
						redoStack.clear();
						frame.getTextArea().append(cmdDonutModify.toString());	
					}
				} else if (shape instanceof Circle) {
					CmdCircleModify cmdCircleModify;
					
					if(line.contains("Undo")) {
						cmdCircleModify = (CmdCircleModify) undoStack.peek();
						cmdCircleModify.unexecute(); 
						undoStack.pop();
						redoStack.push(cmdCircleModify);
						frame.getTextArea().append("Undo " + cmdCircleModify.toString());
					} else if (line.contains("Redo")) {
						cmdCircleModify = (CmdCircleModify) redoStack.peek();
						cmdCircleModify.execute(); 
						redoStack.pop();
						undoStack.push(cmdCircleModify);
						frame.getTextArea().append("Redo " + cmdCircleModify.toString());
					} else {
						shape = selectedShapes.get(0);
						Point center = new Point();
						int radius, edgeColor, innerColor;
						
						center.setX(Integer.parseInt(line.substring(findLeftBracket(4, line) + 1, findComma(5, line))));
						center.setY(Integer.parseInt(line.substring(findComma(5, line) + 2, findRightBracket(4, line))));
						radius = Integer.parseInt(line.substring(findEqualSign(2, line) + 1, findComma(7, line)));
						edgeColor = Integer.parseInt(line.substring(findLeftBracket(5, line) + 1, findRightBracket(5, line)));
						innerColor = Integer.parseInt(line.substring(findLeftBracket(6, line) + 1, findRightBracket(6, line)));
						
						Circle newCircle = new Circle(center, radius, new Color(edgeColor), new Color(innerColor));
						
						cmdCircleModify = new CmdCircleModify((Circle) shape, newCircle);
						cmdCircleModify.execute(); 
						undoStack.push(cmdCircleModify);
						redoStack.clear();
						frame.getTextArea().append(cmdCircleModify.toString());
					}
				}
			}
			logCounter++;
			frame.repaint();
		} else {
			frame.getBtnLoadNext().setEnabled(false);
			enableDisableButtons();
			frame.getBtnUndo().setEnabled(false);
		}
	}
	/* *************************************************** FIND NUMBER OF LEFT BRACKETS OCCURS ******************************************************** */
	public int findLeftBracket(int n, String line) {
        int occurr = 0;
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '(') {
                occurr += 1;
            }
            if (occurr == n) {
                return i;
            }
        }
        return -1;
    }
	/* *************************************************** FIND NUMBER OF RIGHT BRACKETS OCCURS ******************************************************** */
	public int findRightBracket(int n, String line) {
        int occurr = 0;
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == ')') {
                occurr += 1;
            }
            if (occurr == n) {
                return i;
            }
        }
        return -1;
    }
	/* *************************************************** FIND NUMBER OF COMMAS OCCURS ******************************************************** */
	public int findComma(int n, String line) {
        int occurr = 0;
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == ',') {
                occurr += 1;
            }
            if (occurr == n) {
                return i;
            }
        }
        return -1;
    }
	/* *************************************************** FIND NUMBER OF EQUAL SIGNS OCCURS ******************************************************** */
	public int findEqualSign(int n, String line) {
        int occurr = 0;
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '=') {
                occurr += 1;
            }
            if (occurr == n) {
                return i;
            }
        }
        return -1;
    }	
	/*
	public int findIndexOf(int n, char c, String s) {
        int occurr = 0;
        for(int i = 0; i < s.length(); i++) {

            if(s.charAt(i) == c) {
                occurr += 1;
            }

            if(occurr == n) {
                return i;
            }
        }
        return -1;
    }
	*/
	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}
}
