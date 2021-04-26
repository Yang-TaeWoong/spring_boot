package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class HelloController {
    //
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }

    //    name을 도메인에서 입력받아서 hello-template라는 뷰를 넘김
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    @PostMapping("uploadFile")
    public String uploadSingle(@RequestParam("files") MultipartFile file) throws Exception {
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String filePath = "/Users/andy/Desktop/study/demo/src/main/resources/files" + "/" + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest); // 파일 업로드 작업 수행
        return "uploaded";
    }

    //    hello + name 그냥 그대로 넘어감
    @GetMapping("hello_spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }
// 이렇게 날리면 hello 객체를 json형태로 날리는데, key를 name으로 value를 name으로 날림
// 객체가 오면, json을 만들어서 반환하는게 기본적인 default 셋팅임.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
