package com.rocky.user_client.admin;

import com.rocky.user_client.oauth.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminDashboard {

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/callback")
    public ModelAndView callback() {
        return new ModelAndView("forward:/dashboard");
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mv = new ModelAndView("dashboard");
        String endpoint = "http://localhost:8081/api/users";
        try {
            UserProfile[] users = restTemplate.getForObject(endpoint, UserProfile[].class);
            mv.addObject("users", users);
        } catch (RestClientException e) {
            throw new RuntimeException("it's not possible to retrieve all users.");
        }
        return mv;
    }
}
