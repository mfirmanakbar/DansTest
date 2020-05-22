# DansTest

...

## Installation

Environment

```bash
PORT=8282;
RDS_DB_NAME=DATABASE_NAME;
RDS_HOSTNAME=localhost;
RDS_PORT=3306;
RDS_USERNAME=DATABASE_USERNAME;
RDS_PASSWORD=password;
SECRET_KEY=secret_key
```

## API Docs

#### Register
    - Endpoint `/register`
    - Method POST
    - Body
        ```json
        {
            "username": "firman",
            "password": "12345678"
        }
        ``` 
        
#### Login
    - Endpoint `/login`
    - Method POST
    - Body
        ```json
        {
            "username": "firman",
            "password": "12345678"
        }
        ``` 
        
#### Positions
    - Endpoint `/positions`
    - Method GET
    - Header
        ```json
        Authorization: Bearer {{token_from_login}}
        ``` 
        
#### Positions by Id
    - Endpoint `/positions/{id}`
    - Method GET
    - Header
        ```json
        Authorization: Bearer {{token_from_login}}
        ``` 

#### Download CSV (open this from browser)
    - Endpoint `http://localhost:8080/download/positions.csv`
