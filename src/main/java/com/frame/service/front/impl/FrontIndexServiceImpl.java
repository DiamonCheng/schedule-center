package com.frame.service.front.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.IndexShowEntity;
import com.frame.service.front.FrontIndexService;

import javax.annotation.Resource;

/**
 * @author wowson
 * @version create time：2017年3月30日 下午6:42:05
 * 类说明 ：
 */
@Service
public class FrontIndexServiceImpl implements FrontIndexService {
    @Resource
    private GeneralDao dao;

    public IndexShowEntity get() {
        String hql =" from IndexShowEntity where status = 1 ";
        List<IndexShowEntity> list = (List<IndexShowEntity>) dao.getHibernateTemplate().find(hql);
        if (list.size() > 0)
            return list.get(0);
        return  null;
    }

}
