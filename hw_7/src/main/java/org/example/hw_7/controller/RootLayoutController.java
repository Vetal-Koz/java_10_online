package org.example.hw_7.controller;

import org.example.hw_7.config.LoaderPage;
import org.example.hw_7.reactiv.NativePubSub;

public class RootLayoutController {

    public void showCars() {
        NativePubSub.getInstance().publish(LoaderPage.CARS);
    }

    public void showGarages() {
        NativePubSub.getInstance().publish(LoaderPage.GARAGES);
    }
}