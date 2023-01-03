import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
// import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
// import javax.swing.JRootPane;
// import javax.swing.UIDefaults;
// import javax.swing.UIManager;


public class PlexWalker extends JFrame {
    private JTextArea textArea;
    private JButton openButton;
    private JButton exportButton;
    private JFileChooser fileChooser;
    private List<File> files;
    // private JProgressBar progressBar;

    public PlexWalker() {
        // Set up the UI
        textArea = new JTextArea("Select a directory to begin...\n", 20, 50);
        textArea.setBackground(Color.LIGHT_GRAY);
        openButton = new JButton("Open");
        exportButton = new JButton("Export");
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // progressBar = new JProgressBar();


        // getRootPane().setBackground(Color.DARK_GRAY);
        // getContentPane().setBackground(Color.LIGHT_GRAY);

        JPanel panel = new JPanel();
        panel.add(openButton);
        panel.add(exportButton);

        add(panel, BorderLayout.SOUTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        // add(progressBar, BorderLayout.NORTH);

        // getContentPane().setBackground(Color.DARK_GRAY);

        // Set up the action listener for the buttons
        ButtonActionListener actionListener = new ButtonActionListener(this);
        openButton.addActionListener(actionListener);
        exportButton.addActionListener(actionListener);
    }

    // Recursively list all the files in the given directory
    public void listFiles(File file, int indent) {
        if (file.isDirectory()) {
            files.add(file);
            File[] subfiles = file.listFiles();
            for (File f : subfiles) {
                listFiles(f, indent + 1);
            }
        } else {
            files.add(file);
            for (int i = 0; i < indent; i++) {
                textArea.append("  ");
            }
            textArea.append(file.getName() + "\n");
        }
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    // public JProgressBar getProgressBar() {
    // return progressBar;
    // }

    public static void main(String[] args) {
        PlexWalker frame = new PlexWalker();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
