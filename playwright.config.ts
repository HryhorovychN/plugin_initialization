import { defineConfig } from '@playwright/test';

export default defineConfig({
    testDir: './src/tests',
    timeout: 50000,
    use: {
        browserName: 'chromium',
        headless: true,
    },
});
