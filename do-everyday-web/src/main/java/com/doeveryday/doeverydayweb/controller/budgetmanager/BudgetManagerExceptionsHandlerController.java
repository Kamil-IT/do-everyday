package com.doeveryday.doeverydayweb.controller.budgetmanager;

import com.doeveryday.doeverydaybudgetmanager.exception.ExistsInDatabaseException;
import com.doeveryday.doeverydaybudgetmanager.exception.NotFoundException;
import com.doeveryday.doeverydayweb.controller.ErrorPagesController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class BudgetManagerExceptionsHandlerController {

    private ErrorPagesController errorPagesController;

    @ExceptionHandler(ExistsInDatabaseException.class)
    public ModelAndView handleExistsInDatabaseException(ExistsInDatabaseException exception){
        return errorPagesController.handleErrorBadRequest(exception);
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(NotFoundException exception){
        return errorPagesController.handleErrorNotFound(exception);
    }

}
