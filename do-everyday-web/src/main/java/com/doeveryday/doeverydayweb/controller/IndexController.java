package com.doeveryday.doeverydayweb.controller;

import com.doeveryday.doeverydaybudgetmanager.service.BudgetService;
import com.doeveryday.doeverydaybudgetmanager.service.TransactionService;
import com.doeveryday.doeverydaytodo.service.BoardService;
import com.doeveryday.doeverydayweather.service.ForecastService;
import com.doeveryday.doeverydayweather.service.GeoLocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class IndexController {

    private final ForecastService forecastService;
    private final GeoLocationService geoLocationService;
    private final BoardService boardService;
    private final BudgetService budgetService;
    private final TransactionService transactionService;


    @GetMapping(value = {"/", "/index"})
    public String getIndex(Model model){
//        Weather
        model.addAttribute("current", forecastService.getCurrentWeather());
        model.addAttribute("location", geoLocationService.getFirstResult());

//        Board
        model.addAttribute("boards", boardService.getBoards());

//        Budget
        model.addAttribute("transactions", transactionService.getTransactionCreateToday());

        return "/index";
    }
}
