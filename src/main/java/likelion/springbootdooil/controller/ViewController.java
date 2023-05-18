package likelion.springbootdooil.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller

public class ViewController {

    @GetMapping("story")
    public String story(@RequestParam("name")String name, Model model){
        model.addAttribute("name",name);
        return "story";

    }
    @GetMapping("home")
    public String home(){
        return "home";

    }


}
