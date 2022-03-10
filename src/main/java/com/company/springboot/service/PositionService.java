package com.company.springboot.service;

import com.company.springboot.model.Customer;
import com.company.springboot.model.Position;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PositionService {

    List<Position> getAllPositions();

    void savePosition(Position position);

    Position getPositionById(long id);

    void deletePositionById(long id);

    Page<Position> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
