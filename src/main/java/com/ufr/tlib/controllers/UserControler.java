package com.ufr.tlib.controllers;


import com.ufr.tlib.dataManagementServices.ILocalService;
import com.ufr.tlib.dataManagementServices.IUserService;
import com.ufr.tlib.excepetions.UserNotFoundException;
import com.ufr.tlib.models.Local;
import com.ufr.tlib.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserControler {
    private final String root = "client/";

    @Autowired
    private IUserService userService;

    @Autowired
    private ILocalService localService;

    @GetMapping("/locaux")
    public String listeLocal(Model model,
                             @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                             @RequestParam(name = "size", defaultValue = "6", required = false) int size,
                             @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
                             @RequestParam(name = "service", defaultValue = "", required = false) Service service

    ) throws UserNotFoundException {
        Page<Local> locauxPage;
        if(service == null){
            locauxPage = localService.getLocalPageByKeyword(keyword, page,size);
        }else{
            locauxPage = localService.getLocalPageByKeywordAndServiceType(keyword,service, page,size);
        }

        model.addAttribute("locaux",locauxPage.getContent());
        model.addAttribute("pages",new int[locauxPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("size",size);
        model.addAttribute("service",service);
        return root + "liste_locaux";
    }

    @GetMapping("locaux/{id}")
    public String localDetails(Model model, @PathVariable long id){

        Local local = localService.getLocal(id);
        model.addAttribute("local",local);
        return root + "local_details";
    }

}
