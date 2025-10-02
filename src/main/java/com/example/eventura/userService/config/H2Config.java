//package com.example.eventura.userService.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.Statement;
//
//@Configuration
//public class H2Config {
//
//    @Bean
//    public DataSource dataSource(DataSourceProperties properties) throws Exception {
//        DataSource dataSource = properties.initializeDataSourceBuilder().build();
//
//        // If running on H2, create alias for gen_random_uuid
//        if (dataSource instanceof HikariDataSource hikari) {
//            try (Connection conn = hikari.getConnection(); Statement stmt = conn.createStatement()) {
//                stmt.execute("CREATE ALIAS IF NOT EXISTS gen_random_uuid FOR \"java.util.UUID.randomUUID\"");
//            }
//        }
//
//        return dataSource;
//    }
//}
