package com.example.backenddt.controllers;

import com.example.backenddt.requeteDTO.AssociationRquestDTO;
import com.example.backenddt.requeteDTO.ParentRequestDTO;
import com.example.backenddt.requeteDTO.ParrainRequestDTO;
import com.example.backenddt.requeteDTO.UserRequestDTO;
import com.example.backenddt.services.AssociationService;
import com.example.backenddt.services.ParentService;
import com.example.backenddt.services.ParrainService;
import com.example.backenddt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private AssociationService associationService;
    @Autowired
    private ParrainService parrainService;
    @Autowired
    private ParentService parentService;

    @PostMapping("new/association")
    public ResponseEntity<?> createAssociation(@RequestBody AssociationRquestDTO users){
        return associationService.createAssociation(users);
    }

    @PostMapping("new/parrain")
    public ResponseEntity<?> createParrain(@RequestBody ParrainRequestDTO users){
        return parrainService.createParrain(users);
    }

    @PostMapping("new/parent")
    public ResponseEntity<?> createParent(@RequestBody ParentRequestDTO users){
        return parentService.createParent(users);
    }
}
