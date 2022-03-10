package com.company.springboot.service;

import com.company.springboot.model.Position;
import com.company.springboot.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionRepository positionRepository;

	@Override
	public List<Position> getAllPositions() {
		return positionRepository.findAll();
	}

	@Override
	public void savePosition(Position position) {
		positionRepository.save(position);
	}

	@Override
	public Position getPositionById(long id) {
		Optional<Position> optional = positionRepository.findById(id);
		Position position = null;
		if (optional.isPresent()) {
			position = optional.get();
		} else {
			throw new RuntimeException(" Position not found for id :: " + id);
		}
		return position;
	}

	@Override
	public void deletePositionById(long id) {
		positionRepository.deleteById(id);
	}

	@Override
	public Page<Position> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return positionRepository.findAll(pageable);
	}
}
