package com.company.springboot.service;

import com.company.springboot.model.Team;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();

    Team saveTeam(Team teamDto);

    Team getTeamById(long id);

    void deleteTeamById(long id);

    Page<Team> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
