public class Main {
    public static void main(String[] args) throws InterruptedException {
        // программа ~ процесс
        // в рамках процесса - несколько тредов
        // [+][-][+][-]
        MyThread thread1 = new MyThread("+");
        MyThread thread2 = new MyThread("-");
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        thread1.flag = false;
        thread1.join(); // ожидаем заверешения работы 1го потока
        test("1 thread is stopped!");
    }
    public static Object KEY = new Object();
    public static void test(String message) {
        synchronized (KEY) {
            try {
                System.out.print("[");
                Thread.sleep(200);
                System.out.print(message);
                Thread.sleep(200);
                System.out.print("]");
             //   KEY.notify(); // возобновить поток наход в режиме ожидания
             //   KEY.wait(); // положить поток в режиме ожидания
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
class MyThread extends Thread {
    public String message;
    public boolean flag = true;
    MyThread(String m) {
        this.message = m;
    }
    @Override
    public void run() {
        while(flag) {
            Main.test(message);
        }
    }
}
