import dao.CruiseInfoDao;
import dao.impl.CruiseInfoDaoImpl;
import model.CruiseInfo;
import model.enums.RoomType;

public class Test {
    public static void main(String[] args) {
        CruiseInfo cruiseInfo=new CruiseInfo();
//        cruiseInfo.setId(1);
        cruiseInfo.setShipId(1);
        cruiseInfo.setRoomType(RoomType.PRESIDENT);
        cruiseInfo.setTotalPrice(1000);
        cruiseInfo.setUserId(2);
        CruiseInfoDao cruiseInfoDao = new CruiseInfoDaoImpl();

        cruiseInfoDao.create(cruiseInfo);
        cruiseInfoDao.findAll();

        /**
         * ОСТАЛОСЬ ДОДЕЛАТЬ:
         *
         * АДМИН МОЖЕТ СМОТРЕТЬ СТРАНИЦУ ПОЛЬЗОВАТЕЛЯ
         *
         * ЛОКАЛИЗАЦИЯ
         *
         * ШИФРОВАНИЕ ПАРОЛЕЙ
         *
         * СОРТИРОВКА КРУИЗОВ
         *
         * МИГРАЦИЯ БАЗ ДАННЫХ FLYWAY
         *
         */

    }
}
