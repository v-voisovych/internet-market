package com.voisovych.internetmarket.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Principal principal, Model model, HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {

            int statusCode = Integer.valueOf(status.toString());

            // 403
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("error403", "For User with name: " + principal.getName());
                model.addAttribute("error403text", ", Error: 403 - Access denied");
                return "errorpage";
            }

            // 404
            else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error404", "Error: 404 - Page Not Found");
                return "errorpage";
            }

            // 405
            else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error405", "Error: 405 - The request method is not allowed");
                return "errorpage";
            }

            // 500
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("error500", "Error: 500 - Internal Server Error ");
                return "errorpage";
            }

        }
        return "redirect:/";
    }

//    @RequestMapping("/error")
//    public String error403(Principal principal, Model model){
//        model.addAttribute("error403", principal.getName());
//        return "403";
//    }


    @Override
    public String getErrorPath() {
        return "error";
    }
}
