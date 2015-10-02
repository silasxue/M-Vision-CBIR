package mvision;

import java.awt.Color;
/** The methods of this class are used to return an array of values containing the converted
 * color model, by inputting an array of values used to convert.
 * All values in the array for both input and output are for separate components.
 * For example, two RGB pixel is a 'double' array of size 6, [R, G, B, R, G, B].
 *
 
 */

public class ColorConversion {
	
	
	public ColorConversion() {
	}
	/** Public method responsible for magnifying and reducing the input ranges
	 * double:	[0,1]
	 * int:	[0,255]
	 *
	 * @param d a double array
	 * @return Int array
	 */
	public static int[] double2int(double d[]) {
		int result[] = new int[d.length / 3];
		for (int i = 0; i < d.length; i += 3) {
			result[i] = (int) Math.round(d[i] * 255D) << 16;
			result[i] |= (int) Math.round(d[i + 1] * 255D) << 8;
			result[i] |= (int) Math.round(d[i + 2] * 255D);
		}

		return result;
	}
	
	/** Public method responsible for magnifying and reducing the input ranges
	 * double:	[0,1]
	 * int:	[0,255]
	 *
	 * @param in int array
	 * @return double array
	 */


	public static double[] int2double(int in[]) {
		double result[] = new double[in.length * 3];
		for (int i = 0; i < in.length; i++) {
			result[i * 3] = (double) (in[i] >> 16 & 0xff) / 255D;
			result[i * 3 + 1] = (double) (in[i] >> 8 & 0xff) / 255D;
			result[i * 3 + 2] = (double) (in[i] & 0xff) / 255D;
		}

		return result;
	}
	/** Convert RGB to CMY
	 * Input	: [0,1]
	 * Output	: [0,1]
	 *
	 * @param rgb double array of RGB
	 * @return double array of CMY
	 */

	public static double[] RGB2CMY01(double rgb[]) {
		double cmy[] = new double[rgb.length];
		int i = 0;
		for (i = 0; i < rgb.length; i++)
			cmy[i] = 1.0D - rgb[i];

		return cmy;
	}
	/** Convert RGB to CMY
	 * Input	: [0,255]
	 * Output	: [0,255]
	 *
	 * @param rgb int array of RGB
	 * @return int array of CMY
	 */

	public static int[] RGB2CMY256(int rgb[]) {
		int cmy[] = new int[rgb.length];
		for (int i = 0; i < rgb.length; i++)
			cmy[i] = ~rgb[i];

		return cmy;
	}
	/** Convert RGB to CMY
	 * Input	: [0,1]
	 * Output	: [0,255]
	 *
	 * @param rgb double array of RGB
	 * @return int array of CMY
	 */

	public static int[] RGB2CMY256(double rgb[]) {
		return double2int(RGB2CMY01(rgb));
	}
	/** Convert RGB to CMY
	 * Input	: [0,255]
	 * Output	: [0,1]
	 *
	 * @param rgb int array of RGB
	 * @return double array of CMY
	 */

	public static double[] RGB2CMY01(int rgb[]) {
		return RGB2CMY01(int2double(rgb));
	}
	
	/** Convert CMY to RGB
	 * Input	: [0,1]
	 * Output	: [0,1]
	 *
	 * @param cmy double array of CMY
	 * @return double array of RGB
	 */


	public static double[] CMY2RGB01(double cmy[]) {
		double rgb[] = new double[cmy.length];
		int i = 0;
		for (i = 0; i < cmy.length; i++)
			rgb[i] = 1.0D - cmy[i];

		return rgb;
	}
	/** Convert CMY to RGB
	 * Input	: [0,255]
	 * Output	: [0,255]
	 *
	 * @param cmy int array of CMY
	 * @return int array of RGB
	 */

	public static int[] CMY2RGB256(int cmy[]) {
		int rgb[] = new int[cmy.length];
		for (int i = 0; i < cmy.length; i++)
			rgb[i] = ~cmy[i];

		return cmy;
	}
	/** Convert CMY to RGB
	 * Input	: [0,1]
	 * Output	: [0,255]
	 *
	 * @param rgb double array of CMY
	 * @return int array of RGB
	 */


	public static int[] CMY2RGB256(double rgb[]) {
		return double2int(CMY2RGB01(rgb));
	}
	/** Convert CMY to RGB
	 * Input	: [0,255]
	 * Output	: [0,1]
	 *
	 * @param rgb int array of RGB
	 * @return double array of RGB
	 */

	public static double[] CMY2RGB01(int rgb[]) {
		return CMY2RGB01(int2double(rgb));
	}

	/** Convert RGB to YIQ
	 * Input	: [0,1]
	 * Output	: [0,1]
	 *
	 * @param rgb double array of RGB
	 * @return double array of YIQ
	 */

	public static double[] RGB2YIQ01(double rgb[]) {
		double yiq[] = new double[rgb.length];
		int i = 0;
		for (i = 0; i < rgb.length; i += 3) {
			yiq[i] = 0.29899999999999999D * rgb[i] + 0.58699999999999997D
					* rgb[i + 1] + 0.114D * rgb[i + 2];
			yiq[i + 1] = 0.59599999999999997D * rgb[i] - 0.27500000000000002D
					* rgb[i + 1] - 0.32100000000000001D * rgb[i + 2];
			yiq[i + 2] = (0.21199999999999999D * rgb[i] - 0.52300000000000002D * rgb[i + 1])
					+ 0.311D * rgb[i + 2];
		}

		return yiq;
	}
	/** Convert RGB to YIQ
	 * Input	: [0,255]
	 * Output	: [0,255]
	 *
	 * @param rgb int array of RGB
	 * @return int array of YIQ
	 */

	public static int[] RGB2YIQ256(int rgb[]) {
		return double2int(RGB2YIQ01(int2double(rgb)));
	}
	/** Convert RGB to YIQ
	 * Input	: [0,1]
	 * Output	: [0,255]
	 *
	 * @param rgb double array of RGB
	 * @return int arry of YQI
	 */

	public static int[] RGB2YIQ256(double rgb[]) {
		return double2int(RGB2YIQ01(rgb));
	}
	/** Convert RGB to YIQ
	 * Input	: [0,255]
	 * Output	: [0,1]
	 *
	 * @param rgb int array of RGB
	 * @return double array of YIQ
	 */

	public static double[] RGB2YIQ01(int rgb[]) {
		return RGB2YIQ01(int2double(rgb));
	}
	/** Convert YIQ to RGB
	 * Input	: [0,1]
	 * Output	: [0,1]
	 *
	 * @param yiq double array of YIQ
	 * @return double array of RGB
	 */

	public static double[] YIQ2RGB01(double yiq[]) {
		double rgb[] = new double[yiq.length];
		int i = 0;
		for (i = 0; i < rgb.length; i += 3) {
			rgb[i] = yiq[i] + 0.95599999999999996D * yiq[i + 1] + 0.621D
					* yiq[i + 2];
			rgb[i + 1] = yiq[i] - 0.27200000000000002D * yiq[i + 1]
					- 0.64700000000000002D * yiq[i + 2];
			rgb[i + 2] = (yiq[i] - 1.105D * yiq[i + 1]) + 1.702D * yiq[i + 2];
		}

		return rgb;
	}
	/** Convert YIQ to RGB
	 * Input	: [0,255]
	 * Output	: [0,255]
	 *
	 * @param rgb int array of YIQ
	 * @return int array of RGB
	 */

	public static int[] YIQ2RGB256(int yiq[]) {
		return double2int(YIQ2RGB01(int2double(yiq)));
	}
	/** Convert YIQ to RGB
	 * Input	: [0,1]
	 * Output	: [0,255]
	 *
	 * @param rgb double array of YIQ
	 * @return int array of RGB
	 */

	public static int[] YIQ2RGB256(double yiq[]) {
		return double2int(YIQ2RGB01(yiq));
	}
	/** Convert YIQ to RGB
	 * Input	: [0,255]
	 * Output	: [0,1]
	 *
	 * @param rgb int array of YIQ
	 * @return double array of RGB
	 */

	public static double[] YIQ2RGB01(int yiq[]) {
		return YIQ2RGB01(int2double(yiq));
	}
	/** Transform the RGB array into a gray scale array
	 *
	 * @param rgb int array of RGB
	 * @level number of gray scale level
	 * @return gray int array of grayscal
	 */


	public static int[] RGB2grayscale(int rgb[], int level) {
		int Y[] = new int[rgb.length];
		int i = 0;
		for (i = 0; i < rgb.length; i++)
			Y[i] = (int) (((0.29899999999999999D
					* (double) (rgb[i] >> 16 & 0xff) + 0.58699999999999997D
					* (double) (rgb[i] >> 8 & 0xff) + 0.114D * (double) (rgb[i] & 0xff)) / 256D)
					* (double) level + 0.5D);

		return Y;
	}
	/** Transform the RGB array into a gray scale array of 256 levels
	 *
	 * @param rgb int array of RGB
	 * @return gray int array of grayscal of 256 levels
	 */

	public static int[] RGB2grayscale256(int rgb[]) {
		return RGB2grayscale(rgb, 256);
	}
	/** RGB model convert to HSI model
	 * Input	: [0,1]
	 * Output	: [0,1] for SI; [0,2PI] for H
	 *
	 * @param rgb double array of RGB
	 * @return double array of HSI
	 */

	public static double[] RGB2HSI(double rgb[]) {
		double hsi[] = new double[rgb.length];
		int i = 0;
		for (i = 0; i < rgb.length; i += 3) {
			double r = rgb[i];
			double g = rgb[i + 1];
			double b = rgb[i + 2];
			double min = Math.min(r, g);
			min = Math.min(min, b);
			hsi[i] = Math.acos((0.5D * ((r - g) + (r - b)))
					/ Math.pow(Math.pow(r - g, 2D) + (r - b) * (g - b), 0.5D));
			hsi[i + 1] = 1.0D - (3D / (r + g + b)) * min;
			hsi[i + 2] = (r + g + b) / 3D;
			if (b / hsi[i + 2] > g / hsi[i + 2])
				hsi[i] = 6.2831853071795862D - hsi[i];
			if (r == g && g == b) {
				hsi[i] = -1D;
				if (hsi[i + 2] == 0.0D)
					hsi[i + 1] = -1D;
			}
		}

		return hsi;
	}
	/** RGB model convert to HSI model
	 * Input	: [0,255]
	 * Output	: [0,1] for SI; [0,2PI] for H
	 *
	 * @param rgb int array of RGB
	 * @return double array of HSI
	 */

	public static double[] RGB2HSI(int rgb[]) {
		return RGB2HSI(int2double(rgb));
	}

	public static double[] HSI2RGB01(double hsi[]) {
		double rgb[] = new double[hsi.length];
		int k = 0;
		for (k = 0; k < hsi.length; k += 3) {
			double h = hsi[k];
			double s = hsi[k + 1];
			double i = hsi[k + 2];
			if (0.0D < h && h <= 2.0943951023931953D) {
				rgb[k] = i
						* (1.0D + (s * Math.cos(h))
								/ Math.cos(1.0471975511965976D - h));
				rgb[k + 2] = i * (1.0D - s);
				rgb[k + 1] = 3D * i * (1.0D - (rgb[k] + rgb[k + 2]) / (3D * i));
			} else if (2.0943951023931953D < h && h <= 4.1887902047863905D) {
				h -= 2.0943951023931953D;
				rgb[k + 1] = i * (1.0D - s);
				rgb[k] = i
						* (1.0D + (s * Math.cos(h))
								/ Math.cos(1.0471975511965976D - h));
				rgb[k + 2] = 3D * i * (1.0D - (rgb[k] + rgb[k + 1]) / (3D * i));
			} else if (4.1887902047863905D < h && h <= 6.2831853071795862D) {
				h -= 4.1887902047863905D;
				rgb[k + 2] = i
						* (1.0D + (s * Math.cos(h))
								/ Math.cos(1.0471975511965976D - h));
				rgb[k + 1] = i * (1.0D - s);
				rgb[k] = 3D * i * (1.0D - (rgb[k + 1] + rgb[k + 2]) / (3D * i));
			} else {
				System.out.println("The hue value is out of range.");
			}
		}

		return rgb;
	}

	public static int[] HSI2RGB256(double hsi[]) {
		return double2int(HSI2RGB01(hsi));
	}

	public static double[] RGB2XYZ01(double rgb[]) {
		double xyz[] = new double[rgb.length];
		int i = 0;
		for (i = 0; i < rgb.length; i += 3) {
			xyz[i] = 0.41245300000000001D * rgb[i] + 0.35758000000000001D
					* rgb[i + 1] + 0.180423D * rgb[i + 2];
			xyz[i + 1] = 0.212671D * rgb[i] + 0.71516000000000002D * rgb[i + 1]
					+ 0.072168999999999997D * rgb[i + 2];
			xyz[i + 2] = 0.019334D * rgb[i] + 0.11919299999999999D * rgb[i + 1]
					+ 0.95022700000000004D * rgb[i + 2];
		}

		return xyz;
	}

	public static int[] RGB2XYZ256(int rgb[]) {
		return double2int(RGB2XYZ01(int2double(rgb)));
	}

	public static int[] RGB2XYZ256(double rgb[]) {
		return double2int(RGB2XYZ01(rgb));
	}

	public static double[] RGB2XYZ01(int rgb[]) {
		return RGB2XYZ01(int2double(rgb));
	}

	public static double[] XYZ2RGB01(double xyz[]) {
		double rgb[] = new double[xyz.length];
		int i = 0;
		for (i = 0; i < xyz.length; i += 3) {
			rgb[i] = 3.2404790000000001D * xyz[i] - 1.53715D * xyz[i + 1]
					- 0.49853500000000001D * xyz[i + 2];
			rgb[i + 1] = -0.96925600000000001D * xyz[i] + 1.8759920000000001D
					* xyz[i + 1] + 0.041556000000000003D * xyz[i + 2];
			rgb[i + 2] = (0.055648000000000003D * xyz[i] - 0.204043D * xyz[i + 1])
					+ 1.0573109999999999D * xyz[i + 2];
		}

		return rgb;
	}

	public static int[] XYZ2RGB256(int rgb[]) {
		return double2int(XYZ2RGB01(int2double(rgb)));
	}

	public static int[] XYZ2RGB256(double rgb[]) {
		return double2int(XYZ2RGB01(rgb));
	}

	public static double[] XYZ2RGB01(int rgb[]) {
		return XYZ2RGB01(int2double(rgb));
	}

	public static double[] RGB2HSV(int rgb[]) {
		double hsv[] = new double[rgb.length * 3];
		float temp[] = new float[3];
		int i = 0;
		for (i = 0; i < rgb.length; i++) {
			Color.RGBtoHSB(rgb[i] >> 24 & 0xff, rgb[i] >> 16 & 0xff,
					rgb[i] & 0xff, temp);
			hsv[3 * i] = temp[0];
			hsv[3 * i + 1] = temp[1];
			hsv[3 * i + 2] = temp[2];
		}

		return hsv;
	}

	public static int[] HSV2RGB256(double hsv[]) {
		int rgb[] = new int[hsv.length / 3];
		int i = 0;
		for (i = 0; i < hsv.length / 3; i++)
			rgb[i] = Color.HSBtoRGB((float) hsv[3 * i], (float) hsv[3 * i + 1],
					(float) hsv[3 * i + 2]);

		return rgb;
	}
}
