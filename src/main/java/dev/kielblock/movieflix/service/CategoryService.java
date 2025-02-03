package dev.kielblock.movieflix.service;

import dev.kielblock.movieflix.entity.Category;
import dev.kielblock.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<Category> findAll() {
       return repository.findAll();
    }

    public Category save (Category category) {
        return repository.save(category);
    }

    public Optional<Category> findById(long id) {
        return repository.findById(id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
