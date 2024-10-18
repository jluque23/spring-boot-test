package com.globalsoftwaresupport.spring.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "cardlist", layout = MainView.class)
@PageTitle("cardlist")
public class CardListView extends HorizontalLayout {
}
