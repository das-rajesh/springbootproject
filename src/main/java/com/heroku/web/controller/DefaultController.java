/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heroku.web.controller;

import com.heroku.web.entity.Color;
import com.heroku.web.entity.repository.ColorRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dell
 */
@Controller
@RequestMapping(value = "/")
public class DefaultController {

    @Autowired
    private ColorRepository colorRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
    
        model.addAttribute("colors", colorRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    @ResponseBody
    public String about() {
        return "<h1> About us </h1>";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("Color")Color color){
        colorRepository.save(color);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id")long id, Model model){
        
          model.addAttribute("color",colorRepository.findById(id).get());
        return "index";
    }
    
    
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable("id")long id){
        colorRepository.deleteById(id);
        return "redirect:/";
    }
}
