package QR;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class ACMQR extends JFrame implements Runnable, ThreadFactory {

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private Webcam webcam = null;
    private WebcamPanel panel = null;
    private JTextArea textarea = null;
    private JFrame frame;
    private JLabel label[] = new JLabel[5];
    private String labelTxt[] = {"Name: ", "Student Number: ", "Course: ", "Position: "};
    
    public void init() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        getContentPane().setLayout(null);
        Dimension size = WebcamResolution.QVGA.getSize();
       // System.out.println(Webcam.getDefault());
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setLayout(null);
        panel.setBounds(screenSize.width-size.width, 0, size.width, size.height);
        
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("idTemplate.jpg");
        Image scaledImage = image.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
        
        textarea = new JTextArea();
        textarea.setEditable(false);
        textarea.setLayout(null);
        textarea.setPreferredSize(size);
        
        frame = new JFrame("ACM QR");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 600));
        frame.getContentPane().setLayout(null);
        frame.setBounds(100, 100, 786, 495);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setContentPane(new JLabel(new ImageIcon(scaledImage)));
        frame.getContentPane().add(panel);
        frame.getContentPane().add(textarea);
        
        for(int i=0; i<4; i++){
            label[i] = new JLabel(labelTxt[i]);
            label[i].setForeground(Color.white);
            label[i].setFont(new Font("Arial", Font.PLAIN, 30));
            //label[i].setLayout(null);
            label[i].setBounds(100, (i*50)+350, 700, 30);
            frame.getContentPane().add(label[i]);
        }

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException e) {
                    // fall thru, it means there is no QR code in image
                }
                
                if (result != null) {
                    /*Member member = new Member();
                    DB db = new DB();
                    try {
                        member = db.getById(Integer.parseInt(result.getText())); 
                        changeValues(member);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: \""+result.getText()+"\" not in database.");
                        
                    }
                    */
                    JOptionPane.showMessageDialog(null, result.getText());
                }
            }

        } while (true);
    }
    
    public void changeValues(Member member){
        label[0].setText(labelTxt[0]+member.getName());
        label[1].setText(labelTxt[1]+""+member.getIdNum());
        label[2].setText(labelTxt[2]+member.getCourse());
        label[3].setText(labelTxt[3]+member.getPosition());
    }

    @Override
    public Thread newThread(Runnable r) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void main(String[] args) {
        ACMQR acm = new ACMQR();
        acm.init();
        acm.run();
    }

}
