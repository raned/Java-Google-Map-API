import javax.swing.*;
import java.awt.*;
public class Frame
{

    public static  void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.add(new MainPane());
        frame.setPreferredSize(new Dimension(600,430));
       
        frame.pack();
         frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
