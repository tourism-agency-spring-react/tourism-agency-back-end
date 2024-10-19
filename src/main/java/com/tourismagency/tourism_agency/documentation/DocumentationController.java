package com.tourismagency.tourism_agency.documentation;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Hidden
@Controller
@RequestMapping("/documentation")
public class DocumentationController {

    @GetMapping
    @ResponseBody
    public void redirectToDocumentation(HttpServletResponse response){
        try{
            response.sendRedirect("/v3/api-docs/swagger-ui.html");

        }catch (IOException e){
            StringBuilder sb = new StringBuilder("UNEXPECTED ERROR");
            if(e.getMessage() != null){
                sb.append(": ").append(e.getMessage());
            }
        }
    }
}
