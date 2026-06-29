# Postman API Test Collection - Sales Manager Application

Base URL: `http://localhost:8080`

---

## 1. CUSTOMER API ENDPOINTS

### 1.1 Create Customer (POST)
**URL:** `POST http://localhost:8080/api/customers`

**Request Body - Example 1:**
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@company.com",
    "phoneNo": "+1-555-0101",
    "organizationName": "Tech Corp"
}
```

**Request Body - Example 2:**
```json
{
    "firstName": "Sarah",
    "lastName": "Smith",
    "email": "sarah.smith@enterprise.com",
    "phoneNo": "+1-555-0102",
    "organizationName": "Enterprise Ltd"
}
```

**Request Body - Example 3:**
```json
{
    "firstName": "Michael",
    "lastName": "Johnson",
    "email": "michael.j@startup.io",
    "phoneNo": "+1-555-0103",
    "organizationName": "Startup Innovations"
}
```

**Request Body - Example 4:**
```json
{
    "firstName": "Emily",
    "lastName": "Brown",
    "email": "emily.brown@finance.com",
    "phoneNo": "+1-555-0104",
    "organizationName": "Finance Solutions"
}
```

---

### 1.2 Get All Customers (GET)
**URL:** `GET http://localhost:8080/api/customers`
**No Request Body Required**

---

### 1.3 Get Customer by ID (GET)
**URL:** `GET http://localhost:8080/api/customers/{customerId}`

**Examples:**
- `GET http://localhost:8080/api/customers/1`
- `GET http://localhost:8080/api/customers/2`
- `GET http://localhost:8080/api/customers/3`
- `GET http://localhost:8080/api/customers/4`

---

### 1.4 Update Customer (PUT)
**URL:** `PUT http://localhost:8080/api/customers/{customerId}`

**Request Body - Example 1 (Update customer 1):**
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe.updated@company.com",
    "phoneNo": "+1-555-9101",
    "organizationName": "Tech Corp International"
}
```

**Request Body - Example 2 (Update customer 2):**
```json
{
    "firstName": "Sarah",
    "lastName": "Smith-Johnson",
    "email": "sarah.smith@enterprise.com",
    "phoneNo": "+1-555-9102",
    "organizationName": "Enterprise Global"
}
```

**Request Body - Example 3 (Update customer 3):**
```json
{
    "firstName": "Michael",
    "lastName": "Johnson",
    "email": "michael.j.updated@startup.io",
    "phoneNo": "+1-555-9103",
    "organizationName": "Startup Innovations Plus"
}
```

**Request Body - Example 4 (Update customer 4):**
```json
{
    "firstName": "Emily",
    "lastName": "Brown",
    "email": "emily.brown.updated@finance.com",
    "phoneNo": "+1-555-9104",
    "organizationName": "Finance Solutions Pro"
}
```

---

### 1.5 Delete Customer (DELETE)
**URL:** `DELETE http://localhost:8080/api/customers/{customerId}`

**Examples:**
- `DELETE http://localhost:8080/api/customers/1` (Soft delete)
- `DELETE http://localhost:8080/api/customers/2` (Soft delete)
- `DELETE http://localhost:8080/api/customers/3` (Soft delete)
- `DELETE http://localhost:8080/api/customers/4` (Soft delete)

---

## 2. EMPLOYEE API ENDPOINTS

### 2.1 Create Employee (POST)
**URL:** `POST http://localhost:8080/api/employees`

**Request Body - Example 1:**
```json
{
    "firstName": "Robert",
    "lastName": "Wilson",
    "title": "Sales Manager",
    "email": "robert.wilson@company.com",
    "country": "USA",
    "city": "New York"
}
```

**Request Body - Example 2:**
```json
{
    "firstName": "Jennifer",
    "lastName": "Martinez",
    "title": "Account Executive",
    "email": "jennifer.martinez@company.com",
    "country": "USA",
    "city": "Los Angeles"
}
```

**Request Body - Example 3:**
```json
{
    "firstName": "David",
    "lastName": "Chen",
    "title": "Business Development",
    "email": "david.chen@company.com",
    "country": "USA",
    "city": "San Francisco"
}
```

**Request Body - Example 4:**
```json
{
    "firstName": "Amanda",
    "lastName": "Rodriguez",
    "title": "Sales Representative",
    "email": "amanda.rodriguez@company.com",
    "country": "USA",
    "city": "Chicago"
}
```

---

### 2.2 Get All Employees (GET)
**URL:** `GET http://localhost:8080/api/employees`
**No Request Body Required**

---

### 2.3 Get Employee by ID (GET)
**URL:** `GET http://localhost:8080/api/employees/{employeeId}`

**Examples:**
- `GET http://localhost:8080/api/employees/1`
- `GET http://localhost:8080/api/employees/2`
- `GET http://localhost:8080/api/employees/3`
- `GET http://localhost:8080/api/employees/4`

---

### 2.4 Update Employee (PUT)
**URL:** `PUT http://localhost:8080/api/employees/{employeeId}`

**Request Body - Example 1 (Update employee 1):**
```json
{
    "firstName": "Robert",
    "lastName": "Wilson",
    "title": "Senior Sales Manager",
    "email": "robert.wilson@company.com",
    "country": "USA",
    "city": "New York"
}
```

**Request Body - Example 2 (Update employee 2):**
```json
{
    "firstName": "Jennifer",
    "lastName": "Martinez",
    "title": "Senior Account Executive",
    "email": "jennifer.martinez@company.com",
    "country": "USA",
    "city": "Los Angeles"
}
```

**Request Body - Example 3 (Update employee 3):**
```json
{
    "firstName": "David",
    "lastName": "Chen",
    "title": "Director of Business Development",
    "email": "david.chen@company.com",
    "country": "USA",
    "city": "San Francisco"
}
```

**Request Body - Example 4 (Update employee 4):**
```json
{
    "firstName": "Amanda",
    "lastName": "Rodriguez",
    "title": "Senior Sales Representative",
    "email": "amanda.rodriguez@company.com",
    "country": "USA",
    "city": "Chicago"
}
```

---

### 2.5 Delete Employee (DELETE)
**URL:** `DELETE http://localhost:8080/api/employees/{employeeId}`

**Examples:**
- `DELETE http://localhost:8080/api/employees/1`
- `DELETE http://localhost:8080/api/employees/2`
- `DELETE http://localhost:8080/api/employees/3`
- `DELETE http://localhost:8080/api/employees/4`

---

## 3. PRODUCT DETAILS API ENDPOINTS

### 3.1 Create Product (POST)
**URL:** `POST http://localhost:8080/api/products`

**Request Body - Example 1:**
```json
{
    "productId": 101,
    "productName": "AI Buzzword Generator",
    "productType": "Software",
    "productStatus": "Active",
    "description": "Advanced AI-powered tool for generating trending buzzwords and marketing content"
}
```

**Request Body - Example 2:**
```json
{
    "productId": 102,
    "productName": "Cloud Analytics Platform",
    "productType": "SaaS",
    "productStatus": "Active",
    "description": "Real-time cloud-based analytics and reporting solution"
}
```

**Request Body - Example 3:**
```json
{
    "productId": 103,
    "productName": "CRM System",
    "productType": "Software",
    "productStatus": "Active",
    "description": "Complete customer relationship management system with automation"
}
```

**Request Body - Example 4:**
```json
{
    "productId": 104,
    "productName": "Data Security Suite",
    "productType": "Software",
    "productStatus": "Active",
    "description": "Enterprise-grade data protection and encryption software"
}
```

---

### 3.2 Get All Products (GET)
**URL:** `GET http://localhost:8080/api/products`
**No Request Body Required**

---

### 3.3 Get Product by ID (GET)
**URL:** `GET http://localhost:8080/api/products/{productId}`

**Examples:**
- `GET http://localhost:8080/api/products/101`
- `GET http://localhost:8080/api/products/102`
- `GET http://localhost:8080/api/products/103`
- `GET http://localhost:8080/api/products/104`

---

### 3.4 Update Product (PUT)
**URL:** `PUT http://localhost:8080/api/products/{productId}`

**Request Body - Example 1 (Update product 101):**
```json
{
    "productId": 101,
    "productName": "AI Buzzword Generator Pro",
    "productType": "Software",
    "productStatus": "Active",
    "description": "Advanced AI-powered tool with premium features for generating trending buzzwords"
}
```

**Request Body - Example 2 (Update product 102):**
```json
{
    "productId": 102,
    "productName": "Cloud Analytics Platform Enterprise",
    "productType": "SaaS",
    "productStatus": "Active",
    "description": "Enterprise real-time cloud-based analytics with advanced features"
}
```

**Request Body - Example 3 (Update product 103):**
```json
{
    "productId": 103,
    "productName": "CRM System Pro",
    "productType": "Software",
    "productStatus": "Active",
    "description": "Premium CRM with AI-powered automation and insights"
}
```

**Request Body - Example 4 (Update product 104):**
```json
{
    "productId": 104,
    "productName": "Data Security Suite Premium",
    "productType": "Software",
    "productStatus": "Active",
    "description": "Enterprise-grade data protection with advanced threat detection"
}
```

---

### 3.5 Delete Product (DELETE)
**URL:** `DELETE http://localhost:8080/api/products/{productId}`

**Examples:**
- `DELETE http://localhost:8080/api/products/101`
- `DELETE http://localhost:8080/api/products/102`
- `DELETE http://localhost:8080/api/products/103`
- `DELETE http://localhost:8080/api/products/104`

---

## 4. LEAD STATUS API ENDPOINTS

### 4.1 Create Lead Status (POST)
**URL:** `POST http://localhost:8080/api/lead-statuses`

**Request Body - Example 1 (Assigned):**
```json
{
    "statusId": 1,
    "statusName": "Assigned"
}
```

**Request Body - Example 2 (Unassigned):**
```json
{
    "statusId": 2,
    "statusName": "Unassigned"
}
```

**Request Body - Example 3 (Success):**
```json
{
    "statusId": 3,
    "statusName": "Success"
}
```

**Request Body - Example 4 (Rejected):**
```json
{
    "statusId": 4,
    "statusName": "Rejected"
}
```

---

### 4.2 Get All Lead Statuses (GET)
**URL:** `GET http://localhost:8080/api/lead-statuses`
**No Request Body Required**

---

### 4.3 Get Lead Status by ID (GET)
**URL:** `GET http://localhost:8080/api/lead-statuses/{statusId}`

**Examples:**
- `GET http://localhost:8080/api/lead-statuses/1` (Assigned)
- `GET http://localhost:8080/api/lead-statuses/2` (Unassigned)
- `GET http://localhost:8080/api/lead-statuses/3` (Success)
- `GET http://localhost:8080/api/lead-statuses/4` (Rejected)

---

### 4.4 Update Lead Status (PUT)
**URL:** `PUT http://localhost:8080/api/lead-statuses/{statusId}`

**Request Body - Example 1 (Update Assigned):**
```json
{
    "statusId": 1,
    "statusName": "Assigned"
}
```

**Request Body - Example 2 (Update Unassigned):**
```json
{
    "statusId": 2,
    "statusName": "Unassigned"
}
```

**Request Body - Example 3 (Update Success):**
```json
{
    "statusId": 3,
    "statusName": "Success"
}
```

**Request Body - Example 4 (Update Rejected):**
```json
{
    "statusId": 4,
    "statusName": "Rejected"
}
```

---

### 4.5 Delete Lead Status (DELETE)
**URL:** `DELETE http://localhost:8080/api/lead-statuses/{statusId}`

**Examples:**
- `DELETE http://localhost:8080/api/lead-statuses/1`
- `DELETE http://localhost:8080/api/lead-statuses/2`
- `DELETE http://localhost:8080/api/lead-statuses/3`
- `DELETE http://localhost:8080/api/lead-statuses/4`

---

## 5. LEAD SOURCE API ENDPOINTS

### 5.1 Create Lead Source (POST)
**URL:** `POST http://localhost:8080/api/lead-sources`

**Request Body - Example 1:**
```json
{
    "sourceId": 1,
    "sourceName": "Website Inquiry"
}
```

**Request Body - Example 2:**
```json
{
    "sourceId": 2,
    "sourceName": "Email Campaign"
}
```

**Request Body - Example 3:**
```json
{
    "sourceId": 3,
    "sourceName": "Social Media"
}
```

**Request Body - Example 4:**
```json
{
    "sourceId": 4,
    "sourceName": "Referral"
}
```

---

### 5.2 Get All Lead Sources (GET)
**URL:** `GET http://localhost:8080/api/lead-sources`
**No Request Body Required**

---

### 5.3 Get Lead Source by ID (GET)
**URL:** `GET http://localhost:8080/api/lead-sources/{sourceId}`

**Examples:**
- `GET http://localhost:8080/api/lead-sources/1`
- `GET http://localhost:8080/api/lead-sources/2`
- `GET http://localhost:8080/api/lead-sources/3`
- `GET http://localhost:8080/api/lead-sources/4`

---

### 5.4 Update Lead Source (PUT)
**URL:** `PUT http://localhost:8080/api/lead-sources/{sourceId}`

**Request Body - Example 1:**
```json
{
    "sourceId": 1,
    "sourceName": "Website Inquiry - Updated"
}
```

**Request Body - Example 2:**
```json
{
    "sourceId": 2,
    "sourceName": "Email Marketing Campaign"
}
```

**Request Body - Example 3:**
```json
{
    "sourceId": 3,
    "sourceName": "Social Media Platform"
}
```

**Request Body - Example 4:**
```json
{
    "sourceId": 4,
    "sourceName": "Customer Referral Program"
}
```

---

### 5.5 Delete Lead Source (DELETE)
**URL:** `DELETE http://localhost:8080/api/lead-sources/{sourceId}`

**Examples:**
- `DELETE http://localhost:8080/api/lead-sources/1`
- `DELETE http://localhost:8080/api/lead-sources/2`
- `DELETE http://localhost:8080/api/lead-sources/3`
- `DELETE http://localhost:8080/api/lead-sources/4`

---

## 6. LOCATIONS API ENDPOINTS

### 6.1 Create Location (POST)
**URL:** `POST http://localhost:8080/api/locations`

**Request Body - Example 1:**
```json
{
    "phone": "+1-212-555-0001",
    "street": "123 Manhattan Ave",
    "country": "USA",
    "city": "New York"
}
```

**Request Body - Example 2:**
```json
{
    "phone": "+1-213-555-0002",
    "street": "456 Hollywood Blvd",
    "country": "USA",
    "city": "Los Angeles"
}
```

**Request Body - Example 3:**
```json
{
    "phone": "+1-415-555-0003",
    "street": "789 Bay Bridge Way",
    "country": "India",
    "city": "Pune"
}
```

**Request Body - Example 4:**
```json
{
    "phone": "+1-312-555-0004",
    "street": "321 Michigan Ave",
    "country": "USA",
    "city": "Chicago"
}
```

---

### 6.2 Get All Locations (GET)
**URL:** `GET http://localhost:8080/api/locations`
**No Request Body Required**

---

### 6.3 Get Location by ID (GET)
**URL:** `GET http://localhost:8080/api/locations/{locationId}`

**Examples:**
- `GET http://localhost:8080/api/locations/1`
- `GET http://localhost:8080/api/locations/2`
- `GET http://localhost:8080/api/locations/3`
- `GET http://localhost:8080/api/locations/4`

---

### 6.4 Update Location (PUT)
**URL:** `PUT http://localhost:8080/api/locations/{locationId}`

**Request Body - Example 1:**
```json
{
    "id": 1,
    "phone": "+1-212-555-9001",
    "street": "123 Manhattan Ave, Suite 100",
    "country": "USA",
    "city": "New York"
}
```

**Request Body - Example 2:**
```json
{
    "id": 2,
    "phone": "+1-213-555-9002",
    "street": "456 Hollywood Blvd, Suite 200",
    "country": "USA",
    "city": "Los Angeles"
}
```

**Request Body - Example 3:**
```json
{
    "id": 3,
    "phone": "+1-415-555-9003",
    "street": "789 Bay Bridge Way, Suite 300",
    "country": "USA",
    "city": "San Francisco"
}
```

**Request Body - Example 4:**
```json
{
    "id": 4,
    "phone": "+1-312-555-9004",
    "street": "321 Michigan Ave, Suite 400",
    "country": "USA",
    "city": "Chicago"
}
```

---

### 6.5 Delete Location (DELETE)
**URL:** `DELETE http://localhost:8080/api/locations/{locationId}`

**Examples:**
- `DELETE http://localhost:8080/api/locations/1`
- `DELETE http://localhost:8080/api/locations/2`
- `DELETE http://localhost:8080/api/locations/3`
- `DELETE http://localhost:8080/api/locations/4`

---

## 7. LEAD API ENDPOINTS

### 7.1 Create Lead (POST)
**URL:** `POST http://localhost:8080/api/leads`

**Request Body - Example 1:**
```json
{
    "customerId": 1,
    "sourceId": 1,
    "productId": 101,
    "createdBy": 1,
    "statusId": 1,
    "assignedTo": 2,
    "description": "Customer submitted an enquiry through the company website regarding AI Buzzword Generator.",
    "priority": "High",
    "value": 50000
}
```

**Request Body - Example 2:**
```json
{
    "customerId": 2,
    "sourceId": 2,
    "productId": 102,
    "createdBy": 2,
    "statusId": 2,
    "assignedTo": 3,
    "description": "Lead from email marketing campaign expressing interest in Cloud Analytics Platform",
    "priority": "Medium",
    "value": 75000
}
```

**Request Body - Example 3:**
```json
{
    "customerId": 3,
    "sourceId": 3,
    "productId": 103,
    "createdBy": 3,
    "statusId": 1,
    "assignedTo": 4,
    "description": "Social media referral for CRM System implementation project",
    "priority": "High",
    "value": 100000
}
```

**Request Body - Example 4:**
```json
{
    "customerId": 4,
    "sourceId": 4,
    "productId": 104,
    "createdBy": 1,
    "statusId": 3,
    "assignedTo": 2,
    "description": "Referral from existing customer for Data Security Suite",
    "priority": "Medium",
    "value": 60000
}
```

---

### 7.2 Get All Leads (GET)
**URL:** `GET http://localhost:8080/api/leads`
**No Request Body Required**

---

### 7.3 Get Lead by ID (GET)
**URL:** `GET http://localhost:8080/api/leads/{leadId}`

**Examples:**
- `GET http://localhost:8080/api/leads/1`
- `GET http://localhost:8080/api/leads/2`
- `GET http://localhost:8080/api/leads/3`
- `GET http://localhost:8080/api/leads/4`

---

### 7.4 Update Lead (PUT)
**URL:** `PUT http://localhost:8080/api/leads/{leadId}`

**Request Body - Example 1 (Update lead 1):**
```json
{
    "customerId": 1,
    "sourceId": 1,
    "productId": 101,
    "createdBy": 1,
    "statusId": 3,
    "assignedTo": 2,
    "description": "Lead successfully converted - customer signed contract for AI Buzzword Generator",
    "priority": "High",
    "value": 50000
}
```

**Request Body - Example 2 (Update lead 2):**
```json
{
    "customerId": 2,
    "sourceId": 2,
    "productId": 102,
    "createdBy": 2,
    "statusId": 3,
    "assignedTo": 3,
    "description": "Successfully closed deal for Cloud Analytics Platform - 1-year contract",
    "priority": "Medium",
    "value": 85000
}
```

**Request Body - Example 3 (Update lead 3):**
```json
{
    "customerId": 3,
    "sourceId": 3,
    "productId": 103,
    "createdBy": 3,
    "statusId": 4,
    "assignedTo": 4,
    "description": "Lead rejected - customer chose competitor solution",
    "priority": "Low",
    "value": 100000
}
```

**Request Body - Example 4 (Update lead 4):**
```json
{
    "customerId": 4,
    "sourceId": 4,
    "productId": 104,
    "createdBy": 1,
    "statusId": 3,
    "assignedTo": 2,
    "description": "Conversion completed - customer purchased premium Data Security Suite",
    "priority": "High",
    "value": 85000
}
```

---

### 7.5 Delete Lead (DELETE)
**URL:** `DELETE http://localhost:8080/api/leads/{leadId}`

**Examples:**
- `DELETE http://localhost:8080/api/leads/1`
- `DELETE http://localhost:8080/api/leads/2`
- `DELETE http://localhost:8080/api/leads/3`
- `DELETE http://localhost:8080/api/leads/4`

---

## 8. LEAD ASSIGNMENT API ENDPOINTS

### 8.1 Create Lead Assignment (POST)
**URL:** `POST http://localhost:8080/api/lead-assignments`

**Request Body - Example 1:**
```json
{
    "leadId": 1,
    "assignedToId": 2,
    "assignedById": 1,
    "remarks": "Assigned to top performer for quick conversion"
}
```

**Request Body - Example 2:**
```json
{
    "leadId": 2,
    "assignedToId": 3,
    "assignedById": 2,
    "remarks": "Escalated to specialist team for complex enterprise deal"
}
```

**Request Body - Example 3:**
```json
{
    "leadId": 3,
    "assignedToId": 4,
    "assignedById": 3,
    "remarks": "New assignment - customer requested specific account manager"
}
```

**Request Body - Example 4:**
```json
{
    "leadId": 4,
    "assignedToId": 1,
    "assignedById": 2,
    "remarks": "Reassignment for better account management and follow-up"
}
```

---

### 8.2 Get All Lead Assignments (GET)
**URL:** `GET http://localhost:8080/api/lead-assignments`
**No Request Body Required**

---

### 8.3 Get Lead Assignment by ID (GET)
**URL:** `GET http://localhost:8080/api/lead-assignments/{assignmentId}`

**Examples:**
- `GET http://localhost:8080/api/lead-assignments/1`
- `GET http://localhost:8080/api/lead-assignments/2`
- `GET http://localhost:8080/api/lead-assignments/3`
- `GET http://localhost:8080/api/lead-assignments/4`

---

### 8.4 Get Assignment History for a Lead (GET)
**URL:** `GET http://localhost:8080/api/lead-assignments/history/{leadId}`

**Examples:**
- `GET http://localhost:8080/api/lead-assignments/history/1`
- `GET http://localhost:8080/api/lead-assignments/history/2`
- `GET http://localhost:8080/api/lead-assignments/history/3`
- `GET http://localhost:8080/api/lead-assignments/history/4`

---

### 8.5 Get Assignments by Employee (GET)
**URL:** `GET http://localhost:8080/api/lead-assignments/employee/{employeeId}`

**Examples:**
- `GET http://localhost:8080/api/lead-assignments/employee/1`
- `GET http://localhost:8080/api/lead-assignments/employee/2`
- `GET http://localhost:8080/api/lead-assignments/employee/3`
- `GET http://localhost:8080/api/lead-assignments/employee/4`

---

### 8.6 Delete Lead Assignment (DELETE)
**URL:** `DELETE http://localhost:8080/api/lead-assignments/{assignmentId}`

**Examples:**
- `DELETE http://localhost:8080/api/lead-assignments/1`
- `DELETE http://localhost:8080/api/lead-assignments/2`
- `DELETE http://localhost:8080/api/lead-assignments/3`
- `DELETE http://localhost:8080/api/lead-assignments/4`

---

## 9. USER ACCOUNT API ENDPOINTS

### 9.1 Create User Account (POST)
**URL:** `POST http://localhost:8080/api/user-accounts`

**Request Body - Example 1:**
```json
{
    "employeeId": 1,
    "username": "rwilson",
    "password": "SecurePass123!",
    "accountStatus": "ACTIVE"
}
```

**Request Body - Example 2:**
```json
{
    "employeeId": 2,
    "username": "jmartinez",
    "password": "SecurePass456!",
    "accountStatus": "ACTIVE"
}
```

**Request Body - Example 3:**
```json
{
    "employeeId": 3,
    "username": "dchen",
    "password": "SecurePass789!",
    "accountStatus": "ACTIVE"
}
```

**Request Body - Example 4:**
```json
{
    "employeeId": 4,
    "username": "arodriguez",
    "password": "SecurePass012!",
    "accountStatus": "ACTIVE"
}
```

---

### 9.2 Get All User Accounts (GET)
**URL:** `GET http://localhost:8080/api/user-accounts`
**No Request Body Required**

---

### 9.3 Get User Account by ID (GET)
**URL:** `GET http://localhost:8080/api/user-accounts/{userId}`

**Examples:**
- `GET http://localhost:8080/api/user-accounts/1`
- `GET http://localhost:8080/api/user-accounts/2`
- `GET http://localhost:8080/api/user-accounts/3`
- `GET http://localhost:8080/api/user-accounts/4`

---

### 9.4 Update User Account (PUT)
**URL:** `PUT http://localhost:8080/api/user-accounts/{userId}`

**Request Body - Example 1:**
```json
{
    "username": "rwilson.updated",
    "password": "NewSecurePass123!",
    "accountStatus": "ACTIVE"
}
```

**Request Body - Example 2:**
```json
{
    "username": "jmartinez.updated",
    "password": "NewSecurePass456!",
    "accountStatus": "ACTIVE"
}
```

**Request Body - Example 3:**
```json
{
    "username": "dchen.updated",
    "password": "NewSecurePass789!",
    "accountStatus": "INACTIVE"
}
```

**Request Body - Example 4:**
```json
{
    "username": "arodriguez.updated",
    "password": "NewSecurePass012!",
    "accountStatus": "ACTIVE"
}
```

---

### 9.5 Delete User Account (DELETE)
**URL:** `DELETE http://localhost:8080/api/user-accounts/{userId}`

**Examples:**
- `DELETE http://localhost:8080/api/user-accounts/1`
- `DELETE http://localhost:8080/api/user-accounts/2`
- `DELETE http://localhost:8080/api/user-accounts/3`
- `DELETE http://localhost:8080/api/user-accounts/4`

---

## Testing Tips for Postman

### Headers Required
Add these headers to all requests:
```
Content-Type: application/json
Accept: application/json
```

### Response Status Codes
- **200 OK** - Successful GET/PUT request
- **201 CREATED** - Successful POST request
- **204 NO CONTENT** - Successful DELETE request
- **400 BAD REQUEST** - Validation error
- **404 NOT FOUND** - Resource not found
- **500 INTERNAL SERVER ERROR** - Server error

### Recommended Testing Order
1. Create Lead Status (1, 2, 3, 4)
2. Create Lead Source (1, 2, 3, 4)
3. Create Locations (1, 2, 3, 4)
4. Create Products (101, 102, 103, 104)
5. Create Customers (1, 2, 3, 4)
6. Create Employees (1, 2, 3, 4)
7. Create User Accounts (1, 2, 3, 4)
8. Create Leads (1, 2, 3, 4)
9. Create Lead Assignments (1, 2, 3, 4)
10. Test all GET endpoints
11. Test all UPDATE endpoints
12. Test DELETE endpoints

### Soft Delete Behavior
- When you DELETE an entity, `isActive` is set to `false`
- GET operations only return entities where `isActive = true`
- Deleted entities can be viewed directly in the database but won't appear in API responses

---

**Created:** 2026-06-28
**Application:** Sales Manager - Spring Boot 4.1.0
**Java Version:** 21
**Database:** MySQL 8.0+
