package com.balaji.jobs.configuration;

import com.yammer.dropwizard.config.Configuration;
import org.codehaus.jackson.annotate.JsonProperty;

public class JobRepositoryConfiguration extends Configuration {


    @JsonProperty
    private DbConfig dbConfig;


    public DbConfig getDbConfig() {
        return dbConfig;
    }

}
