package pl.sda.petclinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.petclinic.model.Owner;
import pl.sda.petclinic.model.Pet;
import pl.sda.petclinic.model.Visit;
import pl.sda.petclinic.services.OwnerService;
import pl.sda.petclinic.services.PetService;
import pl.sda.petclinic.services.VisitService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
    PetService petService;
    OwnerService ownerService;
    VisitService visitService;

    public RestApiController(PetService petService, OwnerService ownerService, VisitService visitService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.visitService = visitService;
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

}