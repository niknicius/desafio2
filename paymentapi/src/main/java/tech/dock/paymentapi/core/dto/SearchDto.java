package tech.dock.paymentapi.core.dto;

import lombok.Data;

@Data
public class SearchDto {
    private SortDto sort = new SortDto();
    private String search = "";
    private Integer pageSize = 10;
    private Integer currentPage = 0;
}
