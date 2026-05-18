package org.iftm.gerenciadorveterinarios.servicies;

import org.iftm.gerenciadorveterinarios.entities.Animal;
import org.iftm.gerenciadorveterinarios.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    @Transactional
    public Animal cadastrar(Animal animal) {
        if ("Cobra".equalsIgnoreCase(animal.getEspecie())
                || "Aranha".equalsIgnoreCase(animal.getEspecie())) {
            throw new IllegalArgumentException("Espécie não atendida pela clínica");
        }
        animal.setInternado(true);
        return repository.save(animal);
    }

    @Transactional
    public Animal darAlta(Integer id) {
        Animal animal = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        animal.setInternado(false);
        return repository.save(animal);
    }
}
