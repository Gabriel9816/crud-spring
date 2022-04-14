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

import br.edu.ifms.crudspring.model.Turma;
import br.edu.ifms.crudspring.service.TurmaService;

@Controller // definindo essa clase como controladora
@RequestMapping("/turma") // caminho
public class TurmaController {
    
    @Autowired
    TurmaService turmaService;

    @PostMapping("/listar")
    public String saves(@ModelAttribute("turma") Turma turma) {
        turmaService.save(turma);
        return "redirect:/turma/listar";
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/listar")
    public String locall(@Valid Turma turma, Model model) {
        List<Turma> turmas = turmaService.getTurmas();
        model.addAttribute("listTurmas", turmas);
        model.addAttribute("turmaUpdate", new Turma());
        return "listTurma";
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/cadastrar")
    public String Cadastrar(Model model) {
        model.addAttribute("turma", new Turma());
        return "cadTurma";
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/remove/{id}")
    public String removeTurma(@PathVariable("id") UUID id) {
        turmaService.delete(id);
        return "redirect:/turma/listar";
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    @PostMapping("/update/{id}")
    public String updateTurma(@PathVariable("id") UUID id, @Valid Turma turma, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            turma.setId(id);
            return "edit";
        }

        this.turmaService.save(turma);

        return "redirect:/turma/listar";
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

}

