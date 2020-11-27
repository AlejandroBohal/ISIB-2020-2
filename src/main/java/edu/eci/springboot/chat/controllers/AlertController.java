package edu.eci.springboot.chat.controllers;

import edu.eci.springboot.chat.models.Plant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
public class AlertController {
    private Plant plant = new Plant();

    @PostMapping("/stats")
    public ResponseEntity<?> create(@RequestBody Plant json) {
        System.out.println(json);
        this.plant = json;
        return new ResponseEntity<Map<String, Object>>(HttpStatus.CREATED);
    }

    @GetMapping("/stats")
    //  Retorno el estado de la creacion (por defecto es OK)
//  @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show() {
        return new ResponseEntity<Plant>(this.plant, HttpStatus.OK);
    }

}
