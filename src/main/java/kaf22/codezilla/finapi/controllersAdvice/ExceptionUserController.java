package kaf22.codezilla.finapi.controllersAdvice;

import kaf22.codezilla.finapi.errors.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionUserController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> showCustomMessage(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", ErrorCode.AT_LEAST_ONE_PARAMETER.getCode());
        response.put("error", ErrorCode.AT_LEAST_ONE_PARAMETER.getMessage());

        return response;
    }
}
