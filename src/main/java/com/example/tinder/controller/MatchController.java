package com.example.tinder.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin
public class MatchController {
}
