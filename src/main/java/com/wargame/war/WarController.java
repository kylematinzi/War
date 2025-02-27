package com.wargame.war;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WarController {

    @RequestMapping("/")
    public String war() {
        return "war"; // must match war.html in templates. This is what's being called on
    }
}
