package net.cosmogrp.storage.redis.channel;

public interface ChannelListener<T> {

    void listen(Channel<T> channel, String server, T object);

    default void send(Channel<T> channel, T object) {}

}
