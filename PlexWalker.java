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


public class PlexWalker extends JFrame { // Create a frame to hold the UI
    private JTextArea textArea; // Create a text area to display the files
    private JButton openButton; // Create a button to open a directory
    private JButton exportButton; // Create a button to export the files
    private JFileChooser fileChooser; // Create a file chooser
    private List<File> files; // Create a list of files
    // private JProgressBar progressBar;

    public PlexWalker() {
        // Set up the UI
        textArea = new JTextArea("Select a directory to begin...\n", 20, 50); // Create the text area to display the files with 20 rows and 50 columns and a default message
        textArea.setBackground(Color.LIGHT_GRAY); // Set the background color of the text area
        textArea.setEditable(false); // Make the text area non-editable
        openButton = new JButton("Open"); // Assign openButton to a new JButton with the text "Open"
        exportButton = new JButton("Export"); // Assign exportButton to a new JButton with the text "Export"
        fileChooser = new JFileChooser(); // Assign fileChooser to a new JFileChooser
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only allow directories to be selected

        JPanel panel = new JPanel(); // Create a panel to hold the buttons
        panel.add(openButton); // Add the open button to the panel
        panel.add(exportButton); // Add the export button to the panel

        add(panel, BorderLayout.SOUTH); // Add the panel to the frame
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Add the text area to the frame

        // Set up the action listener for the buttons
        ButtonActionListener actionListener = new ButtonActionListener(this); // Create an action listener
        openButton.addActionListener(actionListener); // Add the action listener to the open button
        exportButton.addActionListener(actionListener); // Add the action listener to the export button
    }

    // Recursively list all the files in the given directory
    public void listFiles(File file, int indent) { // Recursively list all the files in the given directory
        if (file.isDirectory()) { // If the file is a directory, list all the files in the directory
            files.add(file); // Add the directory to the list of files
            File[] subfiles = file.listFiles(); // Get all the subfiles in the directory
            for (File f : subfiles) { // For each subfile in the directory
                listFiles(f, indent + 1); // Recursively list the files in the directory
            }
        } else {
            files.add(file);
            for (int i = 0; i < indent; i++) {
                textArea.append("  ");
            }
            textArea.append(file.getName() + "\n");
        }
    }

    public List<File> getFiles() { // Expose the files to the action listener
        return files; // Give access to the files to the action listener
    }

    public void setFiles(List<File> files) { // Expose the files to the action listener
        this.files = files; // Give access to the files to the action listener
    }

    public JFileChooser getFileChooser() { // Expose the file chooser to the action listener
        return fileChooser; // Give access to the file chooser to the action listener
    }

    public JTextArea getTextArea() { // Expose the text area to the action listener
        return textArea; // Give access to the text area to the action listener
    }

    // public JProgressBar getProgressBar() {
    // return progressBar;
    // }

    public static void main(String[] args) {
        PlexWalker frame = new PlexWalker(); // Create a frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit when the frame is closed
        frame.pack(); // Set the size of the frame
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setResizable(true); // Allow the user to resize the frame
        frame.setVisible(true); // Display the frame
    }
}
