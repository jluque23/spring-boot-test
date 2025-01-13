package com.globalsoftwaresupport.spring.views.users;

import com.globalsoftwaresupport.spring.dto.response.UsersResponse;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Date;

@Route("/registrationForm")
public class RegistrationForm extends FormLayout {

    private TextField usernameField = new TextField("Username");
    private PasswordField passwordField = new PasswordField("Password");
    private TextField emailField = new TextField("Email");
    private TextField firstNameField = new TextField("First Name");
    private TextField lastNameField = new TextField("Last Name");
    private TextField phoneField = new TextField("Phone");
    private TextField addressField = new TextField("Address");
    private DatePicker createdAtField = new DatePicker("Date");
    private Checkbox enabledField = new Checkbox("Enabled");

    private UsersResponse currentUser;

    public RegistrationForm(UsersResponse userResponse) {
        this.currentUser = userResponse;

        VerticalLayout firstLayout = new VerticalLayout();
        VerticalLayout secondLayout = new VerticalLayout();
        firstLayout.add(usernameField, passwordField, emailField, firstNameField);
        secondLayout.add(phoneField,
                addressField, createdAtField, enabledField);
        add(new HorizontalLayout(firstLayout, secondLayout));

        setUser(userResponse);
    }

    public void setUser(UsersResponse userResponse) {
        this.currentUser = userResponse;
    }

    public void cleanUI() {
        usernameField.clear();
        passwordField.clear();
        emailField.clear();
        firstNameField.clear();
        lastNameField.clear();
        phoneField.clear();
        addressField.clear();
        createdAtField.clear();
        enabledField.clear();
    }

    public UsersResponse getCurrentUser() {
        return this.currentUser;
    }
}
