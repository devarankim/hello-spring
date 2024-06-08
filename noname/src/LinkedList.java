public class LinkedList {
    Node header = null;

     static class Node {
        int data;
        Node next = null;
    }
    LinkedList(){
        header = new Node();
    }

    void append(int d){
         Node end = new Node();
         end.data = d;
         Node n = header;
         while(n.next != null){
             n = n.next;
         }
         n.next = end;
    }

    void delete(int d){
         Node n = header;
         while(n.next!=null){
             if(d == n.next.data){
                 n.next = n.next.next;
             } else {
                 n = n.next;
             }
         }
    }

    void retrieve(){
         Node n = header.next;
         while(n.next!=null){
             System.out.print(n.data+"->");
             n = n.next;
         }
         System.out.println(n.data);
    }

    /*
    private static Node KthNode(Node first, int k){
         Node n = first;
         int total = 1;
         while(n.next!=null){
             total++;
             n = n.next;
        }
         n = first;
         for(int i=1; i<total-k+1;i++){
             n = n.next;
        }

        return n;
    }
   */

    private static int KthToLast(Node n, int k){ //재귀

        if ( n == null){
            System.out.println("nnnnnnssss"+n);
            return 0;
        } else {
            System.out.println("not nullssssss"+n.data);
        }

        int count = KthToLast(n.next, k) +1;
        System.out.println("n.next.data:" + n.data);
        System.out.println("count:"+count);
        if(count == k){
            System.out.println(k+"th to last node is "+n.data);
        }
        return count;
    }
    public static class SinglyLinkedList{
         public static void main(String args[]){
             LinkedList ll = new LinkedList(); // 처음 초기값이 0으로 세팅되네요 확인함
             ll.append(2);
             ll.append(3);
             ll.append(5);
             ll.append(8);
             //ll.retrieve();
/*
             Node first = ll.header;
             Node kth = KthNode(first, 2);
             System.out.println(kth.data);
             */

            int k = 3;
            KthToLast(ll.getFirst(), k);

         }
    }

    private Node getFirst() {
        return header.next;
    }


}
