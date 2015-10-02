package mvision;

import java.util.Vector;


/**
 *	It is a sub-class of the FeatureExtractionModule class.
 * It can get the local color histogram feature from a
 * DiscovirImage class and return a 1024-dimension vector
 * to represent that image. It can compare 2 1024-dimension
 * vectors and return their difference.
 *
 */

public class LocalColorHistogram extends FeatureExtractionModule {

	/**
	 * Constructor
	 * It is the constructor. It initialize some varibles which tell
	 * the feature name, feature length, feature category and version
	 * of this class. The varible _featureDescription tells the class
	 * can get the local clolor histogram from a DiscovirImage.
	 */

	public LocalColorHistogram() {
		
		super();
		
        System.out.println("Inside Local Color Histogram!!!!");
		this._featureName="LocalColorHistogram";
		this._featureLength=1024;
        this._featureCategory="color";
        this._featureVersion=new Double(1.0);
        this._featureDescription="Get the local color histogram";
	};

	/**
	 * Method
	 * It gets the feature vector using default parameters
	 * and it will return a 1024-dimension vector.
	 *
	 * @param image It is a DiscovirImage
	 * @return localColorHistogram It is a 1024-dimension vector representing the image.
	 */

	public Vector getFeatureVector(DiscovirImage image) 
		{
		return getFeatureVector(image, 4);
	}

	/**
	 * Method
	 * It gets the feature vector using user define parameters
	 * and it will return a d*d*64-dimension vector.
	 *
	 * @param image It is a DiscovirImage
	 * @return localColorHistogram It is a 1024-dimension vector representing the image.
	 */


	public Vector getFeatureVector(DiscovirImage image, int d) {
		final int dimen = d*d;
		Vector localColorHistogram=new Vector(dimen*64);

		int i=0, j=0, k=0;
		int width=image.getWidth();
		int height=image.getHeight();
		int size=width*height;
		int[] pixels=image.getPackedRGBPixel();
		double[][] histogram = new double[dimen][64];

    System.out.println(" LCH - Get Feature Vector ");

		for(i=0 ; i<dimen ; i++){
			histogram[i] = new double[64];
			for(j=0 ; j<64 ; j++){
				histogram[i][j] = 0.0;
			}
		}

		for(i=0 ; i<size ; i++){
//			k = pixels[i*3]/64*16 + pixels[i*3+1]/64*4 + pixels[i*3+2]/64;
			k = (pixels[i]>>18 & 0x30) | (pixels[i]>>12 & 0xc) | (pixels[i]>>6 & 0x3);
			j =	decideLocal(i, width, height, d);
			//System.out.println("Pixel"+i);
			//System.out.println("Area"+j);
			histogram[j][k] += 1.0;
		}

		for(i=0 ; i<dimen ; i++)
			for(j=0 ; j<64 ; j++)
				localColorHistogram.add(new Double(histogram[i][j]/(size/dimen)));


		return localColorHistogram;
	}
	/**
	 * Method
	 * It decides the pixel j is belongs to which local area of the image.
	 *
	 * @param j It is the jth pixel of the inputted image.
	 * @param w It is the width of the inputted image.
	 * @param h It is the height of the inputted image.
	 * @param d It is the dimension used in dividing the image..
	 * @return area It is an integer representing a local area of the inputted image.
	 */


	private int decideLocal(double j, double w, double h, int d){
		double localWidth = w/d;
		double localHeight = h/d;

		int area = (int)(j/w/localHeight)*d + (int)((j%w)/localWidth);
		return area;
	}
	/**
	 * Method
	 * It returns the threshold for determining the similarity of 2 different images.
	 * When 2 images have feature difference less than this threshold is said to be
	 * similar. It is set to 20% of the maximum feature value.
	 *
	 * @return the double value of the threshold
	 */

	public double getThreshold() {
		return 2.0*16.0*0.2;
	}
}

