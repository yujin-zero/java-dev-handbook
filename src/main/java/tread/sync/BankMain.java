package tread.sync;

import static java.lang.Thread.sleep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankMain {
    private static final Logger log = LoggerFactory.getLogger(BankAccountV1.class);

    public static void main(String[] args) throws InterruptedException{
        BankAccount account = new BankAccountV1(1000);

        Thread t1 = new Thread(new WithdrawTask(account, 800),"t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800),"t2");
        t1.start();
        t2.start();

        sleep(500); // 검증 완료까지 잠시 대기
        log.info("t1 state: {}",t1.getState());
        log.info("t2 state: {}",t2.getState());

        t1.join();
        t2.join();
        log.info("최종 잔액: {}", account.getBalance());
    }
}
