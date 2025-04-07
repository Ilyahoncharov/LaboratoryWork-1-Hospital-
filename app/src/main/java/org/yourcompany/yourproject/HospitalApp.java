package org.yourcompany.yourproject;

import org.yourcompany.yourproject.models.Hospital;

public class HospitalApp {
    public static void main(String[] args) {
        Hospital model = new Hospital();
        HospitalView view = new HospitalView();
        HospitalPresenter presenter = new HospitalPresenter(model, view);

        presenter.start();
    }
}
