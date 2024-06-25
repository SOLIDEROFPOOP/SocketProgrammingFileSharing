import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Client {
    public static void main(String[] args){
        File fileToSend;

        JFrame jFrame = new JFrame("Client");
        jFrame.setSize(450, 450);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jTitle= new JLabel("Wittcode file sender");
        jTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jTitle.setBorder(new EmptyBorder(20 , 0 , 10 ,0));
        jTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jFileName = new JLabel("Choose a file");
        jFileName.setFont(new Font("Arial", Font.BOLD , 20));
        jFileName.setBorder(new EmptyBorder(50 , 0 , 0 , 0));
        jFileName.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel jButton = new JPanel();
        jButton.setBorder(new EmptyBorder(75 , 0 , 10 , 0));

        JButton jbSendFile = new JButton("Send file");
        jButton.setPreferredSize(new Dimension(150 , 75));
        jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));

        JButton jbChooseFile = new JButton("ChooseFile");
        jbChooseFile.setPreferredSize(new Dimension(150 , 75));
        jbChooseFile.setFont(new Font("Arial", Font.BOLD, 20));

        jButton.add(jbSendFile);
        jButton.add(jbChooseFile);

        jbChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Choose file to send");
                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    
                }
            }
        });
    }
}
