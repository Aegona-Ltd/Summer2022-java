SELECT id, datetime, fullname, email, phone, subject, message
FROM contacts
INTO OUTFILE 'D:\\Summer2022-java\\[TranQuocHieu]-Java-BaiTap2\\ContactsManagement\\src\\main\\resources\\file\\csv\\contacts.csv'
fields terminated by ',' lines terminated by '\n';