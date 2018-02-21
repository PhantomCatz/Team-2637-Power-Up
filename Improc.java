package vision;

import org.opencv.core.*;
import org.opencv.highgui.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class Improc
{
	public JFrame frame;
	public JLabel lbl;
    
    public Improc()
    {
         System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
         initComponents();
    }
    
    private void initComponents()
    {
        frame = new JFrame();
        lbl = new JLabel();
        
        frame.setLayout(new FlowLayout());
        frame.add((new JPanel()).add(lbl));
               
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static BufferedImage Mat2BufferedImage(Mat m)
    {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (m.channels() > 1)
        {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte[] b = new byte[bufferSize];
        m.get(0, 0, b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);  
        return image;
    }

    public void displayImage(Image img)
    {   
    	//icon.setImage(img);
    	lbl.setIcon(new ImageIcon(img));
        //frame.setSize(img.getWidth(null)+50, img.getHeight(null)+50);     
    }

   /* public static void main(String[] args)
    {
        System.out.println(args[0]);
        Mat m = new Mat();
        //Mat m = Highgui.imread(args[0]);
        BufferedImage bi = Mat2BufferedImage(m);
        displayImage(bi);
    }
    
    */
}