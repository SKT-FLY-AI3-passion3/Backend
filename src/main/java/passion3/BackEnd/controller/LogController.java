package passion3.BackEnd.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import passion3.BackEnd.Service.LogService;

@Controller
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/logs")
    public String viewLogs(Model model) {
        model.addAttribute("logs", logService.getLogs());
        return "logs";
    }
}


