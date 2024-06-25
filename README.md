---

# File Transfer Application

This project is a simple file transfer application that allows users to send and receive files over a network. It consists of a server application that listens for incoming connections and a client application that can send files to the server.

## Features

- **Server Application**:
  - Accepts connections from clients.
  - Receives files from clients and displays them in a GUI.
  - Allows users to preview received files before downloading.

- **Client Application**:
  - Allows users to select a file to send.
  - Sends the selected file to the server.

## Requirements

- Java SE Development Kit (JDK) 8 or higher.
- Apache Maven (optional for dependency management).

## Getting Started

### Prerequisites

Ensure you have the following software installed on your system:

- Java JDK: [Download and install Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- Apache Maven (optional): [Download and install Maven](https://maven.apache.org/download.cgi)

### Running the Server

1. Open a terminal or command prompt.
2. Navigate to the directory containing the `Server.java` file.
3. Compile the server code using the following command:

   ```bash
   javac Server.java MyFile.java
   ```

4. Run the server application:

   ```bash
   java Server
   ```

### Running the Client

1. Open a terminal or command prompt.
2. Navigate to the directory containing the `Client.java` file.
3. Compile the client code using the following command:

   ```bash
   javac Client.java
   ```

4. Run the client application:

   ```bash
   java Client
   ```

## Usage

### Server Application

1. Run the server application. A window titled "Murats server" will appear.
2. The server will start listening for incoming connections on port `1234`.

### Client Application

1. Run the client application. A window titled "Client" will appear.
2. Click the "Choose File" button to select a file to send to the server.
3. Click the "Send File" button to send the selected file to the server.
4. The server will receive the file and display it in its GUI.

## Project Structure

```
.
├── Client.java      # Client application source code
├── Server.java      # Server application source code
└── MyFile.java      # File data structure class
```
## Acknowledgments

- Inspired by Wittcode's file transfer tutorials
