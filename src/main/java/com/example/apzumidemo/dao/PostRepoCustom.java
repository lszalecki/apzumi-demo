/********************************************
 *
 * Copyright (c) 2003-2021 XML-INTL Ltd.
 *
 * All Rights Reserved
 *
 ********************************************/
package com.example.apzumidemo.dao;

import com.example.apzumidemo.dao.entity.Post;

/**
 * .
 *
 * @author $Author: lszalecki $
 * @version $Revision: 54867 $, $Date: 2017-06-30 12:20:07 +0200 (Śr, 05 sie 2015) $
 */
public interface PostRepoCustom
{
    Iterable<Post> findByUserId(int userId);
}
