package com.bahadirakin;

public class DatabaseMonitor {

    private Integer id;
    private String name;
    private String sqlQuery;

    public DatabaseMonitor() {
    }

    public DatabaseMonitor(String name, String sqlQuery) {
        this.name = name;
        this.sqlQuery = sqlQuery;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }
}
