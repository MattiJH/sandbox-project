const express = require('express');
const path = require('path');
const app = express();

// Serve static files from dist directory
app.use(express.static(path.join(__dirname, 'dist')));

// Send all requests to index.html
app.get('/*', function(req, res) {
  res.sendFile(path.join(__dirname, 'dist/index.html'));
});

// Start the server
const port = process.env.PORT || 80;
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
}); 