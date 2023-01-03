import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

public class ButtonActionListener implements ActionListener {
  private PlexWalker plexWalker;

  public ButtonActionListener(PlexWalker plexWalker) {
    this.plexWalker = plexWalker;
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Open")) {
      int returnVal = plexWalker.getFileChooser().showOpenDialog(plexWalker);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = plexWalker.getFileChooser().getSelectedFile();
        plexWalker.getTextArea().setText("");
        List<File> files = new ArrayList<>();
        plexWalker.setFiles(files);
        plexWalker.listFiles(file, 0);
      }
    } else if (e.getActionCommand().equals("Export")) {
      // Create a file chooser to select the save location
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx"));
      int result = fileChooser.showSaveDialog(null);

      // If the user selects a file, save the workbook to the file
      if (result == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String filePath = file.getAbsolutePath();
        if (!filePath.endsWith(".xlsx")) {
          filePath += ".xlsx";
        }
        ExcelExporter exporter = new ExcelExporter(plexWalker.getFiles());
        try {
          exporter.export(filePath);
        } catch (Exception ex) {
          ex.printStackTrace();
          JOptionPane.showMessageDialog(null, "Export failed due to multiple sheets with the same name.", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
}
