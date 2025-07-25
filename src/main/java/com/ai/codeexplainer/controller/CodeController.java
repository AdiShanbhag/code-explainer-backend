package com.ai.codeexplainer.controller;

import com.ai.codeexplainer.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin // Allow frontend calls (optional, useful if React or web client is used)
public class CodeController {

    @Autowired
    private OpenAiService openAiService;

    @GetMapping("/")
    public String hello() {
        return "Code Explainer API is live!";
    }

    @PostMapping("/explain")
    public String explainCode(@RequestBody CodeRequest codeRequest) {
        return openAiService.explainCode(codeRequest.getCode());
    }

    public static class CodeRequest {
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
