package com.example.GestionFormations.controllers;

import com.example.GestionFormations.entities.FormateurEntity;
import com.example.GestionFormations.services.FormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/formateur")
public class FormateurController {

    private final FormateurService formateurService;

    @Autowired
    public FormateurController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

    @GetMapping
    public List<FormateurEntity> getFormateurs() {
        return formateurService.getFormateurs();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "{formateurId}")
    FormateurEntity getFormateur(@PathVariable Long formateurId) {
        return formateurService.getFormateur(formateurId);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public FormateurEntity registerNewFormateur(@Valid @RequestBody FormateurEntity formateur, BindingResult bindingResult) {
        formateurService.addNewFormateur(formateur, bindingResult);
        return formateur;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("{id}")
    public void DeleteFormateurById(@PathVariable Long id) {
        formateurService.DeleteFormateur(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping(path = "{formateurId}")
    public void updateFormateur(
            @PathVariable("formateurId") Long formateurId,
            @RequestBody FormateurEntity formateurUpdate
    ) {
        formateurService.updateFormateur(formateurId, formateurUpdate);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path="/link/{sessionId}/{formateurId}")
    public void linkSessionToFormateur(@PathVariable("sessionId") Long sessionId, @PathVariable("formateurId") Long formateurId){
        formateurService.linkNewSessionToFormateur(sessionId, formateurId);
    }


}