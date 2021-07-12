package com.example.apzumidemo.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200 (Śr, 05 sie 2015) $
 */

@Entity
public class Post
{

    @Getter
    @Setter
    @JsonIgnore
    private int userId;

    @Getter
    @Setter
    @Id
    private int id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String body;

    @Getter
    @Setter
    @JsonIgnore
    private boolean editedByUser;

    public Post(){}

    public Post(int userId, int id, String title, String body)
    {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.editedByUser=false;
    }
}
