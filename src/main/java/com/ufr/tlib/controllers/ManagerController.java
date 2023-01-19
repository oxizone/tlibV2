package com.ufr.tlib.controllers;

import com.ufr.tlib.dataManagementServices.*;
import com.ufr.tlib.excepetions.ArtisanNotFound;
import com.ufr.tlib.excepetions.UserNotFoundException;
import com.ufr.tlib.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    public static final String MANAGER_LOCAL = "/manager/local/";
    private final String root = "manager/";
    private final String IMAGE_FOLDER = "/static/artisan_image/";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss");

    @Autowired
    private IUserService userService;
    @Autowired
    private ILocalService localService;
    @Autowired
    private IArtisanService artisanService;
    @Autowired
    private IPrestationService prestationService;

    @Autowired
    private IAbsenceService absenceService;


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

    @GetMapping("/local/liste")
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

         return "redirect:"+MANAGER_LOCAL+local.getId();
     }

     //// Artisant
     @PostMapping("/artisan")
     @ResponseBody
    public String addArtisan(@RequestParam("localId") long idLocal, @RequestParam("fName") String fName,@RequestParam("lName") String lName) throws IOException {


        Local local = Local.builder()
                 .id(idLocal)
                 .build();
        Artisan artisan = Artisan.builder()
                 .firstName(fName)
                 .lastName(lName)
                 .avatar("")
                 .local(local)
                 .build();
        artisanService.addArtisan(artisan);

         return MANAGER_LOCAL+idLocal;
     }

    @PutMapping("/artisan")
    @ResponseBody
    public String updateArtisan(@RequestParam("artisanId") Long artisanId,@RequestParam("fName") String fName, @RequestParam("lName") String lName, @RequestParam("avatar") String avatar,Model model) throws ArtisanNotFound {

        Artisan artisan = artisanService.getArtisanById(artisanId);
        artisan.setAvatar(avatar);
        artisan.setFirstName(fName);
        artisan.setLastName(lName);
        artisanService.updateArtisan(artisan);
        return MANAGER_LOCAL+artisan.getLocal().getId();
    }

    @DeleteMapping("/artisan")
    @ResponseBody
    public HttpStatus deleteArtisan(@RequestParam("artisanId") Long artisanId, Principal principal) {

        try {
            Artisan artisan = artisanService.getArtisanById(artisanId);
            if (!artisan.getLocal().getManager().getUsername().equals(principal.getName()))
                return HttpStatus.UNAUTHORIZED;
            artisanService.deleteArtisan(artisan);
            return HttpStatus.OK;
        } catch (ArtisanNotFound e) {
            return HttpStatus.NOT_FOUND;
        } catch(DataIntegrityViolationException ex){
            return HttpStatus.FAILED_DEPENDENCY;
        }
    }

    @PostMapping("/absence")
    @ResponseBody
    public HttpStatus addAbsence(@RequestParam("artisanId") Long artisanId, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate, Principal principal) {

        Artisan artisan = Artisan.builder()
                .id(artisanId)
                .build();
        Absence absence = Absence.builder()
                .startDate(startDate)
                .endDate(endDate)
                .artisan(artisan)
                .build();
        int code = absenceService.addAbsence(absence);
        if (code == 1)
            return HttpStatus.OK;
        else
            return HttpStatus.CONFLICT;
    }

    @GetMapping("/artisant/absence/{id}")
    @ResponseBody
    public List<Absence> listeAbsence(@PathVariable("id") Long artisanId, Principal principal) throws ArtisanNotFound {

        Artisan artisan = artisanService.getArtisanById(artisanId);

        return artisan.getAbsences();
    }


    //// Prestation

    @PostMapping("/prestation")
    @ResponseBody
    public String addPrestation(@RequestParam("localId") Long idLocal,@RequestParam("titre") String titre, @RequestParam("description") String description, @RequestParam("duration") int duration, @RequestParam("price") double price,Model model){
        Local local = Local.builder()
                .id(idLocal)
                .build();
        Prestation prestation = Prestation.builder()
                .titre(titre)
                .description(description)
                .duration(duration)
                .price(price)
                .local(local)
                .build();
        prestationService.addPrestation(prestation);
        return MANAGER_LOCAL +idLocal;
    }
}
