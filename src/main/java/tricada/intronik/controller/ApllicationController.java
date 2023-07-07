package tricada.intronik.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tricada.intronik.Model.AdditionalModel;
import tricada.intronik.Model.RequestModel;
import tricada.intronik.Model.ReservationModel;
import tricada.intronik.Model.RoomModel;
import tricada.intronik.config.*;
import tricada.intronik.service.ApllicationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tritronik")
public class ApllicationController{

    Logger logger = LogManager.getLogger();

    @Autowired
    private ApllicationService apllicationService;

    public ApllicationController(ApllicationService apllicationService) {
        this.apllicationService = apllicationService;

    }

//    @GetMapping("/search")
//    public Object search(@RequestBody BaseRequest<BaseParameter<RequestModel>> request) {
//        BaseResponse response = new BaseResponse<RequestModel>();
//        if(request.getParameter() == null){
//            throw new ApplicationException(Status.INVALID("field parameter is required"));
//        }
//        if(request.getParameter().getData() == null){
//            throw new ApplicationException(Status.INVALID("field data is required"));
//        }
//        RequestModel requestModel = request.getParameter().getData();
//        try {
//
//            response.setResult(apllicationService.search(requestModel));
//            response.setStatus(Status.SUCCESS("data has been successfully displayed"));
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            response.setStatus(Status.ERROR(e.getMessage()));
//        }
//        return response;
//    }

    @PostMapping("/register")
    public Object register(@RequestBody BaseRequest<BaseParameter<RequestModel>> request) {
        BaseResponse response = new BaseResponse<RequestModel>();
        if(request.getParameter() == null){
            throw new ApplicationException(Status.INVALID("field parameter is required"));
        }
        if(request.getParameter().getData() == null){
            throw new ApplicationException(Status.INVALID("field data is required"));
        }
        RequestModel requestModel = request.getParameter().getData();
        try {
            response.setResult(apllicationService.register(requestModel));
            response.setStatus(Status.SUCCESS("data has been successfully exported"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }
//
    @GetMapping("/roomstype")
    public Object roomstype(@RequestBody BaseRequest<BaseParameter<RoomModel>> request) {
        BaseResponse response = new BaseResponse<RoomModel>();
        if(request.getParameter() == null){
            throw new ApplicationException(Status.INVALID("field parameter is required"));
        }
        if(request.getParameter().getData() == null){
            throw new ApplicationException(Status.INVALID("field data is required"));
        }
        RoomModel roomModel = request.getParameter().getData();
        try {
            response.setResult(apllicationService.roomstype(roomModel));
            response.setStatus(Status.SUCCESS("rooms data has been successfully displayed"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

    @GetMapping("/additional")
    public Object additional(@RequestBody BaseRequest<BaseParameter<AdditionalModel>> request) {
        BaseResponse response = new BaseResponse<AdditionalModel>();
        if(request.getParameter() == null){
            throw new ApplicationException(Status.INVALID("field parameter is required"));
        }
        if(request.getParameter().getData() == null){
            throw new ApplicationException(Status.INVALID("field data is required"));
        }
        AdditionalModel additionalModel = request.getParameter().getData();
        try {
            response.setResult(apllicationService.additional(additionalModel));
            response.setStatus(Status.SUCCESS("additional data has been successfully displayed"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }

    @PostMapping("/reservation")
    public Object reservation(@RequestBody BaseRequest<BaseParameter<ReservationModel>> request) {
        BaseResponse response = new BaseResponse<AdditionalModel>();
        if(request.getParameter() == null){
            throw new ApplicationException(Status.INVALID("field parameter is required"));
        }
        if(request.getParameter().getData() == null){
            throw new ApplicationException(Status.INVALID("field data is required"));
        }
        ReservationModel reservationModel = request.getParameter().getData();
        try {
            response.setResult(apllicationService.reservation(reservationModel));
            response.setStatus(Status.SUCCESS("reservation data has been successfully displayed"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.setStatus(Status.ERROR(e.getMessage()));
        }
        return response;
    }


//
//    @PostMapping("/generatelinkdownload")
//    public Object download(@RequestBody BaseRequest<BaseParameter<RequestModel>> request) {
//        BaseResponse response = new BaseResponse<AppModel>();
//        if(request.getParameter() == null){
//            throw new ApplicationException(Status.INVALID("field parameter is required"));
//        }
//        if(request.getParameter().getData() == null){
//            throw new ApplicationException(Status.INVALID("field data is required"));
//        }
//        RequestModel requestModel = request.getParameter().getData();
//        try {
//            response.setResult(apllicationService.download(requestModel));
//            response.setStatus(Status.SUCCESS("the download link has been successfully generated"));
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            response.setStatus(Status.ERROR(e.getMessage()));
//        }
//        return response;
//    }
//
//    @GetMapping("/downloadfile")
//    public Object downloadfile(@RequestParam(value = "file", required = false) String stringReq) {
//        BaseResponse response = new BaseResponse<AppModel>();
//        try {
//            apllicationService.downloadfile(stringReq);
//            response.setStatus(Status.SUCCESS("file has been successfully downloaded"));
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            response.setStatus(Status.ERROR(e.getMessage()));
//        }
//        return response;
//    }

}

