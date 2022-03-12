package com.company.springboot.controller;

import com.company.springboot.service.CanvasjsChartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class StatisticsController {

    private final CanvasjsChartService canvasjsChartService;

    public StatisticsController(CanvasjsChartService canvasjsChartService) {
        this.canvasjsChartService = canvasjsChartService;
    }

    @GetMapping("/statistics")
    public String springMVC(ModelMap modelMap) {
        List<List<Map<Object, Object>>> projectsPrices = canvasjsChartService.getCanvasjsDataListForProjects();
        List<List<Map<Object, Object>>> positionsCounts = canvasjsChartService.getCanvasjsDataListForPositions();

        modelMap.addAttribute("projectsPrices", projectsPrices);
        modelMap.addAttribute("positionsCounts", positionsCounts);

        return "managerDashboard_statistics";
    }
}
