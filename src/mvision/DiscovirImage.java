package mvision;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.IndexColorModel;
import java.awt.image.Kernel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DiscovirImage {

	public DiscovirImage(String filename) {
		_image = null;
		_filename = filename;
	}

	public DiscovirImage(File fileHandle) {
		_image = null;
		try {
			_filename = fileHandle.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DiscovirImage(BufferedImage image) {
		_image = image;
		_filename = "temp";
	}

	public DiscovirImage(BufferedImage image, String filename) {
		_image = image;
		_filename = filename;
	}

	public boolean readImage() {
		try {
			_image = ImageIO.read(new File(_filename));
			_colorModel = _image.getColorModel();
		} catch (Exception e) {
			System.out.println("read Image=" + _filename);
			return false;
		}
		return true;
	}

	public int getWidth() {
		return _image.getWidth();
	}

	public int getHeight() {
		return _image.getHeight();
	}

	public String getFileName() {
		return _filename;
	}

	public int[] getPackedRGBPixel() {
		return _image.getRGB(0, 0, _image.getWidth(), _image.getHeight(), null,
				0, _image.getWidth());
	}

	public int[] getRGBPixel() {
		int size = _image.getHeight() * _image.getWidth();
		int pixel[] = new int[size];
		int pixels[] = new int[size * 3];
		PixelGrabber pg = new PixelGrabber(_image, 0, 0, _image.getWidth(),
				_image.getHeight(), pixel, 0, _image.getWidth());
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		if ((pg.getStatus() & 0x80) != 0) {
			System.out.println("Error in grabbing pixel");
			return null;
		}
		for (int i = 0; i < size; i++) {
			pixels[i * 3] = pixel[i] >> 16 & 0xff;
			pixels[i * 3 + 1] = pixel[i] >> 8 & 0xff;
			pixels[i * 3 + 2] = pixel[i] & 0xff;
		}

		return pixels;
	}

	public int getPackedRGBPixel(int x, int y) {
		return _image.getRGB(x, y);
	}

	public int[] getRGBPixel(int x, int y) {
		int pixel[] = new int[1];
		int pixels[] = new int[3];
		PixelGrabber pg = new PixelGrabber(_image, x, y, 1, 1, pixel, 0, _image
				.getWidth());
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		if ((pg.getStatus() & 0x80) != 0) {
			System.out.println("Error in grabbing pixel");
			return null;
		} else {
			pixels[0] = pixel[0] >> 16 & 0xff;
			pixels[1] = pixel[0] >> 8 & 0xff;
			pixels[2] = pixel[0] & 0xff;
			return pixels;
		}
	}

	public boolean generateThumbnail(String filename, int width, int height) {
		int originalWidth = _image.getWidth();
		int originalHeight = _image.getHeight();
		double x = (double) width / (double) originalWidth;
		double y = (double) height / (double) originalHeight;
		AffineTransformOp transformFilter = new AffineTransformOp(
				new AffineTransform(x, 0.0D, 0.0D, y, 0.0D, 0.0D), 1);
		BufferedImage dest = new BufferedImage(width, height, 5);
		transformFilter.filter(_image, dest);
		File output = new File(filename);
		try {
			ImageIO.write(dest, "jpg", output);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean generateDefaultThumbnail(String filename) {
		int x;
		int y;
		if (_image.getWidth() > _image.getHeight()) {
			x = 100;
			y = (_image.getHeight() * 100) / _image.getWidth();
		} else {
			y = 100;
			x = (_image.getWidth() * 100) / _image.getHeight();
		}
		return generateThumbnail(filename, x, y);
	}

	public void resize(int width, int height) {
		int originalWidth = _image.getWidth();
		int originalHeight = _image.getHeight();
		double x = (double) width / (double) originalWidth;
		double y = (double) height / (double) originalHeight;
		AffineTransformOp transformFilter = new AffineTransformOp(
				new AffineTransform(x, 0.0D, 0.0D, y, 0.0D, 0.0D), 2);
		BufferedImage dest = new BufferedImage(width, height, _image.getType());
		transformFilter.filter(_image, dest);
		_image = null;
		_image = dest;
	}

	public DiscovirImage getResizedImage(int width, int height) {
		int originalWidth = _image.getWidth();
		int originalHeight = _image.getHeight();
		double x = (double) width / (double) originalWidth;
		double y = (double) height / (double) originalHeight;
		AffineTransformOp transformFilter = new AffineTransformOp(
				new AffineTransform(x, 0.0D, 0.0D, y, 0.0D, 0.0D), 2);
		BufferedImage dest = new BufferedImage(width, height, _image.getType());
		transformFilter.filter(_image, dest);
		return new DiscovirImage(dest, _filename);
	}

	public void blur() {
		float weight = 0.1111111F;
		float elements[] = new float[9];
		for (int i = 0; i < 9; i++)
			elements[i] = weight;

		Kernel myKernel = new Kernel(3, 3, elements);
		ConvolveOp simpleBlur = new ConvolveOp(myKernel, 1, null);
		if (_colorModel instanceof IndexColorModel) {
			BufferedImage des = new BufferedImage(_image.getWidth(), _image
					.getHeight(), 5);
			simpleBlur.filter(_image, des);
			_image = des;
		} else {
			BufferedImage des = new BufferedImage(_image.getWidth(), _image
					.getHeight(), _image.getType());
			simpleBlur.filter(_image, des);
			_image = des;
		}
	}

	protected void finalize() throws Throwable {
		_image = null;
		_filename = null;
		_colorModel = null;
	}

	private BufferedImage _image;

	private String _filename;

	private ColorModel _colorModel;
}
