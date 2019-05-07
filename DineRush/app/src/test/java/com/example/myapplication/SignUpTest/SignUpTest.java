package com.example.myapplication.SignUpTest;

import com.example.myapplication.SignUp;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpTest {

    private SignUp signUpInstance = new SignUp();


    @Test
    public void validateNormalText(){
        boolean result = signUpInstance.validateText("NormalText");
        assertTrue(result);
    }

    @Test
    public void validateFaultyText() {
        boolean result = signUpInstance.validateText("&&Faulty");
        assertFalse(result);
    }


    @Test
    public void validateNormalPassword() {
        boolean result = signUpInstance.validatePassword("IAMLONgEnough");
        assertTrue(result);
    }

    @Test
    public void validateFaultyPassword() {
        boolean result = signUpInstance.validatePassword("12");
        assertFalse(result);
    }


}
