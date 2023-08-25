//package me.shinsunyoung.controller;
//
//import com.google.cloud.dialogflow.cx.v3.*;
//import me.shinsunyoung.Service.DialogflowService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/chatbot")
//public class DialogflowController {
//
//    private final DialogflowService dialogflowService;
//
//    public DialogflowController(DialogflowService dialogflowService) {
//        this.dialogflowService = dialogflowService;
//    }
//
//    @PostMapping("/detectIntent")
//    public String detectIntent(@RequestBody String userQuery) {
//        return dialogflowService.detectIntent(userQuery);
//    }
//}