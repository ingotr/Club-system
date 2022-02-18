package Club;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        int choice = 0;

        while (choice == 0) {
            try {
                choice = reader.nextInt();
                if (choice ==  0) {
                    throw new InputMismatchException();
                }
                reader.nextLine();
            } catch (InputMismatchException e) {
                reader.nextLine();
                System.out.println("ERROR: INVALID INPUT. PLease try again: ");
            }
        }
        return choice;
    }

    private void printClubOptions() {
        System.out.println("\n1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
    }

    public int getChoice() {
        int choice;
        System.out.println("\nWELCOME TO OZONE FITNESS CENTER");
        System.out.println("===============================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        System.out.println("Please select an option (or Enter -1 to quit):");

        choice  = this.getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> Members) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;

        System.out.println("Please type your name: ");
        name = reader.nextLine();

        printClubOptions();
        System.out.println("Please type your club memberID");
        club = getIntInput();
        while (club < 1 || club > 4) {
            System.out.println("Sorry this value is not correct.");
            System.out.println("Please try again anything between 1 and 4");
            club = getIntInput();
        }

        if (Members.size() > 0) {
            memberID = Members.getLast().getMemberID() + 1;
        }   else {
            memberID = 1;
        }

        if (club != 4) {
            cal = (n) -> {
                switch (n) {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S',
                    memberID, name, fees, club);
            Members.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Single Club Member added\n");
        } else {
            cal = (n) -> {
                if (n == 4) {
                    return 1200;
                }
                return -1;
            };
            fees = cal.calculateFees(club);
            mbr = new MultiClubMember('M',
                    memberID, name, fees, 100);
            Members.add(mbr);
            mem = mbr.toString();
            System.out.println(mem);
            System.out.println("\nSTATUS: Multi Club Member added\n");
        }
        System.out.println(mem);
        return mem;
    }

    public void removeMember(LinkedList<Member> Members) {
        int memberID;

        System.out.println("Please enter memberID: ");
        memberID = getIntInput();

        for (int i = 0; i < Members.size(); i++) {
            if (Members.get(i).getMemberID() == memberID) {
                Members.remove(i);
                System.out.printf("Member with %d has been deleted from list\n", memberID);
                return;
            }
            System.out.printf("Member with %d is not found\n", memberID);
        }
    }

    public void printMemberInfo(LinkedList<Member> Members) {
       int memberID;

       System.out.println("Please enter memberID: ");
       memberID = getIntInput();

       for (int i = 0; i < Members.size(); i++) {
           if (Members.get(i).getMemberID() == memberID) {
               String[] memberInfo = Members.get(i).toString().split(", ");
               System.out.printf("Member with %d has been found from list:\n", memberID);
               System.out.printf("Member Type = %s\n", memberInfo[0]);
               System.out.printf("Member ID = %s\n", memberInfo[1]);
               System.out.printf("Member Name = %s\n", memberInfo[2]);
               System.out.printf("Membership Fees = %s\n", memberInfo[3]);
               if (memberInfo[0].equals("S")) {
                   System.out.printf("Club ID = %s\n", memberInfo[4]);
               } else {
                   System.out.printf("Membership Points = %s\n", memberInfo[4]);
               }
               return;
           }
           System.out.printf("Member with %d is not found\n", memberID);
       }
    }
}
