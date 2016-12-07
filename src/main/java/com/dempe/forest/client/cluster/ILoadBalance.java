package com.dempe.forest.client.cluster;

import com.dempe.forest.Referer;

import java.util.List;

/**
 * Created by Dempe on 2016/12/6.
 */
public interface ILoadBalance<T> {

    Referer<T> select();

    void selectToHolder(List<Referer<T>> refersHolder);

    Referer<T> selectByKey();

    void onFresh(List<Referer<T>> refererList);


}
