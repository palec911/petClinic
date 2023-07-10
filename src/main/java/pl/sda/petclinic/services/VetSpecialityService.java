package pl.sda.petclinic.services;

import org.springframework.stereotype.Service;
import pl.sda.petclinic.model.VetSpeciality;
import pl.sda.petclinic.repositories.VetSpecialityRepository;

@Service
public class VetSpecialityService {

    VetSpecialityRepository vetSpecialityRepository;

    public VetSpecialityService(VetSpecialityRepository vetSpecialityRepository) {
        this.vetSpecialityRepository = vetSpecialityRepository;
    }

    public void saveSpeciality(VetSpeciality vetSpeciality) {
        vetSpecialityRepository.save(vetSpeciality);
    }
    public VetSpeciality getSpeciality(Long id) {
        return vetSpecialityRepository.findById(id).get();
    }
}