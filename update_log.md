## 14.01.2023

### Changes:

- All classes implementing <i>Convertible</i>:
  * update() - update object in database 
  * updateObject() - update object with data from db

- Product.java
  * changed <i> suggested </i> type from <i> ArrayList<Product> </i> to <i>Map<Integer, String></i> (<i>Map<K, V> : [product_id, product_name]</i>)
  * setHowManyStock(int < 1) -> visibility = false

- Cart.java
  * createOrder() saves order to database

- Order.java
  * deleted statuses
  * if status of <i>Payment</i> or <i>Shipping</i> changed, <i>Status</i> is updated  

- Shipping.java
  * new status <i>"zwrocono"</i>

- Payment.java
  * renamed statuses to all caps


## 12.01.2023

### Changes:
- DatabaseConnector.java
  * renamed <i>loadFromFile(int, Class)</i> to loadData(int, Class)
  * code refactor
  * preparation for loading and saving items

## 11.01.2023

### Changes:
- Added UserBuilder.java
  * <i>User build()</i> creates and save user and his data to database
- User.java:
  * added: <i> public static UserBuilder getBuilder(); </i>
  * deleted: <i> User(String, String) </i>
  * changed <i>String role</i> to <i> Boolean isAdmin </i>

### Fixes:
- User.java
  * <i> convertToRecord() </i> no longer saves cart, settings and address id.
- usersetting.csv
  * renamed to <i> usersettings.csv </i> (as database sees it)