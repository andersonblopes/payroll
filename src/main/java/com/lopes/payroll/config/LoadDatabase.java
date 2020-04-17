package com.lopes.payroll.config;

import com.lopes.payroll.model.Employee;
import com.lopes.payroll.model.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Load database.
 */
@Configuration
@Slf4j
class LoadDatabase {

    /**
     * Init database command line runner.
     *
     * @param repository the repository
     * @return the command line runner
     */
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));
        };
    }
}
