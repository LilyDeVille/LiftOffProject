package org.launchcode.LiftOffProject.Controllers;


import org.launchcode.LiftOffProject.Repository.StepRepository;
import org.launchcode.LiftOffProject.models.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("steps")
public class StepController {

    @Autowired
    private StepRepository stepRepository;

    @GetMapping("add")
    public String displayAddStepForm(Model model) {
        model.addAttribute(new Step());
        return "steps/add";
    }

    @PostMapping("add")
    public String processAddStepForm(@ModelAttribute Step newStep, Model model) {

        stepRepository.save(newStep);
        return "redirect:../add";
    }



}
