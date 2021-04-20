package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.DataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/data")
public class RestController {

    final private DataService dataService;

    public RestController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/add")
    private List<CustomerDTO> post(@RequestBody CustomerDTO data){
        return dataService.addCustomer(data);
    }

    @GetMapping(value = "/{id}")
    private CustomerDTO getOne(@PathVariable Long id){
        return dataService.getCustomer(id);
    }

    @RequestMapping(value = "/all", method = {RequestMethod.GET})
    private List<CustomerDTO> getAll(){
        return dataService.getCustomerList();
    }

}
