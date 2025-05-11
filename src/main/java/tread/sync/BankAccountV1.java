package tread.sync;

import static java.lang.Thread.sleep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAccountV1 implements BankAccount{
    private static final Logger log = LoggerFactory.getLogger(BankAccountV1.class);
    private int balance;

    public BankAccountV1(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) throws InterruptedException {
        log.info("거래 시작: {}",getClass().getSimpleName());

        log.info("[검증 시작] 출금액: {}, 잔액: {}",amount,balance);
        if (balance < amount) {
            log.info("[검증 실패]");
            return false;
        }

        log.info("[검증 완료]");
        sleep(1000);
        balance = balance - amount;
        log.info("[출금 완료] 출금액: {}, 변경 잔액: {}",amount,balance);

        log.info("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
