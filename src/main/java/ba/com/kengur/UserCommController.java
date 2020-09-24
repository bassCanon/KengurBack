package ba.com.kengur;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ba.com.kengur.article.ArticleUploadRequest;
import ba.com.kengur.image.Image;
import ba.com.kengur.image.ImageController;
import ba.com.kengur.image.StorageService;

@Controller
public class UserCommController {

    private StorageService storageService;

    @Autowired
    private ImageController imageController;

    @Autowired
    public UserCommController(StorageService storageService) {
        this.storageService = storageService;
    }

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
        model.addAttribute("article", new ArticleUploadRequest());
        return "/create-article";
    }

    @GetMapping("/upload")
    public String upload() {
        return "/upload";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) throws IOException {
        for (MultipartFile file : files) {
            storageService.store(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
            Image image = new Image();
            image.setArticleId(2L);
            image.setImageData(Base64.getEncoder().encodeToString(file.getBytes()));
            image.setTitle(file.getOriginalFilename());
            imageController.createNewImage(image);
        }
        return "redirect:/";
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
