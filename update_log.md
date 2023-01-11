## 12.01.2023 #1

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