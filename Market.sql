-- Step 1: Create the Brand table
CREATE TABLE Brand (
    brand_id INT PRIMARY KEY IDENTITY(1,1),
    brandName NVARCHAR(100) NOT NULL
);

-- Step 2: Create the Category table
CREATE TABLE Category (
    cid INT PRIMARY KEY IDENTITY(1,1),
    cname NVARCHAR(100) NOT NULL
);

-- Step 3: Create the Product table
CREATE TABLE Product (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    img NVARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    title NVARCHAR(255),
    description NVARCHAR(MAX),
    cateID INT,  -- Foreign key to Category table
    sellID INT,  -- Seller ID, possibly linked to the Account table
    brand_id INT,  -- Foreign key to Brand table
    CONSTRAINT FK_Product_Category FOREIGN KEY (cateID) REFERENCES Category(cid),
    CONSTRAINT FK_Product_Brand FOREIGN KEY (brand_id) REFERENCES Brand(brand_id)
);

-- Step 4: Create the Role table
CREATE TABLE Role (
    ID INT PRIMARY KEY IDENTITY(1,1),
    roleName NVARCHAR(100) NOT NULL
);

-- Step 5: Create the Account table
CREATE TABLE Account (
    id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(100) NOT NULL,
    password NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) NOT NULL,
    phone NVARCHAR(20),
    address NVARCHAR(255),
    roleID INT,  -- Foreign key to Role table
    status NVARCHAR(50),
    CONSTRAINT FK_Account_Role FOREIGN KEY (roleID) REFERENCES Role(ID)
);


CREATE TABLE Customer (
    customer_id INT PRIMARY KEY IDENTITY(1,1),  
    customer_name NVARCHAR(255) NOT NULL,       
    email NVARCHAR(255),                        
    phone NVARCHAR(20),                         
    address NVARCHAR(500),                      
    customer_type NVARCHAR(50),                 
);

-- Step 2: Create the Seller table
CREATE TABLE Seller (
    seller_id INT PRIMARY KEY IDENTITY(1,1),
    account_id INT,  -- Foreign key to Account table
    store_name NVARCHAR(100),  -- Name of the seller's store
    rating DECIMAL(3,2) CHECK (rating >= 0 AND rating <= 5),  -- Seller rating (0 to 5 scale)
    join_date DATE NOT NULL,  -- Date the seller joined the platform
    CONSTRAINT FK_Seller_Account FOREIGN KEY (account_id) REFERENCES Account(id)
);

-- Step 6: Create the Feedback table
CREATE TABLE Feedback (
    id INT PRIMARY KEY IDENTITY(1,1),
    account_id INT,  -- Foreign key to Account table
    product_id INT,  -- Foreign key to Product table
    rating INT CHECK (rating >= 1 AND rating <= 5),
    create_date DATE NOT NULL,
    CONSTRAINT FK_Feedback_Account FOREIGN KEY (account_id) REFERENCES Account(id),
    CONSTRAINT FK_Feedback_Product FOREIGN KEY (product_id) REFERENCES Product(id)
);

-- Step 7: Create the Cart table
CREATE TABLE Cart (
    AccountID INT,  -- Foreign key to Account table
    ProductID INT,  -- Foreign key to Product table
    Amount INT NOT NULL,
    CONSTRAINT FK_Cart_Account FOREIGN KEY (AccountID) REFERENCES Account(id),
    CONSTRAINT FK_Cart_Product FOREIGN KEY (ProductID) REFERENCES Product(id)
);


-- Step 9: Create the Shipping table
CREATE TABLE Shipping (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    phone NVARCHAR(20),
    address NVARCHAR(255),
    status NVARCHAR(50)
);

CREATE TABLE Orders (
    id INT PRIMARY KEY IDENTITY(1,1),
    accountID INT,  -- Foreign key to Account table
    totalPrice DECIMAL(10, 2),
    note NVARCHAR(255),
    create_date DATE NOT NULL,
    shipping_ID INT,  -- Foreign key to Shipping table
    CONSTRAINT FK_Orders_Account FOREIGN KEY (accountID) REFERENCES Account(id),
    CONSTRAINT FK_Orders_Shipping FOREIGN KEY (shipping_ID) REFERENCES Shipping(id)
);

-- Step 10: Create the OrderDetail table
CREATE TABLE OrderDetail (
    id INT PRIMARY KEY IDENTITY(1,1),
    order_id INT,  -- Foreign key to Orders table
    productName NVARCHAR(100) NOT NULL,
    productImage NVARCHAR(255),
    productPrice DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT FK_OrderDetail_Orders FOREIGN KEY (order_id) REFERENCES Orders(id)
);

-- Step 11: Create the Payments table
CREATE TABLE Payments (
    Payment_id INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT,  -- Foreign key to Orders table
    Payment_date DATE NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentMethod NVARCHAR(50),
    Status NVARCHAR(50),
    CONSTRAINT FK_Payments_Orders FOREIGN KEY (OrderID) REFERENCES Orders(id)
);


-- Step 3: Modify the Product table to link to the Seller table instead of Account
ALTER TABLE Product
ADD seller_id INT;  -- New column for seller_id linking to Seller

-- Add the foreign key relationship between Product and Seller
ALTER TABLE Product
ADD CONSTRAINT FK_Product_Seller FOREIGN KEY (seller_id) REFERENCES Seller(seller_id);

-- Optional: Remove the old sellID column from Product if no longer needed
-- ALTER TABLE Product DROP COLUMN sellID;


-- Step 2: Alter the Orders table to link directly to the Customer table
ALTER TABLE Orders
ADD customer_id INT,  -- Add customer_id column to store the customer reference
CONSTRAINT FK_Orders_Customer FOREIGN KEY (customer_id) REFERENCES Customer(customer_id);  -- Foreign key linking to Customer table

ALTER TABLE Cart
ADD OrderID INT;

ALTER TABLE Cart
ADD CONSTRAINT FK_Cart_Orders FOREIGN KEY (OrderID) REFERENCES Orders(id);

-- update database role, account

INSERT INTO Role (roleName)
VALUES 
('admin'),
('seller'),
('customer'),
('shipper');

INSERT INTO Account (username, password, email, phone, address, roleID, status)
VALUES 
('admin', '123', 'admin@gmail.com', '1234567890', 'a', 1, 'active'),  -- Admin
('seller', '123', 'seller@gmail.com', '1234567891', 'b', 2, 'active'),   -- Seller
('customer', '123', 'customer@gmail.com', '1234567892', 'c', 3, 'active'), -- Customer
('shipper', '123', 'shipper@gmail.com', '1234567893', 'd', 4, 'active'); -- Shipper