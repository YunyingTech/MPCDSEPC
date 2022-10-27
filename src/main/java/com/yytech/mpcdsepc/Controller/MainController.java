package com.yytech.mpcdsepc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class MainController {

    /*根目录跳转至微服务mpcdsepc
    * */
    @RequestMapping(value = "")
    public RedirectView redirectToApp(RedirectAttributes attributes){
        attributes.addFlashAttribute("flashAttribute","redirectToApp");
        attributes.addAttribute("redirect", URLEncoder.encode("/", StandardCharsets.UTF_8));
        return new RedirectView("/mpcdsepc");
    }
}
