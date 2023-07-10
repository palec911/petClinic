package pl.sda.petclinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.petclinic.model.*;
import pl.sda.petclinic.services.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestApiController {
    PetService petService;
    OwnerService ownerService;
    VisitService visitService;
    VetService vetService;
    VetSpecialityService vetSpecialityService;

    public RestApiController(PetService petService, OwnerService ownerService, VisitService visitService, VetService vetService, VetSpecialityService vetSpecialityService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.visitService = visitService;
        this.vetService = vetService;
        this.vetSpecialityService = vetSpecialityService;
    }

    @PostMapping("/owner")
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        ownerService.addOwner(owner);
        return new ResponseEntity<>(owner, HttpStatus.CREATED);
    }

    @PostMapping("/visit")
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        visitService.createVisit(visit);
        return new ResponseEntity<>(visit, HttpStatus.CREATED);
    }

    @PostMapping("/pet")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        return new ResponseEntity<>(petService.createPet(pet).get(), HttpStatus.CREATED);
    }

    @GetMapping("/visit/{id}")
    public ResponseEntity<Visit> getVisit(@PathVariable Long id ) {
        return new ResponseEntity<>(visitService.getVisit(id), HttpStatus.OK);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
        return new ResponseEntity<>(ownerService.getOwnerById(id), HttpStatus.OK);
    }

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        return new ResponseEntity<>(ownerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getPets() {
        return new ResponseEntity<>(petService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/vet")
    public ResponseEntity createVet(@RequestBody Vet vet) {
        vetService.createVet(vet);
        System.out.println( "Created vet " + vet);
        return new ResponseEntity<>(vet, HttpStatus.CREATED);
    }

    @GetMapping("/vets")
    public ResponseEntity getAllVets() {
        return new ResponseEntity<>(vetService.getAllVets(), HttpStatus.CREATED);
    }

    @PostMapping("/speciality")
    public ResponseEntity createSpeciality(@RequestBody VetSpeciality vetSpeciality) {
        vetSpecialityService.saveSpeciality(vetSpeciality);
        System.out.println( "Created vet speciality " + vetSpeciality);
        return new ResponseEntity<>(vetSpeciality, HttpStatus.CREATED);
    }

    @PutMapping("/assign")
    public ResponseEntity assignVetToSpeciality(@RequestParam Long vetId, @RequestParam Long specialityId) {
        Vet vet = vetService.getVet(vetId);
        VetSpeciality specialitiesToSet = vetSpecialityService.getSpeciality(specialityId);
        Set<VetSpeciality> specialitiesFromVet = vet.getSpecialities();
        specialitiesFromVet.add(specialitiesToSet);
        vet.setSpecialities(specialitiesFromVet);
        vetService.createVet(vet);
        Set<Vet> vets = specialitiesToSet.getVets();
        vets.add(vet);
        specialitiesToSet.setVets(vets);
        vetSpecialityService.saveSpeciality(specialitiesToSet);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}