package com.ugtsic.myproject.service;

import com.ugtsic.myproject.model.Application;
import com.ugtsic.myproject.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository repository;

    public ApplicationService(ApplicationRepository repository) {
        this.repository = repository;
    }

    public Application saveApplication(Application application) {
        return repository.save(application);
    }
}
