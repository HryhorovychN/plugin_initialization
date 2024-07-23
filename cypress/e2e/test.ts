describe('Basic Test', () => {
    // Очищаем кеш, localStorage и cookies перед каждым тестом
    beforeEach(() => {
        // Очистка cookies и localStorage
        cy.clearCookies();
        cy.clearLocalStorage();

        // Очистка кеша
        cy.window().then((win) => {
            return win.caches.keys().then((keyList) => {
                return Promise.all(keyList.map((key) => {
                    return win.caches.delete(key);
                }));
            });
        });

        // Жесткая перезагрузка страницы
        cy.window().then((win) => {
            win.location.reload();
        });

        // Дополнительное ожидание после перезагрузки
        cy.wait(5000); // Увеличьте время ожидания, если необходимо
    });

    it('should be visible', () => {
        cy.visit('https://stage-plugins-third-party.stripo.email/', {
            auth: {
                username: 'monitoring',
                password: 'lksh8UHHKns'
            }
        });

        cy.wait(5000); // Увеличьте время ожидания, если необходимо

        cy.get(".backToConfigButton", { timeout: 5000 }).click();
        cy.wait(5000); // Увеличьте время ожидания, если необходимо

        cy.get("#submit", { timeout: 5000 }).click();
        cy.wait(5000); // Увеличьте время ожидания, если необходимо

        cy.get('.stripo-preview-frame').then(($iframe) => {
            const $body = $iframe.contents().find('body');
            cy.wrap($body).find('.esd-stripe', { timeout: 15000 }).should('be.visible');
        });
    });
});
