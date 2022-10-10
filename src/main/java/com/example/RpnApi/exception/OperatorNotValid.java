package com.example.RpnApi.exception;

public class OperatorNotValid extends RuntimeException{

        public OperatorNotValid(){
            super("operator not valid, we support only +,*,-,d");
        }
}
