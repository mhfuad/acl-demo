package com.fuad.aclDemo.controller;

import com.fuad.aclDemo.dto.request.PostRequest;
import com.fuad.aclDemo.entity.Post;
import com.fuad.aclDemo.entity.User;
import com.fuad.aclDemo.repository.PostRepository;
import com.fuad.aclDemo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    PostRepository repository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostRequest postRequest) throws IOException {
        String base64Image = "your_base64_encoded_image_string";

        // Decode the base64 string
        byte[] decodedImage = Base64.getDecoder().decode(base64Image);

        // Create an image from the byte array
        ByteArrayInputStream bis = new ByteArrayInputStream(decodedImage);
        BufferedImage image = ImageIO.read(bis);
        bis.close();

        // Write the image to a file
        File outputfile = new File("output.jpg");
        ImageIO.write(image, "jpg", outputfile);

        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setBody(postRequest.getBody());
        post.setThumbnail(postRequest.getThumbnail());
        User user = userRepository.findById(postRequest.getUser_id()).orElseThrow(()-> new EntityNotFoundException("User not found"));
        post.setUser(user);
        repository.save(post);

        return ResponseEntity.ok("Post save sccess");
    }
}
