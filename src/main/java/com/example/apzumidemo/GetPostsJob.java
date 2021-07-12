package com.example.apzumidemo;

import com.example.apzumidemo.dao.entity.Post;
import com.example.apzumidemo.service.PostsService;
import lombok.Setter;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200 (Śr, 05 sie 2015) $
 */
@Component
public class GetPostsJob extends QuartzJobBean
{

    @Autowired
    @Setter
    private PostsService manager;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        List<Post> postListFromUrl = Util.getPostListFromUrl();
        manager.fillDB(postListFromUrl);
    }
}
