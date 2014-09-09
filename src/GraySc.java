/**
 * GrayScale
 *
 * @author Jacob Stuart
 * @lab 806
 * @date September 08, 2014
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraySc {
	private BufferedImage image;
	private BufferedImage gray;

	GraySc (BufferedImage image){
		this.image = image;
	}


	public static void main(String[] args){
		BufferedImage input = null;

		try {
			input = ImageIO.read(new File(args[0]));
		} catch (IOException e){
			e.printStackTrace();
		}

		GraySc gs = new GraySc(input);
//		gs.toGrayLumosity();
		gs.toGrayScale();
	}

	private void toGrayScale (){
		gray = cloneBF(image);


		for (int y = 0; y < gray.getHeight(); y++){

			for (int x = 0; x < gray.getWidth(); x++){

				Color c = new Color(image.getRGB(x,y));

				int gc = (c.getBlue() + c.getGreen() + c.getRed()) / 3;

				gray.setRGB(x, y, makeRGBInt(gc, 0, 0));
			}
		}

		JFrame frame = new JFrame("GrayScale");
		frame.getContentPane().add(new JLabel(new ImageIcon(gray)));
		frame.setVisible(true);
	}

	private void toGrayLumosity() {

		gray = cloneBF(image);


		for (int y = 0; y < gray.getHeight(); y++){

			for (int x = 0; x < gray.getWidth(); x++){

				Color c = new Color(image.getRGB(x,y));

				int gc = (int) ((c.getBlue() * 0.21) + (c.getGreen() * 0.72) + (c.getRed()) * 0.07) / 3;

				gray.setRGB(x, y, makeRGBInt(gc, gc, gc));
			}
		}

		JFrame frame = new JFrame("Lumosity");
		frame.getContentPane().add(new JLabel(new ImageIcon(gray)));
		frame.setVisible(true);
	}

	private BufferedImage cloneBF(BufferedImage in){
		BufferedImage out = new BufferedImage(in.getWidth(), in.getHeight(), in.getType());
		Graphics g = out.getGraphics();
		g.drawImage(in, 0, 0, null);
		g.dispose();

		return out;

	}


	public static int makeRGBInt (int red, int blue, int green){
		int result = red;
		result = result << 8;

		result += blue;
		result = result << 8;

		result += green;

		return result;
	}
}


