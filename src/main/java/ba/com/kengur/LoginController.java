package ba.com.kengur;

import java.security.Principal;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class LoginController implements WebMvcConfigurer {

    @GetMapping("/login")
    public String showLogin(Model model) {
        return "loginView";
    }

    @PostMapping("/login")
    public ModelAndView tryLogin(final Principal principal) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("username", principal.getName());
        return new ModelAndView("home", model);
    }

}
