package be.manaev.prayer.controllers;

import be.manaev.prayer.Second;
import be.manaev.prayer.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public ModelAndView mavMain() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("beginPagina", true);
        return mav;
    }

    @GetMapping("/{name}")
    public ModelAndView mavMainSearch(@PathVariable(required = false) String name) {
        long begin = System.currentTimeMillis();
        ModelAndView mav = new ModelAndView("index");
        //System.out.println("line 32: " + (System.currentTimeMillis() - begin));

        if (name.startsWith("04")) {
            name = "324" + name.substring(2);
        }
        if (name.startsWith("+32")) {
            name = name.substring(1);
        }
        System.out.println("line 40: " + (System.currentTimeMillis() - begin));
        List<User> lijst = new ArrayList<>();

        if (name.contains(" ")) {
            String[] a = name.split(" ");


            final List<User> listFrontName = new ArrayList<>();
            List<User> listName = new ArrayList<>();

            //System.out.println("line 50: " + (System.currentTimeMillis() - begin));
            Thread thread1 = new Thread(() -> listName.addAll(new Second().loadItUp(a[0], 1)));
            Thread thread2 = new Thread(() -> listName.addAll(new Second().loadItUp(a[1], 1)));
            //System.out.println("line 53: " + (System.currentTimeMillis() - begin));
            thread1.start();thread2.start();
            //System.out.println("line 55: " + (System.currentTimeMillis() - begin));
            try{thread1.join();thread2.join();}catch (InterruptedException ignore){}
            //System.out.println("line 57: " + (System.currentTimeMillis() - begin));
            List<User> tempList = new ArrayList<>();
            for (User u : listName) {

                String stringToCheck = a[0].toLowerCase().trim() + "_" + a[1].toLowerCase().trim();
                String s1 = u.getName().toLowerCase() + "_" + u.getFamilyname().toLowerCase();
                String s2 = u.getFamilyname().toLowerCase() + "_" + u.getName().toLowerCase();

                if (stringToCheck.equals(s1) || stringToCheck.equals(s2)) {
                    tempList.add(u);
                }
            }
            System.out.println("line 69: " + (System.currentTimeMillis() - begin));
            for (User u1 : tempList) {
                if (!lijst.contains(u1)) {
                    lijst.add(u1);
                }
            }
            System.out.println("line 75: " + (System.currentTimeMillis() - begin));
            if (lijst.size() == 0) {
                lijst.addAll(listName);
            }

        } else {
            //System.out.println("line 81: -------" + (System.currentTimeMillis() - begin));
            lijst = new Second().loadItUp(name, 2);
            //System.out.println("line 83: --------" + (System.currentTimeMillis() - begin));
        }

        List<User> sortedListFirstByName = lijst.stream().sorted(Comparator.comparing(User::getName)
                .thenComparing(User::getFamilyname)).toList();

        List<UserWhatsapp> userWhatsapp = new ArrayList<>();
        for (User u : sortedListFirstByName) {
            String whatsappTemp = "https://api.whatsapp.com/send?phone=" + u.getPhonenumber();
            String facebookTemp = "https://www.facebook.com/" + u.getFacebookid();
            String phoneNumberTemp = "+" + u.getPhonenumber();


            if(u.getGender().equals("*")){
                u.setGender("");
            }
            if(u.getCity().equals("*")){
                u.setCity("");
            }
            if(u.getProvince().equals("*")){
                u.setProvince("");
            }

            userWhatsapp.add(new UserWhatsapp(phoneNumberTemp, facebookTemp, u.getName(), u.getFamilyname(),
                    u.getGender(), u.getCity(), u.getProvince(), whatsappTemp));
        }

        mav.addObject("newList", userWhatsapp);

        int amountOfFemales = 0;
        int amountOfMales = 0;

        for (User u : lijst) {
            if (u.getGender().equals("male")) {
                amountOfMales++;
            } else if (u.getGender().equals("female")) {
                amountOfFemales++;
            }
        }
        if (amountOfMales > amountOfFemales) {
            mav.addObject("mostlyMale", true);
            mav.addObject("mostlyFemale", false);
        } else {
            mav.addObject("mostlyFemale", true);
            mav.addObject("mostlyMale", false);
        }
        mav.addObject("time", System.currentTimeMillis() - begin);
        return mav;
    }


    class UserWhatsapp {
        String phonenumber;
        String facebookid;
        String name;
        String familyname;
        String gender;
        String city;
        String province;
        String whatsapp;

        public UserWhatsapp(String phonenumber, String facebookid, String name, String familyname, String gender, String city, String province, String whatsapp) {
            this.phonenumber = phonenumber;
            this.facebookid = facebookid;
            this.name = name;
            this.familyname = familyname;
            this.gender = gender;
            this.city = city;
            this.province = province;
            this.whatsapp = whatsapp;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public String getFacebookid() {
            return facebookid;
        }

        public String getName() {
            return name;
        }

        public String getFamilyname() {
            return familyname;
        }

        public String getGender() {
            return gender;
        }

        public String getCity() {
            return city;
        }

        public String getProvince() {
            return province;
        }

        public String getWhatsapp() {
            return whatsapp;
        }
    }

}
//                System.out.println(a[0]+" " +a[1]);
//                System.out.println(u.getName().toLowerCase() + " " + u.getFamilyname().toLowerCase());
//
//
//
//                //System.out.println(u);
//                if(u.getName().equalsIgnoreCase(a[0]) && u.getFamilyname().equals(a[1]) ||
//                        (u.getName().equalsIgnoreCase(a[1]) && u.getFamilyname().equalsIgnoreCase(a[0]))){
//                    lijst.add(u);
//                }

//            for(User u1 : tempList){
//                if(!lijst.contains(u1)){
//                    lijst.add(u1);
//                }
//            }