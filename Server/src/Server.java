import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static ArrayList<MyFile> files = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int fileId = 0;

        JFrame jFrame = new JFrame("Murats server");
        jFrame.setSize(400 , 400);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        JLabel jlTitle = new JLabel("Murts file reciever");
        jlTitle.setFont(new Font("Arial",Font.BOLD,25));
        jlTitle.setBorder(new EmptyBorder(20, 0 , 10 ,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        jFrame.add(jlTitle);
        jFrame.add(jScrollPane);
        jFrame.setVisible(true);


        ServerSocket serverSocket = new ServerSocket(1234);
        while (true){
            try {
                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                int fileNameLength = dataInputStream.readInt();
                String fileName = null;
                byte[] fileNameBytes = new byte[0];
                if (fileNameLength > 0) {
                    fileNameBytes = new byte[fileNameLength];
                    dataInputStream.readFully(fileNameBytes, 0, fileNameLength);
                    fileName = new String(fileNameBytes);
                    int fileContentLength = dataInputStream.readInt();
                    byte[] fileContentBytes = new byte[0];
                    if (fileContentLength > 0) {
                        fileContentBytes = new byte[fileContentLength];
                        dataInputStream.readFully(fileContentBytes, 0, fileContentLength);
                        JPanel jpFileRow = new JPanel();
                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.Y_AXIS));
                        JLabel jFileName = new JLabel(fileName);
                        jFileName.setFont(new Font("Arial", Font.BOLD, 20));
                        jFileName.setBorder(new EmptyBorder(10, 0, 10, 0));
                        if (getFileNameExtension(fileName).equalsIgnoreCase("txt")) {
                            jpFileRow.setName(String.valueOf(fileId));
                            jpFileRow.addMouseListener(getMyMouseListener());
                            jpFileRow.add(jFileName);
                            panel.add(jpFileRow);
                            jFrame.validate();
                        } else {
                            jpFileRow.setName(String.valueOf(fileId));
                            jpFileRow.addMouseListener(getMyMouseListener());
                            jpFileRow.add(jFileName);
                            panel.add(jpFileRow);
                            jFrame.validate();
                        }
                    }
                    files.add(new MyFile(fileId, fileName, fileContentBytes, getFileNameExtension(fileName)));
                }

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static MouseListener getMyMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel panel = (JPanel) e.getSource();
                int fileId = Integer.parseInt(panel.getName());
                for (MyFile file: files){
                    if (file.getId() == fileId){
                        JFrame jfPreview = createFrame(file.getName(), file.getArray(),file.getFileExtension());
                        jfPreview.setVisible(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    private static JFrame createFrame(String fileName, byte[] fileData, String fileExtension) {
        JFrame jFrame = new JFrame("Murats file downloader");
        jFrame.setSize(400 , 400);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));

        JLabel jlTitle = new JLabel("Murats file downloader");
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder(20 ,0 , 10 ,0));

        JLabel jlPrompt = new JLabel("Are you sure you want to download? ;)");
        jlPrompt.setFont(new Font("Arial", Font.BOLD, 20));
        jlPrompt.setBorder(new EmptyBorder(20 ,0 ,10 ,0));
        jlPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jYes = new JButton("Yes");
        jYes.setPreferredSize(new Dimension(150 , 75));
        jYes.setFont(new Font("Arial", Font.BOLD, 20));


        JButton jNo = new JButton("Yes");
        jNo.setPreferredSize(new Dimension(150 , 75));
        jNo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel jFileContent = new JLabel();
        jFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jpButtons = new JPanel();
        jpButtons.setBorder(new EmptyBorder(20 , 0 , 10 ,0));
        jpButtons.add(jYes);
        jpButtons.add(jNo);

        if (fileExtension.equalsIgnoreCase("txt")){
            jFileContent.setText("<html>" + new String(fileData) + "</html>");
        } else {
            jFileContent.setIcon(new ImageIcon(fileData));
        }
        jYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File fileToDownload = new File(fileName);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                    fileOutputStream.write(fileData);
                    fileOutputStream.close();
                    jFrame.dispose();
                } catch (IOException ex){
                    ex.printStackTrace();;
                }
            }
        });
        jNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
        jPanel.add(jlTitle);
        jPanel.add(jlPrompt);
        jPanel.add(jFileContent);
        jPanel.add(jpButtons);
        jFrame.add(jPanel);
        return jFrame;
    }

    public static String getFileNameExtension(String fileName){
        // not gonna work for such as: .tar.gz
        int i = fileName.indexOf('.');

        if (i > 0){
            return fileName.substring(i + 1);
        } else return "no exension found";
    }
}
