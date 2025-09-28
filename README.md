# LOV-List-of-Values-Management-System
📋 Overview
An enterprise-grade Spring Boot application for managing and retrieving Lists of Values (LOV) from Oracle database through stored procedures. Features comprehensive IBM MQ integration for auditing, message dumping, and enterprise-level transaction tracking.

🚀 Key Features
🔧 Core Functionality
LOV Data Management - Retrieve lists of values from Oracle database via stored procedures

Dynamic LOV Retrieval - Fetch data based on configurable LOV codes

Structured Response Handling - Consistent response format with status codes and timestamps

📊 Enterprise Integration
IBM MQ Messaging - Asynchronous message publishing to multiple queues

Message Dumping - Complete request/response logging for debugging and tracing

Audit Trail Generation - Compliance-ready audit records for all transactions

XML Payload Processing - Structured message formatting using JAXB

🛡️ Enterprise Features
End-to-End Transaction Tracking - Unique message correlation IDs

Comprehensive Exception Handling - Global exception management

Channel-Based Routing - Flexible request processing based on channels

Multi-Format Support - Configurable message formats

🏗️ Architecture
Project Structure
text
src/
├── audit/                 # Audit trail management
│   ├── AuditVars.java
│   ├── EAIMessage.java
│   └── FlowProperties.java
├── controller/            # REST API endpoints
│   └── RESTController.java
├── dao/                   # Data access layer
│   └── LOVListDAO.java
├── exceptionHandling/     # Global exception management
│   ├── GlobalExceptionHandling.java
│   └── RESTExceptionHandling.java
├── logging/               # Message logging utilities
│   ├── DumpMsg.java
│   ├── EAIHeader.java
│   ├── MsgData.java
│   ├── RequestBody.java
│   └── ResponseBody.java
├── LOV/                   # LOV domain models
│   ├── LOVList.java
│   └── LOVListSPR.java
├── response/              # API response models
│   └── LOVListRes.java
├── service/               # Business logic layer
│   ├── LOVService.java
│   └── MQNativeProducer.java
└── utils/                 # Utility classes
    ├── XmlUtils.java
    └── LovlistApplication.java
🔄 Business Flow
Receive Request - REST endpoint receives LOV code

Generate IDs - Create unique message ID and reference number

Log Request - Publish request message to dump queue via IBM MQ

Execute Procedure - Call Oracle stored procedure to fetch LOV data

Log Response - Publish response message to dump queue

Create Audit - Generate audit trail in audit queue

Return Response - Send structured HTTP response to client

🛠️ Technology Stack
Backend Framework: Spring Boot 2.7.18

Database: Oracle 19c with Spring JDBC

Messaging: IBM MQ 9.3.4.0

XML Processing: JAXB 4.0.4

Build Tool: Maven

Java Version: 11

📥 Installation & Setup
Prerequisites
Java 11 or higher

Maven 3.6+

Oracle Database 19c

IBM MQ 9.3.4.0 or compatible
