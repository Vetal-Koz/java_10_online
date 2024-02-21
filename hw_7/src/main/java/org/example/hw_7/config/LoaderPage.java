package org.example.hw_7.config;

public enum LoaderPage {

    LAYOUT("layout", "root-layout.fxml"),
    CARS("cars", "cars-view.fxml"),
    GARAGES("garages", "garages-view.fxml");

    private final String view;
    private final String page;

    LoaderPage(String view, String page) {
        this.view = view;
        this.page = page;
    }

    public String getView() {
        return view;
    }

    public String getPage() {
        return page;
    }
}
