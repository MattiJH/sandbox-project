import { defineConfig, devices } from '@playwright/test';

export default defineConfig({
  testDir: './tests',
  timeout: 30000,  // 30 seconds max per test
  expect: {
    timeout: 5000,  // 5 seconds max for assertions
  },
  // Enable parallel execution
  fullyParallel: true,
  workers: undefined,   // Use default number of workers
  forbidOnly: false,
  retries: 1,
  reporter: 'html',
  use: {
    baseURL: 'http://localhost:80',
    trace: 'on-first-retry',
    // Add screenshot on failure
    screenshot: 'only-on-failure',
    // Add video recording
    video: 'retain-on-failure',
  },
  projects: [
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] },
    },
    {
      name: 'firefox',
      use: { ...devices['Desktop Firefox'] },
    },
    {
      name: 'webkit',
      use: { ...devices['Desktop Safari'] },
    },
  ],
  webServer: {
    command: 'docker-compose up --build',
    url: 'http://localhost:80',
    reuseExistingServer: true,
    timeout: 60000,  // 60 seconds for server startup
    stdout: 'pipe',
    stderr: 'pipe',
  },
}); 