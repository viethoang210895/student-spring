package com.codegym.controller;

import com.codegym.model.Classes;
import com.codegym.model.Student;
import com.codegym.service.ICRUDService;
import com.codegym.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private ICRUDService<Student, Long> icrudService;
    @Autowired
    private IClassService iClassService;
    @Value("${upload}")
    private String upload;

    @ModelAttribute("classes")
    public List<Classes> getClasses() {
        return iClassService.findClass();
    }
    @GetMapping
    public ModelAndView listProducts(@PageableDefault(size = 3) Pageable pageable,
                                     @RequestParam("search") Optional<String> name) {
        ModelAndView modelAndView = new ModelAndView("student/list");
        if (!name.isPresent()) {
            modelAndView.addObject("students", icrudService.findAll( pageable));
        } else {
            modelAndView.addObject("students", icrudService.findByName(name.get(), pageable));
            modelAndView.addObject("search", name.get());
        }
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("student/form");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("student/form");
        Optional<Student> student = Optional.ofNullable(icrudService.findById(id));
        if (student.isPresent()) {
            modelAndView.addObject("student", student.get());
        } else {
            return new ModelAndView("404");
        }
        return modelAndView;
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        MultipartFile image = student.getImg();
        String name = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(image.getBytes(), new File(upload + name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        student.setImage(name);
        icrudService.save(student);
        return "redirect:/students";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        icrudService.delete(id);
        return "redirect:/students";
    }
}
