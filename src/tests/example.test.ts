import { test, expect } from '@playwright/test';

test('basic test', async ({ page }) => {
    await page.context().setHTTPCredentials({
        username: 'hryhorovych',
        password: 'secret'
    });
    await page.goto('https://stage-plugins-third-party.stripo.email/');
    await expect(page.locator(".esdev-blocks")).toBeVisible({timeout: 10000});
});
