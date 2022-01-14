package org.springframework.samples.petclinic.feeding;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {

    private static final String VIEWS_FEEDING_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";

    @Autowired
    private FeedingService feedingService;

    @Autowired
    private PetService petService;

    @GetMapping(value = "/create")
    public String initCreationForm(Pet pet, ModelMap model) {
        Feeding feeding = new Feeding();
        List<FeedingType> allFeedingTypes = this.feedingService.getAllFeedingTypes();
        List<Pet> allPets = this.petService.getAllPets();
        model.put("feeding", feeding);
        model.put("feedingTypes", allFeedingTypes);
        model.put("pets", allPets);
        return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/create")
    public String processCreationForm(@Valid Feeding feeding, ModelMap model) {
        try {
            this.feedingService.save(feeding);
            return "redirect:/welcome";
        } catch (Exception e) {
            return "feedings/createOrUpdateFeedingForm";
        }
    }
}
