import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter { // Create exporter class
    private List<File> files; // List of files to export

    public ExcelExporter(List<File> files) { // Constructor
        this.files = files; // Set files
    }

    public void export(String filePath) throws Exception { // Export method
        // Create a new workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); // Create workbook

        for (File file : files) { // Loop through files
            System.out.println(file); // TODO remove
            if (file.isDirectory()) { // If the file is a directory
                // Choose a unique name for the sheet
                String sheetName = file.getName(); // Get the name of the directory
                int sheetIndex = 1; // Use this to add a number to the end of the name if it already exists
                // Check if the sheet already exists
                if (workbook.getSheet(sheetName) != null) { // If the sheet already exists
                    sheetName += " " + sheetIndex; // Add a number to the end of the name
                    sheetIndex++; // Increment the number
                }
                XSSFSheet sheet = workbook.createSheet(sheetName); // Create the sheet
                // Add the files in the directory to the sheet
                File[] subfiles = file.listFiles(); // Get the subfiles in the directory
                int rowNum = 0; // Row number
                for (File f : subfiles) { // Loop through subfiles
                    System.out.println(f); // TODO remove
                    Row row = sheet.createRow(rowNum++); // Create a row
                    Cell cell = row.createCell(0); // Create a cell
                    cell.setCellValue(f.getName()); // Set the cell value to the name of the file
                }
            }
        }

            System.out.println("FILEPATH: " + filePath); // TODO remove
            FileOutputStream out = new FileOutputStream(filePath); // Create a file output stream
            workbook.write(out); // Write the workbook to the file
            out.close(); // Close the file output stream
            workbook.close(); // Close the workbook
    }
}
