package pl.sda.petclinic.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.sda.petclinic.model.Vet;
import pl.sda.petclinic.repositories.VetRepository;

import java.util.List;

@Service
public class VetService {
    VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public void createVet(Vet vet) {
        vetRepository.save(vet);
    }
    public Vet getVet(Long id) {
        return vetRepository.findById(id).get();
    }

    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }
}