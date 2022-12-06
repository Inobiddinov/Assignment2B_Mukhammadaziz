package project;

import java.util.Scanner;

public class Edric{
    public static void main (String []args) {
        Project1 a = new Project1() ;
        a.displayMenu();
    }
}

class Project1 {
    Scanner scan = new Scanner(System.in);
    int row=3;
    int column = 15;
    String names[] = { "Jasmin" , "Leyla" ,"Sofiya"};
    int orders[][] = new int [row][column];


    final int PRICE_SHOULDER_MASSAGE=60;
    final int PRICE_WHOLE_BODY_MASSAGE=100;
    final int PRICE_ADD_MINUTES=10;
    String a=null,e=null,b=null;
    int indexTherapist=0;
    int option =0;
    int remainder=0;
    int total2 =0 ,total3 =0;
    int sum = 0;
    boolean valid ;
    String index =null;
    String ordernum1 = null;
    int totalminutes=0;
    int massage_package=0;
    int namesNo =0;



    public void newOrder() {
        int valids=0;
        int x =0 , y =0  ,w = 0;


        do {
            //print names of therapist
            do {
                for (int i=0 ; i<names.length ;i++) {
                    System.out.printf("%d : %s\n" ,  i+1 , names[i]);
                }
                System.out.printf("select a therapist [ 1 - %d  ]: " , names.length);
                namesNo = scan.nextInt();
                if(namesNo != 1 && namesNo != 2 && namesNo !=3 ) {
                    System.out.println("Invalid");
                }

            }while(namesNo != 1 && namesNo != 2 && namesNo !=3 );
            //check if array is full
            checkArray(namesNo);

            if ( valid == false) {
                System.out.println("therapist is full , pls select another therapist");
            }
        }while(valid ==false );

        //prompt user to enter massage package
        do {
            System.out.println("Please select a massage package :\n  1.Shoulder massage \n2.Whole body massage ");
            massage_package= scan.nextInt();
            if(massage_package!= 1 && massage_package!=2 ) {
                System.out.println("invalid");
            }
        }while (massage_package!= 1 && massage_package!=2 );
        //prompt user to enter minutes
        do {
            System.out.println("Please enter total minutes");
            totalminutes = scan.nextInt();
            if(totalminutes <= 0) {
                System.out.println("invalid");
            }
        }while (totalminutes <= 0);


        if (namesNo == 1) {
            valids = x;
            displayOrder(valids);
            x++;

        }else if(namesNo == 2) {
            valids = y;
            displayOrder(valids);
            y++;

        }else {
            valids = w;
            displayOrder(valids);
            w++;
        }

        System.out.println(orders[1][0]);


    }

    public void modify() {
        int refund =0;
        int topup =0 ;
        int q = 0, w = 0, e = 0, f = 0;
        boolean rerun = true;
        int sum1 =0;
        int newTime =0;

        do {
            //prompt user to enter order number
            System.out.println("Enter order number");
            scan.nextLine();
            ordernum1 =scan.nextLine();

            q= Integer.parseInt(String.valueOf(ordernum1.charAt(0))); //012
            w = Integer.parseInt(String.valueOf(ordernum1.charAt(1)));
            e = Integer.parseInt(String.valueOf(ordernum1.charAt(2)));
            f= Integer.parseInt(String.valueOf(ordernum1.charAt(3)));

            //check if order number exist
            if (w == 1) {
                if(orders[q][10+e] > 0 ) {
                    rerun = false ;
                }
            }else if(w== 0){
                if(orders[q][e] > 0 ) {
                    rerun = false ;
                }else {
                    System.out.println("Invalid order number");

                }

            }
        }while(rerun == true);
        //display order information
        System.out.println("Your order number : " + ordernum1);
        System.out.println("Your therapist name : " + names[q]);
        System.out.println("Your Massage package : " + massagePackageDisplay(f));

        if (w == 1) {
            System.out.println("Your cost : Rm" + orders[q][10+e]);
        }else {
            System.out.println("Your cost : Rm" + orders[q][e]);
        }
        //prompt user to enter new time
        System.out.println("Enter new time " );
        newTime = scan.nextInt();

        //method to calculate new charge

        sum1= charge(newTime , f );


        // refund or top up from charge by new time
        if(w==0) {
            if (sum1< orders[q][e] ) { ///20 < 90
                refund = orders[q][e] -sum1;
                System.out.println("Refund amount : Rm " + refund);

            }else {
                topup = sum1 - orders[q][e];
                System.out.println("Top up amount : Rm " + topup);

            }

            orders[q][e] =sum1;

        }else {
            if (sum1 < orders[q][10+e] ) { ///20 < 90
                refund = orders[q][10+e] - sum1;
                System.out.println("Refund amount : Rm " + refund);
            }else {
                topup = sum1 - orders[q][10+e];
                System.out.println("Top up amount : Rm " + topup);
            }

            orders[q][10+e] = sum1;
        }


    }


    public void checkArray(int namesNo) {

        for (int row=namesNo-1; row < namesNo ; row++) {//3
            for (int col=0; col < column ; col++) {

                if (orders[row][col] == 0 ) {
                    valid = true ; //empty
                }else {
                    valid = false; //not empty
                }
            }
        }
    }

    public void displayMenu() {

        do {
            System.out.printf("%5s %s\n", " ", "Main Menu");
            System.out.println("1. Enter a new record");
            System.out.println("2. Modify a record");
            System.out.println("3. View all records");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            option = scan.nextInt();
            switch (option){
                case 1:
                    newOrder();
                    break;

                case 2:
                    modify();
                    break;

                case 3:
                    displayOrder();
                    break;

                case 4:
                    System.out.println ("You exited the program.");
                    break;

                default:
                    System.out.println("Invalid option, please re-enter."); //prompt user to re-enter option
            }

        } while (option != 4);

    }



    public void displayOrder() {

        int totalOrders1=0;
        int totalOrders2=0;
        int totalOrders3=0;
        int orderRecord[] = new int [3];
        double grossWage [] = new double [3];
        double commission [] = new double [3];
        double totalWage [] = new double [3];
        double sum [] = new double [3];
        double sum_Gross=grossWage[0]+grossWage[1]+grossWage[2];
        double sum_Commission=commission[0]+commission[1]+commission[2];
        double sum_Wage=totalWage[0]+totalWage[1]+totalWage[2];
        int totall=0;
        int cnts = 0;
        //print information of orders
        System.out.println("Edric massage and reflexology daily report for 30 Nov 2022");
        System.out.print("therapist " +"   ");
        for (int i=0 ; i<names.length ;i++) {
            System.out.print( "             " + names[i] );
        }

        System.out.println("     total");

        System.out.println();


        for (int cnt=0; cnt<orders[0].length; cnt++) {
            System.out.printf("%-10s",  " Order "+ (cnt+1));
            for (int row=0; row<orders.length; row++) {
                System.out.printf("\t\t%3d ",orders[row][cnt]);
                totall+=orders[row][cnt];

            }
            System.out.print("          " + totall);
            totall=0;
            System.out.println();
        }

        //calculate total wage and commission
        for(int j=0 ; j<orders.length ;j++) {

            for(int i=0 ; i<orders[0].length ; i++) {
                sum[j] += orders[j][i];
                grossWage[j] = sum[j];

                if (orderRecord[j] >10) {
                    commission[j] = (grossWage[j]/100)*40;
                }
                else {
                    commission[j] = (grossWage[j]/100)*30;
                }

                totalWage[j]=grossWage[j]+commission[j];
            }
        }

        //calculate total orders for different therapist
        for (int rows=0; rows<orders.length; rows++) {
            for (int col=0; col<orders[0].length; col++) {

                if (orders[rows][col] != 0) {
                    ++cnts;
                    if(rows == 0) {
                        totalOrders1 = cnts;

                    }else if(rows == 1) {
                        totalOrders2 = cnts-totalOrders1;
                    }else {
                        totalOrders3 = cnts-totalOrders2-totalOrders1;
                    }
                }
            }
        }


        //print gross wage, commission , total wage  and orders
        System.out.println(" Total no of Orders :     "+ totalOrders1+ "               " + totalOrders2 + "               " + totalOrders3  + "           "  +(totalOrders1+totalOrders2+totalOrders3));
        System.out.print(" Gross Wages :        ");
        for (int cnt=0; cnt<grossWage.length; cnt++) {
            System.out.printf("%8.2f", grossWage[cnt]);
            System.out.print("        ");
        }
        System.out.printf("%.2f", sum_Gross);
        System.out.println();
        System.out.print(" Commission :         ");
        for (int cnt=0; cnt<commission.length; cnt++) {
            System.out.printf("%8.2f", commission[cnt]);
            System.out.print( "        ");
        }
        System.out.printf("%.2f", sum_Commission);
        System.out.println();
        System.out.print(" Total Wages :        ");
        for (int cnt=0; cnt<totalWage.length; cnt++) {
            System.out.printf("%8.2f", totalWage[cnt]);
            System.out.print( "        ");
        }
        System.out.printf("%.2f", sum_Wage);

        System.out.println();
    }



    //create index number from order
    public String indexCreate(int therapist , int massage_package, int ordernum1) {


        indexTherapist = therapist - 1;
        a=String.valueOf(indexTherapist);
        e=String.format("%02d", ordernum1);
        b=String.valueOf(massage_package);

        index = a + e + b;


        return index;


    }
    //check price
    public int charge(int minutes, int packages) {
        int minutes_Plus=0;
        int price_Package=0;

        if (packages == 1) {
            price_Package = PRICE_SHOULDER_MASSAGE;
        }else {
            price_Package = PRICE_WHOLE_BODY_MASSAGE;
        }

        if (minutes >= 30) {
            minutes_Plus = (int)(minutes-30);
            minutes_Plus = (int)Math.round(minutes_Plus/10.0) * 10;
        }


        sum = price_Package+minutes_Plus;

        return sum;
    }

    //display order information
    public void displayOrder(int x){



        orders[namesNo-1][x] = charge(totalminutes , massage_package);
        System.out.println("Your index number : "+ indexCreate(namesNo,massage_package,x));
        System.out.println("Your Therapist name : " + names[namesNo-1]);
        System.out.println("Your massage package : " + massagePackageDisplay(massage_package));
        System.out.println("Total sum : RM" + sum);

    }
    //massage package method
    public String massagePackageDisplay(int i) {
        if (i == 1) {
            return "Shoulder massage - Rm60 for first 30 minutes + Rm10 per every additional minutes" ;
        }else {
            return "Body massage - Rm100 for first 30 minutes + Rm10 per every additional minutes";
        }
    }


}





