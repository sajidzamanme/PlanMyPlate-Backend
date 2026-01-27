package com.teamconfused.planmyplate.service;

import com.teamconfused.planmyplate.entity.Allergy;
import com.teamconfused.planmyplate.repository.AllergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergyService {
  private final AllergyRepository repository;

  public List<Allergy> getAll() {
    return repository.findAll();
  }
}
