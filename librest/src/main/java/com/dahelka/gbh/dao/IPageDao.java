
package com.dahelka.gbh.dao;

import com.dahelka.gbh.entity.Page;
import java.util.List;

public interface IPageDao {

    public List<Page> findAllPages();

    public Page findPageById(Page page);

}