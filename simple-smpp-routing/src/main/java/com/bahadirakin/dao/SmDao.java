package com.bahadirakin.dao;

import com.bahadirakin.entities.ShortMessage;

/**
 * Created by bhdrkn on 14/11/14.
 */
public interface SmDao {

    public void persist(final ShortMessage shortMessage);
}
