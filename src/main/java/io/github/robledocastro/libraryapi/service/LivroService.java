package io.github.robledocastro.libraryapi.service;

import io.github.robledocastro.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
}
