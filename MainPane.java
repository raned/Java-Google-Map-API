import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
public class MainPane extends JPanel
{

    JButton btnin ;
    JButton btnout ;
    ImageIcon img;
    int zoom=1;
    JPanel screenpane=null;
    JComboBox combo;
    String typemap;

    JButton btnup ;
    JButton btndown ;
    JButton btnleft;
    JButton btnright ;

    double xlat=18.1824;
    double xlong=-77.3218;
    double step=1;
    public MainPane()
    {

        img = new ImageIcon();
        screenpane = new JPanel();
        JPanel controlpane = new JPanel();
        setLayout(new BorderLayout());

        screenpane.setLayout(new BorderLayout());
        add(screenpane, BorderLayout.CENTER);
        add(controlpane, BorderLayout.WEST);

        screenpane.setBackground(Color.BLACK);

        getMap();
        screenpane.add(new JLabel(img),BorderLayout.CENTER);

        btnin = new JButton("+");
        btnout = new JButton("-");

        btnup = new JButton("^");
        btndown = new JButton("v");
        btnleft= new JButton("<");
        btnright = new JButton(">");

        JPanel nav = new JPanel();
        nav.setLayout(new BorderLayout());
        nav.add(btnup,BorderLayout.NORTH);
        nav.add(btndown, BorderLayout.SOUTH);
        nav.add(btnleft, BorderLayout.WEST);
        nav.add(btnright, BorderLayout.EAST);

        combo=new JComboBox();       
        combo.addItem("roadmap");
        combo.addItem("hybrid");
        combo.addItem("satellite");
        combo.addItem("terrain");

        controlpane.setPreferredSize(new Dimension(100,400));
        controlpane.add(btnout);
        controlpane.add(btnin);
        controlpane.add(combo);
        controlpane.add(nav);

        btnup.addActionListener(new listener());
        btndown.addActionListener(new listener());
        btnleft.addActionListener(new listener());
        btnright.addActionListener(new listener());

        screenpane.addMouseMotionListener(new Mlistener());
        combo.addActionListener(new listener());
        btnin.addActionListener(new listener());
        btnout.addActionListener(new listener());
    }

    private class Mlistener implements MouseMotionListener{
        public void mouseMoved(MouseEvent evt){
            evt.getPoint();
        }

        public void mouseDragged(MouseEvent evt){
            evt.getPoint();
        }
    }

    public void getMap(){
        new Thread(new Runnable(){
                public void run(){
                    xlat=getDouble(xlat);
                    xlong=getDouble(xlong);

                    try{
                        System.out.println(xlat+" : " +xlong);
                        //http://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=12&size=400x400&maptype=hybrid&sensor=true
                        img.setImage(ImageIco.getMap("http://maps.googleapis.com/maps/api/staticmap?center="+xlat+","+xlong+"&zoom="+zoom+"&size=500x400&maptype="+typemap+"&sensor=true&key=AIzaSyA-Y2n0AoEnL9nRQPTB9UdYG4Hp92eiDvw&markers=color:red%7Clabel:S%7C"+ImageIco.getIp()).getImage()); 
                    }catch(Exception e){}
                    screenpane.repaint();
                }}).start();

    }

    public double getDouble(double d){
        DecimalFormat twoDForm = new DecimalFormat("#.####");
        return Double.valueOf(twoDForm.format(d));
    }

    private class listener implements ActionListener{

        public void actionPerformed(ActionEvent evt){
            Object source = evt.getSource();
            if(btnup==source){
                xlat+=step;
                getMap();
            }

            if(btndown==source){
                xlat-=step;
                getMap();
            }

            if(btnleft==source){

                xlong-=step;                
                getMap();
            }

            if(btnright==source){
                xlong+=step;                
                getMap();
            }

            if(btnin==source){
                zoom++;
                step=1/zoom;
                getMap();
            }

            if(combo==source){
                typemap=combo.getSelectedItem()+"";
                getMap();
            }

            if(btnout==source){

                if(zoom > 2){
                    zoom--;
                    getMap();
                }
            }
        }
    }
}
