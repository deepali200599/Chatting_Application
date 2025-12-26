
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.beans.SimpleBeanInfo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Clients implements ActionListener{
    JTextField text;
    static JPanel a1;
    static Box vertical=Box.createVerticalBox();

    static JFrame t=new JFrame();
    static DataOutputStream  dout;
    static DataInputStream din;
    JScrollPane pane;
    Socket s;
    Clients(){

        t.setLayout(null);
        JPanel p1=new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        t.add(p1);
        p1.setLayout(null);

        ImageIcon il=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2=il.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me){
                t.setVisible(false );

            }
        });
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile=new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);

        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel video=new JLabel(i9);
        video.setBounds(300,20,30,30);
        p1.add(video);

        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11=i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel phone=new JLabel(i12);
        phone.setBounds(360,20,35,30);
        p1.add(phone);

        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel morevert=new JLabel(i15);
        morevert.setBounds(420,20,10,25);
        p1.add(morevert);

        JLabel name=new JLabel("Shreyaa");
        name.setBounds(110,15,100,30);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        p1.add(name); 

        
        JLabel status=new JLabel("Active Now");
        status.setBounds(110,35,100,30);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        p1.add(status); 
        
         a1=new JPanel();
        //a1.setBounds(5,75,440,570);
        a1.setLayout(new BoxLayout(a1,BoxLayout.Y_AXIS));
        t.add(a1);

        
        JScrollPane pane = new JScrollPane(a1);
        pane.setBounds(5, 75, 440, 570);
         pane.setBorder(BorderFactory.createEmptyBorder());
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        t.add(pane);
        

        text=new JTextField();
        text.setBounds(5,655,310,40);
        t.add(text);

        JButton send=new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        // send.setFocusable(false);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        t.add(send);
        
        //   text.addKeyListener(new KeyAdapter() {
        //     public void keyPressed(KeyEvent ke) {
        //         if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
        //             sendMessage();
        //         }
        //     }
        // });


        t.setSize(450,700);
        t.setUndecorated(true);
        t.setVisible(true);
        t.setLocation(700,50);
   
        t.getContentPane().setBackground(Color.WHITE);

    }
    // public void actionPerformed(ActionEvent ae){
    //     sendMessage();
    // }
    public void actionPerformed(ActionEvent ae){
        try{
        String out=text.getText().trim();
        if (out.equals(""))
        return;

        

        JPanel p2=formatLabel(out);
       
        // System.out.println(out);

        a1.setLayout(new BorderLayout());
        JPanel right= new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(30));
        a1.add(vertical,BorderLayout.PAGE_START);
        dout.writeUTF(out);

        t.repaint();
        t.invalidate();
        t.validate();
        // autoScroll();
          text.setText("");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static JPanel formatLabel(String out){
        JPanel panel=new JPanel();
        
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JLabel output=new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));

        panel.add(output);
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");

        JLabel time=new JLabel();

        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;

    }
    // private void autoScroll(){
    //     SwingUtilities.invokeLater(() -> {
    //         JScrollBar verticalbar = scrollpane.getVerticalScrollBar();
    //         verticalbar.setValue(verticalbar.getMaximum());
    //     });
    // }
    public static void main(String[] args){
    new Clients();
    try{
        Socket s=new Socket("127.0.0.1",1024);
       din= new DataInputStream(s.getInputStream());
       dout=new DataOutputStream(s.getOutputStream());
    //    Thread f=new Thread(()->{

       

        while (true) {
            

            //a1.setLayout(new BorderLayout());
            String  msg=din.readUTF();
            JPanel panel=formatLabel(msg);

            JPanel left =new JPanel(new BorderLayout());
            left.add(panel,BorderLayout.LINE_START);
            // SwingUtilities.invokeLater(()->{

            

            vertical.add(left);
        
             t.validate();
           vertical.add(Box.createVerticalStrut(15));


            // JPanel tpanel=(JPanel)

            //             t.getContentPane().getComponent(1);
            //             tpanel.add(vertical,BorderLayout.PAGE_START);
                

           
            //  });
           
        }

    // });
}
    
    
    catch(Exception e){
        e.printStackTrace();
    }
    }


}