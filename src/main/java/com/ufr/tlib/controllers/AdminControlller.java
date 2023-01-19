package com.ufr.tlib.controllers;

import com.ufr.tlib.dataManagementServices.ILocalService;
import com.ufr.tlib.dataManagementServices.IUserService;
import com.ufr.tlib.excepetions.LocalNotFoundException;
import com.ufr.tlib.excepetions.UserNotFoundException;
import com.ufr.tlib.models.Etat;
import com.ufr.tlib.models.Local;
import com.ufr.tlib.models.Service;
import com.ufr.tlib.models.User;
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


    @GetMapping("")
    public String accueilPage(Model model){
        return root + "indexAdmin";
    }

    /*@GetMapping("/liste/users")
    public String listeUsers(Model model) throws UserNotFoundException {
        model.addAttribute("users",userService.getListUsers());
        return root + "liste_users";
    }*/

    @GetMapping("/liste/local")
    public String listeLocal(Model model) throws UserNotFoundException {
        model.addAttribute("locals",localService.getListLocal());
        model.addAttribute("filtreName", "tous");
        return root + "liste_local";
    }

    @GetMapping("/liste/local/enable")
    public String listeLocalEnable(Model model) {
        model.addAttribute("locals",localService.getListEnableLocal());
        model.addAttribute("filtreName", "enable");
        return root + "liste_local";
    }

    @GetMapping("/liste/local/disable")
    public String listeLocalDisable(Model model) {
        model.addAttribute("locals",localService.getListDisableLocal());
        model.addAttribute("filtreName", "disable");
        return root + "liste_local";
    }

    @GetMapping("/liste/local/inwaiting")
    public String listeLocalWaiting(Model model) {
        model.addAttribute("locals",localService.getListWaitingLocal());
        model.addAttribute("filtreName", "wait");
        return root + "liste_local";
    }

    @GetMapping("/local/bloquer/{id}")
    public String bloquerLocal(@PathVariable("id") long id, Model model) throws LocalNotFoundException {
        localService.disableLocal(id);
        model.addAttribute("locals",localService.getListLocal());
        return root + "liste_local";
    }

    @GetMapping("/local/debloquer/{id}")
    public String debloquerLocal(@PathVariable("id") long id, Model model) throws LocalNotFoundException {
        localService.enableLocal(id);
        model.addAttribute("locals",localService.getListLocal());
        return root + "liste_local";
    }

    @GetMapping("/local/delete/{id}")
    public String deleteLocal(@PathVariable("id") long id, Model model) throws LocalNotFoundException {
        localService.deleteLocal(id);
        model.addAttribute("locals",localService.getListLocal());
        return root + "liste_local";
    }


    @GetMapping("/local/accepter/{id}")
    public String accepterLocal(@PathVariable("id") long id, Model model) throws LocalNotFoundException {
        localService.enableLocal(id);
        model.addAttribute("locals",localService.getListLocal());
        return root + "liste_local";
    }

    @GetMapping("/local/{id}")
    public String localDetails(@PathVariable("id") long id, Model model){
        Local local= null;
        try{
            local = localService.getLocalById(id);
        }catch(Exception ex){
            return "error/400";
        }

        model.addAttribute("local",local);
        model.addAttribute("services", Service.values());
        return root + "details_local";
    }


    @GetMapping("/users/bloquer/{id}")
    public String bloquerUser(@PathVariable("id") long id, Model model) throws UserNotFoundException {
        userService.disableUser(id);
        model.addAttribute("users",userService.getListUsers());
        return root + "liste_users";
    }

    @GetMapping("/users/debloquer/{id}")
    public String debloquerUser(@PathVariable("id") long id, Model model) throws UserNotFoundException {
        userService.enableUser(id);
        model.addAttribute("users",userService.getListUsers());
        return root + "liste_users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) throws UserNotFoundException {
        userService.deleteUserById((int) id);
        model.addAttribute("users",userService.getListUsers());
        return root + "liste_users";
    }

    @GetMapping("/users/{id}")
    public String userInformations(@PathVariable("id") long id, Model model){
        User user= null;
        try{
            user = userService.getUserById(id);
        }catch(Exception ex){
            return "error/400";
        }

        model.addAttribute("user",user);
        model.addAttribute("services", Service.values());
        return root + "info_user";
    }

}
