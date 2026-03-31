package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulithArchitectureTests {

    ApplicationModules modules = ApplicationModules.of(ModulithDemoApplication.class);

    /**
     * Prints all detected application modules to standard output.
     * Useful for inspecting which modules Spring Modulith has discovered
     * and their internal structure (named interfaces, dependencies, etc.).
     */
    @Test
    void printModules() {
        modules.forEach(System.out::println);
    }

    /**
     * Verifies that the application module structure adheres to Spring Modulith's
     * architectural rules: no illegal cross-module dependencies, no cycles,
     * and that only exposed API types are referenced from other modules.
     */
    @Test
    void verifyModules() {
        modules.verify();
    }

    /**
     * Generates AsciiDoc documentation and PlantUML diagrams for all modules.
     * Output is written to the target/spring-modulith-docs directory.
     * - writeDocumentation()           → produces all-docs.adoc and per-module .adoc files
     * - writeIndividualModulesAsPlantUml() → produces .puml diagram files
     */
    @Test
    void createModuleDocumentation() {
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }
}
