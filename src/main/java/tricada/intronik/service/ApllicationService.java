package tricada.intronik.service;

import com.itextpdf.text.DocumentException;
import tricada.intronik.Model.AdditionalModel;
import tricada.intronik.Model.RequestModel;
import tricada.intronik.Model.ReservationModel;
import tricada.intronik.Model.RoomModel;

import java.io.IOException;
import java.text.ParseException;

public interface ApllicationService  {

//    Object search(RequestModel appModel) throws IOException, DocumentException;
    Object register(RequestModel appModel) throws IOException, DocumentException, ParseException;
    Object roomstype(RoomModel appModel) throws IOException, DocumentException, ParseException;
    Object additional(AdditionalModel appModel) throws IOException, DocumentException, ParseException;
    Object reservation(ReservationModel appModel) throws IOException, DocumentException, ParseException;
//    Object download(RequestModel appModel) throws IOException;
//    Object downloadfile(String appModel) throws IOException;

}
