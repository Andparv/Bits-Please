package com.example.demo.controllers;

import com.example.demo.smartid.SmartIdAuthenticationToken;
import com.example.demo.smartid.Verification;
import ee.sk.smartid.*;
import ee.sk.smartid.exception.*;
import ee.sk.smartid.rest.dao.NationalIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/smart-id/authentication")
public class SmartIdRestController {

    private SmartIdClient smartIdClient;

    @Autowired
    SmartIdRestController(SmartIdClient smartIdClient) {
        this.smartIdClient = smartIdClient;
    }

    @PostMapping(value = "/start")
    public Verification startAuthentication(@RequestBody NationalIdentity nationalIdentity, HttpSession httpSession) {
        // For security reasons a new hash value must be created for each new authentication request
        AuthenticationHash authenticationHash = AuthenticationHash.generateRandomHash();

        httpSession.setAttribute("nationalIdentity", nationalIdentity);
        httpSession.setAttribute("authenticationHash", authenticationHash);

        Verification verification = new Verification();
        verification.setCode(authenticationHash.calculateVerificationCode());
        return verification;
    }

    @PostMapping(value = "/poll")
    public SmartIdAuthenticationResult pollAuthenticationResult(HttpSession httpSession) {

        SmartIdAuthenticationResponse authenticationResponse = smartIdClient
                .createAuthentication()
                .withNationalIdentity((NationalIdentity) httpSession.getAttribute("nationalIdentity"))
                .withAuthenticationHash((AuthenticationHash) httpSession.getAttribute("authenticationHash"))
                .withCertificateLevel("QUALIFIED") // Certificate level can either be "QUALIFIED" or "ADVANCED"
                .withDisplayText("Logging in...")
                .authenticate();

        AuthenticationResponseValidator authenticationResponseValidator = new AuthenticationResponseValidator();
        SmartIdAuthenticationResult authenticationResult = authenticationResponseValidator.validate(authenticationResponse);



        Authentication auth = new SmartIdAuthenticationToken(
                authenticationResult.getAuthenticationIdentity(),
                null,
                null
        );
        SecurityContextHolder.getContext().setAuthentication(auth);


        return authenticationResult;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = SmartIdException.class)
    public Map<String, String> smartIdExceptionHandler(SmartIdException smartIdException) {
        Map<String, String> errorMap = new HashMap<>();
        if (smartIdException instanceof UserAccountNotFoundException) {
            errorMap.put("errorMessage", "Account not found!");
        }
        if (smartIdException instanceof UserRefusedException) {
            errorMap.put("errorMessage", "User cancelled Smart-ID request");
        }
        /*if (smartIdException instanceof InvalidParametersException){
            errorMap.put("errorMessage", "Invalid parameters");
        }
        if (smartIdException instanceof RequestForbiddenException){
            errorMap.put("errorMessage", "Request forbidden");
        }
        if (smartIdException instanceof SessionTimeoutException){
            errorMap.put("errorMessage", "Session timed out");
        }
        if (smartIdException instanceof DocumentUnusableException){
            errorMap.put("errorMessage", "Document unusable. Check Smart-ID application for more information");
        }
        if (smartIdException instanceof TechnicalErrorException){
            errorMap.put("errorMessage", "Unknown values inserted");
        }
        if (smartIdException instanceof ClientNotSupportedException){
            errorMap.put("errorMessage", "Client not supported: API id old");
        }
        if (smartIdException instanceof ServerMaintenanceException){
            errorMap.put("errorMessage", "Sorry! Server under maintenance!");
        }*/
        return errorMap;
    }


}
