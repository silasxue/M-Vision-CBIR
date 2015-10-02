package mvision;

import java.awt.image.BufferedImage;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class FeatureExtractionModule {

	public FeatureExtractionModule() {
		_featureLength = 0;
		_featureName = "FeatureExtractionModule";
		_featureDescription = "This is the abstract class for Discovir Feature Extraction Module";
		_featureVersion = new Double(0.10000000000000001D);
		_featureCategory = "Unknown";
	}

	public double getThreshold() {
		return 15D;
	}

	public String getFeatureName() {
		return _featureName;
	}

	public int getFeatureLength() {
		return _featureLength;
	}

	public Double getFeatureVersion() {
		return _featureVersion;
	}

	public String getFeatureCategory() {
		return _featureCategory;
	}

	public String getFeatureDescription() {
		return _featureDescription;
	}

	public abstract Vector getFeatureVector(DiscovirImage discovirimage);

	public Vector getFeatureVector(BufferedImage image) {
		DiscovirImage dImage = new DiscovirImage(image);
		return getFeatureVector(dImage);
	}

	public double compareFeatureVector(Vector a, Vector b) {
		int noOfDimension = a.size();
		if (noOfDimension != b.size())
			return 99999.899999999994D;
		double total = 0.0D;
		for (int i = 0; i < noOfDimension; i++) {
			double doubleA = ((Double) a.elementAt(i)).doubleValue();
			double doubleB = ((Double) b.elementAt(i)).doubleValue();
			total += (doubleA - doubleB) * (doubleA - doubleB);
		}

		return Math.sqrt(total / (double) noOfDimension);
	}

	public double compareFeatureString(String a, String b) {
		return compareFeatureVector(string2Vector(a), string2Vector(b));
	}

	public String vector2String(Vector vec) {
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < vec.size() - 1; i++)
			temp.append(((Double) vec.elementAt(i)).doubleValue() + ",");

		temp.append(((Double) vec.elementAt(vec.size() - 1)).doubleValue());
		return temp.toString();
	}

	public Vector string2Vector(String str) {
		StringTokenizer strToken = new StringTokenizer(str, ",");
		Vector vec = new Vector(strToken.countTokens());
		for (; strToken.hasMoreElements(); vec.add(new Double(strToken
				.nextToken())))
			;
		return vec;
	}

	protected String _featureName;

	protected int _featureLength;

	protected Double _featureVersion;

	protected String _featureCategory;

	protected String _featureDescription;

	public static int COLOR_RGB = 1;

	public static int COLOR_HSV = 2;

	public static int COLOR_YIQ = 3;

	public static int COLOR_HSI = 4;

	public static int COLOR_XYZ = 5;

	public static int COLOR_CMY = 6;

}
