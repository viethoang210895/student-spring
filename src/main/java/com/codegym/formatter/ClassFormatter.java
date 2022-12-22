package com.codegym.formatter;

import com.codegym.model.Classes;
import com.codegym.service.ICRUDService;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class ClassFormatter implements Formatter<Classes> {
    private final ICRUDService<Classes, Long> iCrudService;

    public ClassFormatter(ICRUDService<Classes, Long> iCrudService) {
        this.iCrudService = iCrudService;
    }
    @Override
    public Classes parse(String text, Locale locale) {
        Optional<Classes> classes = Optional.ofNullable(iCrudService.findById(Long.parseLong(text)));
        return classes.orElseGet(Classes::new);
    }


    @Override
    public String print(Classes object, Locale locale) {
        return null;
    }
}
