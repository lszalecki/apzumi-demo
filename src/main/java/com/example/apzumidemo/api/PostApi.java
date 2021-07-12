package com.example.apzumidemo.api;

import com.example.apzumidemo.dao.entity.Post;
import com.example.apzumidemo.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200 (Śr, 05 sie 2015) $
 */

@RestController
@RequestMapping("/api/posts")
public class PostApi
{
    private PostsService postsService;

    @Autowired
    public PostApi(PostsService postsService)
    {
        this.postsService = postsService;
    }

    @GetMapping("/all")
    public Iterable<Post> findAll()
    {
        return postsService.findAll();
    }

    @GetMapping("/REST")
    public Iterable<Post> getAll()
    {
        return postsService.getAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Post> getById(@PathVariable int id)
    {
        return postsService.findById(id);
    }

    @GetMapping("/userId/{userId}")
    public Iterable<Post> getByUserid(@PathVariable int userId)
    {
        return postsService.findByUserId(userId);
    }

    @PostMapping
    public Post addPost(@RequestBody Post post)
    {
        return postsService.save(post);
    }

    @PutMapping("/{post}")
    public Post updatePost(@RequestBody Post post)
    {
        post.setEditedByUser(true);
        return postsService.save(post);
    }

    @PutMapping
    public Post updatePostById(@RequestParam int id, @RequestBody Post postUpdate)
    {
        Post post = postsService.findById(id).orElseThrow(() -> new NoSuchElementException("Post not found for this id :: " + id));
        post.setUserId(postUpdate.getUserId());
        post.setTitle(postUpdate.getTitle());
        post.setBody(postUpdate.getBody());
        post.setEditedByUser(true);
        return postsService.save(post);
    }


    @PatchMapping("/{title}")
    public Post updatePostTitle(@RequestParam int id, @RequestBody String title)
    {
        Post post = postsService.findById(id).orElseThrow(() -> new NoSuchElementException("Post not found for this id :: " + id));
        post.setTitle(title);
        post.setEditedByUser(true);
        return postsService.save(post);
    }

    @PatchMapping("/{body}")
    public Post updatePostBody(@RequestParam int id, @RequestBody String body)
    {
        Post post = postsService.findById(id).orElseThrow(() -> new NoSuchElementException("Post not found for this id :: " + id));
        post.setBody(body);
        post.setEditedByUser(true);
        return postsService.save(post);
    }

    @DeleteMapping
    public void deletePost(@RequestParam int id)
    {
         postsService.deleteById(id);
    }
}
