package club.smartsheep.storagehome.Controller.Error;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseErrorHandlerController {

    @ExceptionHandler(value = DataAccessException.class)
    public String initDatabase() {
        return "redirect:/error/database";
    }
}
