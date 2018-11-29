package com.example.demo.controllers;

import com.example.demo.entities.Picture;
import com.example.demo.entities.User;
import com.example.demo.repository.PictureRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.storage.StorageFileNotFoundException;
import com.example.demo.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping("/mypage")
    public String listUploadedFiles(Model model, Principal principal) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));


        User user = userService.getUser(principal);

        System.out.println(pictureRepository.findPicture(user.getEmail()));

        if (user != null) {
            model.addAttribute("pictureName", pictureRepository.findPicture(user.getEmail()));
        }
        else {
            model.addAttribute("pictureName", null);
        }

        return "mypage";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/mypage")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, Principal principal) {

        storageService.store(file);
        User user = userService.getUser(principal);
        Picture picture = new Picture(file.getOriginalFilename());
        Map<String, Object> info = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();
        String uid = (String) info.get("id");
        picture.setUid(uid);

        pictureRepository.save(picture);

        if (user != null) {
            user.setPictureName(picture);
        }

        return "redirect:/mypage";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}