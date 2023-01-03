import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

public class ButtonActionListener implements ActionListener { // Create an action listener
  private PlexWalker plexWalker; // Create a PlexWalker object which will be used to access the UI

  public ButtonActionListener(PlexWalker plexWalker) { // Create a constructor for the action listener
    this.plexWalker = plexWalker; // Assign the PlexWalker object to the action listener
  }

  public void actionPerformed(ActionEvent e) { // Create an action performed method
    if (e.getActionCommand().equals("Open")) { // If the open button is clicked
      int returnVal = plexWalker.getFileChooser().showOpenDialog(plexWalker); // Open the file chooser
      if (returnVal == JFileChooser.APPROVE_OPTION) { // If the user selects a file
        File file = plexWalker.getFileChooser().getSelectedFile(); // Get the selected file
        plexWalker.getTextArea().setText(""); // Clear the text area
        List<File> files = new ArrayList<>(); // Create a list of files
        plexWalker.setFiles(files); // Set the list of files in the PlexWalker object
        plexWalker.listFiles(file, 0); // Recursively list all the files in the selected directory
      }
    } else if (e.getActionCommand().equals("Export")) { // If the export button is clicked
      // Create a file chooser to select the save location
      JFileChooser fileChooser = new JFileChooser(); // Create a file chooser
      fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx")); // Set the file filter to only show .xlsx files
      int result = fileChooser.showSaveDialog(null); // Show the file chooser

      // If the user selects a file, save the workbook to the file
      if (result == JFileChooser.APPROVE_OPTION) { // If the user selects a file
        File file = fileChooser.getSelectedFile(); // Get the selected file
        String filePath = file.getAbsolutePath(); // Get the absolute path of the file
        if (!filePath.endsWith(".xlsx")) { // If the file doesn't end with .xlsx
          filePath += ".xlsx"; // Add .xlsx to the end of the file path
        }
        ExcelExporter exporter = new ExcelExporter(plexWalker.getFiles()); // Create an ExcelExporter object
        try { // Try to export the workbook
          exporter.export(filePath); // Export the workbook to the file path
        } catch (Exception ex) { // If an error occurs
          ex.printStackTrace(); // Print the stack trace
          JOptionPane.showMessageDialog(null, "Export failed due to multiple sheets with the same name.", "Error", JOptionPane.ERROR_MESSAGE); // Show an error message dialog box
        }
      }
    }
  }
}
