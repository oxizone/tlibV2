package com.ufr.tlib.controllers;

import com.ufr.tlib.dataManagementServices.ILocalService;
import com.ufr.tlib.dataManagementServices.IUserService;
import com.ufr.tlib.excepetions.UserNotFoundException;
import com.ufr.tlib.models.Local;
import com.ufr.tlib.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminControlller {

    private final String root = "admin/";

    @Autowired
    private IUserService userService;
    @Autowired
    private ILocalService localService;

    @GetMapping("/liste/local")
    public String listeLocal(Model model) throws UserNotFoundException {
        model.addAttribute("locals",localService.getListLocal());
        return root + "liste_local";
    }

    @GetMapping("/local/bloquer/{id}")
    public String bloquerLocal(Model model) throws UserNotFoundException{
        
    }
}
