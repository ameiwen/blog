package com.blog.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BackResponse implements Serializable {

    private String attach_path;
    private String theme_path;
    private String sql_path;

}
