package com.example.RpnApi.service;


import com.example.RpnApi.entity.StackRpn;
import com.example.RpnApi.exception.OperatorNotValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class StackService {

    @Autowired
    private StackRpn stackRpn;

    public StackService(StackRpn stackRpn){
        this.stackRpn=stackRpn;
    }

    public int CalculateLastTwoOperand( int RightOperand,int LeftOperand,String Operator){
        //we have 4 cases: "+","-","*",("d" or "/")
        switch (Operator) {
            case "+":
                return  LeftOperand + RightOperand;
            case "-":
                return  LeftOperand - RightOperand;
            case "*":
                return  LeftOperand * RightOperand;
            case "d":
                return  LeftOperand / RightOperand;

            default: throw new OperatorNotValid();
        }

    }

    }


