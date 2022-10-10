package com.example.RpnApi.controller;


import com.example.RpnApi.entity.StackRpn;
import com.example.RpnApi.repo.StackRepository;
import com.example.RpnApi.service.StackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StackController {

    @Autowired
    private StackRepository stackRepository;
    @Autowired
    private StackService stackService;
    @ApiIgnore
    @RequestMapping("/")
    public String home() {
        return "this should be a home page of the RPN API";
    }
    //list all available stacks
    @GetMapping("/stacks")
    public List<StackRpn> stacks(){
        return stackRepository.findAll();
    }

    //Create new stack
    @PostMapping(path ="/stack",consumes = {"application/json"})
    public StackRpn addStack(@RequestBody StackRpn stackRpn){
        return stackRepository.save(stackRpn);
    }

    //Delete a stack
    @DeleteMapping("/stack/{sid}")
    public String deleteStack(@PathVariable long sid){
        StackRpn stackToDelete = stackRepository.getById(sid);
        stackRepository.delete(stackToDelete);
        return "deleted";
    }
    // list all operators
    @GetMapping("/op")
    public List<String> OperatorsListing(){
        List<String> OperatorsList = Arrays.asList("+","-","*","d");
        return OperatorsList;
    }
    //push new value to stack
    @PostMapping("/stack/{sid}/{value}")
    public StackRpn addOperandToStack(@PathVariable long sid,@PathVariable int value){
        StackRpn stackToPushOn = stackRepository.getById(sid);
        stackToPushOn.getStack().push(value);
        return stackRepository.save(stackToPushOn);
    }
    //Apply operation to stack
    @PostMapping("/op/{op}/stack/{sid}")
    public StackRpn addOperandToStack(@PathVariable long sid,@PathVariable String op){
        StackRpn stackToPushOn = stackRepository.getById(sid);
        if (stackToPushOn.getStack().size()<=2){
            stackToPushOn.getStack().push(
         stackService.CalculateLastTwoOperand(stackToPushOn.getStack().pop(),stackToPushOn.getStack().pop(),op)
            );
        }
        return stackRepository.save(stackToPushOn);
    }

}
