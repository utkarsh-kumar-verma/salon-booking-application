package com.itsmerishab08.categoryservice.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.itsmerishab08.categoryservice.model.CategoryModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse extends BaseResponse {

    @JsonProperty("category_model")
    private CategoryModel category;

    @JsonProperty("category_list")
    private List<CategoryModel> categoryModelList;
}
