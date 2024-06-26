package com.xantrix.webapp.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xantrix.webapp.exception.BindingException;
import com.xantrix.webapp.model.Utenti;
import com.xantrix.webapp.service.UtentiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/utenti")
public class UtentiController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UtentiService utentiService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ResourceBundleMessageSource errMessage;

    @GetMapping(value = "/cerca/userId/{userId}")
    public Utenti getUtente(@PathVariable("userId") String UserId){
        LOG.info("Otteniamo l'utente " + UserId);

        Utenti retVal = utentiService.SelUser(UserId);
        return retVal;
    }

    @GetMapping(value="/cerca/tutti")
    public List<Utenti> getAllUser(){

        LOG.info("otteniamop tutti gli utenti");

        return utentiService.SelTutti();
    }

    @PostMapping(value = "/inserisci")
    public ResponseEntity<?> addNewUser(@Validated @RequestBody Utenti utente,
                                        BindingResult bindingResult) throws Exception{
        LOG.info("inserimento nuovo utente");
        if (bindingResult.hasErrors()) {
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());

            LOG.warn(MsgErr);

            throw new BindingException(MsgErr);
        }

        String encodedPassword = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(encodedPassword);
        utentiService.Save(utente);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message","inserimento utente" + utente.getUserId()+ "eseguita con successo");
        return new ResponseEntity<>(responseNode, headers, HttpStatus.CREATED);


    }
}
