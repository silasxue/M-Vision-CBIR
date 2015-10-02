/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvision;

import java.util.LinkedList;
import java.util.Vector;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Mahdi
 */
public class Bhattacharyya extends FeatureExtractionModule{
    public double calcCorrelation(String input,String img) {
		return Imgproc.compareHist(histogram(input),histogram(img), 2);
	}
	
	public Mat histogram(String img) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat image = Highgui.imread(img);
		
		//Mat image = Highgui.imread("C:\\image1.jpg");
		
	    //Mat src = new Mat(image.height(), image.width(), CvType.CV_8UC2);

	    Imgproc.cvtColor(image, image, Imgproc.COLOR_RGB2HSV);
	    java.util.List<Mat> matList = new LinkedList<Mat>();
	    matList.add(image);
	    Mat histogram = new Mat();
	    MatOfFloat ranges=new MatOfFloat(0,256);
	    MatOfInt histSize = new MatOfInt(255);
	    Imgproc.calcHist(
	                    matList, 
	                    new MatOfInt(0), 
	                    new Mat(), 
	                    histogram , 
	                    histSize , 
	                    ranges);

	    // Create space for histogram image
	    Mat histImage = Mat.zeros( 100, (int)histSize.get(0, 0)[0], CvType.CV_8UC1);
	    
	    
	    histogram.convertTo(histogram,CvType.CV_32F);
	   
	    
	    // Normalize histogram      
	    Core.normalize(histogram, histogram, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() );   
	    // Draw lines for histogram points
	    for( int i = 0; i < (int)histSize.get(0, 0)[0]; i++ )
	    {                   
	            Core.line(
	                    histImage,
	                    new org.opencv.core.Point( i, histImage.rows() ),
	                    new org.opencv.core.Point( i, histImage.rows()-Math.round( histogram.get(i,0)[0] )) ,
	                    new Scalar( 255, 255, 255),
	                    1, 8, 0 );
	    }
	    return histogram;
		
	}

    @Override
    public Vector getFeatureVector(DiscovirImage discovirimage) {
        return null;
    }
}
