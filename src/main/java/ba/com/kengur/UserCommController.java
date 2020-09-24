package ba.com.kengur;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ba.com.kengur.article.Article;

@Controller
public class UserCommController {

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/create-article")
    public String createArticle(Model model) {
        model.addAttribute("article", new Article());
        return "/create-article";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @GetMapping("/principal")
    public String retrievePrincipal(Principal principal) {
        return "home";
    }

    // @GetMapping("/home")
    // public ModelAndView tryLogin(final Principal principal) {
    // HashMap<String, Object> model = new HashMap<>();
    // model.put("username", principal.getName());
    // return new ModelAndView("home", model);
    // }

}
