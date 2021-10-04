package сonstants;

public class Constants {
    public static final String SQL_INSERT_INTO_CRUISE_INFO = "INSERT INTO cruise_info (SHIP_ID, ROOM_TYPE, TOTAL_PRICE, USER_ID) VALUES (?, ?, ?, ?)";
    public static final String SQL_FIND_CRUISE_INFO = "SELECT * FROM cruise_info WHERE id = ?";
    public static final String SQL_FIND_ALL_CRUISE_INFO = "SELECT * FROM cruise_info";
    public static final String SQL_UPDATE_CRUISE_INFO = "UPDATE  cruise_info SET ship_id, room_type = ?, total_price = ? WHERE id = ?";
    public static final String SQL_DELETE_CRUISE_INFO = "DELETE FROM cruise_info WHERE id = ?";
    public static final String SQL_FIND_ALL_CRUISE_INFO_BY_USER_ID = "SELECT * FROM CRUISE_INFO JOIN USER_ROLE ON CRUISE_INFO.USER_ID = USER_ROLE.id WHERE user_role.id = ?";


    public static final String SQL_FIND_SHIP = "Select * from ship WHERE id = ?";
    public static final String SQL_FIND_ALL_SHIPS = "select * from ship";
    public static final String SQL_DELETE_SHIP = "DELETE FROM ship WHERE id = ?";
    public static final String SQL_UPDATE_SHIP =
            "UPDATE  ship set name = ?, passenger_amount = ?, route_to = ?, route_from = ?,amount_ports = ?," +
                    "travel_start = ?,travel_end = ?,standart_price = ?" +
                    "where id = ?";
    public static final String SQL_INSERT_INTO_SHIP =
            "INSERT INTO ship (name, passenger_amount, route_to, route_from, " +
                    "amount_ports, travel_start, travel_end, standart_price) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_GET_BY_LOGIN_AND_PASS = "Select * from user_role as u where u.login = ? and u.password = ?";
    public static final String SQL_FIND_USER = "Select * from user_role WHERE id = ?";
    public static final String SQL_FIND_ALL_USERS = "select * from user_role";
    public static final String SQL_DELETE_USER = "DELETE FROM user_role WHERE id = ?";
    public static final String SQL_GET_NUMBER_OF_ROWS = "select COUNT(id) FROM user_role";
    public static final String SQL_FIND_USERS_USING_LIMIT_AND_OFFSET = "select * from user_role ORDER BY ID LIMIT ? OFFSET ?";
    public static final String SQL_UPDATE_USER =
            "UPDATE  user_role set login = ?, password = ?, role = ?, name = ? , surname = ?, balance = ?  " +
            "where id = ?";
    public static final String SQL_INSERT_INTO_USER =
            "INSERT INTO user_role (login, password, role, name, surname, balance) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
}
