package mvision;

import java.util.Vector;

/** Sub-class of the Feature extraction module.
 * Responsible for returning a vector with dimension equal 3 * number of color channel
 * and comparing two vectors with a difference return
 */

public class ColorMoment extends FeatureExtractionModule {

	public ColorMoment() {
		/** Number of Color channels: Default to be 3
		 */

		numChannel = 3;
		w1 = 1.0D;	// The weights of the 3 moments in the similarity function

		w2 = 1.0D;// Default values are all 1

		w3 = 1.0D;// i.e. equal weighting

		colorType = FeatureExtractionModule.COLOR_RGB;// Default Color Model

		_featureName = "ColorMoment";
		_featureLength = numChannel;
	}
	/** getFeatureVector method using parameters
	 *
	 * @param image DiscovirImage Object
	 * @param numChannel Number of Color Channel
	 * @param w1 Weight on Average
	 * @param w2 Weight on Variance
	 * @param w3 Weight on Skewness
	 * @param colorType Color Type
	 * @return A vector with dimension = 3 * numChannel
	 */

	public Vector getFeatureVector(DiscovirImage discovirimage, int i,
			double d, double d1, double d2, int j) {
		_featureLength = i;
		w1 = d;
		w2 = d1;
		w3 = d2;
		colorType = j;
		return getFeatureVector(discovirimage);
	}
	
	/** The dimension of the return vector depends on the number of Color channel being processed
	 *
	 * @param image DiscovirImage Object
	 * @return A vector with dimension equal 3 * numChannel
	 */


	public Vector getFeatureVector(DiscovirImage discovirimage) {
		Vector vector = new Vector(3 * numChannel);
		int i = discovirimage.getWidth() * discovirimage.getHeight();
		
//		 Get the RGB Color Model first then transform to user specified model
		// ********** Conversion starts **********

		int ai[] = discovirimage.getPackedRGBPixel();// Get the RGB pixels
        double ad[] = null;
        
        
		if (colorType == FeatureExtractionModule.COLOR_RGB)
			ad = ColorConversion.int2double(ai);
		
//		 HSV conversion

		else if (colorType == FeatureExtractionModule.COLOR_HSV)
			ad = ColorConversion.RGB2HSV(ai);
		
//		 YIQ conversion

		else if (colorType == FeatureExtractionModule.COLOR_YIQ)
			ad = ColorConversion.RGB2YIQ01(ai);
		
//		 CMY conversion

		else if (colorType == FeatureExtractionModule.COLOR_CMY)
			ad = ColorConversion.RGB2CMY01(ai);
		
//		 HSI conversion

		else if (colorType == FeatureExtractionModule.COLOR_HSI)
			ad = ColorConversion.RGB2HSI(ai);
		
//		 XYZ conversion

		else if (colorType == FeatureExtractionModule.COLOR_XYZ)
			ad = ColorConversion.RGB2XYZ01(ai);
		
//		 ********** Conversion ends **********

		double ad1[] = new double[numChannel];
		for (int j = 0; j < numChannel; j++)
			ad1[j] = 0.0D;

		for (int k = 0; k < i; k++) {
			for (int l = 0; l < numChannel; l++)
				ad1[l] += ad[k * 3 + l];//Might need to change if not using RGB


		}
//		 The Average Moments: Ei

		for (int i1 = 0; i1 < numChannel; i1++)
			vector.add(new Double(ad1[i1] / (double) i));

//		 Calculation of the second moment: variance

		double d = 0.0D;
		for (int j1 = 0; j1 < numChannel; j1++) {
			double d1 = 0.0D;
			for (int k1 = 0; k1 < i; k1++)
				d1 += (ad[k1 * 3 + j1] - ((Double) vector.elementAt(j1))
						.doubleValue())
						* (ad[k1 * 3 + j1] - ((Double) vector.elementAt(j1))
								.doubleValue());

//			 The Variance Moments: Oi

			vector.add(new Double(Math.sqrt(d1 / (double) i)));
		}

		// Calculation of the third moment: skewness

		for (int l1 = 0; l1 < numChannel; l1++) {
			double d2 = 0.0D;
			for (int i2 = 0; i2 < i; i2++)
				d2 += (ad[i2 * 3 + l1] - ((Double) vector.elementAt(l1))
						.doubleValue())
						* (ad[i2 * 3 + l1] - ((Double) vector.elementAt(l1))
								.doubleValue())
						* (ad[i2 * 3 + l1] - ((Double) vector.elementAt(l1))
								.doubleValue());

//			 The Skewness Moments: Si

			int j2 = 1;
			if (d2 < 0.0D)
				j2 = -1;
			vector.add(new Double((double) j2
					* Math.pow(((double) j2 * d2) / (double) i,
							0.33333333333333331D)));
		}

		return vector;
	}
	/** Return the similarity value between the 2 vectors using the 3 moments mentioned
	 *
	 * @param a An input vector
	 * @param b An input vector
	 * @return The distance between the 2 vectors
	 */

	public double compareFeatureVector(Vector vector, Vector vector1) {
		double d = 0.0D;
		
//		Array storing the 3 types of moments

		double ad[] = new double[numChannel];
		double ad1[] = new double[numChannel];
		double ad2[] = new double[numChannel];
		double ad3[] = new double[numChannel];
		double ad4[] = new double[numChannel];
		double ad5[] = new double[numChannel];
		
//		 Retrieving the values from the vectors

		for (int i = 0; i < numChannel; i++) {
			ad[i] = ((Double) vector.elementAt(i)).doubleValue();
			ad1[i] = ((Double) vector.elementAt(i + numChannel)).doubleValue();
			ad2[i] = ((Double) vector.elementAt(i + numChannel * 2))
					.doubleValue();
			ad3[i] = ((Double) vector1.elementAt(i)).doubleValue();
			ad4[i] = ((Double) vector1.elementAt(i + numChannel)).doubleValue();
			ad5[i] = ((Double) vector1.elementAt(i + numChannel * 2))
					.doubleValue();
		}

		for (int j = 0; j < numChannel; j++)
			d += w1 * Math.abs(ad[j] - ad3[j]) + w2 * Math.abs(ad1[j] - ad4[j])
					+ w3 * Math.abs(ad2[j] - ad5[j]);

		return d;
	}
	/**
	 * It returns the threshold for determining the similarity of 2 different images.
	 * When 2 images have feature difference less than this threshold is said to be
	 * similar.
	 *
	 * @return the double value of the threshold
	 */

	public double getThreshold() {
		return 0.90000000000000002D;
	}

	int numChannel;

	double w1;

	double w2;

	double w3;

	int colorType;
}
