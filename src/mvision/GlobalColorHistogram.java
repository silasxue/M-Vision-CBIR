package mvision;

import java.util.Vector;
/**
 *	It is a sub-class of the FeatureExtractionModule class.
 * It can get the global color histogram feature from a
 * DiscovirImage class and return a 64-dimension vector
 * to represent that image. It can compare 2 64-dimension
 * vectors and return their difference.
 *
 */

public class GlobalColorHistogram extends FeatureExtractionModule {

	/**
	 * Constructor
	 * It is the constructor. It initialize some varibles which tell
	 * the feature name, feature length, feature category and version
	 * of this class. The varible _featureDescription tells the class
	 * can get the global clolor histogram from a DiscovirImage.
	 */

	public GlobalColorHistogram() {
		_featureName = "GlobalColorHistogram";
		_featureLength = 64;
		_featureCategory = "color";
		_featureVersion = new Double(1.0D);
		_featureDescription = "Get the global color histogram";
	}

	/**
	 * Method
	 * It gets the feature vector using default parameters
	 * and it will return a 64-dimension vector.
	 *
	 * @param image It is a DiscovirImage
	 * @return globalColorHistogram It is a 64-dimension vector representing the image.
	 */

	public Vector getFeatureVector(DiscovirImage image) {
		Vector globalColorHistogram = new Vector(64);
		int width = image.getWidth();
		int height = image.getHeight();
		int size = width * height;
		int pixels[] = image.getPackedRGBPixel();
		double histogram[] = new double[64];

		System.out.println(" GCH - Get Feature Vector ");

		for (int i = 0; i < 64; i++)
			histogram[i] = 0.0D;

		for (int i = 0; i < size; i++) {
			int k = pixels[i] >> 18 & 0x30 | pixels[i] >> 12 & 0xc
					| pixels[i] >> 6 & 3;
			histogram[k]++;
		}

		for (int i = 0; i < 64; i++)
			globalColorHistogram.add(new Double(histogram[i] / (double) size));

		return globalColorHistogram;
	}
	/**
	 * Method
	 * It returns the threshold for determining the similarity of 2 different images.
	 * When 2 images have feature difference less than this threshold is said to be
	 * similar. It is set to 40% of the maximum feature value.
	 *
	 * @return the double value of the threshold
	 */

	public double getThreshold() {
		return 0.050000000000000003D;
	}
}
