import { defineConfig } from '@playwright/test';

export default defineConfig({
    testDir: './src/tests',
    timeout: 55000,
    use: {
        browserName: 'chromium',
        headless: false,
    },
});
