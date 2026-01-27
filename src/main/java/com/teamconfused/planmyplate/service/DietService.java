package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.Diet;
import com.teamconfused.planmyplate.repository.DietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietService {
  private final DietRepository repository;

  public List<Diet> getAll() {
    return repository.findAll();
  }
}
