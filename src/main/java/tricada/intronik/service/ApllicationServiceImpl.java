package tricada.intronik.service;

import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import tricada.intronik.Model.AdditionalModel;
import tricada.intronik.Model.RequestModel;
import tricada.intronik.Model.ReservationModel;
import tricada.intronik.Model.RoomModel;
import tricada.intronik.config.ApplicationException;
import tricada.intronik.config.DateUtils;
import tricada.intronik.config.GeneratorUtils;
import tricada.intronik.config.Status;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
//@Transactional
public class ApllicationServiceImpl implements ApllicationService {

    Logger logger = LogManager.getLogger(ApllicationServiceImpl.class);

    @Override
    public Object register(RequestModel requestModel) throws IOException, DocumentException, ParseException {

        if (requestModel.getUsername() == null || requestModel.getUsername().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field username is required"));
        }
        if (requestModel.getPassword() == null || requestModel.getPassword().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field password is required"));
        }
        if (requestModel.getFirstname() == null || requestModel.getFirstname().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field firstname is required"));
        }
        if (requestModel.getLastname() == null || requestModel.getLastname().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field lastname is required"));
        }
        if (requestModel.getGender() == null || requestModel.getGender().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field gender is required"));
        }
        if (requestModel.getDateofbirth() == null || requestModel.getDateofbirth().toString().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field dateofbirth is required"));
        }
        if (requestModel.getNationality() == null || requestModel.getNationality().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field nationality is required"));
        }
        if (requestModel.getEmail() == null || requestModel.getEmail().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field email is required"));
        }
        if (requestModel.getAddress() == null || requestModel.getAddress().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field address is required"));
        }
        if (requestModel.getPhonenumber() == null || requestModel.getPhonenumber().toString().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field phonenumber is required"));
        }

        String projectDirectory = System.getProperty("user.dir");
        String sheetName = "member";
        String dateString = DateUtils.now().toString();
        String targetFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sourceFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat targetDateFormat = new SimpleDateFormat(targetFormat);
        Date date = sourceFormat.parse(dateString);
        String formattedDate = targetDateFormat.format(date);
        String id = GeneratorUtils.GenerateId("", DateUtils.now(), 5);
        Object[] rowData = {id, requestModel.getUsername(), requestModel.getPassword(), requestModel.getFirstname(),
                requestModel.getLastname(), requestModel.getGender(),requestModel.getDateofbirth(),
                requestModel.getNationality(), requestModel.getEmail(), requestModel.getAddress(),
                requestModel.getPhonenumber(), formattedDate, "System"};
        String filePath = projectDirectory + File.separator + "Database.xlsx";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int lastRowNum = sheet.getLastRowNum();

            for (int i = 1; i <= lastRowNum; i++) {
                Cell cell = sheet.getRow(i).getCell(1);
                String cl = cell.getStringCellValue();
                if (cl.equals(requestModel.getUsername())) {
                    throw new ApplicationException(Status.INVALID("username Already Exist"));
                }
            }

            Row newRow = sheet.createRow(lastRowNum + 1);

            for (int i = 0; i < rowData.length; i++) {
                Cell cell = newRow.createCell(i);
                cell.setCellValue(rowData[i].toString());
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
                System.out.println("Data added to Excel file successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to the file");
//                throw new ApplicationException(Status.INVALID("Error writing to the file"));
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error reading the Excel file.");
//            throw new ApplicationException(Status.INVALID("Error reading the Excel file"));
            e.printStackTrace();
        }

        return requestModel;
    }

    @Override
    public Object roomstype(RoomModel roomModel) throws IOException, DocumentException {

        String projectDirectory = System.getProperty("user.dir");
        String sheetName = "room";
        String filePath = projectDirectory + File.separator + "Database.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();
        List<RoomModel> list = new ArrayList<>();

        for (int i = 1; i <= lastRowNum; i++) {
            RoomModel tampung = new RoomModel();
            tampung.setId(sheet.getRow(i).getCell(0).getNumericCellValue());
            tampung.setRoomtype(sheet.getRow(i).getCell(1).getStringCellValue());
//            String mr = sheet.getRow(i).getCell(2).getStringCellValue();
            tampung.setDescription(sheet.getRow(i).getCell(2).getStringCellValue());
            tampung.setPrice(sheet.getRow(i).getCell(3).getNumericCellValue());
            list.add(tampung);
        }

        return list;
    }

    @Override
    public Object additional(AdditionalModel additionalModel) throws IOException, DocumentException {

        String projectDirectory = System.getProperty("user.dir");
        String sheetName = "additional";
        String filePath = projectDirectory + File.separator + "Database.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();
        List<AdditionalModel> list = new ArrayList<>();

        for (int i = 1; i <= lastRowNum; i++) {
            AdditionalModel tampung = new AdditionalModel();
            tampung.setId(sheet.getRow(i).getCell(0).getNumericCellValue());
            tampung.setAdditional(sheet.getRow(i).getCell(1).getStringCellValue());
//            String mr = sheet.getRow(i).getCell(2).getStringCellValue();
            tampung.setDescription(sheet.getRow(i).getCell(2).getStringCellValue());
            tampung.setPrice(sheet.getRow(i).getCell(3).getNumericCellValue());
            list.add(tampung);
        }

        return list;
    }

    @Override
    public Object reservation(ReservationModel requestModel) throws IOException, DocumentException, ParseException {

        if (requestModel.getUsername() == null || requestModel.getUsername().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field username is required"));
        }
        if (requestModel.getRoomtype() == null || requestModel.getRoomtype().trim().equals("")) {
            throw new ApplicationException(Status.INVALID("field roomtype is required"));
        }
        if (requestModel.getNumberofnights() == null) {
            throw new ApplicationException(Status.INVALID("field numberofnights is required"));
        }
        if (requestModel.getCheckin() == null ) {
            throw new ApplicationException(Status.INVALID("field checkin is required"));
        }

        String projectDirectory = System.getProperty("user.dir");
        String sheetNamemember = "member";
        String sheetNameadditional = "additional";
        String sheetNamereservation = "reservation";
        String sheetNameroom = "room";
        String dateString = DateUtils.now().toString();
        String targetFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sourceFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat targetDateFormat = new SimpleDateFormat(targetFormat);
        Date date = sourceFormat.parse(dateString);
        String formattedDate = targetDateFormat.format(date);

        String checkInDateString = requestModel.getCheckin();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkInDate = LocalDate.parse(checkInDateString, formatter);
        LocalDate checkOutDate = checkInDate.plusDays(requestModel.getNumberofnights());
        String checkOutDateString = checkOutDate.format(formatter);

        String id = GeneratorUtils.GenerateId("", DateUtils.now(), 5);
        String filePath = projectDirectory + File.separator + "Database.xlsx";

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheetmember = workbook.getSheet(sheetNamemember);
            Sheet sheetaddtional = workbook.getSheet(sheetNameadditional);
            Sheet sheetreservation = workbook.getSheet(sheetNamereservation);
            Sheet sheetroom = workbook.getSheet(sheetNameroom);

            int lastRowNummember = sheetmember.getLastRowNum();
            int lastRowNumadditional = sheetaddtional.getLastRowNum();
            int lastRowNumreservation = sheetreservation.getLastRowNum();
            int lastRowNumroom = sheetroom.getLastRowNum();

            Double tp = Double.valueOf(0);

            Boolean ada1 = false;
            for (int i = 1; i <= lastRowNummember; i++) {
                Cell cell = sheetmember.getRow(i).getCell(1);
                String cl = cell.getStringCellValue();
                if (cl.equals(requestModel.getUsername())) {
                    ada1 = true;
//                    throw new ApplicationException(Status.INVALID("username Already Exist"));
                }
            }
            if(ada1.equals(false)){
                throw new ApplicationException(Status.INVALID("username Not Found"));
            }

            Boolean ada2 = false;
            for (int i = 1; i <= lastRowNumroom; i++) {
                Cell cell = sheetroom.getRow(i).getCell(1);
                String cl = cell.getStringCellValue();
                if (cl.equals(requestModel.getRoomtype())) {
                    ada2 = true;
                    Cell cell2 = sheetroom.getRow(i).getCell(3);
                    Double cl2 = cell2.getNumericCellValue();
                    tp = tp + cl2;
//                    throw new ApplicationException(Status.INVALID("username Already Exist"));
                }
            }
            if(ada2.equals(false)){
                throw new ApplicationException(Status.INVALID("room Not Found"));
            }

            Integer ada3 = 0;
            for (int i = 1; i <= lastRowNumadditional; i++) {
                Cell cell = sheetaddtional.getRow(i).getCell(1);
                String cl = cell.getStringCellValue();
                for(String item:requestModel.getAdditional()){
                    if (cl.equals(item)) {
                        ada3 = ada3 + 1;
                        Cell cell2 = sheetroom.getRow(i).getCell(3);
                        Double cl2 = cell2.getNumericCellValue();
                        tp = tp + cl2;
//                    throw new ApplicationException(Status.INVALID("username Already Exist"));
                    }
                }
            }
            if(ada3 != requestModel.getAdditional().size()){
                throw new ApplicationException(Status.INVALID("additional Not Found"));
            }

            Row newRow = sheetreservation.createRow(lastRowNumreservation + 1);

            Object[] rowData = {id, requestModel.getUsername(), requestModel.getRoomtype(), requestModel.getAdditional(),
                    requestModel.getNumberofnights(), requestModel.getCheckin(),checkOutDateString,
                    "BOOKED", tp*requestModel.getNumberofnights(), formattedDate, "System"};

            for (int i = 0; i < rowData.length; i++) {
                Cell cell = newRow.createCell(i);
                cell.setCellValue(rowData[i].toString());
            }

            requestModel.setId(id);
            requestModel.setCheckout(checkOutDateString);
            requestModel.setStatus("BOOKED");
            requestModel.setTotalprice(tp*requestModel.getNumberofnights());
            requestModel.setCreateddate(date);
            requestModel.setCreatedby("System");

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
                System.out.println("Data added to Excel file successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to the file");
//                throw new ApplicationException(Status.INVALID("Error writing to the file"));
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error reading the Excel file.");
//            throw new ApplicationException(Status.INVALID("Error reading the Excel file"));
            e.printStackTrace();
        }

        return requestModel;
    }

}
