package br.edu.ifms.crudspring.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifms.crudspring.model.Teacher;
import br.edu.ifms.crudspring.service.TeacherService;

@Controller // definindo essa clase como controladora
@RequestMapping("/teacher") // caminho

public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping("/listar")
    public String saves(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.save(teacher);
        return "redirect:/teacher/listar";
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/listar")
    public String locall(@Valid Teacher teacher, Model model) {
        List<Teacher> teachers = teacherService.getTeachers();
        model.addAttribute("listTeachers", teachers);
        model.addAttribute("teacherUpdate", new Teacher());
        return "listTeacher";
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/cadastrar")
    public String Cadastrar(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "cadTeacher";
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/remove/{id}")
    public String removeTeacher(@PathVariable("id") UUID id) {
        teacherService.delete(id);
        return "redirect:/teacher/listar";
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable("id") UUID id, @Valid Teacher teacher, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            teacher.setId(id);
            return "edit";
        }

        this.teacherService.save(teacher);

        return "redirect:/teacher/listar";
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

}
