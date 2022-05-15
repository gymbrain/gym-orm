package org.gymbrain.orm.generator.utils;

public class Constants {
    public final static String TABLE_NAME_KEY = "{{table_name}}";
    public final static String INSERT_COLUMN_NAMES_KEY = "{{insert_column_name}}";
    public final static String INSERT_COLUMN_VALUES_KEY = "{{insert_column_value}}";
    public final static String INSERT_QUERY_TEMPLATE = "INSERT into " + TABLE_NAME_KEY + "\n(" + INSERT_COLUMN_NAMES_KEY + ")\n" +
            "VALUES \n" +
            "(" + INSERT_COLUMN_VALUES_KEY + ")"
            + ";";
}
