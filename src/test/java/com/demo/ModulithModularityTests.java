package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModulithModularityTests {
    static ApplicationModules modules = ApplicationModules.of(ModulithDemoApplication.class);

    @Test
    void verifyModules() {
        modules.verify();
    }
}