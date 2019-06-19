package com.project.petlife.controller;


import com.project.petlife.model.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

    @GetMapping("owners")
    public String ownersList(Model model) {
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "owners/ownersList";
    }
}
