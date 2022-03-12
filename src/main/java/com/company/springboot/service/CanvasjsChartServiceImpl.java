package com.company.springboot.service;

import com.company.springboot.model.Position;
import com.company.springboot.model.Project;
import com.company.springboot.model.User;
import com.company.springboot.repository.PositionRepository;
import com.company.springboot.repository.ProjectRepository;
import com.company.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CanvasjsChartServiceImpl implements CanvasjsChartService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsDataListForProjects() {
        List<Project> projects = projectRepository.findAll();

        Map<Object, Object> map = null;
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
        List<Map<Object, Object>> dataPoints = new ArrayList<Map<Object, Object>>();

        for (Project project : projects
        ) {
            map = new HashMap<Object, Object>();
            map.put("label", project.getName());
            map.put("y", project.getPrice());
            dataPoints.add(map);
        }
        list.add(dataPoints);

        return list;
    }

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsDataListForPositions() {
        List<User> users = userRepository.findAll();
        Map<String, Long> positionsCounts =
                users.stream().collect(Collectors.groupingBy(e -> e.getPosition().getPositionName(), Collectors.counting()));
        int usersCount = users.size();

        Map<Object, Object> map = null;
        List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
        List<Map<Object, Object>> dataPoints = new ArrayList<Map<Object, Object>>();

        for (Map.Entry<String, Long> entry : positionsCounts.entrySet()
        ) {
            map = new HashMap<Object, Object>();
            map.put("label", entry.getKey());
            map.put("y", entry.getValue().doubleValue() / usersCount * 100);
            dataPoints.add(map);
        }
        list.add(dataPoints);

        return list;
    }

}
