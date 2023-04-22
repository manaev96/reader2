package be.manaev.prayer;

import be.manaev.prayer.domain.User;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
0-637
637-1273
1273-1910
1910-2546
2546-3183
 */
@Component
public class Second {
    public List<User> loadItUp(String query, int version) {
        long begin = System.currentTimeMillis();
        List<User> list = new ArrayList<>();

        Thread thread1 = new Thread(() -> list.addAll(readFile(version, query, 1)));
        Thread thread2 = new Thread(() -> list.addAll(readFile(version, query, 2)));

        thread1.start();thread2.start();

        try{thread1.join();thread2.join();}catch (InterruptedException ignore){}

        System.out.println("Time it took for loadItUp: " + (System.currentTimeMillis() - begin) + ", size:" + list.size());
        return list;
    }


    public List<User> readFile(int version, String query, int textfileNumber) {
        List<User> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(Paths.get("belgium" + textfileNumber + ".txt"))));
            String line = reader.readLine();
            int counter = 0;
            System.out.println("query::" + query);
            while (line != null) {

                if(line.toLowerCase().contains(query)){
                    String[] a = line.split(",");
                    User user = new User(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
                    list.add(user);
                }



                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ignore) {}

        Set<User> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);

        return list;
    }
}

//    LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(String.valueOf(Paths.get("belgium.txt"))));
//            System.out.println(lineNumberReader.getLineNumber());
//            lineNumberReader.setLineNumber(beginLine);
//            System.out.println(lineNumberReader.getLineNumber());
//    String line = lineNumberReader.readLine();
//            System.out.println(lineNumberReader.getLineNumber());
//            System.out.println(beginLine + "::: " + line);
//    int counter = 0;
//            while (lineNumberReader.getLineNumber() <= endLine) {
//        counter++;
//        String[] a = line.split(",");
//        User user = new User(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
//
//        if (version == 1) {
//            if (user.getName().toLowerCase().contains(query.toLowerCase()) || user.getFamilyname().toLowerCase().contains(query.toLowerCase())) {
//                listThread.add(user);
//            }
//        } else if (version == 2) {
//            if (user.toString().toLowerCase().contains(query.toLowerCase())) {
//                listThread.add(user);
//            }
//        }
//        line = lineNumberReader.readLine();
//    }
//            System.out.println(beginLine + ":: " + counter);
//} catch (IOException e) {
//        throw new RuntimeException(e);
//        }
//        return listThread;

/*
        List<User> list1 = new ArrayList<>();
        List<User> list2 = new ArrayList<>();
        List<User> list3 = new ArrayList<>();
        List<User> list4 = new ArrayList<>();
        List<User> list5 = new ArrayList<>();


        Thread thread1 = new Thread(() -> list1.addAll(readFile(version, query, 0, 636_707)));
        Thread thread2 = new Thread(() -> list2.addAll(readFile(version, query, 636_707, 1_273_414)));
        Thread thread3 = new Thread(() -> list3.addAll(readFile(version, query, 1_273_414, 1_910_121)));
        Thread thread4 = new Thread(() -> list4.addAll(readFile(version, query, 1_910_121, 2_546_828)));
        Thread thread5 = new Thread(() -> list5.addAll(readFile(version, query, 2_546_828, 3_183_535)));

        thread1.start();//thread2.start();thread3.start();thread4.start();thread5.start();

        try{thread1.join();thread2.join();thread3.join();thread4.join();thread5.join();
        }catch (InterruptedException ignore){}

        System.out.println("list1: " + list1.size());
        System.out.println("list2: " + list2.size());
        System.out.println("list3: " + list3.size());
        System.out.println("list4: " + list4.size());
        System.out.println("list5: " + list5.size());


        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        list.addAll(list4);
        list.addAll(list5);

 */


//    BufferedReader reader;
//    reader = new BufferedReader(new FileReader(String.valueOf(Paths.get("belgium.txt"))));
//lineNumberReader = new LineNumberReader(new FileReader(String.valueOf(Paths.get("belgium.txt"))));
//String line1 = lineNumberReader.readLine();

/*
    public void writeToNewTXTFile() throws IOException {

        FileWriter myWriter = new FileWriter("belgiumTest.txt");
        List<User> lijst = loadItUp("");

        for (User u : lijst) {

            if (u.getPhonenumber().isBlank() || u.getPhonenumber().isEmpty()) {
                u.setPhonenumber("*");
            }
            if (u.getFacebookid().isBlank() || u.getFacebookid().isEmpty()) {
                u.setFacebookid("*");
            }
            if (u.getName().isBlank() || u.getName().isEmpty()) {
                u.setName("*");
            }
            if (u.getFamilyname().isBlank() || u.getFamilyname().isEmpty()) {
                u.setFamilyname("*");
            }
            if (u.getGender().isBlank() || u.getGender().isEmpty()) {
                u.setGender("*");
            }
            if (u.getCity().isBlank() || u.getCity().isEmpty()) {
                u.setCity("*");
            }
            if (u.getProvince().isBlank() || u.getProvince().isEmpty()) {
                u.setProvince("*");
            }

            String fbID = u.getFacebookid().substring(25);
            myWriter.write(u.getPhonenumber() + "," + fbID + "," + u.getName() + "," + u.getFamilyname()
                    + "," + u.getGender() + "," + u.getCity() + "," + u.getProvince() + "\n");
        }
        myWriter.close();
    }
*/
/*
    public void writeLinesToTXTFile(){
        try {
            FileWriter myWriter = new FileWriter("belgiumClean.txt");

            List<User> lijst = new Second().loadFile();
            System.out.println("lijstzsize"+lijst.size());

            int a = 0;
            for(User u : lijst) {
                a++;
                if (a % 1000 == 0) {
                    System.out.println(a);
                }
//                    try {
//                        myWriter.write(u.getPhoneNumber() + "," + u.getFacebookId() + "," + u.getName() + "," + u.getFamilyName()
//                                + "," + u.getGender() + "," + u.getCity() + "," + u.getProvince()  + "\n");
//                    } catch (IOException e) {
//                        System.err.println("done goofed");
//                    }
            }
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/
/*
    public void writeToDB(){
        List<User> lijst = loadFile();
        System.out.println("done getting list, size: " + lijst.size());

        try {
            System.out.println("start");
            saveAllItemsInDB(lijst,40);
            System.out.println("end");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
*/
/*
    private void saveAllItemsInDB(List<User> newItemsSet, int howManyThreads) throws InterruptedException{
        List<User> newItems = new ArrayList<>(newItemsSet);
        int chunkSize = newItems.size() / howManyThreads;
        ArrayList<List<User>> chunkList = new ArrayList<>();
        int guide = newItems.size();
        int index = 0;
        int tale = chunkSize;
        while (tale < newItems.size()) {
            chunkList.add(newItems.subList(index, tale));
            guide = guide - chunkSize;
            index = index + chunkSize;
            tale = tale + chunkSize;
        }
        if (guide > 0) {
            chunkList.add(newItems.subList(index, index + guide));
        }
        int listSize = chunkList.size();
        Thread[] myThread = new Thread[listSize];
        for (int i = 0; i < listSize; i++) {
            int finalI = i;
            myThread[i] = new Thread(() -> {
                for (User item : chunkList.get(finalI)) {
                    userRepository.save(item);
                }});}
        for (int i = 0; i < listSize; i++) {
            myThread[i].start();
        }
        for (int i = 0; i < listSize; i++) {
            myThread[i].join();
        }}
*/
/*
    public List<User> loadFile() {

        List<User>[] group = new ArrayList[3];

        long begin = System.currentTimeMillis();

        int listSize = 3;
        Thread[] myThread = new Thread[listSize];
        for (int i = 0; i < listSize; i++) {
            int finalI1 = i;
            int finalI = i;
            int finalI2 = i;
            myThread[i] = new Thread(() -> {
                group[finalI2] = loadItUp(finalI1);
            });
        }

        for (int i = 0; i < listSize; i++) {
            myThread[i].start();
        }

        for (int i = 0; i < listSize; i++) {
            try {
                myThread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //System.out.println("list1 size:" + group[0].size());
        //System.out.println("list2 size:" + group[1].size());
       // System.out.println("list3 size:" + group[2].size());

        List<User> newList = Stream.of(group[0], group[1], group[2])
                .flatMap(Collection::stream)
                .collect(Collectors.toList());



       // System.out.println("newListSize:: " + newList.size());
        //System.out.println("Time took:" + (System.currentTimeMillis()-begin));


        return newList;


    }
*/


//            List<User> listThread1 = new ArrayList<>();
//            Thread thread1 = new Thread(() -> {
//                try {
//                    String line = null;
//                    line = lineNumberReader.readLine();
//                    while (lineNumberReader.getLineNumber() <= 1_600_000) {
//                        String[] a = line.split(",");
//                        User user = new User(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
//
//                        if (version == 1) {
//                            if (user.getName().toLowerCase().contains(query.toLowerCase()) || user.getFamilyname().toLowerCase().contains(query.toLowerCase())) {
//                                listThread1.add(user);
//                            }
//                        } else if (version == 2) {
//                            if (user.toString().toLowerCase().contains(query.toLowerCase())) {
//                                listThread1.add(user);
//                            }
//                        }
//                        line = lineNumberReader.readLine();
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });

//            List<User> listThread2 = new ArrayList<>();
//            Thread thread2 = new Thread(() -> {
//                try {
//                    lineNumberReader.setLineNumber(1_600_000);
//                    String line = lineNumberReader.readLine();
//                    while (lineNumberReader.getLineNumber() <= 3_183_535) {
//                        String[] a = line.split(",");
//                        User user = new User(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
//
//                        if (version == 1) {
//                            if (user.getName().toLowerCase().contains(query.toLowerCase()) || user.getFamilyname().toLowerCase().contains(query.toLowerCase())) {
//                                listThread1.add(user);
//                            }
//                        } else if (version == 2) {
//                            if (user.toString().toLowerCase().contains(query.toLowerCase())) {
//                                listThread1.add(user);
//                            }
//                        }
//                        line = lineNumberReader.readLine();
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });


//            thread1.start(); thread2.start();
//
//            try{thread1.join();thread2.join();}catch (InterruptedException ignore){}

//            list.addAll(listThread1);
//            list.addAll(listThread2);



/*
            int lines = 0;
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                lines++;
                String[] a = line.split(",");
                User user = new User(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);

                if(version == 1){
                if (user.getName().toLowerCase().contains(query.toLowerCase()) || user.getFamilyname().toLowerCase().contains(query.toLowerCase())) {
                    list.add(user);
                }
                }else if(version == 2){
                    if(user.toString().toLowerCase().contains(query.toLowerCase())){
                        list.add(user);
                    }
                }

                line = reader.readLine();
            }

            System.out.println(lines);
            System.out.println("line 81: " + (System.currentTimeMillis() - begin));
*/
//reader.close();