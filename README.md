# Real-Time Local LLM Chatbot with Ollama and Spring Boot

This project is a fully functional, real-time chatbot application that runs a Large Language Model (LLM) entirely on your local machine using **Ollama**. The backend is built with **Java and Spring Boot**, communicating with the front end over **WebSockets** for an instant, interactive chat experience.

The primary goal of this project is to create a powerful, private, and cost-effective AI chatbot that does not rely on external, cloud-based APIs.


### ✨ Key Features

- **Real-Time Communication:** Instant, bi-directional messaging between the user and the AI using Spring WebSockets.
- **Powered by Local LLMs:** Leverages **Ollama** to run powerful open-source models like Llama 3, Mistral, and more, directly on your hardware.
- **100% Private & Offline:** Since everything runs locally, no data ever leaves your machine. It works perfectly without an internet connection.
- **Scalable Backend:** Built on the robust and efficient Spring Boot framework, capable of managing multiple concurrent WebSocket connections.

---

### ⚙️ Architecture & How It Works

The application follows a straightforward, event-driven architecture:

1.  A user connects to the server from the simple HTML front end, establishing a WebSocket connection.
2.  The user sends a message through the WebSocket.
3.  The Spring Boot backend receives the message and forwards it as a prompt to the locally running Ollama model.
4.  Ollama streams the AI's response back to the Spring application.
5.  The Spring backend immediately streams that response back to the front end through the WebSocket, displaying the answer in real-time.

---

### 🛠️ Technology Stack

- **Backend:** **Java**, **Spring Boot**, **Spring WebSocket**
- **AI Integration:** **Ollama**
- **Build Tool:** **Maven** / **Gradle**
- **Frontend:** Plain **HTML**, **JavaScript**, and **CSS** for the user interface.

---

### 🚀 Getting Started

To run this project, you first need to set up Ollama and then run the Spring Boot application.

**1. Install and Run Ollama:**
First, follow the official instructions to [install Ollama](https://ollama.com/) on your system.

Once installed, pull a model to run locally. We recommend Llama 3, the latest powerful open-source model.

```bash
ollama pull llama3
```

**2. Clone the Repository:**
```bash
git clone [https://github.com/sidhu1512/ollama-chatbot.git](https://github.com/sidhu1512/ollama-chatbot.git)
cd ollama-chatbot
```

**3. Configure the Model in the Application:**
By default, the application is configured to use the `llama3` model. If you want to use a different model, open `src/main/resources/application.properties` and change the following property:

```properties
ollama.model=llama3
```

**4. Build and Run the Spring Boot Application:**
```bash
./mvnw spring-boot:run
```
The backend server will start on `http://localhost:8080`.

**5. Open the Chat Interface:**
Open your web browser and navigate to the `index.html` file located in the `src/main/resources/static` directory, or simply go to:
[http://localhost:8080](http://localhost:8080)

You can now start chatting with your local AI!
