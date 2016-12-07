package com.dempe.forest;

import com.dempe.forest.client.ChannelPool;
import com.dempe.forest.codec.Message;
import com.dempe.forest.codec.Response;
import com.dempe.forest.core.exception.ForestFrameworkException;
import com.dempe.forest.transport.NettyClient;
import com.dempe.forest.transport.NettyResponseFuture;

/**
 * Created by Dempe on 2016/12/7.
 */
public class Referer<T> {

    private ChannelPool channelPool;

    public Referer(ClientConfig clientConfig) throws InterruptedException {
        channelPool = new ChannelPool(new NettyClient(clientConfig));

    }

    public boolean isAvailable() {
        return true;
    }

    public Object call(Message message) throws Exception {
        if (!isAvailable()) {
            throw new ForestFrameworkException("client is not available now");
        }
        NettyResponseFuture<Response> responseFuture = channelPool.write(message, Constants.DEFAULT_TIMEOUT);
        return  responseFuture.getPromise().await().getResult();

    }
}
