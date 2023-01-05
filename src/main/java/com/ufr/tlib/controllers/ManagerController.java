package com.ufr.tlib.controllers;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.ufr.tlib.dataManagementServices.ILocalService;
import com.ufr.tlib.dataManagementServices.IUserService;
import com.ufr.tlib.excepetions.UserNotFoundException;
import com.ufr.tlib.models.Local;
import com.ufr.tlib.models.Service;
import com.ufr.tlib.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final String root = "manager/";

    @Autowired
    private IUserService userService;
    @Autowired
    private ILocalService localService;


    @GetMapping("/creation")
    public String localForm(Model model){
        model.addAttribute("services", Service.values());
        model.addAttribute("local", new Local());
        return root + "create_local";
    }

    @PostMapping("/addLocal")
    public String localCreation(@ModelAttribute("local") @Valid Local local, Principal principal, BindingResult result, Model model) throws UserNotFoundException {
        if (result.hasErrors()) {
            model.addAttribute("services", Service.values());
            return root +"create_local";
        }
        localService.addLocal(local, principal.getName());
        return "redirect:creation?success";
    }

    @GetMapping("/liste/local")
    public String listeLocal(Principal principal,Model model) throws UserNotFoundException {
        model.addAttribute("locals",localService.getListLocalByManager(principal.getName()));
        return root + "liste_local";
    }

     @GetMapping("/local/edit/{id}")
    public String editForm(@PathVariable("id") long id, Principal principal, Model model){
         Local local= null;
        try{
            local = localService.getLocalById(id);
        }catch(Exception ex){
            return "error/400";
        }
        if(!principal.getName().equals(local.getManager().getUsername()))
                return "error/403";

        model.addAttribute("local",local);
        model.addAttribute("services", Service.values());
        return root + "update_local";
     }

    @GetMapping("/local/{id}")
    public String localDetails(@PathVariable("id") long id, Principal principal, Model model){
        Local local= null;
        try{
            local = localService.getLocalById(id);
        }catch(Exception ex){
            return "error/400";
        }
        System.out.println(local.getManager().getUsername());
        if(!principal.getName().equals(local.getManager().getUsername()))
            return "error/403";

        model.addAttribute("local",local);
        model.addAttribute("services", Service.values());
        return root + "details_local";
    }

     @PostMapping("/local/update")
    public String updateLocal(@ModelAttribute("local") @Valid Local local, BindingResult result,Model model){
         if (result.hasErrors()) {
             model.addAttribute("services", Service.values());
             return root +"update_local";
         }
         localService.updateLocal(local);

         return "redirect:/manager/liste/local?updateSuccess";
     }
}

