package com.blog.model.entity;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import lombok.Data;

/**
 * 配置选项
 *
 * @author biezhi
 */
@Data
@Table(value = "bl_options", pk = "name")
public class Options extends ActiveRecord {

    // 配置名称
    private String name;

    // 配置值
    private String value;

    // 配置描述
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}