//package mx.edu.cetys.software_quality_lab.commons;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalControllerAdvice {
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiResponse<Void> handleAll(Exception e){
//        return switch (e){
//            case HttpMessageNotReadableException exception ->
//                new ApiResponse<>("Bad Request", null, e.getMessage());
//            default ->
//                new ApiResponse<>("Bad request", null,e.getMessage());
//        };
//    }
//}