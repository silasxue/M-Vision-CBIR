package mvision;

import java.util.Vector;

/**
 * It is a sub-class of the FeatureExtractionModule class.
 * It can get the average RGB feature from a
 * DiscovirImage class and return a 3-dimension vector
 * to represent that image.
 *
 */

public class AverageRGB extends FeatureExtractionModule {
	
	
	/** Constructor for initialization
	 */
public AverageRGB() {
		_featureName = "AverageRGB";
		_featureLength = 3;
		_featureCategory = "color";
		_featureVersion = new Double(1.0D);
		_featureDescription = "Average the RGB values of whole image.";
	}

	/** Extract the averageRGB vector from an image
	 *
	 * @param image image to be extracted
	 * @return a Vector compose of 3 Doubles (averageRed, averageGreen,
	 *     averageBlue)
	 */

	public Vector getFeatureVector(DiscovirImage discovirimage) {
		Vector vector = new Vector(3);
		int i = discovirimage.getWidth() * discovirimage.getHeight();
		int ai[] = discovirimage.getRGBPixel();
		int j = 0;
		int k = 0;
		int l = 0;
		for (int i1 = 0; i1 < i; i1++) {
			j += ai[i1 * 3];
			k += ai[i1 * 3 + 1];
			l += ai[i1 * 3 + 2];
		}

		double d = (double) j / (double) i;
		double d1 = (double) k / (double) i;
		double d2 = (double) l / (double) i;
		vector.add(new Double(d));
		vector.add(new Double(d1));
		vector.add(new Double(d2));
		return vector;
	}

	public double compareFeatureVector(Vector vector, Vector vector1) {
		Double double1 = (Double) vector.elementAt(0);
		Double double2 = (Double) vector.elementAt(1);
		Double double3 = (Double) vector.elementAt(2);
		Double double4 = (Double) vector1.elementAt(0);
		Double double5 = (Double) vector1.elementAt(1);
		Double double6 = (Double) vector1.elementAt(2);
		double d = (double1.doubleValue() - double4.doubleValue())
				* (double1.doubleValue() - double4.doubleValue());
		double d1 = (double2.doubleValue() - double5.doubleValue())
				* (double2.doubleValue() - double5.doubleValue());
		double d2 = (double3.doubleValue() - double6.doubleValue())
				* (double3.doubleValue() - double6.doubleValue());
		return Math.sqrt((d + d1 + d2) / 3D);
	}
}