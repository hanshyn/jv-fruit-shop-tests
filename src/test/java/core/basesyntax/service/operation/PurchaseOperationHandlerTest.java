package core.basesyntax.service.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.dao.FruitDaoImpl;
import core.basesyntax.db.Storage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PurchaseOperationHandlerTest {
    private static final String VALID_FRUIT = "banana";
    private static final Integer VALID_QUANTITY = 20;
    private static final Integer PURCHASE = 10;
    private static OperationHandler purchaseHandler;
    private static FruitDao fruitDao;

    @BeforeAll
    static void beforeAll() {
        fruitDao = new FruitDaoImpl();
        purchaseHandler = new PurchaseOperationHandler(fruitDao);
    }

    @AfterAll
    static void afterAll() {
        Storage.storageFruit.clear();
    }

    @Test
    void processTransaction_purchaseOperation_ok() {
        fruitDao.add(VALID_FRUIT,VALID_QUANTITY);
        purchaseHandler.processTransaction(VALID_FRUIT, PURCHASE);
        Integer expected = VALID_QUANTITY - PURCHASE;
        assertEquals(expected, fruitDao.get(VALID_FRUIT));
    }
}