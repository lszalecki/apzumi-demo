/********************************************
 *
 * Copyright (c) 2003-2021 XML-INTL Ltd.
 *
 * All Rights Reserved
 *
 ********************************************/
package com.example.apzumidemo.api;

import com.example.apzumidemo.service.PostsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PathVariable;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200 (Śr, 05 sie 2015) $
 */

@SpringBootTest
@AutoConfigureMockMvc
public class PostApiTest
{
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PostsService postService;


    @Test
    public void findAllTest() throws Exception
    {
        mvc.perform(get("/api/posts/all")).andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void getAll() throws Exception
    {
        mvc.perform(get("/api/posts/REST")).andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    public void getById() throws Exception
    {
        mvc.perform(get("/api/posts/id/1").param("id", "1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

    @Test
    public void getByUserid() throws Exception
    {
        mvc.perform(get("/api/posts/userId/2").param("userId", "2")).andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }


}
