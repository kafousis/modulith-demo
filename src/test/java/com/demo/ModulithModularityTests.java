package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulithModularityTests {
    static ApplicationModules modules = ApplicationModules.of(ModulithDemoApplication.class);

    @Test
    void printModules() {
        modules.forEach(System.out::println);
    }

    @Test
    void verifyModules() {
        modules.verify();
    }

    @Test
    void createModuleDocumentation() {
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }
}