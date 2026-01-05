INSERT INTO TEST.USERS ( ID
                                       , USERNAME
                                       , NAME
                                       , SURNAME
                                       , ENABLED
                                       , PASSWORD_RESET_DATE
                                       , CREATED_DATE
                                       , CREATED_BY
                                             , MODIFIED_DATE
                                             , MODIFIED_BY)
VALUES ( 1
       , 'test_user'
       , 'Name'
       , 'Surname'
       , 1
       , TIMESTAMP '2024-01-01 11:59:27.000000'
       , TIMESTAMP '2025-01-01 11:59:27.000000'
       , 2
       , TIMESTAMP '2025-01-12 16:37:12.000000'
       , 2);