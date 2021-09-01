package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import mvc.DrawingModel;

public class SavePainting implements Save {

	@Override
	public void save(Object o, File file) {

		DrawingModel model = (DrawingModel) o;
		ObjectOutputStream objectOutputStream = null;

		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(model.getShapes());
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}