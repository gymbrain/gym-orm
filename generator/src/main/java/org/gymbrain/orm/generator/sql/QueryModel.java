package org.gymbrain.orm.generator.sql;

import org.gymbrain.orm.generator.utils.Constants;
import org.gymbrain.orm.generator.utils.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class QueryModel {
    private QueryType type;
    private String tableName;
    private List<String> selectColumns = new ArrayList<>();
    private List<String> insertColumns = new ArrayList<>();
    private String insertValues;
    private List<KeyValue> update = new ArrayList<>();
    private List<KeyValue> wheres = new ArrayList<>();

    public QueryType getType() {
        return type;
    }

    public void setType(QueryType type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getSelectColumns() {
        return selectColumns;
    }

    public void setSelectColumns(List<String> selectColumns) {
        this.selectColumns = selectColumns;
    }

    public List<String> getInsertColumns() {
        return insertColumns;
    }

    public void setInsertColumns(List<String> insertColumns) {
        this.insertColumns = insertColumns;
    }


    public List<KeyValue> getUpdate() {
        return update;
    }

    public void setUpdate(List<KeyValue> update) {
        this.update = update;
    }

    public List<KeyValue> getWheres() {
        return wheres;
    }

    public void setWheres(List<KeyValue> wheres) {
        this.wheres = wheres;
    }

    public String getInsertValues() {
        return insertValues;
    }

    public void setInsertValues(String insertValues) {
        this.insertValues = insertValues;
    }

    public String toQuery(){
        switch (type){
            case INSERT:{
                String query = Constants.INSERT_QUERY_TEMPLATE;
                query = query.replace(Constants.TABLE_NAME_KEY,tableName);
                query = query.replace(Constants.INSERT_COLUMN_NAMES_KEY,String.join(",",insertColumns));
                query = query.replace(Constants.INSERT_COLUMN_VALUES_KEY,insertValues);
                System.out.println(query);
            }
        }

        return "";
    }
}
