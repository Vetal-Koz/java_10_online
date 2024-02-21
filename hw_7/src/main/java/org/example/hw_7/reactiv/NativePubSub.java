package org.example.hw_7.reactiv;

import org.example.hw_7.config.LoaderPage;

import java.util.function.Consumer;

public class NativePubSub {
    private final static NativePubSub instance = new NativePubSub();

    private Consumer<Boolean> publisherGarage;
    private Consumer<Boolean> publisherCar;
    private NativePubSub() {};

    public static NativePubSub getInstance() {return instance;}

    private Consumer<LoaderPage> publisher;

    public void publishGarage(Boolean publishGarage) {
        publisherGarage.accept(publishGarage);
    }

    public void publishCar(Boolean publishCar) {
        publisherCar.accept(publishCar);
    }

    public void publish(LoaderPage page){
        publisher.accept(page);
    }

    public void subscribe(Consumer<LoaderPage> consumer){
        this.publisher = consumer;
    }
    public void subscribeGarage(Consumer<Boolean> consumer) {
        this.publisherGarage = consumer;
    }

    public void subscribeCar(Consumer<Boolean> consumer) {
        this.publisherCar = consumer;
    }
}
