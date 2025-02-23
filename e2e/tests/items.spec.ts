import { test, expect } from '@playwright/test';

test.describe('Items Management', () => {
  const getUniqueId = () => new Date().getTime();

  test('should create new item', async ({ page }) => {
    const uniqueId = getUniqueId();
    const testItem = {
      name: `Test Item ${uniqueId}`,
      description: `Test Description ${uniqueId}`
    };

    // Start at items page and wait for it to be ready
    await page.goto('/items');
    await expect(page.locator('#add-item-btn')).toBeVisible();
    
    // Click the "Add New Item" button
    await page.locator('#add-item-btn').click();
    await expect(page.locator('#create-item-title')).toBeVisible();

    // Fill in the new item form
    await page.locator('#item-name').fill(testItem.name);
    await page.locator('#item-description').fill(testItem.description);

    // Submit the form and wait for response
    await page.locator('#submit-item').click();
    await page.waitForResponse(response => 
      response.url().includes('/api/items') && response.status() === 200
    );

    // Verify the new item appears in the list
    await expect(page.getByRole('heading', { name: testItem.name })).toBeVisible();
    await expect(page.getByText(testItem.description)).toBeVisible();
  });

  test.describe('with created item', () => {
    let testItem: { name: string; description: string };

    test.beforeEach(async ({ page }) => {
      // Create unique test item for this test
      const uniqueId = getUniqueId();
      testItem = {
        name: `Test Item ${uniqueId}`,
        description: `Test Description ${uniqueId}`
      };

      // Create a new item for this test
      await page.goto('/items');
      await expect(page.locator('#add-item-btn')).toBeVisible();
      await page.locator('#add-item-btn').click();
      await expect(page.locator('#create-item-title')).toBeVisible();
      await page.locator('#item-name').fill(testItem.name);
      await page.locator('#item-description').fill(testItem.description);

      // Submit and wait for response
      await page.locator('#submit-item').click();
      await page.waitForResponse(response => 
        response.url().includes('/api/items') && response.status() === 200
      );
      
      // Wait for the item to appear
      await expect(page.getByRole('heading', { name: testItem.name })).toBeVisible();
    });

    test('should display items list', async ({ page }) => {
      await expect(page.getByRole('heading', { name: testItem.name })).toBeVisible();
      await expect(page.getByText(testItem.description)).toBeVisible();
    });

    test('should view item details', async ({ page }) => {
      // Find and click view button next to our item
      const itemCard = page.locator('.card', { has: page.getByRole('heading', { name: testItem.name }) });
      await itemCard.getByRole('link', { name: 'View' }).click();

      // Wait for details page
      await expect(page.locator('#item-details-title')).toBeVisible();
      await expect(page.locator('#detail-title')).toHaveText(testItem.name);
      await expect(page.locator('#detail-description')).toHaveText(testItem.description);
      
      // Navigate back and verify
      await page.locator('#back-to-list').click();
      await expect(page.getByRole('heading', { name: testItem.name })).toBeVisible();
    });

    test('should delete an item', async ({ page }) => {
      // Find and delete our test item
      const itemCard = page.locator('.card', { has: page.getByRole('heading', { name: testItem.name }) });
      await itemCard.getByRole('button', { name: 'Delete' }).click();

      // Verify the item is gone
      await expect(page.getByRole('heading', { name: testItem.name })).not.toBeVisible();
      await expect(page.getByText(testItem.description)).not.toBeVisible();
    });
  });
}); 