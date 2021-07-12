package com.example.apzumidemo.service;

import com.example.apzumidemo.Util;
import com.example.apzumidemo.dao.PostRepo;
import com.example.apzumidemo.dao.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200 (Śr, 05 sie 2015) $
 */
@Service
public class PostsService
{
    @Autowired
    private PostRepo postRepo;

    @Autowired
    public PostsService(PostRepo postRepo)
    {
        this.postRepo = postRepo;
    }


    public Iterable<Post> getAll()
    {
        List<Post> postListFromUrl = Util.getPostListFromUrl();
        fillDB(postListFromUrl);

        return  postRepo.findAll();
    }


    public Iterable<Post> findAll()
    {
        return  postRepo.findAll();
    }

    public Optional<Post> findById(Integer id)
    {
        return postRepo.findById(id);
    }

    public Iterable<Post> findByUserId(int userId)
    {
        return postRepo.findByUserId(userId);
    }

    public Post save(Post post)
    {
        return postRepo.save(post);
    }

    public void addPost()
    {

    }

    public void deleteById(int id)
    {
        postRepo.deleteById(id);
    }

    public void fillDB(List<Post> postList)
    {
        postList.forEach(post -> save(post));
    }
}
