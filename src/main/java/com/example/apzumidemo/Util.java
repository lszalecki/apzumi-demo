/********************************************
 *
 * Copyright (c) 2003-2021 XML-INTL Ltd.
 *
 * All Rights Reserved
 *
 ********************************************/
package com.example.apzumidemo;

import com.example.apzumidemo.dao.entity.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200 (Śr, 05 sie 2015) $
 */

public class Util
{
    public static List<Post> getPostListFromUrl()
    {
        List<Post> list = new ArrayList<>();
        String sURL = "https://jsonplaceholder.typicode.com/posts";

        try
        {
            URL url = new URL(sURL);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            Gson gson = new Gson();
            list = gson.fromJson(reader, new TypeToken<List<Post>>(){}.getType());
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return list;
    }
}
