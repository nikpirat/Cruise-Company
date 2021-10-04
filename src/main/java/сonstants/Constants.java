package сonstants;

public class Constants {
    public static final String SQL_INSERT_INTO_CRUISE_INFO = "INSERT INTO cruise_info (SHIP_ID, ROOM_TYPE, TOTAL_PRICE, USER_ID) VALUES (?, ?, ?, ?)";
    public static final String SQL_FIND_CRUISE_INFO = "SELECT * FROM cruise_info WHERE id = ?";
    public static final String SQL_FIND_ALL_CRUISE_INFO = "SELECT * FROM cruise_info";
    public static final String SQL_UPDATE_CRUISE_INFO = "UPDATE  cruise_info SET ship_id, room_type = ?, total_price = ? WHERE id = ?";
    public static final String SQL_DELETE_CRUISE_INFO = "DELETE FROM cruise_info WHERE id = ?";
    public static final String SQL_FIND_ALL_CRUISE_INFO_BY_USER_ID = "SELECT * FROM CRUISE_INFO JOIN USER_ROLE ON CRUISE_INFO.USER_ID = USER_ROLE.id WHERE user_role.id = ?";
}
