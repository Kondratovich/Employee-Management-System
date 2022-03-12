package com.company.springboot.service;

import java.util.List;
import java.util.Map;

public interface CanvasjsChartService {

    List<List<Map<Object, Object>>> getCanvasjsDataListForProjects();

    List<List<Map<Object, Object>>> getCanvasjsDataListForPositions();

}
