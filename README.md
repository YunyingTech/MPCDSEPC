# MPCDSEPC - AI-Enhanced Epidemic Prevention Document Collaboration System

<p align="center">
  <img src="https://img.shields.io/badge/Spring Boot-2.6.1-brightgreen" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Python-3.9+-blue" alt="Python">
  <img src="https://img.shields.io/badge/LLM-Integrated-orange" alt="LLM">
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License">
</p>

## Overview

MPCDSEPC (Multi-Person Collaborative Document System for Epidemic Prevention and Control) is an enterprise-grade web application designed for city and district-level health authorities to collaboratively manage epidemic investigation documents in real-time.

### Key Features

- **🤖 AI-Powered Document Analysis**: Leverages large language models (LLM) for intelligent document processing and anomaly detection
- **🔄 Real-time Collaboration**: WebSocket-based multi-user editing with live presence indicators
- **📄 Intelligent Report Generation**: Automated Word document generation from structured data using AI-assisted templating
- **📊 Database Management**: Complete CRUD operations with MyBatis, supporting data visualization and export
- **🔔 Real-time Notifications**: WebSocket push notifications for document updates and collaboration events
- **📝 Change History Tracking**: Complete audit trail of all document modifications

## Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Frontend (HTML/JS)                       │
├─────────────────────────────────────────────────────────────┤
│                    Spring Boot Backend                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │  Controllers │  │  Services    │  │   Mappers    │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
├─────────────────────────────────────────────────────────────┤
│              AI Analysis Module (Python)                    │
│  ┌─────────────────────────────────────────────────────┐    │
│  │  LLM-powered Document Intelligence Engine           │    │
│  │  • Semantic Analysis    • Auto-completion           │    │
│  │  • Pattern Detection    • Anomaly Alerting          │    │
│  └─────────────────────────────────────────────────────┘    │
├─────────────────────────────────────────────────────────────┤
│                    MySQL Database                           │
└─────────────────────────────────────────────────────────────┘
```

## Technology Stack

### Backend
- **Framework**: Spring Boot 2.6.1
- **Database**: MySQL 8.0
- **ORM**: MyBatis
- **WebSocket**: Spring WebSocket
- **Template Engine**: FreeMarker
- **PDF Generation**: Apache POI, iTextPDF,Aspose

### AI Module
- **Runtime**: Python 3.9+
- **LLM Integration**: OpenAI API / Anthropic Claude API
- **ML Libraries**: NumPy, Pandas

## Installation

### Prerequisites
- JDK 11+
- Maven 3.6+
- MySQL 8.0+
- Python 3.9+

### Backend Setup

```bash
# Clone the repository
git clone https://github.com/your-org/mpcdsepc.git
cd mpcdsepc

# Configure database (edit src/main/resources/application.yml)
# Update MySQL connection settings

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### AI Module Setup

```bash
# Navigate to AI module
cd ai_engine

# Create virtual environment
python -m venv venv
source venv/bin/activate  # Linux/Mac
# or
venv\Scripts\activate     # Windows

# Install dependencies
pip install -r requirements.txt

# Configure API keys
cp config.example.yaml config.yaml
# Edit config.yaml with your API keys
```

## AI Engine Module

The AI Engine is a Python-based module that integrates large language models for intelligent document processing:

### Features

- **Semantic Document Understanding**: Uses transformer-based models to understand epidemic investigation document semantics
- **Intelligent Auto-completion**: Suggests field values based on context and historical data
- **Anomaly Detection**: Identifies unusual patterns in epidemiological data
- **Natural Language Query**: Query documents using natural language

### Quick Start

```python
from ai_engine import DocumentIntelligenceEngine

# Initialize the engine
engine = DocumentIntelligenceEngine(
    model="gpt-4",
    api_key="your-api-key",
    temperature=0.7
)

# Analyze a document
result = engine.analyze_document(document_text)
print(f"Analysis: {result}")

# Generate intelligent suggestions
suggestions = engine.generate_suggestions(context)
print(f"Suggestions: {suggestions}")
```

## API Endpoints

### Document Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/document/upload` | Upload new document |
| GET | `/api/document/{id}` | Get document by ID |
| PUT | `/api/document/{id}` | Update document |
| DELETE | `/api/document/{id}` | Delete document |
| POST | `/api/document/analyze` | AI analysis of document |

### Person Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/person/list` | List all persons |
| POST | `/api/person/add` | Add new person |
| PUT | `/api/person/{id}` | Update person info |
| DELETE | `/api/person/{id}` | Delete person |

### Report Generation
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/gen/word` | Generate Word report |
| POST | `/api/gen/pdf` | Convert to PDF |

## Configuration

### application.yml

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mpcdsepc
    username: root
    password: your-password
  servlet:
    multipart:
      max-file-size: 50MB
      max-request-size: 50MB

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.yytech.mpcdsepc.entity
```

## Use Cases

1. **Epidemic Investigation**: City and district health workers collaborate on investigation documents
2. **Contact Tracing**: Track and manage contact relationships between confirmed cases
3. **Report Generation**: Automatically generate standardized epidemic investigation reports
4. **Data Analysis**: AI-powered insights into epidemic patterns and anomalies

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

MIT License

## Acknowledgments

- Spring Boot Community
- MyBatis Community
- OpenAI/Anthropic for LLM capabilities

---

<p align="center">Built with ❤️ for epidemic prevention efforts</p>