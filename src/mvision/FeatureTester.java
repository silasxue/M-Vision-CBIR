package mvision;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JPanel;

public class FeatureTester {
	public FeatureTester(String argv[], double d, double d1, JPanel view) {
		// }
		this.d = d;
		this.d1 = d1;
		this.view = view;
		for (int i = 0; i < argv.length; i++)
			System.out.println(argv[i]);
		if (argv.length != 2) {
			System.out
					.println("Usage: java Tester <ModuleName> <NumberOfImage> <DirectoryName> <input file name>");
		} else {
			Runtime rt = Runtime.getRuntime();
			String modulename = argv[0];
                        modulN=modulename;
			// String directoryName = argv[2];
			System.out.println("Module Name " + modulename);
			try {
				// max_count = Integer.parseInt(argv[1]);
				UD = new UserDAO();
				max_count = UD.retrive();
				feature = new Vector[max_count];
				Class moduleClass = Class.forName(modulename);
				module = (FeatureExtractionModule) moduleClass.newInstance();
				moduleClass = null;
				startTime = new Date();
				processFile1(new File(argv[1]));
				inputImage= (String)argv[1];
				list = UD.getFileList();
				
				if(Class.forName(modulename)==Correlation.class){
					System.out.println("image Name " );
					
					col();
				}
                                else if(Class.forName(modulename)==ChiSquare.class){
					System.out.println("image Name " );
					
					calcChiSquare();
				}
                                else if(Class.forName(modulename)==Intersection.class){
					System.out.println("image Name " );
					
					calcIntersection();
				}
                                else if(Class.forName(modulename)==Bhattacharyya.class){
					System.out.println("image Name " );
					
					calcBhattacharyya();
				}
                                
				else {
				for (int i = 0; i < list.size(); i++) {
					
						processFile(new File((String) list.get(i)));
					}
					
					
				}
				Date currentTime = new Date();
				long millisecond = currentTime.getTime() - startTime.getTime();

				// System.out.println("Total Time used = " + millisecond + "
				// ms");
			} catch (Exception e) {
				System.out.println("ERROR: Cons");
				e.printStackTrace();
			}
			return;
		}
	}

	private static void processFile1(File fileHandle) {
		DiscovirImage image = new DiscovirImage(fileHandle);
		if (!image.readImage()) {
			System.out.println("Error in reading image" + fileHandle.getName());
			return;
		}
		input = module.getFeatureVector(image);
		image = null;
	}

	private void processFile(File fileHandle) {
		DiscovirImage image = new DiscovirImage(fileHandle);
		if (!image.readImage()) {
			System.out.println("Error in reading image");
			return;
		}
		// System.out.println("count="+count);
		feature[count++] = module.getFeatureVector(image);
		
		image = null;
		if (count == max_count) {
			Date currentTime = new Date();
			long millisecond = currentTime.getTime() - startTime.getTime();
			compute();
		}
	}
	
	private void col() {
		Vector distance = new Vector();
                Correlation c = new Correlation();
		for (int i = 0; (i < list.size() - 1); i++) {
		distance.add(new Double (c.calcCorrelation(inputImage,(String)list.get(i))));
		}
		ArrayList al = new ArrayList();
		ArrayList a2 = new ArrayList();
		
		for (int i = 0; (i < list.size() - 1); i++) {
			if ((((Double) distance.elementAt(i)).doubleValue()) <= d
					&& (((Double) distance.elementAt(i)).doubleValue()) >= d1) {
				al.add((String) list.get(i));
				a2.add(((Double) distance.elementAt(i)).doubleValue());
				// System.out.println((String)list.get(i)+"="+distance.elementAt(i));

			}
		
		}
		double a;
		String b;
		for (int i = 0; i < a2.size(); i++) {
			for (int j = i+1; j < a2.size(); j++) {
				if((Double)a2.get(i)>(Double)a2.get(j)){
					a=(Double)a2.get(i);
					a2.set(i,a2.get(j));
					a2.set(j,a);
					b=(String)al.get(i);
					al.set(i,al.get(j));
					al.set(j,b);
					
				}
					
				
			}
			
		}
		outputList = null;
		outputList = al;
		count = 0;
		
		
		
		
	}
        private void calcChiSquare() {
		Vector distance = new Vector();
                ChiSquare c = new ChiSquare();
		for (int i = 0; (i < list.size() - 1); i++) {
		distance.add(new Double (c.calcCorrelation(inputImage,(String)list.get(i))));
		System.out.println(distance.get(i));
		}
		ArrayList al = new ArrayList();
		ArrayList a2 = new ArrayList();
		
		for (int i = 0; (i < list.size() - 1); i++) {
			if ((((Double) distance.elementAt(i)).doubleValue()) <= d
					&& (((Double) distance.elementAt(i)).doubleValue()) >= d1) {
				al.add((String) list.get(i));
				a2.add(((Double) distance.elementAt(i)).doubleValue());
				// System.out.println((String)list.get(i)+"="+distance.elementAt(i));

			}
		
		}
		double a;
		String b;
		for (int i = 0; i < a2.size(); i++) {
			for (int j = i+1; j < a2.size(); j++) {
				if((Double)a2.get(i)>(Double)a2.get(j)){
					a=(Double)a2.get(i);
					a2.set(i,a2.get(j));
					a2.set(j,a);
					b=(String)al.get(i);
					al.set(i,al.get(j));
					al.set(j,b);
					
				}
					
				
			}
			
		}
		outputList = null;
		outputList = al;
		count = 0;
		
		
		
		
	}
        private void calcIntersection() {
		Vector distance = new Vector();
                Intersection c = new Intersection();
		for (int i = 0; (i < list.size() - 1); i++) {
		distance.add(new Double (c.calcCorrelation(inputImage,(String)list.get(i))));
		System.out.println(distance.get(i));
		}
		ArrayList al = new ArrayList();
		ArrayList a2 = new ArrayList();
		
		for (int i = 0; (i < list.size() - 1); i++) {
			if ((((Double) distance.elementAt(i)).doubleValue()) <= d
					&& (((Double) distance.elementAt(i)).doubleValue()) >= d1) {
				al.add((String) list.get(i));
				a2.add(((Double) distance.elementAt(i)).doubleValue());
				// System.out.println((String)list.get(i)+"="+distance.elementAt(i));

			}
		
		}
		double a;
		String b;
		for (int i = 0; i < a2.size(); i++) {
			for (int j = i+1; j < a2.size(); j++) {
				if((Double)a2.get(i)<(Double)a2.get(j)){
					a=(Double)a2.get(i);
					a2.set(i,a2.get(j));
					a2.set(j,a);
					b=(String)al.get(i);
					al.set(i,al.get(j));
					al.set(j,b);
					
				}
					
				
			}
			
		}
		outputList = null;
		outputList = al;
		count = 0;
		
		
		
		
	}
        private void calcBhattacharyya() {
		Vector distance = new Vector();
                Bhattacharyya c = new Bhattacharyya();
		for (int i = 0; (i < list.size() - 1); i++) {
		distance.add(new Double (c.calcCorrelation(inputImage,(String)list.get(i))));
		System.out.println(distance.get(i));
		}
		ArrayList al = new ArrayList();
		ArrayList a2 = new ArrayList();
		
		for (int i = 0; (i < list.size() - 1); i++) {
			if ((((Double) distance.elementAt(i)).doubleValue()) <= d
					&& (((Double) distance.elementAt(i)).doubleValue()) >= d1) {
				al.add((String) list.get(i));
				a2.add(((Double) distance.elementAt(i)).doubleValue());
				// System.out.println((String)list.get(i)+"="+distance.elementAt(i));

			}
		
		}
		double a;
		String b;
		for (int i = 0; i < a2.size(); i++) {
			for (int j = i+1; j < a2.size(); j++) {
				if((Double)a2.get(i)>(Double)a2.get(j)){
					a=(Double)a2.get(i);
					a2.set(i,a2.get(j));
					a2.set(j,a);
					b=(String)al.get(i);
					al.set(i,al.get(j));
					al.set(j,b);
					
				}
					
				
			}
			
		}
		outputList = null;
		outputList = al;
		count = 0;
		
		
		
		
	}

	private void compute() {
		Vector distance = new Vector();
		int max_index = (count * (count - 1)) / 2;
		for (int i = 0; i < count; i++) {
			distance.add(new Double(module.compareFeatureVector(input,
					feature[i])));
			
			
			

		}

		// System.out.println(distance);
		// System.out.println(list);
		ArrayList al = new ArrayList();
		ArrayList a2 = new ArrayList();
		
		for (int i = 0; (i < list.size() - 1); i++) {
			if ((((Double) distance.elementAt(i)).doubleValue()) <= d
					&& (((Double) distance.elementAt(i)).doubleValue()) >= d1) {
				al.add((String) list.get(i));
				a2.add(((Double) distance.elementAt(i)).doubleValue());
				// System.out.println((String)list.get(i)+"="+distance.elementAt(i));

			}
		
		}
		double a;
		String b;
		for (int i = 0; i < a2.size(); i++) {
			for (int j = i+1; j < a2.size(); j++) {
				if((Double)a2.get(i)>(Double)a2.get(j)){
					a=(Double)a2.get(i);
					a2.set(i,a2.get(j));
					a2.set(j,a);
					b=(String)al.get(i);
					al.set(i,al.get(j));
					al.set(j,b);
					
				}
					
				
			}
			
		}
		
		
		
		
		
		
		
		// view.list=al;
		// view.repaint();
		outputList = null;
		outputList = al;
		count = 0;
	}

	public ArrayList getOutputList() {
		return outputList;
	}

	private static int count = 0;

	private static int max_count = 0;

	private static FeatureExtractionModule module = null;

	private static Date startTime = null;

	private static Vector feature[] = null;

	private static Vector input = null;

	private static File children[];

	private static double d = 0;

	private static double d1 = 0;

	private static JPanel outputPanel;

	private static ArrayList list, outputList;
	
	private static String inputImage;

	JPanel view;

	UserDAO UD = null;
        String modulN;
        
}
