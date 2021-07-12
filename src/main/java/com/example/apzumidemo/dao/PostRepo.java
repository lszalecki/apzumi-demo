package com.example.apzumidemo.dao;


import com.example.apzumidemo.dao.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200 (Śr, 05 sie 2015) $
 */
@Repository
public interface PostRepo extends CrudRepository<Post, Integer>
{
    Iterable<Post> findByUserId(int userId);
}
