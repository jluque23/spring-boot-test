package com.globalsoftwaresupport.spring.views.users;

import com.globalsoftwaresupport.spring.dto.response.UsersResponse;
import com.globalsoftwaresupport.spring.services.interfaces.IRegistrationService;
import com.globalsoftwaresupport.spring.views.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "userRegistrationView", layout = MainView.class)
@RouteAlias(value = "registrationView", layout = MainView.class)
@PageTitle("Registration View")
public class UsersRegistrationView extends HorizontalLayout {

    private final IRegistrationService registrationService;

    private Grid<UsersResponse> usersGrid;

    @Autowired
    public UsersRegistrationView(IRegistrationService registrationService) {
        this.registrationService = registrationService;

        RegistrationForm registrationForm = new RegistrationForm(null);

        Button createAccountButton = new Button("Create Account");
        createAccountButton.addClickListener(e -> {

        });

        add(new VerticalLayout(registrationForm,createAccountButton));
    }




}
