package com.codegym.controller;

import com.codegym.model.Classes;
import com.codegym.service.ICRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
@RequestMapping("/classes")
public class ClassController {
    @Autowired
    private ICRUDService<Classes,Long>icrudService;
    @GetMapping
    public ModelAndView listClasses(@PageableDefault(size = 3)Pageable pageable,
                                    @RequestParam("search") Optional<String> name){
        ModelAndView modelAndView=new ModelAndView("class/list");
        if (!name.isPresent()){
            modelAndView.addObject("classes",icrudService.findAll(pageable));
        }else {
            modelAndView.addObject("classes",icrudService.findByName(name.get(),pageable));
            modelAndView.addObject("search", name.get());
        }return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("class/form");
        modelAndView.addObject("classes", new Classes());
        return modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("category/form");
        Optional<Classes> classes = Optional.ofNullable(icrudService.findById(id));
        if (classes.isPresent()) {
            modelAndView.addObject("classes", classes.get());
        } else {
            return new ModelAndView("404");
        }
        return modelAndView;
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Classes classes) {
        icrudService.save(classes);
        return "redirect:/classes";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        icrudService.delete(id);
        return "redirect:/classes";
    }


}
