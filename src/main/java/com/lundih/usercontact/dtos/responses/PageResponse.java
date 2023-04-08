package com.lundih.usercontact.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Generic response for items to be returned as a page
 *
 * @author lundih
 * @since 0.0.1
 *
 * @param <T> Type of items to be returned
 */
public class PageResponse<T> {
    /**
     * List of items received from the repository
     */
    @Getter
    @Setter
    private List<T> items;

    /**
     * Total number of items in the repository. Note that this is not the total number of items in the items variable
     * but the total number of items available in the repository from which the items were retrieved
     */
    @Getter
    @Setter
    private Long totalItems;

    public PageResponse(List<T> items, Long totalItems) {
        this.items = items;
        this.totalItems = totalItems;
    }
}
