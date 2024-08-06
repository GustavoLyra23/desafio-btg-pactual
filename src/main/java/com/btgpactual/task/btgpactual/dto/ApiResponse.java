package com.btgpactual.task.btgpactual.dto;

import java.util.List;

public record ApiResponse<T>(List<T> data, PaginationResponse paginationResponse) {
}
