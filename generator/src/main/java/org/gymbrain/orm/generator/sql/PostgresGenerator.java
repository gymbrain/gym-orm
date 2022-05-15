package org.gymbrain.orm.generator.sql;

public class PostgresGenerator {
    public String getQuery(QueryModel queryModel){
        return queryModel.toQuery();
    }
}
