package pl.sda.petclinic.services;

import org.springframework.stereotype.Service;
import pl.sda.petclinic.model.Visit;
import pl.sda.petclinic.repositories.VisitRepository;

@Service
public class VisitService {
    VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public void createVisit(Visit visit) {
        visitRepository.save(visit);
    }

    public Visit getVisit(Long id){
        return visitRepository.findById(id).orElse(null);
    }
}