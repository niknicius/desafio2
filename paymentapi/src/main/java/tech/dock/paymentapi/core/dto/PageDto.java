package tech.dock.paymentapi.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto {
    private List<?> items;
    private Integer pageSize;
    private Integer currentPage;
    private Long totalRecords;
    private Integer totalPages;

}
