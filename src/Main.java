import Model.Experience_cadidate;
import Model.Notification;
import Model.Skill;
import Service.CandidateImpl;
import Service.CandidateService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String fileName = "C:\\Users\\ADMIN\\Desktop\\javabasicfpt\\input.txt";
    public static final String excelFilePath = "C:\\Users\\ADMIN\\Desktop\\javabasicfpt\\notification.xlsx";
    public static final int COLUMN_DONG = 0;
    public static final int COLUMN_SAI_DINH_DANG_NGAY_THANG = 1;
    public static final int COLUMN_SAI_DINH_DANG_SO_DIEN_THOAI = 2;
    public static final int COLUMN_SAI_DINH_DANG_DIA_CHI_EMAIL = 3;
    public static final int COLUMN_SAI_DINH_DANG_SO_NAM_KINH_NGHIEM = 4;
    public static final int COLUMN_THUA_DU_LIEU = 5;
    public static CellStyle cellStyleFormatNumber = null;
    public static void main(String[] args) throws IOException, SQLException {
        File file = new File(excelFilePath);
        file.delete();
        // Lấy dữ liệu trong file input
        List<String> valueList = getDataFile();
        List<Notification> notificationList = new ArrayList<>();
        // Tiến hành thêm dữ liệu vào database
        int position = 0;
        for (String value : valueList) {
            position++;
            insertDataToDataBase(value, position, notificationList);
        }
        // ghi vào file excel những trường bị sai dữ liệu
        if(notificationList.size() > 0) {
            writeExcel(notificationList);
        }
    }

    public static List<String> getDataFile() {
        List<String> valueList = new ArrayList<>();
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fin);
            int data = bis.read();
            StringBuilder line = new StringBuilder();
            while (data != -1) {
                if (((char) data == '\n') || ((char) data == '\r')) {
                    if(!line.toString().equals("")) {
                        System.out.println(line.toString());
                        valueList.add(line.toString());
                    }
                    line.delete(0, line.length());
                    data = bis.read();
                    continue;
                }
                line.append((char) data);
                data = bis.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return valueList;
    }

    public static void insertDataToDataBase(String value, int position, List<Notification> notificationList) throws SQLException {
        String[] a = value.split(", ");
        List<String> newArr = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        CandidateImpl c = new CandidateImpl();
        String notification;
        String empID = "FPT" + c.countEmployee();
        int id;
        int check = 0;

        for (int i = 0; i < a.length; i++) {
            if(!a[i].equals("None")) {
                newArr.add(a[i]);
            }
            else {
                check++;
            }
        }
        if(newArr.get(0).equals("1")) {
            for(int i = 8; i < newArr.size() - 1; i++) {
                Skill s = new Skill(newArr.get(i));
                skills.add(s);
            }
            if(c.insertDataSkill(skills, empID)) {
                System.out.println("Them du lieu skill thanh cong");
            }
        }

        System.out.println(newArr.toString());


        notification = checkValue(newArr, check);
        if(!notification.equals("Du lieu D")) {
            Notification noti = new Notification(position, notification);
            notificationList.add(noti);
        }
        else {
            if(c.insertData(newArr, empID)) {
                System.out.println("Thêm dữ liệu nhân viên vào thành công");
            }

        }


    }
    public static String checkValue(List<String> arr, int check) {
        if (arr.get(0).equals("1") && check == 7) {
            double exp = Math.ceil(Double.parseDouble(arr.get(7))) / 10;
            CandidateService c = new CandidateImpl();
            String notification = c.checkValidate(arr);
            if(!notification.equals("Du lieu D")) {
                return notification;
            }
            Experience_cadidate ex = new Experience_cadidate();
            String notification2 = ex.setNumberOfExp(exp);
            if(!notification2.equals("Du lieu D")) {
                return notification2;
            }
            return "Du lieu D";
        }
        if (arr.get(0).equals("2") && check == 7) {
            CandidateService c = new CandidateImpl();
            String notification = c.checkValidate(arr);
            if(!notification.equals("Du lieu D")) {
                return notification;
            }
            return "Du lieu D";
        }
        if(arr.get(0).equals("3") && check == 6) {
            CandidateService c = new CandidateImpl();
            String notification = c.checkValidate(arr);
            if(!notification.equals("Du lieu D")) {
                return notification;
            }
            return "Du lieu D";
        }

        return "Du lieu bi du";
    }

    // viet vao file excel
    public static void writeExcel(List<Notification> notificationList) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("Notification");

        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;
        for (Notification noti : notificationList) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(noti, row);
            rowIndex++;
        }

        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    private static void writeBook(Notification notification, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(COLUMN_DONG);
        cell.setCellValue(notification.getLine());
        String content = notification.getContent();

        switch (content) {
            case "Sai dinh dang ngay thang nam sinh":
                cell = row.createCell(COLUMN_SAI_DINH_DANG_NGAY_THANG);
                cell.setCellValue("Yes");
                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_DIEN_THOAI);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_DIA_CHI_EMAIL);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_NAM_KINH_NGHIEM);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_THUA_DU_LIEU);
                cell.setCellValue("");
                break;
            case "Sai dinh dang so dien thoai":
                cell = row.createCell(COLUMN_SAI_DINH_DANG_NGAY_THANG);
                cell.setCellValue("");
                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_DIEN_THOAI);
                cell.setCellValue("Yes");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_DIA_CHI_EMAIL);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_NAM_KINH_NGHIEM);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_THUA_DU_LIEU);
                cell.setCellValue("");
                break;
            case "Sai dinh dang dia chi email":
                cell = row.createCell(COLUMN_SAI_DINH_DANG_NGAY_THANG);
                cell.setCellValue("");
                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_DIEN_THOAI);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_DIA_CHI_EMAIL);
                cell.setCellValue("Yes");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_NAM_KINH_NGHIEM);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_THUA_DU_LIEU);
                cell.setCellValue("");
                break;
            case "Sai dinh dang so nam kinh nghiem":
                cell = row.createCell(COLUMN_SAI_DINH_DANG_NGAY_THANG);
                cell.setCellValue("");
                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_DIEN_THOAI);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_DIA_CHI_EMAIL);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_NAM_KINH_NGHIEM);
                cell.setCellValue("Yes");

                cell = row.createCell(COLUMN_THUA_DU_LIEU);
                cell.setCellValue("");
                break;
            case "Du lieu bi du":
                cell = row.createCell(COLUMN_SAI_DINH_DANG_NGAY_THANG);
                cell.setCellValue("");
                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_DIEN_THOAI);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_DIA_CHI_EMAIL);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_NAM_KINH_NGHIEM);
                cell.setCellValue("");

                cell = row.createCell(COLUMN_THUA_DU_LIEU);
                cell.setCellValue("Yes");
                break;
            default:
                break;
        }



    }

    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_DONG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Dong");

        cell = row.createCell(COLUMN_SAI_DINH_DANG_NGAY_THANG);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Sai dinh dang ngay thang");

        cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_DIEN_THOAI);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Sai dinh dang so dien thoai");

        cell = row.createCell(COLUMN_SAI_DINH_DANG_DIA_CHI_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Sai dinh dang dai chi email");

        cell = row.createCell(COLUMN_SAI_DINH_DANG_SO_NAM_KINH_NGHIEM);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Sai dinh dang so nam kinh nghiem");

        cell = row.createCell(COLUMN_THUA_DU_LIEU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Thua du lieu");

    }
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

}