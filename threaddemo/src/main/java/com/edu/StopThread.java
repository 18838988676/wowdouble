package com.edu;

public class StopThread {
    private static User user = new User();

    private static class User {
        private int id ;
        private int name ;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name=" + name +
                    '}';
        }
    }

    private static class ReadThread implements Runnable{

        public void run() {
            while (true){
                synchronized (user){
                    if (user.getId()!=user.getName()) {
                        System.out.println(user);
                    }
                }
            }
        }
    }

    private static  class WriteThread implements Runnable{

        public void run() {
            while (true){
                int id = (int) System.currentTimeMillis();
                synchronized (user){
                    user.setId(id);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(id);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ReadThread()).start();
        while (true){
          Thread t = new Thread(new WriteThread());
          t.start();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.stop();
        }
    }
}
