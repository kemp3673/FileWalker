import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter {
    private List<File> files;

    public ExcelExporter(List<File> files) {
        this.files = files;
    }

    public void export(String filePath) throws Exception {
        // Create a new workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        for (File file : files) {
            System.out.println(file); // TODO remove
            if (file.isDirectory()) {
                // Choose a unique name for the sheet
                String sheetName = file.getName();
                int sheetIndex = 1;
                // Check if the sheet already exists
                if (workbook.getSheet(sheetName) != null) {
                    sheetName += " " + sheetIndex;
                    sheetIndex++;
                }
                XSSFSheet sheet = workbook.createSheet(sheetName);
                // Add the files in the directory to the sheet
                File[] subfiles = file.listFiles();
                int rowNum = 0;
                for (File f : subfiles) {
                    System.out.println(f); // TODO remove
                    Row row = sheet.createRow(rowNum++);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(f.getName());
                }
            }
        }

            System.out.println("FILEPATH: " + filePath); // TODO remove
            FileOutputStream out = new FileOutputStream(filePath);
            workbook.write(out);
            out.close();
            workbook.close();
    }
}
