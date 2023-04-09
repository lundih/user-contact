package com.lundih.usercontact.dtos.responses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PageResponseTest {
    private final List<String> items = List.of("a", "b");
    private final Long totalItems = (items.size() + 10L);

    private final PageResponse<String> response = new PageResponse<>(items, totalItems);

    @Test
    void setItems_should_set_items() {
        response.setItems(items);
        Assertions.assertEquals(items, response.getItems());
    }

    @Test
    void setTotalItems_should_set_totalItems() {
        response.setTotalItems(totalItems);
        Assertions.assertEquals(totalItems, response.getTotalItems());
    }
}
