package com.blog.model.entity;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import com.blade.kit.DateKit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志记录
 *
 * @author biezhi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("bl_logs")
public class Logs extends ActiveRecord {

    // 项目主键
    private Integer id;

    // 产生的动作
    private String action;

    // 产生的数据
    private String data;

    // 发生人id
    private Integer author_id;

    // 日志产生的ip
    private String ip;

    // 日志创建时间
    private Integer created;

    public Logs(String action, String data, String ip, Integer uid) {
        this.action = action;
        this.data = data;
        this.ip = ip;
        this.author_id = uid;
        this.created = DateKit.nowUnix();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }
}