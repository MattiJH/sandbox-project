# Build stage
FROM node:18 as build
WORKDIR /app

# Copy package files first for better caching
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application
COPY . .

# Build the application
RUN npm run build

# Run stage
FROM node:18-slim
WORKDIR /app

# Copy package files and server
COPY --from=build /app/package.json ./
COPY --from=build /app/server.js ./
COPY --from=build /app/dist ./dist

# Install only production dependencies
RUN npm install --omit=dev

EXPOSE 80
CMD ["node", "server.js"] 